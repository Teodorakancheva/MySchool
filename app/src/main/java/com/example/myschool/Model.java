package com.example.myschool;

public class Model {

    private  int id;
    private String  grade,subject ,fullname,img;
    public Model(){};

    public Model(int id, String grade, String subject, String fullname, String img) {
        this.id = id;
        this.grade = grade;
        this.subject = subject;
        this.fullname = fullname;
        this.img = img;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
