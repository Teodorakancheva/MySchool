<?php
      require_once 'connect.php';

    
		if(isset($_POST['homework']))
		{
		
		$target_dir = "StudentUpload/";
		$image = $_POST['homework'];
		$fullname = $_POST['id_fullname'];
		$grade = $_POST['grade'];
		$id_subject = $_POST['id_subject'];
		$key = $_POST['key_homework'];
		
		$imageStore = rand()."_".time().".jpg";
	    $target_dir = $target_dir."/".$imageStore;
	    $actualPath = "http://".$_SERVER['SERVER_NAME']."/MySchool/StudentUpload/$imageStore";
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

	   $sql = "SELECT id FROM student_table WHERE fullname ='".$fullname."' LIMIT 1";
	   $res = mysqli_query($conn,$sql);

	  $row = mysqli_fetch_assoc($res);
	 
	   $select= "INSERT into homework_completed_table(id_fullname,grade,homework,id_subject,key_homework) 
				 VALUES ('".$row['id']."','".$grade."','".$imageStore."','".$sub."','".$key."')";

	  $responce = mysqli_query($conn,$select);
		
		if($responce)
				{
								
					echo "Image Uploaded";
					mysqli_close($conn);
					
				}
		else{
				echo "Failed";
				}
		}