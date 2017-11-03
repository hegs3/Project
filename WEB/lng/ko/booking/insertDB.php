<?php 

	$name= $_POST['name'];
	$phone= $_POST['pnum'];
	$num= $_POST['num'];
	$date= $_POST['date'];
	$message= $_POST['msg'];

	//데이터 베이스서버(MySQL서버)에 연결..
	$conn= mysql_connect("localhost","hegs3", "1q2w3e4r");

	//원하는 DB파일 선택..
	mysql_select_db("hegs3", $conn);

	//MYSQL에서 한글 깨짐 방지..
	mysql_query("set names utf8");

	//훤하는 쿼리문 작성 및 요청..
	$query="insert into book(name, pnum, num, date, msg) values( '$name' , '$phone' , '$num' , '$date' , '$message' )";

	//작성된 쿼리문으로 요청..
	$result= mysql_query($query);

	if($result){
		echo "<script>alert(\"sucsses\");self.close();</script>";
	}else{
		echo "<script>alert(\"fail\");self.close();</script>";
	}


	//데이터 베이스 닫기

	mysql_close($conn);

 ?>