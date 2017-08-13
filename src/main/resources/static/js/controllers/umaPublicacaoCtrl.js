angular.module("desabafoApp").controller("umaPublicacaoCtrl", function($scope, $http, $routeParams, config) {
	
	$scope.publicacao = {};
	
	$scope.getPublicacao = function(id) {
			
		$http({
			method : 'GET',
			url : config.baseUrl + '/publicacoes/' + id,
		}).then(function(response) {
			
			$scope.publicacao = response.data;
			
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
		
	};
		
	$scope.getPublicacao($routeParams.id);
	
});