angular.module("desabafoApp").controller("comentarioCtrl", function($scope, $http, config) {
	
	$scope.comentario = {};
	
	$scope.comentar = function(publicacao) {	
		
		$http({
			method : 'POST',
			url : config.baseUrl + '/publicacoes/comentar/' + publicacao.id,
			data: $scope.comentario
		}).then(function(response) {
			
			publicacao.comentarios.unshift(response.data);
			
			$scope.comentario = null;
			
		}, function(response) {
			
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
});