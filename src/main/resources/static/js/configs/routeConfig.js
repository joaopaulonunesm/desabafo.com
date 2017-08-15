angular.module("desabafoApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/timeline.html",
	});
	
	$routeProvider.when("/ultimos/assuntos", {
		templateUrl: "views/ultimosAssuntos.html",
	});
	
	$routeProvider.when("/top/assuntos", {
		templateUrl: "views/topAssuntos.html",
	});
	
	$routeProvider.when("/top/desabafos", {
		templateUrl: "views/topDesabafos.html",
	});
	
	$routeProvider.when("/assunto/:assunto", {
		templateUrl: "views/assunto.html",
		controller: "umAssuntoCtrl"
	});
	
	$routeProvider.when("/publicacao/:id", {
		templateUrl: "views/publicacao.html",
		controller: "umaPublicacaoCtrl"
	});
	
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});