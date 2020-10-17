<?php

      require_once 'connect.php';
      $result = array();
         if ($_SERVER['REQUEST_METHOD']=='POST') {
     
      $messages = $_POST['text_message'];      
      $nameTeacher= $_POST['id_name_teacher'];
      $childName= $_POST['id_fullname'];
      $grade = $_POST['grade'];

      $query1 = "SELECT id FROM teacher_table WHERE fullname ='".$nameTeacher."'";
      $r = mysqli_query($conn,$query1);
      $row1 = mysqli_fetch_assoc($r);
      if(!$row1){
        echo "Failed teacher name";
        exit;
      }   

      $sub = $row1['id'];

   if($childName==='Select All'){

           $sql = "SELECT pt.id
                   FROM parent_table as pt INNER JOIN student_table as st ON pt.childName = st.id
                   WHERE  pt.gradeChild = $grade";

            $res = mysqli_query($conn,$sql);

             $result = array();
          while($row = mysqli_fetch_assoc($res)){

	              $select= "INSERT into parent_messege_table(text_message,id_name_teacher,id_name_parent) 
                          VALUES ('".$messages."','".$sub."','".$row['id']."')";

                $responce = mysqli_query($conn,$select);
          }
         
    }else{

                $sql = "SELECT pt.id
                        FROM parent_table as pt INNER JOIN student_table as st ON pt.childName = st.id
                        WHERE  pt.gradeChild = $grade";

                $res = mysqli_query($conn,$sql);
  
                $row = mysqli_fetch_assoc($res);
      
                $select= "INSERT into parent_messege_table(text_message,id_name_teacher,id_name_parent) 
                VALUES ('".$messages."','".$sub."','".$row['id']."')";
               	$responce = mysqli_query($conn,$select);

    }
      if($responce)
				{			
					echo "Message Uploaded";
				
					mysqli_close($conn);
					
				}else{
				echo "Failed";
        }
        
  }
    
  
    
