var app = angular.module('myApp');
app.service("tableService", function($http, $q) {
	return({
		getDetails: getDetails,
		updateOrder: updateOrder
	});
    function getDetails($scope){
    	$http({
	 		url: 'getDetails',
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
		}).success(function(data, status, headers, config) {
			$scope.users = data.users;
			if(data.orders.length === 0){
				$scope.users.forEach(function(item, i){
			        var obj = item;
			        obj.isShare = 'share';
			        obj.item = 'veg';
			        obj.amount = 0;
			        obj.needFood = false;
			        $scope.orders.push(obj);
			    });
			}else{
				$scope.orders = data.orders;
			}
		    $scope.updateAmount();
		}).error(function(data, status, headers, config) {
			console.log('Failure');
			$scope.unmask();
		});
    }
    function updateOrder($scope){
    	var data = JSON.stringify($scope.orders);
    	$http({
	 		url: 'update',
	 		data: data,
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
		}).success(function(data, status, headers, config) {
			console.log('success');		
		}).error(function(data, status, headers, config) {
			console.log('Failure');
			$scope.unmask();
		});
    }
});