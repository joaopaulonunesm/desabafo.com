angular.module("desabafoApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/timeline.html",
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