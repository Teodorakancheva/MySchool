<?php
require_once 'connect.php';
if ($_SERVER['REQUEST_METHOD'] =='POST'){

    $fullname = $_POST['fullname'];
    $email = $_POST['email'];
    $password = $_POST['password'];
    $childName = $_POST['childName'];
    $gradeChild = $_POST['gradeChild'];

    $password = md5($password);

    $query= "SELECT * FROM parent_table WHERE email='$email'";
    
    $res= mysqli_query($conn, $query);
 
    if(mysqli_num_rows($res) > 0){  
        $result["success"] = "2";
        $result["message"] = "Email already exist!";
        echo json_encode($result);
 
      
    }else{

        $sql = "SELECT id FROM student_table WHERE fullname='".$childName."'";
        $res = mysqli_query($conn,$sql);
    
        $result = array();
 
        while($row = mysqli_fetch_array($res)){
          array_push($result,array(
              $result[0]=$row["id"]
          ));
       }
         
   
        $sql = "INSERT INTO parent_table (fullname, email, password, childName, gradeChild) 
                VALUES ('$fullname', '$email', '$password','$result[0]', '$gradeChild')";
          
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