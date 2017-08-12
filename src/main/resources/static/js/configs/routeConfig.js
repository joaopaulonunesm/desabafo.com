angular.module("desabafoApp").config(function ($routeProvider) {
	
	$routeProvider.when("/", {
		templateUrl: "views/timeline.html",
		controller: "publicacaoCtrl",
	});
	
	$routeProvider.otherwise({redirectTo: "/"});
	
});