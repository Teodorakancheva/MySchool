<?php
require_once "connect.php";

$name = $_POST['fullname'];

$query = "SELECT tmt.text_message 
        FROM parent_messege_table as tmt 
        INNER JOIN teacher_table as tt ON tmt.id_name_teacher = tt.id 
        INNER JOIN parent_table as pt ON tmt.id_name_parent = pt.id
        INNER JOIN student_table as st ON pt.childName = st.id
        WHERE st.fullname ='".$name."'" ;

$res= mysqli_query($conn, $query);
$result = array();
$result['msg'] = array();

if ( mysqli_num_rows($res)) {
    
    $row = mysqli_fetch_assoc($res);
  
        $index['text_message'] = $row['text_message'];
    

        array_push($result['msg'], $index);

        $result['success'] = "1";
        $result['message'] = "success";
        echo json_encode($result);
    
        mysqli_close($conn);

}else {

    $result['success'] = "0";
    $result['message'] = "error";
    echo json_encode($result);

    mysqli_close($conn);

}

