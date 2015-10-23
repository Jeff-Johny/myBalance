var app = angular.module('myApp');
app.service("tableService", function($http, $q) {
	return({
		getDetails: getDetails
	});
    function getDetails($scope){
    	$http({
	 		url: '/getDetails',
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "X-CSRF-Token": AUTH_TOKEN
            }
		}).success(function(data, status, headers, config) {
			console.log('save succes')
			console.log(data)
		}).error(function(data, status, headers, config) {
				console.log('Failure');
				$scope.unmask();
		});
    }
});