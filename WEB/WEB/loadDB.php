<?php 
	$conn= mysql_connect("localhost", "hegs3", "1q2w3e4r");
	mysql_select_db("hegs3", $conn);
	mysql_query("set names utf8");

	$query="select * from board";

	$result= mysql_query($query);

	//총 레코드의 수를 얻어오기
	$rowNum= mysql_num_rows($result);

	//총 레크드 수만큼 반복해서 해당 레코드(행 하나 )의 필득값들을 얻어오기
	for($i=0; $i<$rowNum; $i++){
		//0번부터 차례로 결과셋에서 해당
		//레크드 라인으로 이동.
		mysql_data_seek($result, $i);

		//이동된 해당 레코드를 얻어오기
		$row= mysql_fetch_array($result);

		echo "$row[id] $row[name] $row[msg]<br/>";
	}

	mysql_close($conn);

 ?>