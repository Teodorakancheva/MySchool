package com.example.myschool;

public class URLs {

    public static final String BASE_URL = "http://192.168.0.167";

    public static String URL_REGISTER_PARENT = BASE_URL + "/MySchool/registerParent.php";
    public static String URL_REGISTER_STUDENT = BASE_URL + "/MySchool/registerStudent.php";
    public static String URL_REGISTER_TEACHER = BASE_URL + "/MySchool/registerTeacher.php";

    public static String URL_LOGIN_PARENT = BASE_URL +"/MySchool/loginParent.php";
    public static String URL_LOGIN_STUDENT = BASE_URL +"/MySchool/loginStudent.php";
    public static String URL_LOGIN_TEACHER = BASE_URL +"/MySchool/loginTeacher.php";

    public static final String UPLOAD_STUD_URL = BASE_URL +"/MySchool/uploadStudent.php";
    public static final String UPLOAD_TEACHER_URL = BASE_URL +"/MySchool/uploadTeacher.php";

    public static final String DOWNLOAD_STUD_URL = BASE_URL +"/MySchool/downloadStud.php";
    public static final String DOWNLOAD_TEACHER = BASE_URL +"/MySchool/teacherDownload.php";

    public static final String SPINNER_URL = BASE_URL +"/MySchool/spinerFetch.php?grade=";
    public static final String SPINNER_PARENT_URL = BASE_URL +"/MySchool/spinerFetchTeacher.php?grade=";

    public static final String SEND_MESSEGE_TEACHER = BASE_URL +"/MySchool/teacherSendMessege.php";
    public static final String GET_MESSEGE_TEACHER = BASE_URL +"/MySchool/teacherGetMessege.php";
    public static final String SEND_MESSEGE_PARENT = BASE_URL +"/MySchool/parentSendMessege.php";
    public static final String GET_MESSEGE_PARENT = BASE_URL +"/MySchool/parentGetMessege.php";

    public static final String SEND_MARKS = BASE_URL +"/MySchool/marks.php";

    public static final String VIEW_STATE = BASE_URL +"/MySchool/viewState.php";
}
