window.onload = function() {
	var b=localStorage.getItem('n');
	var input = document.getElementById("incle");
	input.setAttribute("w3-include-html", "menu"+b+".html");
	w3.includeHTML();
}


function linkmenu(a){
   var b=a+1;
   var input = document.getElementById("incle");
   input.setAttribute("w3-include-html", "menu"+b+".html");
   w3.includeHTML();
}

function popup(){
	open("../booking/book.html","small", "width=500,height=500,scrollbars=yes,menubar=no");
}


