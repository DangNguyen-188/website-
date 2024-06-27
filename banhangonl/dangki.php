<?php
include "connect.php";
$email  = $_POST['email'];
$username  = $_POST['username'];
$pass  = $_POST['pass'];
$mobile  = $_POST['mobile'];


$query= 'SELECT * FROM `user` WHERE `email`="'.$email.'"';
$data = mysqli_query($conn, $query);
$numrow  = mysqli_num_rows($data);

if ($numrow > 0){
	$arr = [
		'success' => false,
		'message' => "Email đã tồn tại "
		
	];
}else{

$query = 'INSERT INTO `user`(`email`,`username`, `pass`, `mobile`) VALUES ("'.$email.'","'.$username.'","'.$pass.'","'.$mobile.'")';
$data = mysqli_query($conn, $query);
if ($data == true) {
	$arr = [
		'success' => true,
		'message' => "Thành công"
		
	];
	}else{
	$arr = [
		'success' => false,
		'message' => "Không thành công"
		
	];
}
}


print_r(json_encode($arr));

?> 