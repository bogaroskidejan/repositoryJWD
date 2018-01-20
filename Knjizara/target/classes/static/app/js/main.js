var knjizara = angular.module("KnjizaraApp", ['ngRoute']);

knjizara.controller("knjigeCtrl", function($scope, $http, $location){
	
	var baseUrl = "/api/knjige";
	var baseUrlIzdavaci = "/api/izdavaci";
	
	$scope.knjige = [];
	$scope.izdavaci = [];
	$scope.stranica = true;
	
	$scope.page = 0;
	$scope.totalPages = 0;
	
	$scope.newKnjiga = {};
	$scope.newKnjiga.naziv =  "";
	$scope.newKnjiga.pisac = "";
	$scope.newKnjiga.ISBN = "";
	$scope.newKnjiga.kolicina = "";
	$scope.newKnjiga.cena = "";
	$scope.newKnjiga.idIzdavaca = "";
	
	$scope.searchedKnjiga = {};
	$scope.searchedKnjiga.naziv = "";
	$scope.searchedKnjiga.pisac = "";
	$scope.searchedKnjiga.gornjaKol = "";
	
	var getKnjige = function(){
		
		var config = {params:{}};
		
		if($scope.searchedKnjiga.naziv != ""){
			config.params.naziv = $scope.searchedKnjiga.naziv;
		}
		if($scope.searchedKnjiga.pisac != ""){
			config.params.pisac = $scope.searchedKnjiga.pisac;
		}
		if($scope.searchedKnjiga.gornjaKol != ""){
			config.params.gornjaKol = $scope.searchedKnjiga.gornjaKol;
		}
		
		config.params.page = $scope.page;
		
		$http.get(baseUrl, config).then(
			function success(data){
				$scope.knjige = data.data;
				$scope.totalPages = data.headers("totalPages");
			},
			function greska(data){
				console.log(data);
				alert("Get knjige went wrong!")
			}
		);		
	}
	getKnjige();
	
	var getIzdavaci = function() {
		$http.get(baseUrlIzdavaci).then(
			function success(data) {
				$scope.izdavaci = data.data;
			}, 
			function error(data){
				alert("Get izdavaci went wrong!")
			}
		);
	}
	getIzdavaci();
	
	$scope.addKnjiga = function() {
		$http.post(baseUrl, $scope.newKnjiga).then(
				function success(data){
					getKnjige();
					$scope.newKnjiga = {};
				}, 
				function error(data){
					alert("Add knjiga went wrong!")
				}
		);
	}
	
	$scope.deleteKnjiga = function(id) {
		var promise = $http.delete(baseUrl + "/" + id);
		promise.then(
				function success(data) {
					getKnjige(); 
				}, 
				function error(data) {
					alert("Delete knjiga went wrong!")
				}
		);
	}
	
	$scope.editKnjiga = function(id){
		$location.path("/knjige/edit/" + id);
	}
	
	$scope.go = function(par){
		$scope.page = $scope.page + par;
		getKnjige();
	}
	
	$scope.search = function(){
		getKnjige();
	}
	
	$scope.promeniStranicu = function() {
		$scope.stranica = !$scope.stranica;
	}
	
	$scope.kupi = function(id){	
    	$http.post(baseUrl + "/" + id + "/kupovina").then(
    		function success(data){
    			alert("Knjiga je uspesno kupljena.");
    			getKnjige();
    		},
    		function error(data){
    			alert("Nije uspela kupovina knjige.")
    		}
    	)
    }
	
});

knjizara.controller("editKnjigaCtrl", function($scope, $http, $routeParams, $location){

	var baseUrl = "/api/knjige/";
	var baseUrlIzdavaci = "/api/izdavaci";
	
	$scope.oldKnjiga = {};
	$scope.oldKnjiga.naziv = "";
	$scope.oldKnjiga.pisac = "";
	$scope.oldKnjiga.cena = "";
	$scope.oldKnjiga.kolicina = "";
	$scope.oldKnjiga.idIzdavaca = "";
	
	$scope.izdavaci = [];
	
	var getKnjiga = function(){
		$http.get(baseUrl + "/" + $routeParams.id).
			then(
				function success(data){
					$scope.oldKnjiga = data.data;
					console.log($scope.oldKnjiga);
				},
				function error(data){
				}
			);	
	}
	getKnjiga();
	
	var getIzdavaci = function() {
		$http.get(baseUrlIzdavaci).then(
			function success(data) {
				$scope.izdavaci = data.data;
			}, 
			function error(data){
				alert("Get izdavaci went wrong!")
			}
		);
	}
	getIzdavaci();
	
	$scope.edit = function(){
		$http.put(baseUrl + "/" + $scope.oldKnjiga.id, $scope.oldKnjiga)
			.then(
				function uspeh(data){
					$location.path("/knjige");
				},
				function error(data){
					alert("Something went wrong!");
				}
			);
	}
	
});

knjizara.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/', {
			templateUrl : '/app/html/partial/home.html'
		})
		.when('/knjige', {
			templateUrl : '/app/html/partial/knjige.html'
		})
		.when('/knjige/edit/:id', {
			templateUrl : '/app/html/partial/edit_knjiga.html'
		})
		.otherwise({
			redirectTo: '/'
		});
}]);