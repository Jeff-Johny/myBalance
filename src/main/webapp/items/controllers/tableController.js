var app = angular.module('myApp', []);
app.controller('tableController', function($scope, tableService) {
	
    $scope.users = [];
    $scope.init  = function() {
    	$scope.users = [];
        $scope.date = $scope.getCurrentDate();
        $scope.orders = [];
    	tableService.getDetails($scope);
	};
    $scope.getCurrentDate = function(){
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth()+1; //January is 0!

        var yyyy = today.getFullYear();
        if(dd<10){
            dd='0'+dd
        } 
        if(mm<10){
            mm='0'+mm
        } 
        var today = dd+'/'+mm+'/'+yyyy;
        return today;
    }
    $scope.updateAmount = function() {
    	$scope.totalMeals = 0, $scope.totalBeef = 0, $scope.totalChicken = 0, $scope.totalFish = 0, $scope.totalLunchers = 0;
    	var shares = $scope.orders.filter(function(t,i){return t.isShare ==="share" && t.needFood}).length;
        var beefAmt = 40, chickenAmt = 40, fishAmt = 30, mealAmt = 0, specialAmt = 0, sharedMeals = 0;

        switch(shares){
        	case 0: sharedMeals = 0; break;
            case 1: sharedMeals = 1; break;
            case 2: sharedMeals = 1; break;
            case 3: sharedMeals = 2; break;
            case 4: sharedMeals = 3; break;
            default : sharedMeals = shares - 2;
        }
        mealAmt = (sharedMeals * 40) / shares;
        $scope.orders.forEach(function(item, index){
        	if(item.needFood){
        		$scope.totalLunchers++;
        		switch(item.item){
	                case 'beef': specialAmt = beefAmt; $scope.totalBeef++; break;
	                case 'chicken': specialAmt = chickenAmt; $scope.totalChicken++; break;
	                case 'fish': specialAmt = fishAmt; $scope.totalFish++; break;
	                default : specialAmt = 0;
	            }
	            if(item.isShare === "full"){
	            	$scope.totalMeals = $scope.totalMeals + 1;
	                item.amount = Math.round(40 + specialAmt);
	            }else if(item.isShare === "nothing"){
	                item.amount = Math.round(specialAmt);
	            }else{
	                item.amount = Math.round(mealAmt + specialAmt);
	            }
        	}else{
        		item.amount = 0;
        	}
        });
        $scope.totalMeals = $scope.totalMeals + sharedMeals;
    };
    $scope.updateOrder  = function() {
    	tableService.updateOrder($scope);
    };
    
});