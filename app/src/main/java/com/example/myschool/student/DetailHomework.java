package com.example.myschool.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myschool.R;

public class DetailHomework extends AppCompatActivity {

    private ImageView imageView;
    private TextView subject_textView, grade_textView, name_textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_homework);

        String subject = getIntent().getExtras().getString("id_subject");
        String grade = getIntent().getExtras().getString("grade");
        String name = getIntent().getExtras().getString("id_fullname");
        String image_url = getIntent().getExtras().getString("homework");

        imageView = findViewById(R.id.image_view);
        subject_textView = findViewById(R.id.subject_textView);
        subject_textView.setText(subject);
        grade_textView = findViewById(R.id.grade_textView);
        grade_textView.setText(name);
        name_textView = findViewById(R.id.name_textView);
        name_textView.setText(grade);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.ic_andoid).error(R.drawable.ic_andoid);
        Glide.with(this).load(image_url).apply(requestOptions).into(imageView);

    }
}