<?php
      require_once 'connect.php';
  

      $name  = $_POST['fullname'];
      $grade  = $_POST['grade'];

    $sql =("SELECT st.subject, mt.mark, mt.key_homework 
    FROM  marks_table AS mt INNER JOIN subject_table st ON mt.id_subject = st.id
    INNER JOIN parent_table AS pt ON pt.childName = mt.id_student AND pt.gradeChild = mt.grade
    INNER JOIN student_table AS stud ON pt.childName = stud.id AND pt.gradeChild = stud.grade
    WHERE stud.fullname = '$name' AND stud.grade = '$grade'");

    $res = mysqli_query($conn,$sql);
    $result = array();

    while($row = mysqli_fetch_assoc($res)){
      
        $temp[] = $row;
     array_push($result, $row);
    }

     echo json_encode(array("result"=>$result));
     
    mysqli_close($conn);

?>