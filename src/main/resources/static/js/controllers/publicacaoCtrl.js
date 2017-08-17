angular.module("desabafoApp").controller("publicacaoCtrl", function($scope, $http, config) {

	$scope.publicacoes = [];
	
	$scope.publicacoesPorCurtidas = [];
	
	$scope.publicacoesPorAssunto = [];
	
	$scope.assuntosMaisFalados = [];
	
	$scope.assuntosMaisRecentes = [];
	
	$scope.comentarios = [];

	$scope.publicacao = {};
	
	$scope.assuntoSelecionado = {};
	
	$scope.publicar = function() {
		
		$scope.publicacao.comentarios = [];
		
		$http({
			method : 'POST',
			url : config.baseUrl + '/publicacoes',
			data: $scope.publicacao
		}).then(function(response) {
			
			$scope.publicacoes.unshift(response.data);
			
			$scope.publicacao.assunto = null;
			$scope.publicacao.texto = null;
			
		}, function(response) {
			
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
	$scope.curtirPublicacao = function(publicacao) {	
		
		$http({
			method : 'PUT',
			url : config.baseUrl + '/publicacoes/curtir/'+ publicacao.id,
		}).then(function(response) {
			
			publicacao.qntCurtidas = response.data.qntCurtidas;
			
		}, function(response) {
			
			console.log(response.data);
			console.log(response.status);
			
		});
	};
	
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
	
	$scope.getPublicacoesOrdenadoPorCurtida = function() {
		
		$http({
			method : 'GET',
			url : config.baseUrl + '/publicacoes/curtidas',
		}).then(function(response) {
			
			$scope.publicacoesPorCurtidas = response.data;
			
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
		
	};
	
	$scope.getPublicacoesOrdenadoPorCurtida();
	
	$scope.getAssuntosMaisFalados = function() {
		
		$http({
			method : 'GET',
			url : config.baseUrl + '/assuntos',
		}).then(function(response) {
			
			$scope.assuntosMaisFalados = response.data;
			
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
		
	};
	
	$scope.getAssuntosMaisFalados();
	
	$scope.getAssuntosMaisRecentes = function() {
		
		$http({
			method : 'GET',
			url : config.baseUrl + '/assuntos/recentes',
		}).then(function(response) {
			
			$scope.assuntosMaisRecentes = [];
			
			angular.forEach(response.data, function(element) {
				
				var nome = {"nome": element};
				
				$scope.assuntosMaisRecentes.push(nome);
				
			});
			
		}, function(response) {
			console.log(response.data);
			console.log(response.status);
		});
		
	};
	
	$scope.getAssuntosMaisRecentes();
	
	//rotinas
	setInterval($scope.getPublicacoes, 100000);
	setInterval($scope.getPublicacoesOrdenadoPorCurtida, 100000);
	setInterval($scope.getAssuntosMaisFalados, 100000);
	setInterval($scope.getAssuntosMaisRecentes, 100000);

});
