
function scrollWin(a) {
	switch(a){
	case 0:  return window.scrollTo(0, document.getElementById("toweris").offsetTop-60);	
	case 1: return window.scrollTo(0, document.getElementById("foodis").offsetTop-60);
	case 2: return window.scrollTo(0, document.getElementById("localis").offsetTop-60);
	default: return window.scrollTo(0, document.getElementById("toweris").offsetTop-60);	
	}
}

function linkmenu(a) {
	var n=a+1;
	localStorage.setItem('n', n);
	location.href="http://hegs3.dothome.co.kr/lng/ko/menu/menu.html";
	return false;
}

function popup(){
	open("booking/book.html","small", "width=500,height=500,scrollbars=yes,menubar=no");
}
