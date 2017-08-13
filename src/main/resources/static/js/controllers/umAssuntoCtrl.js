angular.module("desabafoApp").controller("umAssuntoCtrl", function($scope, $http, $routeParams, config) {
	
	$scope.publicacoesPorAssunto = [];
	
	$scope.getPublicacoesPorAssunto = function(assunto) {
		
		$http({
			method : 'GET',
			url : config.baseUrl + '/publicacoes/assunto/' + assunto,
		}).then(function(response) {
			
			$scope.publicacoesPorAssunto = response.data;
			
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
		
	};
		
	$scope.getPublicacoesPorAssunto($routeParams.assunto);
	
});