package com.example.myschool.student;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myschool.R;
import com.example.myschool.SessionManger;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;



public class StudentHomeActivity extends AppCompatActivity{

    private TextView  viewFullname, viewGrade;
    private Button btnUpload, btnDownload;
    private SessionManger sessionManger;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        sessionManger = new SessionManger(this);

        btnDownload = findViewById(R.id.btnDownload);
        btnUpload = findViewById(R.id.btnUpload);
        viewFullname = findViewById(R.id.viewFullname);
        viewGrade = findViewById(R.id.viewGrade);


        Intent intentFullname = getIntent();
        Intent intentGrade = getIntent();

        String mFullname = intentFullname.getStringExtra("fullname");
        String mGrade = intentGrade.getStringExtra("grade");
        viewFullname.setText(mFullname);
        viewGrade.setText(mGrade);


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StudentHomeActivity.this,StudentUpload.class);
                intent.putExtra("fullname",mFullname);
                intent.putExtra("grade",mGrade);
                startActivity(intent);
            }
        });

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentHomeActivity.this,StudentDownload.class);
                intent.putExtra("fullname",mFullname);
                intent.putExtra("grade",mGrade);
                startActivity(intent);
            }
        });
    }
}
