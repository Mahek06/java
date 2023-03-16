package com.sumit.mycrud;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.sumit.mycrud.model.ApiList;
import com.sumit.mycrud.model.Employee;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiCalls {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public ApiCalls() {
        ApiList.init();
    }

    public void callapi(String type, AppCompatActivity activity, int id, Employee employee) throws IOException {
        String url = "";
        Request request = null;
        OkHttpClient client = new OkHttpClient();
        if (type.equals("delete")) {
            url = ApiList.getApis("delete");
            request = new Request.Builder()
                    .url(url + id)
                    .build();
        } else if (type.equals("update")) {
            url = ApiList.getApis("update");
            request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSON, new Gson().toJson(employee)))
                    .build();
        } else if (type.equals("activate")) {
            url = ApiList.getApis("activate");
            request = new Request.Builder()
                    .url(url + id)
                    .build();
        }

        if (request != null) {
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String myResponse = response.body().string();

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject json = new JSONObject(myResponse);
                                Intent intent = new Intent(activity, MainActivity.class);
                                if (json.has("message")) {
                                    intent.putExtra("message", json.get("message").toString());
                                } else if (json.has("success")) {
                                    intent.putExtra("message", json.get("success").toString());
                                } else if (json.has("error")) {
                                    intent.putExtra("message", json.get("error").toString());
                                }
                                activity.startActivity(intent);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    });
                }
            });
        }
    }
}
