package de.symeda.sormas.backend.nepalfeature.news;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.symeda.sormas.api.nepalsfeature.news.*;
import de.symeda.sormas.api.utils.SortProperty;
import de.symeda.sormas.backend.common.ConfigFacadeEjb;
import de.symeda.sormas.backend.nepalfeature.rest.GsonRestClient;
import de.symeda.sormas.backend.nepalfeature.rest.RestClient;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Stateless(name = "NewsFacade")
public class NewsFacadeEjb implements NewsFacade {
   private final Gson gson = new GsonBuilder().create();
   private final String LIMIT = "limit";
   private final String OFFSET = "offset";
   private final String GET_NEWS = "/get-news";
   private final String NEWS = "/news";

   @EJB
   private ConfigFacadeEjb.ConfigFacadeEjbLocal configFacadeEjb;
    @Override
    public long count(NewsCriteria criteria) {
        NewsConfig newsConfig = configFacadeEjb.getNewsConfig();
        RestClient restClient = new GsonRestClient(gson)
                .setAuthHeader(String.format("Bearer %s", newsConfig.getAuthToken()));
        Map<String, Object> filterQueryMap = getFilterQueryMap(criteria, null, null);
        filterQueryMap.put("page", 1);
        filterQueryMap.put("pagination_count", 1);
        NewsBodyResponse response = restClient.get(newsConfig.getBaseUrl()+NEWS, filterQueryMap, NewsBodyResponse.class);
        return response.getNewsMetaResponse().getTotal();
    }

    @Override
    public List<NewsDto> getNewList(NewsCriteria criteria, Integer first, Integer max, List<SortProperty> sortProperties) {
        LoggerFactory.getLogger(this.getClass())
                .info("@getNewList first={} max={}", first, max);
        NewsConfig newsConfig = configFacadeEjb.getNewsConfig();
        RestClient restClient = new RestClient()
                .setAuthHeader(String.format("Bearer %s", newsConfig.getAuthToken()));
        String responseStr =  restClient.get(newsConfig.getBaseUrl()+GET_NEWS, getFilterQueryMap(criteria, first, max),String.class);
        return gson.fromJson(responseStr, new TypeToken<List<NewsDto>>(){}.getType());
    }

    private Map<String, Object> getFilterQueryMap(NewsCriteria criteria, Integer first, Integer max) {
        NewsRestFilterCriteria restCriteria = new NewsRestFilterCriteria();
        if (criteria != null) {
            if (criteria.getRegion() != null) {
                restCriteria.setProvinceName(criteria.getRegion().getCaption());
            }
            if (criteria.getDistrict() != null) {
                restCriteria.setDistrictName(criteria.getDistrict().getCaption());
            }
            if (criteria.getCommunity() != null) {
                restCriteria.setPalikaName(criteria.getCommunity().getCaption());
            }
            restCriteria.setRiskLevel(criteria.getRiskLevel());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            if (criteria.getStartDate() != null) {
                restCriteria.setFromDateQuery(dateFormat.format(criteria.getStartDate()));
            }
            if (criteria.getEndDate() != null) {
                restCriteria.setToDateQuery(dateFormat.format(criteria.getEndDate()));
            }
        }
        restCriteria.setLimit(max);
        restCriteria.setOffset(first);
       return restCriteria.toFilterMap();
    }

    @LocalBean
    @Stateless
    public static class NewsFacadeEjbLocal extends NewsFacadeEjb{}
}
