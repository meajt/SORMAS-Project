package de.symeda.sormas.backend.nepalfeature.rest;

import de.symeda.sormas.backend.util.ClientHelper;
import org.slf4j.LoggerFactory;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public  class RestClient {
    private String authHeader;
   public RestClient setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
        return this;
    }
    public <T> T get(String url, Class<T> responseType){
        return sendRequest(url,null, responseType, HttpMethod.GET);
    }

    public <T> T get(String url, Map<String, Object> queryParams, Class<T> responseType) {
        return sendRequest(url+"?"+queryParamsToString(queryParams),null, responseType, HttpMethod.GET);
    }

    public <T> T post(String url, Object body, Class<T> responseType) {
        return sendRequest(url,body, responseType, HttpMethod.GET);
    }

    private <T> T sendRequest(String endpoint,Object body, Class<T> responseTyp, String method) {
       LoggerFactory.getLogger(this.getClass())
               .debug("calling {}", endpoint);
        Entity entity = null;
        if (body != null) {
            entity = Entity.entity(body,MediaType.APPLICATION_JSON_TYPE);
        }
        Invocation.Builder invocation = buildRestClient(endpoint);
        Response response = null;
        switch (method) {
            case HttpMethod.POST:
                response = invocation.post(entity);
                break;
            case HttpMethod.GET:
            response = invocation.get();
            break;
        }
        return handleResponse(response, responseTyp);
    }

    private Invocation.Builder buildRestClient(String url) {
        LoggerFactory.getLogger(this.getClass())
                .info("link is {}", url);
        return ClientHelper.newBuilderWithProxy()
                .build()
                .target(url)
                .request()
                .header("Authorization", authHeader);
    }

    protected   <T> T handleResponse(Response response , Class<T> responseType) {
        return response.readEntity(responseType);
    }

    public String queryParamsToString(Map<String, Object> queryParams) {
       return queryParams.entrySet()
                .stream()
               .filter(entry -> entry.getValue() != null)
                .map(entry -> entry.getKey()+"="+entry.getValue())
                .collect(Collectors.joining("&"));

    }

}
