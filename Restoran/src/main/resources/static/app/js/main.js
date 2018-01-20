var restoran = angular.module("RestoranApp", ['ngRoute']);

restoran.controller("jelaCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/jela";
	var baseUrlVrsteJela = "/api/vrsteJela";
	
	$scope.jela = [];
	$scope.vrsteJela = [];
	
	$scope.newJelo = {};
	$scope.newJelo.naziv =  "";
	$scope.newJelo.cena = "";
	$scope.newJelo.idV = "";
	
	$scope.searchedJelo = {};
	$scope.searchedJelo.naziv = "";
	$scope.searchedJelo.nazivV = "";
	
	$scope.origJelo = {};
	
	$scope.view = false;
	$scope.switchView = false;
	
	$scope.page = 0;
	$scope.totalPages = 0;
	
	var getJela = function(){
		
		var config = {params:{}};
		
		if($scope.searchedJelo.naziv != ""){
			config.params.naziv = $scope.searchedJelo.naziv;
		}
		if($scope.searchedJelo.nazivV!= ""){
			config.params.nazivV = $scope.searchedJelo.nazivV;
		}
		
		config.params.page = $scope.page;
		
		$http.get(baseUrl, config).then(
			function success(data){
				$scope.jela = data.data;
				$scope.totalPages = data.headers("totalPages");
				$scope.jelo = {};
			},
			function greska(data){
				console.log(data);
			}
		);		
	}
	getJela();
	
	var getVrsteJela = function() {
		$http.get(baseUrlVrsteJela).then(
				function success(data) {
					$scope.vrsteJela = data.data;
				}, 
				function error(data){
					alert("Get vrste jela went wrong!")
				}
		);
	}
	getVrsteJela();
	
	$scope.deleteJelo = function(id) {
		var promise = $http.delete(baseUrl + "/" + id); 
		
		promise.then(
				function success(data) {
					getJela();
				}, 
				function error(data) {
					alert("Delete went wrong!")
				}
		);
	}
	
	$scope.editJelo = function(aid){
		$location.path("/jela/edit/" + aid);
	}
	
	$scope.addJelo = function() {
		$http.post(baseUrl, $scope.newJelo).then(
				function success(data){
					getJela();
					$scope.newJelo =  "";
				}, 
				function error(data){
					alert("Add jelo went wrong!")
				}
		);
	}
	
	$scope.go = function(par){
		$scope.page = $scope.page + par;
		getJela();
	}
	
	$scope.search = function(){
		getJela();
	}
	
	 $scope.editJeloSP = function(id){
			$http.get(baseUrl + "/" + id).
				then(
					function success(data){
						$scope.newJelo = data.data;
						$scope.origJelo = angular.copy($scope.newJelo);
						$scope.switchView = true;
						console.log($scope.newJelo);
					},
					function error(data){
				}
			);	
	}
	
	$scope.edit = function(id){
		$http.put(baseUrl + "/" + id, $scope.newJelo).
			then(
				function uspeh(data){
					$location.path("/jela");
					$scope.switchView = false;
					$scope.newJelo = {};
					getJela();
				},
				function error(data){
					alert("Something went wrong!");
				}
			);
	}
	
	 $scope.revert = function() {
	     $scope.newJelo = angular.copy($scope.origJelo);
	  };
	
});

restoran.controller("editJelaCtrl", function($scope, $http, $routeParams, $location){
	
	var baseUrl = "/api/jela/";
	var baseUrlVrsteJela = "/api/vrsteJela";
	
	var id = $routeParams.aid;
	
	$scope.oldJelo = {};
	$scope.oldJelo.naziv = "";
	$scope.oldJelo.cena = "";
	$scope.oldJelo.nazivV = "";
	$scope.copy = {};
	
	$scope.vrsteJela = [];
	
	var getJelo = function(){
		$http.get(baseUrl + id).
			then(
				function success(data){
					$scope.oldJelo = data.data;
					$scope.copy = angular.copy($scope.oldJelo);
				},
				function error(data){
					alert("Something went wrong!");
				}
			);	
	}
	getJelo();
	
	var getVrsteJela = function() {
		$http.get(baseUrlVrsteJela).then(
				function success(data) {
					$scope.vrsteJela = data.data;
				}, 
				function error(data){
					alert("Get vrste jela went wrong!")
				}
		);
	}
	getVrsteJela();
	
	$scope.edit = function(){
		$http.put(baseUrl + id, $scope.oldJelo).
			then(
				function uspeh(data){
					$location.path("/jela");
				},
				function error(data){
					console.log(data);
					alert("Something went wrong!");
				}
			);
	}
	
	$scope.revert = function () {
    	$scope.oldJelo = angular.copy($scope.copy);
    }
	
});

restoran.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/partial/home.html'
		})
		.when('/jela', {
			templateUrl : '/app/html/partial/jela.html'
		})
		.when('/home', {
			templateUrl : '/app/html/partial/home.html'
		})
		.when('/jela/edit/:aid', {
			templateUrl : '/app/html/partial/edit_jela.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);