package com.example.myschool.student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myschool.Model;
import com.example.myschool.MyAdapter;
import com.example.myschool.MySingleton;
import com.example.myschool.R;
import com.example.myschool.SessionManger;
import com.example.myschool.URLs;
import com.example.myschool.parent.MessegeParent;
import com.example.myschool.teacher.TeacherDownload;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentDownload extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private RequestQueue mRequest;
    private List<Model> mList;
    private SessionManger sessionManger;
    private TextView view_fullname, view_grade;
    private Button btnViewDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_download);

        sessionManger = new SessionManger(this);
        view_fullname = findViewById(R.id.text_fullname);
        view_grade = findViewById(R.id.text_grade);

        btnViewDownload = findViewById(R.id.btnViewDownload);

        Intent intentFullname = getIntent();
        Intent intentGrade = getIntent();

        String mFullname = intentFullname.getStringExtra("fullname");
        String mGrade = intentGrade.getStringExtra("grade");

        view_fullname.setText(mFullname);
        view_grade.setText(mGrade);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerTemp);
        mRequest = Volley.newRequestQueue(getApplicationContext());
        mList = new ArrayList<>();
        btnViewDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                request();

            }
        });
        mManager = new LinearLayoutManager(StudentDownload.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new MyAdapter(mList, StudentDownload.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void request() {

        final String name = view_fullname.getText().toString().trim();
        final String grade = view_grade.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.DOWNLOAD_STUD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);
                                mList.add(new Model(
                                        product.getInt("id"),
                                        product.getString("fullname"),
                                        product.getString("subject"),
                                        product.getString("grade"),
                                        product.getString("homework")
                                ));
                            }

                            MyAdapter adapter = new MyAdapter(mList, StudentDownload.this);
                            mRecyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(StudentDownload.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("grade",grade);
                params.put("fullname",name);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}

