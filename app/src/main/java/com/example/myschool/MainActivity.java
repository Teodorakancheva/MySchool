package com.example.myschool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.myschool.parent.ParentRegisterActivity;
import com.example.myschool.student.StudentRegisterActivity;
import com.example.myschool.teacher.RegisterTeacherActivity;

public class MainActivity extends AppCompatActivity {
      private   ProgressBar progressBar;
      private Button btnTeacher, btnStudent, btnParent;
      private String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressbar);
        btnTeacher = findViewById(R.id.btnTeacher);
        btnStudent = findViewById(R.id.btnStudent);
        btnParent = findViewById(R.id.btnParent);

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Teacher";
                Intent intent  = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("type","Teacher");
                startActivity(intent);
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Student";
                Intent intent  = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("type","Student");
                startActivity(intent);
            }
        });

        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = "Parent";
                Intent intent  = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("type","Parent");
                startActivity(intent);
            }
        });
    }
}