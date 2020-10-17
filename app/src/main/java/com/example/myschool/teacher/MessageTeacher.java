package com.example.myschool.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myschool.MySingleton;
import com.example.myschool.R;
import com.example.myschool.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageTeacher extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private Spinner spinner;
    private ArrayList<String> students;
    private EditText textGrade, messege;
    private Button btnSelectGrade, btnSend, btnGet;
    private String spinerName="";
    private TextView vew_teach_name,viewMessege;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messege_teacher);

        vew_teach_name =findViewById(R.id.vew_teach_name);
        students = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.spinnerSend);
        spinner.setOnItemSelectedListener(this);
        textGrade = findViewById(R.id.textGrade);
        messege =findViewById(R.id.messege);
        btnSelectGrade = findViewById(R.id.btnSelectGrade);
        btnSend = findViewById(R.id.btnSend);
        btnGet = findViewById(R.id.btnGetMessege);
        viewMessege = findViewById(R.id.viewMessege);
        Intent intent = getIntent();
        String teacherName = intent.getStringExtra("fullname");
        vew_teach_name.setText(teacherName);

        btnSelectGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                students.clear();
                students.add("Select Student");
                students.add("Select All");
                getData();
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMessege();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mMessege= messege.getText().toString().trim();
                String mGrade = textGrade.getText().toString().trim();
                String mTeacherName = vew_teach_name.getText().toString().trim();
                StringRequest request = new StringRequest(Request.Method.POST, URLs.SEND_MESSEGE_TEACHER
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(MessageTeacher.this, response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MessageTeacher.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("text_message", mMessege);
                        params.put("id_fullname", spinerName);
                        params.put("grade", mGrade);
                        params.put("id_name_teacher", mTeacherName);

                        return params;

                    }

                };

                MySingleton.getInstance(MessageTeacher.this).addToRequestQueue(request);
            }
        });
    }

    private void getMessege() {
       final String name = vew_teach_name.getText().toString().trim();

        
        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLs.GET_MESSEGE_TEACHER  , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("msg");
                    if(success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String text = object.getString("text_message").trim();
                            viewMessege.setText(text);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MessageTeacher.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fullname", name);
                return params;

            }
        };
        MySingleton.getInstance(MessageTeacher.this).addToRequestQueue(stringRequest);
    }

    private void getData() {

        final String grade = textGrade.getText().toString().trim();
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
                            spinner.setAdapter(new ArrayAdapter<String>(MessageTeacher.this, android.R.layout.simple_spinner_dropdown_item, students));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MessageTeacher.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

        };
        MySingleton.getInstance(MessageTeacher.this).addToRequestQueue(stringRequest);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinerName = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinerName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}