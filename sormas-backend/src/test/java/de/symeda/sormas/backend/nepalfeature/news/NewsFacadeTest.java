package de.symeda.sormas.backend.nepalfeature.news;

import de.symeda.sormas.api.event.RiskLevel;
import de.symeda.sormas.api.infrastructure.region.RegionReferenceDto;
import de.symeda.sormas.api.nepalsfeature.news.NewsCriteria;
import de.symeda.sormas.api.nepalsfeature.news.NewsFacade;
import de.symeda.sormas.api.nepalsfeature.news.NewsDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NewsFacadeTest {

    @Test
    void contTest() {

    }
    @Test
    void getNewsListTest() {
        NewsFacade facadeEjb = new NewsFacadeEjb();
        Assertions.assertNotNull(facadeEjb);
        NewsCriteria newsCriteria = new NewsCriteria();
        newsCriteria.setRiskLevel(RiskLevel.LOW);
        newsCriteria.setRegion(new RegionReferenceDto("uidd", "Bagmati", ""));
        long count = facadeEjb.count(newsCriteria);
        Assertions.assertTrue(count > 0);
        List<NewsDto> newList = facadeEjb.getNewList(newsCriteria, 0, 100, null);
        Assertions.assertTrue(!newList.isEmpty());
        Assertions.assertEquals(newList.get(0).getClass().getSimpleName(), NewsDto.class.getSimpleName());
    }
}
