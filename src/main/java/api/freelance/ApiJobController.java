package api.freelance;

import api.BaseApi;
import api.freelance.entities.JobData;
import com.google.gson.Gson;
import common.logger.Logger;
import okhttp3.*;
import org.json.JSONArray;

import java.io.IOException;

public class ApiJobController extends BaseApi  {

    private static String controllerPath = "/api/job";

    public JSONArray getAllJobs() {
        String apiEntry = freelanceApiURL + controllerPath + "/all";
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

    public JobData createJob(JobData newJob) {
        String apiEntry = freelanceApiURL + controllerPath + "/create";
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(gson.toJson(newJob), MediaType.parse("application/json"));
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
            JobData updatedJobData = gson.fromJson(response.body().string(), JobData.class);
            return updatedJobData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public JobData getSpecificJobByID(int jobID) {
        String apiEntry = freelanceApiURL + controllerPath + "/" + jobID;
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
            JobData jobData = gson.fromJson(response.body().string(), JobData.class);
            return jobData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteJob(int jobID) {
        String apiEntry = freelanceApiURL + controllerPath + "/delete/" + jobID;
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(null, new byte[]{});
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
            return response.body().string().contains("Job is deleted");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
