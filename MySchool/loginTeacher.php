<?php
require_once 'connect.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {
    
    $email = $_POST['email'];
    $password = md5($_POST["password"]);
    
        $sql = "SELECT * FROM teacher_table WHERE email='$email' AND password = '$password' ";

        $res = mysqli_query($conn,$sql);
        $result = array();
       
        while($row = mysqli_fetch_assoc($res)){
          
            $temp[] = $row;
         array_push($result, $row);
        }
    
         echo json_encode(array("login"=>$result));
         
        mysqli_close($conn);
    
    }
 ?>