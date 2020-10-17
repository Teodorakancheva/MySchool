package com.example.myschool;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.myschool.teacher.TeacherHome;

import java.util.HashMap;

public class SessionManger {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String FULLNAME = "FULLNAME";
    public static final String EMAIL = "EMAIL";

    public SessionManger(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("PREF_NAME",PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String email, String password){
        editor.putBoolean(LOGIN,true);
        editor.putString(FULLNAME,email);
        editor.putString(EMAIL,password);
        editor.apply();
    }
    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checkLogin(){
        if(!this.isLogin()){
            Intent i = new Intent(context,LoginActivity.class);
            context.startActivity(i);
            ((TeacherHome)context).finish();
        }
    }

    public HashMap<String, String> getUsersDetail() {
        HashMap<String, String> users = new HashMap<>();
        users.put(FULLNAME,sharedPreferences.getString(FULLNAME,null));
        users.put(EMAIL,sharedPreferences.getString(EMAIL,null));

        return users;
    }
    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context,LoginActivity.class);
        context.startActivity(i);
        ((TeacherHome)context).finish();
    }

}
