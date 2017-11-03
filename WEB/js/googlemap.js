function initMap() {
var map = new google.maps.Map(document.getElementById('mainmap'), {
          zoom: 16,
          center: new google.maps.LatLng(37.55126605766366, 126.98818534612656),

        });
       var marker = new google.maps.Marker({
            position: new google.maps.LatLng(37.55126605766366, 126.98818534612656),
            icon: '../../img/map/a.png',
            map: map
          });
       marker.addListener( 'click', function(){
    			open('http://www.ngrill.co.kr/','_blank');
    		});
}
