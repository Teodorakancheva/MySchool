package com.example.myschool.parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.example.myschool.teacher.MessageTeacher;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewStatus extends AppCompatActivity {

    private TextView textViewSubject,textViewMarks,textViewKey;
    private String childName, gradeChild;
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_status);

        listview = (ListView)findViewById(R.id.listView);


        Intent intent = getIntent();
         childName = intent.getStringExtra("fullname");
        Intent intentGrdae = getIntent();
         gradeChild = intentGrdae.getStringExtra("grade");
        Log.d("STR>>>>>>", childName);

        getData();
    }

    private void getData(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.VIEW_STATE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("STR>>>>>>", response);
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ViewStatus.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("fullname",childName);
                params.put("grade",gradeChild);
                return params;
            }
        };

        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void showJSON(String response) {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
                 JSONObject jsonObject = new JSONObject(response);
                 JSONArray result = jsonObject.getJSONArray("result");

             for(int i=0;i<result.length();i++){

                JSONObject object = result.getJSONObject(i);
                String subject = object.getString("subject").trim();
                String mark = object.getString("mark").trim();
                 String key = object.getString("key_homework").trim();


                final HashMap<String,String> employees = new HashMap<>();
                employees.put("subject",subject);
                employees.put("mark",mark);
                 employees.put("key_homework",key);
                list.add(employees);

             }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ViewStatus.this, list, R.layout.activity_mylist_state,
                new String[]{"subject","mark","key_homework"},
                new int[]{R.id.textViewSubject,R.id.textViewMarks,R.id.textViewKey});
        listview.setAdapter(adapter);
    }
}
