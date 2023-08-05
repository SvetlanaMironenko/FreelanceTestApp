package api.freelance;

import api.BaseApi;
import api.freelance.entities.PersonalUserData;
import com.google.gson.Gson;
import common.logger.Logger;
import okhttp3.*;

import java.io.IOException;

public class ApiUserController extends BaseApi {

    private static String controllerPath = "/api/user";

    public PersonalUserData getSelfUser() {
        String apiEntry = freelanceApiURL + controllerPath + "/";
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url(apiEntry)
                .addHeader("Authorization", ApiAuthController.getToken())
                .build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                Logger.fail("Response code NOT 200! Returned message: " + response.body().string());
            } else {
                Logger.info("Request sent, response code is 200.");
            }
            PersonalUserData personalUserData = gson.fromJson(response.body().string(), PersonalUserData.class);
            return personalUserData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PersonalUserData getSpecificUserByID(int userID) {
        String apiEntry = freelanceApiURL + controllerPath + "/" + userID;
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url(apiEntry)
                .addHeader("Authorization", ApiAuthController.getToken())
                .build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200) {
                Logger.fail("Response code NOT 200! Returned message: " + response.body().string());
            } else {
                Logger.info("Request sent, response code is 200.");
            }
            PersonalUserData personalUserData = gson.fromJson(response.body().string(), PersonalUserData.class);
            return personalUserData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PersonalUserData updateUserData(PersonalUserData userDataForUpdate) {
        String apiEntry = freelanceApiURL + controllerPath + "/update";
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(gson.toJson(userDataForUpdate), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url(apiEntry)
                .addHeader("Authorization", ApiAuthController.getToken())
                .addHeader("Content-Type", "application?json")
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
            PersonalUserData updatedUserData = gson.fromJson(response.body().string(), PersonalUserData.class);
            return updatedUserData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
