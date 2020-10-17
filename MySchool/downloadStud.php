<?php 

require_once 'connect.php';

$grade  = $_POST['grade'];
$id_fullname  = $_POST['fullname'];


$sql = ("SELECT stud.fullname, hct.grade, hct.homework, hct.id, st.subject
FROM homework_forstudent_table hct INNER JOIN subject_table st ON hct.id_subject = st.id
INNER JOIN student_table as stud ON stud.id = hct.id_fullname
 WHERE hct.grade = '$grade'  AND stud.fullname = '$id_fullname'");

$res = mysqli_query($conn,$sql);

$products = array(); 

while($row=mysqli_fetch_assoc($res)){
$temp = array();
$temp[] = $row; 


array_push($products, $row);

}
 

echo json_encode($products);
mysqli_close($conn);
