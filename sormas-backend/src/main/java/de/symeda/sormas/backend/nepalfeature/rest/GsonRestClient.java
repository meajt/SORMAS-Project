package de.symeda.sormas.backend.nepalfeature.rest;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class GsonRestClient extends RestClient {
    final Gson gson;

    public GsonRestClient(Gson gson) {
        this.gson = gson;
    }

    @Override
    protected  <T> T handleResponse(Response response , Class<T> responseType) {
        String strRes = response.readEntity(String.class);
        return gson.fromJson(strRes, responseType);
    }
}
