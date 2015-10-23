var app = angular.module('myApp');
app.service("tableService", function($http, $q) {
	return({
		getDetails: getDetails
	});
    function getDetails($scope){
    	$http({
	 		url: 'getDetails',
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
		}).success(function(data, status, headers, config) {
			$scope.users = data;
		}).error(function(data, status, headers, config) {
				console.log('Failure');
				$scope.unmask();
		});
    }
});