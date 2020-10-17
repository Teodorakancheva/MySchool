<?php
      require_once 'connect.php';
      
      $grade  = $_POST['grade'];
      $id_subject  = $_POST['subject'];
    
     
      $sql = ("SELECT stud.fullname, hct.grade, hct.homework, hct.key_homework, st.subject
      FROM homework_completed_table hct INNER JOIN subject_table st ON hct.id_subject = st.id
      INNER JOIN student_table as stud ON stud.id = hct.id_fullname
       WHERE hct.grade like'".$grade."'  AND st.subject like'".$id_subject."'");
    
    $res = mysqli_query($conn,$sql);
    
    $products = array(); 
     
      while($row=mysqli_fetch_assoc($res)){
      $temp = array();
      $temp[] = $row; 
   
    
      array_push($products, $row);
    
    }
      
   
      echo json_encode($products);
      