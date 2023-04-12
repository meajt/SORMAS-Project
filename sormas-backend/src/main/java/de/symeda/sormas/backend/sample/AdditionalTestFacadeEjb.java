package de.symeda.sormas.backend.sample;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import de.symeda.sormas.api.common.Page;
import de.symeda.sormas.api.logger.CustomLoggerFactory;
import de.symeda.sormas.api.logger.LoggerType;
import de.symeda.sormas.api.sample.AdditionalTestCriteria;
import de.symeda.sormas.api.sample.AdditionalTestDto;
import de.symeda.sormas.api.sample.AdditionalTestFacade;
import de.symeda.sormas.api.sample.ncd.*;
import de.symeda.sormas.api.user.UserRight;
import de.symeda.sormas.api.utils.SortProperty;
import de.symeda.sormas.backend.FacadeHelper;
import de.symeda.sormas.backend.sample.ncd.*;
import de.symeda.sormas.backend.user.User;
import de.symeda.sormas.backend.user.UserService;
import de.symeda.sormas.backend.util.DtoHelper;
import de.symeda.sormas.backend.util.ModelConstants;
import de.symeda.sormas.backend.util.Pseudonymizer;

@Stateless(name = "AdditionalTestFacade")
public class AdditionalTestFacadeEjb implements AdditionalTestFacade {

	@PersistenceContext(unitName = ModelConstants.PERSISTENCE_UNIT_NAME)
	private EntityManager em;

	@EJB
	private AdditionalTestService service;
	@EJB
	private SampleService sampleService;
	@EJB
	private UserService userService;

	@Override
	public AdditionalTestDto getByUuid(String uuid) {
		return toDto(service.getByUuid(uuid));
	}

	@Override
	public List<AdditionalTestDto> getAllBySample(String sampleUuid) {

		if (sampleUuid == null) {
			return Collections.emptyList();
		}

		Sample sample = sampleService.getByUuid(sampleUuid);
		return service.getAllBySample(sample).stream().map(s -> toDto(s)).collect(Collectors.toList());
	}

	public List<AdditionalTestDto> getIndexList(
		AdditionalTestCriteria additionalTestCriteria,
		Integer first,
		Integer max,
		List<SortProperty> sortProperties) {

		return toPseudonymizedDtos(service.getIndexList(additionalTestCriteria, first, max, sortProperties));
	}

	public Page<AdditionalTestDto> getIndexPage(
		AdditionalTestCriteria additionalTestCriteria,
		Integer offset,
		Integer size,
		List<SortProperty> sortProperties) {

		List<AdditionalTestDto> additionalTestList = getIndexList(additionalTestCriteria, offset, size, sortProperties);
		long totalElementCount = service.count(additionalTestCriteria);
		return new Page<>(additionalTestList, offset, size, totalElementCount);

	}

	@Override
	public AdditionalTestDto saveAdditionalTest(@Valid AdditionalTestDto additionalTest) {
		return saveAdditionalTest(additionalTest, true);
	}

	public AdditionalTestDto saveAdditionalTest(@Valid AdditionalTestDto additionalTest, boolean checkChangeDate) {
		AdditionalTest existingAdditionalTest = service.getByUuid(additionalTest.getUuid());
		FacadeHelper.checkCreateAndEditRights(existingAdditionalTest, userService, UserRight.ADDITIONAL_TEST_CREATE, UserRight.ADDITIONAL_TEST_EDIT);
		AdditionalTest entity = fillOrBuildEntity(additionalTest, existingAdditionalTest, checkChangeDate);
		if (entity.getRftSample() != null)
			em.persist(entity.getRftSample());
		if (entity.getLftSample() != null)
			em.persist(entity.getLftSample());
		if (entity.getLipidProfileSample() != null)
			em.persist(entity.getLipidProfileSample());
		if (entity.getCompleteBloodCountSample() != null)
			em.persist(entity.getCompleteBloodCountSample());
		if (entity.getUrineRoutineExaminationSample() != null)
			em.persist(entity.getUrineRoutineExaminationSample());

		service.ensurePersisted(entity);
		return toDto(entity);
	}

	@Override
	public void deleteAdditionalTest(String additionalTestUuid) {

		if (!userService.hasRight(UserRight.ADDITIONAL_TEST_DELETE)) {
			throw new UnsupportedOperationException("Your user is not allowed to delete additional tests");
		}

		AdditionalTest additionalTest = service.getByUuid(additionalTestUuid);
		service.deletePermanent(additionalTest);
	}

	@Override
	public List<AdditionalTestDto> getAllActiveAdditionalTestsAfter(Date date) {
		return getAllActiveAdditionalTestsAfter(date, null, null);
	}

	@Override
	public List<AdditionalTestDto> getAllActiveAdditionalTestsAfter(Date date, Integer batchSize, String lastSynchronizedUuid) {
		User user = userService.getCurrentUser();
		if (user == null) {
			return Collections.emptyList();
		}

		return service.getAllAfter(date, batchSize, lastSynchronizedUuid).stream().map(e -> toDto(e)).collect(Collectors.toList());
	}

	private List<AdditionalTestDto> toPseudonymizedDtos(List<AdditionalTest> entities) {

		List<Long> inJurisdictionIds = service.getInJurisdictionIds(entities);
		Pseudonymizer pseudonymizer = Pseudonymizer.getDefault(userService::hasRight);
		List<AdditionalTestDto> dtos =
			entities.stream().map(p -> convertToDto(p, pseudonymizer, inJurisdictionIds.contains(p.getId()))).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public List<AdditionalTestDto> getByUuids(List<String> uuids) {
		return service.getByUuids(uuids).stream().map(c -> toDto(c)).collect(Collectors.toList());
	}

	@Override
	public List<String> getAllActiveUuids() {

		User user = userService.getCurrentUser();
		if (user == null) {
			return Collections.emptyList();
		}

		return service.getAllActiveUuids(user);
	}

	public AdditionalTestDto convertToDto(AdditionalTest source, Pseudonymizer pseudonymizer) {

		if (source == null) {
			return null;
		}

		return convertToDto(source, pseudonymizer, service.inJurisdictionOrOwned(source));
	}

	private AdditionalTestDto convertToDto(AdditionalTest source, Pseudonymizer pseudonymizer, boolean inJurisdiction) {

		AdditionalTestDto dto = toDto(source);
		pseudonymizer.pseudonymizeDto(AdditionalTestDto.class, dto, inJurisdiction, null);
		return dto;
	}

	public AdditionalTest fillOrBuildEntity(@NotNull AdditionalTestDto source, AdditionalTest target, boolean checkChangeDate) {
		CustomLoggerFactory.getLogger(LoggerType.WEB)
				.logObj("@fillOrBuildEntity AdditionalTestDto", source);
		target = DtoHelper.fillOrBuildEntity(source, target, AdditionalTest::new, checkChangeDate);

		target.setSample(sampleService.getByReferenceDto(source.getSample()));
		target.setTestDateTime(source.getTestDateTime());
		target.setHaemoglobinuria(source.getHaemoglobinuria());
		target.setProteinuria(source.getProteinuria());
		target.setHematuria(source.getHematuria());
		target.setArterialVenousGasPH(source.getArterialVenousGasPH());
		target.setArterialVenousGasPco2(source.getArterialVenousGasPco2());
		target.setArterialVenousGasPao2(source.getArterialVenousGasPao2());
		target.setArterialVenousGasHco3(source.getArterialVenousGasHco3());
		target.setGasOxygenTherapy(source.getGasOxygenTherapy());
		target.setAltSgpt(source.getAltSgpt());
		target.setAstSgot(source.getAstSgot());
		target.setCreatinine(source.getCreatinine());
		target.setPotassium(source.getPotassium());
		target.setUrea(source.getUrea());
		target.setHaemoglobin(source.getHaemoglobin());
		target.setTotalBilirubin(source.getTotalBilirubin());
		target.setConjBilirubin(source.getConjBilirubin());
		target.setWbcCount(source.getWbcCount());
		target.setPlatelets(source.getPlatelets());
		target.setProthrombinTime(source.getProthrombinTime());
		target.setOtherTestResults(source.getOtherTestResults());
		target.setHasPremiumHealthPackage(source.isHasPremiumHealthPackage());
		target.setHasRFT(source.isHasRFT());
		target.setHasLipidProfile(source.isHasLipidProfile());
		target.setHasLFT(source.isHasLFT());
		target.setHasUrineRE(source.isHasUrineRE());
		target.setHasCompleteBloodCount(source.isHasCompleteBloodCount());
		target.setRftSample(fillOrBuildRftSampleEntity(source.getRftSampleDto(), target.getRftSample(), false));
		target.setLftSample(fillOrBuildLftSampleEntity(source.getLftSampleDto(), target.getLftSample(),false));
		target.setLipidProfileSample(fillOrBuildLipidProfileSampleEntity(source.getLipidProfileSampleDto(), target.getLipidProfileSample(), false));
		target.setCompleteBloodCountSample(fillOrBuildCdcSampleEntity(source.getCompleteBloodCountSampleDto(), target.getCompleteBloodCountSample(), false));
		target.setUrineRoutineExaminationSample(fillOrBuildUrineRoutineExaminationSample(source.getUrineRoutineExaminationSampleDto(), target.getUrineRoutineExaminationSample(), false));
		return target;
	}

	public static AdditionalTestDto toDto(AdditionalTest source) {

		if (source == null) {
			return null;
		}

		AdditionalTestDto target = new AdditionalTestDto();
		DtoHelper.fillDto(target, source);

		target.setSample(SampleFacadeEjb.toReferenceDto(source.getSample()));
		target.setTestDateTime(source.getTestDateTime());
		target.setHaemoglobinuria(source.getHaemoglobinuria());
		target.setProteinuria(source.getProteinuria());
		target.setHematuria(source.getHematuria());
		target.setArterialVenousGasPH(source.getArterialVenousGasPH());
		target.setArterialVenousGasPco2(source.getArterialVenousGasPco2());
		target.setArterialVenousGasPao2(source.getArterialVenousGasPao2());
		target.setArterialVenousGasHco3(source.getArterialVenousGasHco3());
		target.setGasOxygenTherapy(source.getGasOxygenTherapy());
		target.setAltSgpt(source.getAltSgpt());
		target.setAstSgot(source.getAstSgot());
		target.setCreatinine(source.getCreatinine());
		target.setPotassium(source.getPotassium());
		target.setUrea(source.getUrea());
		target.setHaemoglobin(source.getHaemoglobin());
		target.setTotalBilirubin(source.getTotalBilirubin());
		target.setConjBilirubin(source.getConjBilirubin());
		target.setWbcCount(source.getWbcCount());
		target.setPlatelets(source.getPlatelets());
		target.setProthrombinTime(source.getProthrombinTime());
		target.setOtherTestResults(source.getOtherTestResults());
		target.setHasPremiumHealthPackage(source.isHasPremiumHealthPackage());
		target.setHasRFT(source.isHasRFT());
		target.setHasLipidProfile(source.isHasLipidProfile());
		target.setHasLFT(source.isHasLFT());
		target.setHasUrineRE(source.isHasUrineRE());
		target.setHasCompleteBloodCount(source.isHasCompleteBloodCount());
		target.setRftSampleDto(toRftSampleDto(source.getRftSample(), target.getRftSampleDto()));
		target.setLftSampleDto(toLftSampleDto(source.getLftSample()));
		target.setLipidProfileSampleDto(toLipidProfileSampleDto(source.getLipidProfileSample()));
		target.setCompleteBloodCountSampleDto(toCcdSampleDto(source.getCompleteBloodCountSample()));
		target.setUrineRoutineExaminationSampleDto(toUrineRoutineExaminationDto(source.getUrineRoutineExaminationSample()));
		return target;
	}

	public static LftSampleDto toLftSampleDto(LftSample source) {
		if (source == null)
			return null;
		LftSampleDto target = new LftSampleDto();
		DtoHelper.fillDto(target, source);
		target.setBilirubinT(source.getBilirubinT());
		target.setBilirubinD(source.getBilirubinD());
		target.setAlkalinePhospatase(source.getAlkalinePhospatase());
		target.setKinetic(source.getKinetic());
		target.setSgpt(source.getSgpt());
		target.setSgot(source.getSgot());
		target.setVldl(source.getVldl());
		return target;
	}

	public static LipidProfileSampleDto toLipidProfileSampleDto(LipidProfileSample source) {
		if (source == null)
			return null;
		LipidProfileSampleDto target = new LipidProfileSampleDto();
		DtoHelper.fillDto(target, source);
		target.setCholestrolMethod(source.getCholestrolMethod());
		target.setHdlMethod(source.getHdlMethod());
		target.setLdlMethod(source.getLdlMethod());
		target.setTriglycerideMethod(source.getTriglycerideMethod());
		target.setUricAcidMethod(source.getUricAcidMethod());
		target.setTotalProteinMethod(source.getTotalProteinMethod());
		target.setAlbuminMethod(source.getAlbuminMethod());
		target.setCalciumMethod(source.getCalciumMethod());
		return target;
	}

	public static RftSampleDto toRftSampleDto(RftSample source, RftSampleDto target) {
		if (source == null)
			return null;
		if(target == null)
			target = new RftSampleDto();
		DtoHelper.fillDto(target, source);
		target.setUrea(source.getUrea());
		target.setCreatinine(source.getCreatinine());
		target.setSodium(source.getSodium());
		target.setPotassium(source.getPotassium());
		return target;
	}

	public static CompleteBloodCountSampleDto toCcdSampleDto(CompleteBloodCountSample source) {
		if (source == null)
			return null;
		CompleteBloodCountSampleDto target = new CompleteBloodCountSampleDto();
		DtoHelper.fillDto(target, source);
		target.setTlc(source.getTlc());
		target.setNeutrophils(source.getNeutrophils());
		target.setLymphocytes(source.getLymphocytes());
		target.setMonocytes(source.getMonocytes());
		target.setEosinophils(source.getEosinophils());
		target.setBasophils(source.getBasophils());
		target.setPlatetetCount(source.getPlatetetCount());
		target.setRbc(source.getRbc());
		target.setHb(source.getHb());
		target.setPcv(source.getPcv());
		target.setMcv(source.getMcv());
		target.setHch(source.getHch());
		target.setMchc(source.getMchc());
		target.setRdwCv(source.getRdwCv());
		target.setHbA1c(source.getHbA1c());
		return target;
	}

	public static UrineRoutineExaminationSampleDto toUrineRoutineExaminationDto(UrineRoutineExaminationSample source) {
		if(source == null)
			return null;
		UrineRoutineExaminationSampleDto target = new UrineRoutineExaminationSampleDto();
		target.setColor(source.getColor());
		target.setTransparency(source.getTransparency());
		target.setPh(source.getPh());
		target.setAlbumin(source.getAlbumin());
		target.setSugar(source.getSugar());
		target.setPusCells(source.getPusCells());
		target.setRbc(source.getRbc());
		target.setEpithelialCell(source.getEpithelialCell());
		target.setCrystals(source.getCrystals());
		target.setCasts(source.getCasts());
		target.setUrineOtherTest(source.getUrineOtherTest());
		return target;
	}

	public LipidProfileSample fillOrBuildLipidProfileSampleEntity(LipidProfileSampleDto source, LipidProfileSample target, boolean checkChangeDate) {
		if (source == null)
			return null;
		target = DtoHelper.fillOrBuildEntity(source, target, LipidProfileSample::new, checkChangeDate);
		target.setCholestrolMethod(source.getCholestrolMethod());
		target.setHdlMethod(source.getHdlMethod());
		target.setLdlMethod(source.getLdlMethod());
		target.setTriglycerideMethod(source.getTriglycerideMethod());
		target.setUricAcidMethod(source.getUricAcidMethod());
		target.setTotalProteinMethod(source.getTotalProteinMethod());
		target.setAlbuminMethod(source.getAlbuminMethod());
		target.setCalciumMethod(source.getCalciumMethod());
		return target;
	}

	public LftSample fillOrBuildLftSampleEntity(LftSampleDto source, LftSample target, boolean checkChangeDate) {
		if (source == null)
			return null;
		target = DtoHelper.fillOrBuildEntity(source, target, LftSample::new, checkChangeDate);
		target.setBilirubinT(source.getBilirubinT());
		target.setBilirubinD(source.getBilirubinD());
		target.setAlkalinePhospatase(source.getAlkalinePhospatase());
		target.setKinetic(source.getKinetic());
		target.setSgpt(source.getSgpt());
		target.setSgot(source.getSgot());
		target.setVldl(source.getVldl());
		return target;
	}

	public CompleteBloodCountSample fillOrBuildCdcSampleEntity(CompleteBloodCountSampleDto source, CompleteBloodCountSample target, boolean checkChangeDate) {
		if (source == null)
			return null;
		target = DtoHelper.fillOrBuildEntity(source, target, CompleteBloodCountSample::new, checkChangeDate);
		target.setTlc(source.getTlc());
		target.setNeutrophils(source.getNeutrophils());
		target.setLymphocytes(source.getLymphocytes());
		target.setMonocytes(source.getMonocytes());
		target.setEosinophils(source.getEosinophils());
		target.setBasophils(source.getBasophils());
		target.setPlatetetCount(source.getPlatetetCount());
		target.setRbc(source.getRbc());
		target.setHb(source.getHb());
		target.setPcv(source.getPcv());
		target.setMcv(source.getMcv());
		target.setHch(source.getHch());
		target.setMchc(source.getMchc());
		target.setRdwCv(source.getRdwCv());
		target.setHbA1c(source.getHbA1c());
		return target;
	}

	public RftSample fillOrBuildRftSampleEntity(RftSampleDto source, RftSample target, boolean checkChangeDate) {
		if (source == null)
			return null;
		target = DtoHelper.fillOrBuildEntity(source, target, RftSample::new, checkChangeDate);
		target.setUrea(source.getUrea());
		target.setCreatinine(source.getCreatinine());
		target.setSodium(source.getSodium());
		target.setPotassium(source.getPotassium());
		return target;
	}

	public UrineRoutineExaminationSample fillOrBuildUrineRoutineExaminationSample(UrineRoutineExaminationSampleDto source, UrineRoutineExaminationSample target, boolean checkChangeDate) {
		if (source == null)
			return null;
		target = DtoHelper.fillOrBuildEntity(source, target, UrineRoutineExaminationSample::new, checkChangeDate);
		target.setColor(source.getColor());
		target.setTransparency(source.getTransparency());
		target.setPh(source.getPh());
		target.setAlbumin(source.getAlbumin());
		target.setSugar(source.getSugar());
		target.setPusCells(source.getPusCells());
		target.setRbc(source.getRbc());
		target.setEpithelialCell(source.getEpithelialCell());
		target.setCrystals(source.getCrystals());
		target.setCasts(source.getCasts());
		target.setUrineOtherTest(source.getUrineOtherTest());
		return target;
	}

	@LocalBean
	@Stateless
	public static class AdditionalTestFacadeEjbLocal extends AdditionalTestFacadeEjb {

	}

}
