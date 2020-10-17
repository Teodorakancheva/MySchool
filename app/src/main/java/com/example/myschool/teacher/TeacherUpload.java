package com.example.myschool.teacher;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myschool.MySingleton;
import com.example.myschool.R;
import com.example.myschool.SessionManger;
import com.example.myschool.URLs;
import com.example.myschool.student.StudentHomeActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static com.example.myschool.URLs.UPLOAD_TEACHER_URL;

public class TeacherUpload extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    private EditText subject, textGrade;
    private ImageView imageView;
    private String encodedImage;
    private Bitmap bitmap;
    private Spinner spinner;
    private ArrayList<String> students;
    private JSONArray result;
    private Button btnSelectGrade,btnUpload;
    private String spinerName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_upload);

        imageView = findViewById(R.id.imageView);
        subject = findViewById(R.id.subject);
        btnUpload = findViewById(R.id.btnUpload);

        students = new ArrayList<String>();
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        textGrade = findViewById(R.id.textGrade);
        btnSelectGrade = findViewById(R.id.btnSelectGrade);
        btnSelectGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String grade = textGrade.getText().toString().trim();
                students.clear();
                students.add("Select Student");
                students.add("Select All");
                getData(grade);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(TeacherUpload.this)
                        .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent intent  = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("*/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }
                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mSubject = subject.getText().toString().trim();
                String mGrade = textGrade.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_TEACHER_URL
                        , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(TeacherUpload.this, "Image Uploaded", Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TeacherUpload.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("homework", encodedImage);
                        params.put("id_subject", mSubject);
                        params.put("id_fullname", spinerName);
                        params.put("grade", mGrade);

                        return params;

                    }

                };

                MySingleton.getInstance(TeacherUpload.this).addToRequestQueue(stringRequest);
            }
        });
    }

    public void getData(final String grade){


        final String url = URLs.SPINNER_URL+grade;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj  = new JSONObject(response);
                            JSONArray jsonArray= obj.getJSONArray("result");

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject json = jsonArray.getJSONObject(i);
                                students.add(json.getString("fullname"));

                            }
                            spinner.setAdapter(new ArrayAdapter<String>(TeacherUpload.this, android.R.layout.simple_list_item_1, students));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TeacherUpload.this, "Error"+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

        };
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        spinerName = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinerName, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK && data!=null){
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
                imageStore(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void imageStore(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] imageBytes = stream.toByteArray();
        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

}

