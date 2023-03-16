package com.sumit.mycrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sumit.mycrud.adapter.EmployeeAdapter;
import com.sumit.mycrud.model.ApiList;
import com.sumit.mycrud.model.Employee;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private String empData ;
    private List<Employee> employeeList;
    private RecyclerView recyclerView ;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiList.init();
        recyclerView = findViewById(R.id.datalist);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);

        Intent intent = getIntent();
        if(intent.getStringExtra("message") != null){
            Snackbar snackbar = Snackbar.make(findViewById(R.id.main),intent.getStringExtra("message"), BaseTransientBottomBar.LENGTH_LONG);
            snackbar.setTextColor(R.color.white);
            View sn = snackbar.getView();
//            sn.setBackground(getDrawable(R.color.design_default_color_background));
            snackbar.show();
//            Toast.makeText(getApplicationContext(),intent.getStringExtra("message"),Toast.LENGTH_LONG).show();
        }

        employeeList = new ArrayList<>();
        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() throws IOException{
        OkHttpClient client = new OkHttpClient();
        System.out.println(ApiList.getApis("all"));
        Request request = new Request.Builder()
                .url(ApiList.getApis("all"))
                .build();

        // async call -> asynchronization call / thread late binding
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(myResponse);
                            JSONArray jsonArray = json.getJSONArray("data");
                            empData = jsonArray.toString();
                            System.out.println("Employee Data" + empData);
                            final Gson gson = new Gson();
                            employeeList = gson.fromJson(empData,new TypeToken<List<Employee>>(){}.getType());
                            EmployeeAdapter employeeAdapter = new EmployeeAdapter(employeeList,MainActivity.this);
                            recyclerView.setAdapter(employeeAdapter);
                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                });
            }
        });
    }
}