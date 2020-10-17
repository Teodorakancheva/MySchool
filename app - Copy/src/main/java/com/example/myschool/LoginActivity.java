package com.example.myschool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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
import com.example.myschool.student.StudentHomeActivity;
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
    private TextView linkRegister ,type ;
    private static String URL_LOGIN = "http://192.168.100.7/MySchool/login.php";

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
        type = findViewById(R.id.type);




        linkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterTeacherActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();
                String mType = type.getText().toString().trim();

                if(!mEmail.isEmpty() || !mPass.isEmpty() || !mType.isEmpty()){
                    Login(mEmail,mPass,mType);
                }else{
                    email.setError("Please insert email");
                    password.setError("Please insert password");
                }
            }
        });
    }

    private void Login(final String email, final String password, final String type) {

        progressbar.setVisibility(View.VISIBLE);
        btnLogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if(success.equals("1")){
                                for (int i = 0; i <jsonArray.length() ; i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String fullname = object.getString("fullname").trim();
                                    String email = object.getString("email").trim();

                                    sessionManger.createSession(fullname,email);
                                    if(type.equals("teacher")){
                                        Intent intent = new Intent(LoginActivity.this, TeacherHome.class);
                                        intent.putExtra("fullname",fullname);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                        finish();
                                        progressbar.setVisibility(View.GONE);
                                    }else if(type.equals("student")){
                                        Intent intent = new Intent(LoginActivity.this, StudentHomeActivity.class);
                                        intent.putExtra("fullname",fullname);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                        finish();
                                        progressbar.setVisibility(View.GONE);
                                    }else{
                                        Intent intent = new Intent(LoginActivity.this, ParentHomeActivity.class);
                                        intent.putExtra("fullname",fullname);
                                        intent.putExtra("email",email);
                                        startActivity(intent);
                                        finish();
                                        progressbar.setVisibility(View.GONE);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressbar.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(LoginActivity.this, "Error"+e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressbar.setVisibility(View.GONE);
                        btnLogin.setVisibility(View.VISIBLE);
                        Toast.makeText(LoginActivity.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
                  @Override
                   protected Map<String, String>getParams() throws AuthFailureError{
                      Map<String, String>params = new HashMap<>();
                      params.put("email",email);
                      params.put("password",password);
                      params.put("type",type);
                      return params;
                  }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.teacher:
                type.setText("teacher");
                break;
            case R.id.student:
                type.setText("student");
                break;
            case R.id.parent:
                type.setText("parent");
                break;
        }
    }
}