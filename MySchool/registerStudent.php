<?php

require_once 'connect.php';
if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $grade = $_POST['grade'];

    $password = md5($password);

    $query= "SELECT * FROM student_table WHERE email='$email'";
	        $res= mysqli_query($conn, $query);
		 
	        if(mysqli_num_rows($res) > 0){  
                $result["success"] = "2";
                $result["message"] = "Email already exist!";
                echo json_encode($result);
              
            }else{   
                      $sql = "INSERT INTO student_table (fullname, email, password,grade) 
                              VALUES ('$fullname', '$email', '$password', '$grade')";

                        if ( mysqli_query($conn, $sql) ) {
                            $result["success"] = "1";
                            $result["message"] = "success";

                             echo json_encode($result);
                             mysqli_close($conn);

                        } else {

                                 $result["success"] = "0";
                                 $result["message"] = "error";

                                 echo json_encode($result);
                                 mysqli_close($conn);
                                } 
                }
}

?>