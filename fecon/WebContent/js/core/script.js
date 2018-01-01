///////////////////////////////////////////////////////////////////////////////////////////////
// Configuracion principal de la aplicacion.                                                 //
//    - Configuracion de desarrollo y produccion.                                            //
//    - Configuracion de temas.                                                              //
///////////////////////////////////////////////////////////////////////////////////////////////
// Version   | F. Fin Desa | F. Install | Comentarios de version                             //
// v.0.00.00 |  31/01/2017 | xx/xx/xxxx | Primera version del SW                             //
///////////////////////////////////////////////////////////////////////////////////////////////

//Configuracion
var app = angular.module('app', ['ngRoute','ngMaterial']);
//URL Desarrollo
//var targetHost = 'http://localhost:8080/fecon/';
//URL Produccion (tengo que conseguir que funcione ./ y entonces valdría para prod y desa, pero para ello necesito que
// en la URL de openshift no aparezca la aplicación
//var targetHost = 'http://fecon-luisda1983.193b.starter-ca-central-1.openshiftapps.com/';
//var targetHost = 'http://fecon-luisda1983.rhcloud.com/';
var targetHost = 'http://fecon-luisda1983.1d35.starter-us-east-1.openshiftapps.com/fecon';
//Configuracion de tema primario y secundario
app.config(function($mdThemingProvider) {
	$mdThemingProvider
	   .theme('default')
	   .primaryPalette('indigo')
	   .accentPalette('orange')
	   .warnPalette('blue-grey');
});
