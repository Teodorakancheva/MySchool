package com.example.myschool.parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.myschool.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ParentRegisterActivity extends AppCompatActivity {
    private Button btnRegister;
    private TextView linkLogin;
    private EditText fullname, email, password ,childName, gradeChild;
    private ProgressBar progressbar;
    private static String URL_REGISTER = "http://192.168.0.107/MySchool/registerParent.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_register);

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

            final String fullname = this.fullname.getText().toString().trim();
            final String email = this.email.getText().toString().trim();
            final String password = this.password.getText().toString().trim();
            final String childName = this.childName.getText().toString().trim();
            final String gradeChild = this.gradeChild.getText().toString().trim();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                if(success.equals("1")){
                                    Toast.makeText(ParentRegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
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

                    params.put("fullname",fullname);
                    params.put("email",email);
                    params.put("password",password);
                    params.put("childName",childName);
                    params.put("gradeChild",gradeChild);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }
}