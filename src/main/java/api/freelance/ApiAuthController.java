package api.freelance;

import api.BaseApi;
import api.freelance.entities.LoginUser;
import com.google.gson.Gson;
import common.logger.Logger;
import api.freelance.entities.RegisterUser;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class ApiAuthController extends BaseApi {

    private static String controllerPath = "/api/auth";

    private static String token = null;

    public static String getToken() {
        if (token == null) {
            Logger.fail("You forgot login and tried use 'null' token");
        }
        return token;
    }

    private static void setToken(String newToken) {
        token = newToken;
        Logger.info("New token:\n" + token);
    }

    public boolean registerUser(RegisterUser newRegisterUser) {
        String apiEntry = freelanceApiURL + controllerPath + "/signup";
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(gson.toJson(newRegisterUser), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(apiEntry)
                .header("Content-Type", "application?json")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                Logger.fail("Response code NOT 200! Returned message: " + response.body().string());
            } else {
                Logger.info("Request sent, response code is 200");
            }
            return response.body().string().contains("User registered successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(LoginUser userToLogin) {
        String apiEntry = freelanceApiURL + controllerPath + "/signin";
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(gson.toJson(userToLogin), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(apiEntry)
                .header("Content-Type", "application?json")
                .post(body)
                .build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                Logger.fail("Response code NOT 200! Returned message: " + response.body().string());
            } else {
                Logger.info("Request sent, response code is 200.");
            }
            JSONObject resJson = new JSONObject(response.body().string());
            Logger.info(userToLogin.toString());
            setToken(resJson.get("token").toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
