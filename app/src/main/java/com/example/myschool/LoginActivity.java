package com.example.myschool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myschool.parent.ParentHomeActivity;
import com.example.myschool.parent.ParentRegisterActivity;
import com.example.myschool.student.StudentHomeActivity;
import com.example.myschool.student.StudentRegisterActivity;
import com.example.myschool.teacher.RegisterTeacherActivity;
import com.example.myschool.teacher.TeacherHome;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private Button  btnLogin;
    private EditText email, password;
    private ProgressBar progressbar;
    private TextView linkRegister ;
    private RadioGroup radioGroup;
    private String type;

    SessionManger sessionManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManger = new SessionManger(this);

        linkRegister = findViewById(R.id.linkRegister);
        btnLogin = findViewById(R.id.btnLogin);
        progressbar = findViewById(R.id.progressbar);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);


        Intent intentType = getIntent();
        type = intentType.getStringExtra("type");

        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("Teacher")) {
                    Intent intent = new Intent(LoginActivity.this, RegisterTeacherActivity.class);
                    startActivity(intent);
                }else if(type.equals("Student")){
                    Intent intent = new Intent(LoginActivity.this, StudentRegisterActivity.class);
                    startActivity(intent);
                }else if(type.equals("Parent")){
                    Intent intent = new Intent(LoginActivity.this, ParentRegisterActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Login();
            }
        });
    }

    private void Login() {

        progressbar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        final String mEmail = email.getText().toString().trim();
        final String mPass = password.getText().toString().trim();

        if (TextUtils.isEmpty(mEmail)) {
            email.setError("Please enter your username");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(mPass)) {
            password.setError("Please enter your password");
            password.requestFocus();
            return;
        }
           if(type.equals("Teacher")){

               StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_TEACHER,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {

                               try {
                                   JSONObject jsonObject = new JSONObject(response);

                                   JSONArray jsonArray = jsonObject.getJSONArray("login");

                                       for (int i = 0; i <jsonArray.length() ; i++) {
                                           JSONObject object = jsonArray.getJSONObject(i);

                                           String fullname = object.getString("fullname").trim();

                                               sessionManger.createSession(fullname,null);
                                               Intent intent = new Intent(LoginActivity.this, TeacherHome.class);
                                               intent.putExtra("fullname",fullname);
                                               startActivity(intent);
                                               finish();
                                               progressbar.setVisibility(View.GONE);

                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                                   progressbar.setVisibility(View.GONE);
                                   btnLogin.setVisibility(View.VISIBLE);
                                   Toast.makeText(LoginActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                               }
                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               progressbar.setVisibility(View.GONE);
                               btnLogin.setVisibility(View.VISIBLE);
                               Toast.makeText(LoginActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                           }
                       })
               {
                   @Override
                   protected Map<String, String>getParams() throws AuthFailureError{
                       Map<String, String>params = new HashMap<>();
                       params.put("email",mEmail);
                       params.put("password",mPass);
                       return params;
                   }
               };
               MySingleton.getInstance(this).addToRequestQueue(stringRequest);


           }else if(type.equals("Student")){

               StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_STUDENT,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {

                               try {
                                   JSONObject jsonObject = new JSONObject(response);
                                   JSONArray jsonArray = jsonObject.getJSONArray("login");

                                       for (int i = 0; i <jsonArray.length() ; i++) {
                                           JSONObject object = jsonArray.getJSONObject(i);

                                           String fullname = object.getString("fullname").trim();
                                           String grade = object.getString("grade").trim();

                                               sessionManger.createSession(fullname,grade);
                                               Intent intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
                                               intent.putExtra("fullname",fullname);
                                               intent.putExtra("grade",grade);
                                               startActivity(intent);
                                               finish();
                                               progressbar.setVisibility(View.GONE);

                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                                   progressbar.setVisibility(View.GONE);
                                   btnLogin.setVisibility(View.VISIBLE);
                                   Toast.makeText(LoginActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                               }
                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               progressbar.setVisibility(View.GONE);
                               btnLogin.setVisibility(View.VISIBLE);
                               Toast.makeText(LoginActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                           }
                       })
               {
                   @Override
                   protected Map<String, String>getParams() throws AuthFailureError{
                       Map<String, String>params = new HashMap<>();
                       params.put("email",mEmail);
                       params.put("password",mPass);
                       return params;
                   }
               };
               MySingleton.getInstance(this).addToRequestQueue(stringRequest);

           }else if(type.equals("Parent")){

               StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN_PARENT,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {

                               try {
                                   JSONObject jsonObject = new JSONObject(response);
                                   JSONArray jsonArray = jsonObject.getJSONArray("login");

                                       for (int i = 0; i < jsonArray.length(); i++) {
                                           JSONObject object = jsonArray.getJSONObject(i);

                                           String childName = object.getString("fullname").trim();
                                           String gradeChild = object.getString("grade").trim();

                                               sessionManger.createSession(childName,gradeChild);
                                               Intent intent = new Intent(LoginActivity.this, ParentHomeActivity.class);
                                               intent.putExtra("childName", childName);
                                               intent.putExtra("gradeChild",gradeChild);
                                               startActivity(intent);
                                               finish();
                                               progressbar.setVisibility(View.GONE);

                                   }
                               } catch (JSONException e) {
                                   e.printStackTrace();
                                   progressbar.setVisibility(View.GONE);
                                   btnLogin.setVisibility(View.VISIBLE);
                                   Toast.makeText(LoginActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                               }
                           }
                       },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               progressbar.setVisibility(View.GONE);
                               btnLogin.setVisibility(View.VISIBLE);
                               Toast.makeText(LoginActivity.this, "User is not exist", Toast.LENGTH_SHORT).show();
                           }
                       }) {
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {
                       Map<String, String> params = new HashMap<>();
                       params.put("email", mEmail);
                       params.put("password", mPass);
                       return params;
                   }
               };
               MySingleton.getInstance(this).addToRequestQueue(stringRequest);
           }
    }

}