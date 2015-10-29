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
    	var shares = $scope.orders.filter(function(t,i){return t.isShare ==="share"}).length;
        var beefAmt = 40, chickenAmt = 40, fishAmt = 30, mealAmt = 0, specialAmt = 0;

        switch(shares){
            case 1: $scope.sharedMeals = 1; break;
            case 2: $scope.sharedMeals = 1; break;
            case 3: $scope.sharedMeals = 2; break;
            case 4: $scope.sharedMeals = 3; break;
            default : $scope.sharedMeals = shares - 2;
        }
        mealAmt = ($scope.sharedMeals * 40) / shares;
        $scope.orders.forEach(function(item, index){
            switch(item.item){
                case 'beef': specialAmt = beefAmt; break;
                case 'chicken': specialAmt = chickenAmt; break;
                case 'fish': specialAmt = fishAmt; break;
                default : specialAmt = 0;
            }
            if(item.isShare === "full"){
                item.amount = 40 + specialAmt;
            }else{
                item.amount = mealAmt + specialAmt;
            }
        });
    };
    $scope.updateOrder  = function() {
    	tableService.updateOrder($scope);
    };
    
});