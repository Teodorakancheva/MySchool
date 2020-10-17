<?php

require_once 'connect.php';

    $grade = $_GET['grade'];
   
    $sql =("SELECT id, fullname FROM student_table WHERE grade like '%$grade%'");
    $res = mysqli_query($conn,$sql);
    $result = array();
     
    while($row = mysqli_fetch_array($res)){
    array_push($result,array(
        'fullname'=>$row[1],
         'id'=>$row[0]
    
    ));
    }
     
    echo json_encode(array("result"=>$result));
     
    mysqli_close($conn);
     
    ?>