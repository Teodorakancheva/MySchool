<?php
      require_once 'connect.php';
	  $result = array();
		if(isset($_POST['homework']))
		{
		
		$target_dir = "TeacherUpload/";
		$image = $_POST['homework'];
		$fullname = $_POST['id_fullname'];
		$grade = $_POST['grade'];
		$id_subject = $_POST['id_subject'];
		
	$imageStore = rand()."_".time().".jpg";
	$target_dir = $target_dir."/".$imageStore;
    $actualPath = "http://".$_SERVER['SERVER_NAME']."/MySchool/TeacherUpload/$imageStore";
	$imageStore = $actualPath;	
	file_put_contents($target_dir, base64_decode($image));

	$query = "SELECT id FROM subject_table WHERE subject='".$id_subject."'";
        $r = mysqli_query($conn,$query);
    
      
		
	$row = mysqli_fetch_assoc($r);
	if(!$row){
		echo "Failed subject";
		exit;
	}   

	$sub = $row['id'];
	 
 if($fullname==='Select All'){
   $sql = "SELECT id FROM student_table WHERE grade='".$grade."'";
   $res = mysqli_query($conn,$sql);
  
  
   while($row = mysqli_fetch_assoc($res)){
	$select= "INSERT into homework_forstudent_table(id_fullname,grade,homework,id_subject) 
	VALUES ('".$row['id']."','".$grade."','".$imageStore."','".$sub."')";
	$responce = mysqli_query($conn,$select);
   }
	  
	}else{
		   $sql = "SELECT id FROM student_table WHERE fullname ='".$fullname."' LIMIT 1";
		   $res = mysqli_query($conn,$sql);
 
          $row = mysqli_fetch_assoc($res);
		 
		   $select= "INSERT into homework_forstudent_table(id_fullname,grade,homework,id_subject) 
                     VALUES ('".$row['id']."','".$grade."','".$imageStore."','".$sub."')";
    
		  $responce = mysqli_query($conn,$select);
	
        }
		if($responce)
				{			
					echo "Image Uploaded";
					echo json_encode($select);
					mysqli_close($conn);
					
				}
		else{
				echo "Failed";
				}
		}

            
      