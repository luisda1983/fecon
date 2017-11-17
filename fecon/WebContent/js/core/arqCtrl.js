//CORE: en desarrollo
app.controller('appCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {
	//srv.logOut(); //Sustituyendo logOut por inicApp
	srv.inicApp();
});

app.controller('homeController', function($rootScope, $scope, $http, $routeParams, $q, srv) {
//	if ($rootScope.idSesion === undefined || $rootScope.idSesion === 0) {
//		srv.logOut();
//	} else {
		srv.home();
//	}
});

//CORE: en desarrollo: encapsular la función en SRV
app.controller('secCtrl', function($rootScope, $scope, $http, $routeParams, $q, srv) {
	if ($rootScope.esta.sesi === undefined || $rootScope.esta.sesi === 0) {
		srv.logOut();
	} 
});