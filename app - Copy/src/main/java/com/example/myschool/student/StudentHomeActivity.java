package com.example.myschool.student;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myschool.R;

public class StudentHomeActivity extends AppCompatActivity {

    TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        textView = findViewById(R.id.textView);
    }
}
