<?php
      require_once 'connect.php';
  if ($_SERVER['REQUEST_METHOD']=='POST') {
     
        $mark = $_POST['mark'];
        $key = $_POST['key_homework'];
		$id_student = $_POST['id_student'];
        $id_subject = $_POST['id_subject'];
        $grade = $_POST['grade'];


        $query = "SELECT id
        FROM subject_table 
        WHERE subject = '".$id_subject."'";
     $r = mysqli_query($conn,$query);

     $row = mysqli_fetch_assoc($r);
     if(!$row){
         echo "Failed subject";
         exit;
     }   
     $sub = $row['id'];
     

     $query1 = "SELECT id FROM student_table WHERE fullname ='".$id_student."'";
     $res = mysqli_query($conn,$query1);

     $row = mysqli_fetch_assoc($res);
     if(!$row){
         echo "Failed name";
         exit;
     }   
     $name = $row['id'];
    


       $query2 = "SELECT key_homework FROM homework_completed_table WHERE key_homework ='".$key."'";
     $result = mysqli_query($conn,$query2);

     $row = mysqli_fetch_assoc($result);
     if(!$row){
         echo "Failed key";
         exit;
     }   
     $keyss = $row['key_homework'];
     $row = mysqli_fetch_assoc($result);


        $sql = "INSERT INTO marks_table(mark, id_subject,id_student,key_homework,grade)
        VALUES($mark, $sub, $name, $keyss,'$grade')";

        $responce = mysqli_query($conn,$sql);

        if ($responce) {
            echo "Mark Uploaded";
                mysqli_close($conn);
        
        }else {
        
            echo "Error";
         
            echo json_encode($rr);
        
            mysqli_close($conn);
        
        }
    }
        
