<?php
include "connect.php";
$page = $_POST['page'];
$total = 5;
$pos =($page-1)*$total; 
$loai =$_POST['loai'];
$query = 'SELECT * FROM `sanphammoi` WHERE `loai`='.$loai.' LIMIT '.$pos.','.$total.' ';
$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
	$result[] = ($row);
	// code...
}
if (!empty($result)) {
	$arr = [
		'success' => true,
		'message' => "Thành công",
		'result'  => $result	
	];
}else{
	$arr = [
		'success' => false,
		'message' => " Không thành công",
		'result'  => $result	
	];
}
print_r(json_encode($arr));

?> 