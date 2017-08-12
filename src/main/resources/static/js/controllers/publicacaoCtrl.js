angular.module("desabafoApp").controller("publicacaoCtrl", function($scope, $http, config) {

	$scope.publicacoes = [];
	
	$scope.publicacao = {};
	
	$scope.comentarios = [];
	
	$scope.getPublicacoes = function() {
		
		$http({
			method : 'GET',
			url : config.baseUrl + '/publicacoes',
		}).then(function(response) {
			
			$scope.publicacoes = response.data;
			
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
		
	};
	
	$scope.getPublicacoes();
	
	$scope.publicar = function() {
		
		$scope.publicacao.comentarios = [];
		
		$http({
			method : 'POST',
			url : config.baseUrl + '/publicacoes',
			data: $scope.publicacao
		}).then(function(response) {
			
			$scope.publicacoes.unshift(response.data);
			
			$scope.publicacao = null;
			
		}, function(response) {
			
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
	

});