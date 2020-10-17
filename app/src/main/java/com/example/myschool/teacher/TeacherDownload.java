package com.example.myschool.teacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myschool.Model;
import com.example.myschool.MyAdapter;
import com.example.myschool.MySingleton;
import com.example.myschool.R;
import com.example.myschool.SessionManger;
import com.example.myschool.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherDownload extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter  mAdapter;
    private RecyclerView.LayoutManager mManager;
    private RequestQueue mRequest;
    private List<Model> mList;
    private ArrayList<String> students;
    private SessionManger sessionManger;
    private EditText text_grade, text_mark, text_key;
    private Button btnViewDownload, btnSelectStud,btnSend;
    private Spinner subject_spinner,spinner;
    private String spinnerSubject, spinnerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_download);

        btnSend = findViewById(R.id.btnSend);
        text_mark = findViewById(R.id.text_mark);
        text_key = findViewById(R.id.text_key);
        btnViewDownload = findViewById(R.id.btnViewDownload);
        text_grade = findViewById(R.id.text_Grade);
        subject_spinner = findViewById(R.id.subject_spinner);
        btnSelectStud = findViewById(R.id.btnSelectStud);
        mRecyclerView = findViewById(R.id.recyclerTemp);
        spinner = findViewById(R.id.name_spinner);

        mRequest = Volley.newRequestQueue(getApplicationContext());
        mList=new ArrayList<>();
        students = new ArrayList<String>();

        spinner.setOnItemSelectedListener(this);
        subject_spinner.setOnItemSelectedListener(this);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.school_subject, android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject_spinner.setAdapter(adapter);

        btnViewDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPost();

            }
        });

        mManager = new LinearLayoutManager(TeacherDownload.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = new MyAdapter(mList,TeacherDownload.this);
        mRecyclerView.setAdapter(mAdapter);

        btnSelectStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String grade = text_grade.getText().toString().trim();
                getData(grade);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMarks();
            }
        });
    }

    private void sendMarks() {
        final String mark = text_mark.getText().toString().trim();
        final String key = text_key.getText().toString().trim();
        final String name = spinnerName;
        final String subject = spinnerSubject;
        final String Mgrade = text_grade.getText().toString().trim();
        Log.d("strrrrr", ">>" + Mgrade);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.SEND_MARKS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                                Toast.makeText(TeacherDownload.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TeacherDownload.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mark",mark);
                params.put("key_homework",key);
                params.put("id_student",name);
                params.put("id_subject",subject);
                params.put("grade",Mgrade);
                return params;

            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void getData(final String grade) {


        final String url = URLs.SPINNER_URL+grade;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj  = new JSONObject(response);
                            JSONArray jsonArray= obj.getJSONArray("result");

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject json = jsonArray.getJSONObject(i);
                                students.add(json.getString("fullname"));

                            }
                            spinner.setAdapter(new ArrayAdapter<String>(TeacherDownload.this, android.R.layout.simple_list_item_1, students));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TeacherDownload.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    private void requestPost() {
        final String grade = text_grade.getText().toString().trim();
        final String subject = spinnerSubject.trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.DOWNLOAD_TEACHER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);
                                mList.add(new Model(
                                        product.getInt("key_homework"),
                                        product.getString("fullname"),
                                        product.getString("subject"),
                                        product.getString("grade"),
                                        product.getString("homework")
                                ));
                            }

                            MyAdapter adapter = new MyAdapter(mList,TeacherDownload.this);
                            mRecyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TeacherDownload.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("grade",grade);
                params.put("subject",subject);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.name_spinner)
        {
            spinnerName = parent.getItemAtPosition(position).toString();
        }
        else if(spinner.getId() == R.id.subject_spinner)
        {
            spinnerSubject = parent.getItemAtPosition(position).toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
