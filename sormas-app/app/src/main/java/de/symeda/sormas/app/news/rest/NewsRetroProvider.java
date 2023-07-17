package de.symeda.sormas.app.news.rest;


import static de.symeda.sormas.app.rest.RetroProvider.isConnectedToNetwork;

import android.content.Context;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import de.symeda.sormas.app.rest.ApiVersionException;
import de.symeda.sormas.app.rest.NoConnectionException;
import de.symeda.sormas.app.rest.RetroProvider;
import de.symeda.sormas.app.rest.ServerCommunicationException;
import de.symeda.sormas.app.rest.ServerConnectionException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

final public class NewsRetroProvider {
    private static NewsRetroProvider instance = null;
    private final Retrofit retrofit;
    private static boolean connecting = false;
    private Context context;
    private NewsFacadeRetro newsFacadeRetro;
    private final String BASE_URL = "http://172.177.142.86/";
    private final String BEARER_TOKEN = "1|zPCfbXStpwsCrIEWQWQKutgdVVuYk657YiDcYDC7";

    private NewsRetroProvider(Context context) {
        this.context = context;
        retrofit = buildRetrofit(BASE_URL);
    }

    private   Retrofit buildRetrofit(String serverUrl) {
        Gson gson = initGson();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(300, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer "+BEARER_TOKEN)
                    .build();
            return chain.proceed(request);
        });

        return new Retrofit.Builder().baseUrl(serverUrl).addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build()).build();
    }

    private Gson initGson() {
        return new Gson();
    }

    public static void connect(Context context) throws ApiVersionException, ServerConnectionException, ServerCommunicationException {

        if (RetroProvider.isConnected()) {
            throw new IllegalStateException("Connection already established.");
        }
        if (connecting) {
            throw new IllegalStateException("Already connecting.");
        }
        if (!isConnectedToNetwork(context)) {
            throw new ServerConnectionException(600);
        }

        try {
            connecting = true;
            instance = new NewsRetroProvider(context);
        } catch (Exception e) {
            instance = null;
            throw e;
        } finally {
            connecting = false;
        }
    }

    public static boolean isConnected() {
        return instance != null && isConnectedToNetwork(instance.context);
    }

    public static NewsFacadeRetro getNewsFacadeRetro() throws NoConnectionException {
        if (instance == null)
            throw new NoConnectionException();
        if (instance.newsFacadeRetro == null) {
            synchronized ((NewsFacadeRetro.class)) {
                if (instance.newsFacadeRetro == null) {
                    instance.newsFacadeRetro = instance.retrofit.create(NewsFacadeRetro.class);
                }
            }
        }
        return instance.newsFacadeRetro;
    }

}
