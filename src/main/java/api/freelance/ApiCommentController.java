package api.freelance;

import api.BaseApi;
import api.freelance.entities.CommentData;
import com.google.gson.Gson;
import common.logger.Logger;
import okhttp3.*;
import org.json.JSONArray;

import java.io.IOException;

public class ApiCommentController extends BaseApi {

    private static String controllerPath = "/api/comment";

    public CommentData createComment(int jobID, CommentData newComment) {
        String apiEntry = freelanceApiURL + controllerPath + "/" + jobID + "/create";
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(gson.toJson(newComment), MediaType.parse("application/json"));
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
            CommentData createdComment = gson.fromJson(response.body().string(), CommentData.class);
            return createdComment;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JSONArray getAllCommentsForSpecificJob(int jobID) {
        String apiEntry = freelanceApiURL + controllerPath + "/" + jobID + "/all";
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
            JSONArray myResponse = new JSONArray(response.body().string());
            return myResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
