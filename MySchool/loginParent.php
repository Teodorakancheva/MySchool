<?php
require_once 'connect.php';
if ($_SERVER['REQUEST_METHOD']=='POST') {

    $email = $_POST['email'];
    $password = md5($_POST["password"]);
  
        $sql = "SELECT st.fullname, st.grade
         FROM parent_table pt, student_table st
         WHERE pt.email='".$email."'AND pt.childName = st.id AND pt.password = '$password'";
    
    $res = mysqli_query($conn,$sql);
    $result = array();
   
    while($row = mysqli_fetch_assoc($res)){
      
       
     array_push($result, $row);
    }

     echo json_encode(array("login"=>$result));
     
    mysqli_close($conn);
}

?>