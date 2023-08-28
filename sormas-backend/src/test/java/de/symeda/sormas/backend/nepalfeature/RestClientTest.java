package de.symeda.sormas.backend.nepalfeature;

import ca.uhn.fhir.util.UrlUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.symeda.sormas.api.nepalsfeature.news.NewsBodyResponse;
import de.symeda.sormas.backend.nepalfeature.rest.GsonRestClient;
import de.symeda.sormas.backend.nepalfeature.rest.RestClient;
import de.symeda.sormas.backend.util.ClientHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import wiremock.org.eclipse.jetty.util.UrlEncoded;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.util.Map;


public class RestClientTest {

    @Test
    void newsRestClientTest() {
        Invocation.Builder requestBuilder = ClientHelper.newBuilderWithProxy()
                .build()
                .target("http://172.177.142.86/api/news")
                .request()
                .header("Authorization", String.format("Bearer %s", "1|zPCfbXStpwsCrIEWQWQKutgdVVuYk657YiDcYDC7"));
        Response response = requestBuilder.get();
        Assertions.assertEquals(response.getStatus(), 200);
        Assertions.assertNotNull(response.getEntity());
        LoggerFactory.getLogger(this.getClass().getName())
                .info(response.readEntity(String.class));
    }

    @Test
    void restClientTest() {
        Gson gson = new GsonBuilder().create();
        RestClient restClient = new GsonRestClient(gson)
                .setAuthHeader(String.format("Bearer %s", "1|zPCfbXStpwsCrIEWQWQKutgdVVuYk657YiDcYDC7"));
        NewsBodyResponse response = restClient.get("http://172.177.142.86/api/news", NewsBodyResponse.class);
        LoggerFactory.getLogger(this.getClass().getName())
                .info(gson.toJson(response));
    }

    @Test
    void queryParamsToStringTest() {
        RestClient client = new RestClient();
        String paramsToString = client.queryParamsToString(Map.of("palika", "Swasthya Khabar", "Aj", "Bj"));
        Assertions.assertEquals(paramsToString, "palika=Swasthya%20%Khabar&Aj=Bj");
    }

}
