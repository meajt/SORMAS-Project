package de.symeda.sormas.app.sample.read;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import de.symeda.sormas.app.BasePrintActivity;
import de.symeda.sormas.app.backend.common.DatabaseHelper;
import de.symeda.sormas.app.backend.facility.Facility;
import de.symeda.sormas.app.backend.person.Person;
import de.symeda.sormas.app.backend.sample.PathogenTest;
import de.symeda.sormas.app.backend.sample.Sample;
import de.symeda.sormas.app.util.DateFormatHelper;


public class SamplePrintActivity extends BasePrintActivity<Sample> {

    public static void startActivity(Context context, String rootUuid) {
        BasePrintActivity.startActivity(context, SamplePrintActivity.class, rootUuid);
    }

    @Override
    protected Sample queryRootEntity(String recordUuid) {
        return DatabaseHelper.getSampleDao().queryUuid(recordUuid);
    }


    @Override
    protected void buildLayout() {
        requestRootData(sample -> {
            createHeader(sample.getLab());
            createSubHeader(sample.getAssociatedCase().getPerson());
            setBodyAdapter(sample);
            binding.testedDate.setText("Requisition Date: " + DateFormatHelper.formatLocalDate(sample.getSampleDateTime()));
        });
    }

    private void createHeader(Facility lab) {
        binding.mainTitle.setText(lab.getName());
        //binding.a1.setText(lab.getStreet()+", "+lab.getCity());
    }

    private void createSubHeader(Person person) {
        binding.personName.setText("Patient Name: " + person.getFirstName() + " " + person.getLastName());
        binding.personId.setText("Patient Id: " + person.getUuid());
        binding.personAge.setText("Patient DOF: " + person.getBirthdateDD()+"-"+person.getBirthdateMM()+"-"+person.getBirthdateYYYY());
        binding.personAddress.setText("Patient Address: " + person.getAddress().buildCaption());
        if (TextUtils.isEmpty(person.getMobileNo()))
            binding.personPhoneNo.setVisibility(View.GONE);
        else
            binding.personPhoneNo.setText("Patient Phone no: " + person.getMobileNo());
    }

    private void setBodyAdapter(Sample sample) {
        List<PathogenTest> pathogenTestSet = DatabaseHelper.getSampleTestDao().queryBySample(sample);
        binding.sampleTestItemRv.setLayoutManager(new LinearLayoutManager(this));
        binding.sampleTestItemRv.setAdapter(new SamplePrintListAdapter(pathogenTestSet));
        binding.sampleTestItemRv.setHasFixedSize(true);
    }
}