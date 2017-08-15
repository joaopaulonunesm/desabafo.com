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
	
	$scope.curtirComentario = function(comentario) {	
		
		$http({
			method : 'PUT',
			url : config.baseUrl + '/publicacoes/comentario/' + comentario.id + '/curtir',
		}).then(function(response) {
			
			comentario.qntCurtidas++;
			
		}, function(response) {
			
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
});