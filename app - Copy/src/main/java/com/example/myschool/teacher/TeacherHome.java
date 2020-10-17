package com.example.myschool.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myschool.R;
import com.example.myschool.SessionManger;
import com.example.myschool.teacher.search.SearshStudent;

import java.util.HashMap;

public class TeacherHome extends AppCompatActivity {

    private TextView fullname,email;

    private Button btnSend, btnUpload, btnLogout;
    SessionManger sessionManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        sessionManger = new SessionManger(this);

        sessionManger.checkLogin();
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        btnLogout = findViewById(R.id.btnLogout);
        btnUpload = findViewById(R.id.btnUpload);
        btnSend = findViewById(R.id.btnSend);

        HashMap<String, String>user = sessionManger.getUsersDetail();

        String mFullname = user.get(sessionManger.FULLNAME);
        String mEmail = user.get(sessionManger.EMAIL);

        fullname.setText(mFullname);
        email.setText(mEmail);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManger.logout();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherHome.this, SearshStudent.class);
                startActivity(intent);
            }
        });
    }

}