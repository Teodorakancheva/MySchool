package com.example.myschool.parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myschool.LoginActivity;
import com.example.myschool.MySingleton;
import com.example.myschool.R;
import com.example.myschool.SessionManger;
import com.example.myschool.URLs;
import com.example.myschool.teacher.RegisterTeacherActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParentRegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private TextView linkLogin;
    private EditText fullname, email, password, childName, gradeChild;
    private ProgressBar progressbar;
    private SessionManger sessionManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);

            sessionManger = new SessionManger(this);
            btnRegister = findViewById(R.id.btnRegister);
            linkLogin = findViewById(R.id.linkLogin);
            progressbar = findViewById(R.id.progressbar);
            fullname = findViewById(R.id.fullname);
            email = findViewById(R.id.email);
            password = findViewById(R.id.password);
            childName = findViewById(R.id.childName);
            gradeChild = findViewById(R.id.gradeChild);

            linkLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ParentRegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Regist();
                }
            });
        }
        private void Regist(){
            progressbar.setVisibility(View.VISIBLE);
            btnRegister.setVisibility(View.VISIBLE);

            final String mFullname = fullname.getText().toString().trim();
            final String mEmail = email.getText().toString().trim();
            final String mPassword = password.getText().toString().trim();
            final String mChildName = childName.getText().toString().trim();
            final String mGradeChild = gradeChild.getText().toString().trim();

            if (TextUtils.isEmpty(mFullname)) {
                fullname.setError("Please enter fullname");
                fullname.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(mEmail)) {
                email.setError("Please enter your email");
                email.requestFocus();
                return;
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()) {
                email.setError("Enter a valid email");
                email.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(mPassword)) {
                password.setError("Enter a password");
                password.requestFocus();
                return;
            }


            if (TextUtils.isEmpty(mChildName)) {
                childName.setError("Enter a child name");
                childName.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(mGradeChild)) {
                gradeChild.setError("Enter a child grade");
                gradeChild.requestFocus();
                return;
            }


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER_PARENT,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("strrrrr", ">>" + response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                if(success.equals("2")){
                                    Toast.makeText(ParentRegisterActivity.this, "Email already exist!", Toast.LENGTH_SHORT).show();
                                }
                                if(success.equals("1")){
                                    Toast.makeText(ParentRegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    sessionManger.createSession(mChildName,mGradeChild);
                                    Intent intent = new Intent(ParentRegisterActivity.this, ParentHomeActivity.class);
                                    intent.putExtra("childName",mChildName);
                                    intent.putExtra("gradeChild",mGradeChild);
                                    startActivity(intent);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(ParentRegisterActivity.this, "Registered Error!"+e.toString(), Toast.LENGTH_SHORT).show();
                                progressbar.setVisibility(View.GONE);
                                btnRegister.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ParentRegisterActivity.this, "Registered Error!"+error.toString(), Toast.LENGTH_SHORT).show();
                            progressbar.setVisibility(View.GONE);
                            btnRegister.setVisibility(View.VISIBLE);
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params = new HashMap<>();

                    params.put("fullname",mFullname);
                    params.put("email",mEmail);
                    params.put("password",mPassword);
                    params.put("childName",mChildName);
                    params.put("gradeChild",mGradeChild);
                    return params;
                }
            };

            MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}