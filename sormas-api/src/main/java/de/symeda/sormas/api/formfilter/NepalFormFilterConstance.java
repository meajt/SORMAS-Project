package de.symeda.sormas.api.formfilter;

import de.symeda.sormas.api.sample.PathogenTestType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface NepalFormFilterConstance {

     PathogenTestType[] pathogenTestTypeToShowArray = new PathogenTestType[]{
             PathogenTestType.PCR_RT_PCR,
             PathogenTestType.CULTURE,
             PathogenTestType.RAPID_TEST,
             PathogenTestType.ANTIBODY_DETECTION,
             PathogenTestType.IGM_SERUM_ANTIBODY,
             PathogenTestType.IGM_SERUM_ANTIBODY,
             PathogenTestType.MICROSCOPY,
             PathogenTestType.RDT};
     Set<PathogenTestType> pathogenTestTypeToShow = new HashSet<>(Arrays.asList(pathogenTestTypeToShowArray));
}
