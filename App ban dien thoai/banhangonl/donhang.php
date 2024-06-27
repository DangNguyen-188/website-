<?php
include "connect.php";
$sdt = $_POST['sdt'];
$email =$_POST['email'];
$iduser =$_POST['iduser'];
$tongtien =$_POST['tongtien'];
$diachi =$_POST['diachi'];
$soluong =$_POST['soluong'];
$chitiet=$_POST['chitiet'];



$query = 'INSERT INTO `donhang`( `iduser`, `email`, `diachi`, `sodienthoai`, `soluong`, `tongtien`) VALUES ("'.$iduser.'","'.$diachi.'","'.$sdt.'","'.$email.'","'.$soluong.'","'.$tongtien.'")';
$data = mysqli_query($conn, $query);
	if($data==true){
		$query='SELECT id AS iddonghang FROM `donhang` WHERE `iduser`='.$iduser.' ORDER BY id DESC LIMIT 1';
		$data = mysqli_query($conn, $query);
		while($row = mysqli_fetch_assoc($data)){
			$iddonhang = ($row);
		}
		if (!empty($iddonhang)) {
			// code...
			$chitiet=json_decode($chitiet, true);
			foreach( $chitiet as $key => $value){
				$truyvan = 'INSERT INTO `chitietdonhang`(`iddonhang`, `idsanpham`, `soluong`, `gia`) VALUES ('.$iddonhang["iddonghang"].','.$value["idsp"].','.$value["soluong"].',"'.$value["giasp"].'")';
			
				$data = mysqli_query($conn, $truyvan);
			}
				if ($data==true) {
					$arr = [
						'success' => true,
						'message' => " Thành công"
		
					];
				}else{
					$arr = [
						'success' => false,
						'message' => " Không thành công"
		
					];
				}
				print_r(json_encode($arr));
		}
	}else{
		$arr = [
		'success' => false,
		'message' => " khong thanh cong"
		
		];
		print_r(json_encode($arr));
	}	

?> 