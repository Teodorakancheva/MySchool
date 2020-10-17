package com.example.myschool.parent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myschool.R;
import com.example.myschool.SessionManger;

public class ParentHomeActivity extends AppCompatActivity {

   private TextView childName, childGrade;
   private Button btnSendMessage, btnView, btnState;
   private SessionManger sessionManger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_home);

        sessionManger = new SessionManger(this);
        childName = findViewById(R.id.pCfullname);
        childGrade = findViewById(R.id.pChildGrade);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        btnView = findViewById(R.id.btnView);


        Intent intentName = getIntent();
        Intent intentGread = getIntent();
        String mChildName = intentName.getStringExtra("childName");
        String mChildGrade = intentGread.getStringExtra("gradeChild");
        childName.setText(mChildName);
        childGrade.setText(mChildGrade);


        btnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentHomeActivity.this, MessegeParent.class);
                intent.putExtra("fullname",mChildName);
                intent.putExtra("grade",mChildGrade);
                startActivity(intent);

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParentHomeActivity.this, ViewStatus.class);
                intent.putExtra("fullname",mChildName);
                intent.putExtra("grade",mChildGrade);
                startActivity(intent);
            }
        });


    }
}