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
			$scope.users.forEach(function(item, index){
				var orderItem = data.orders.filter(function(t, i){return t.userId === item.userId});
				if(!orderItem.length){
					var obj = item;
			        obj.isShare = 'share';
			        obj.item = 'veg';
			        obj.amount = 0;
			        obj.needFood = false;
			        obj.paid = 0;
			        $scope.orders.push(obj);
				}else{
					orderItem[0].name = item.name;
					$scope.orders.push(orderItem[0]);
				}
		    });
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