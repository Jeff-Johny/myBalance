var app = angular.module('myApp', []);
app.controller('tableController', function($scope, tableService) {
	
    $scope.users = [];
    $scope.init  = function() {
    	$scope.users = [{name:'J',balance:23},
    	                {name:'L',balance:36},
    	                {name:'R',balance:13},
    	                {name:'M',balance:76}
        ]
//    	tableService.getDetails($scope);
	};
    $scope.clear = function() {
    	
    };
    $scope.save  = function() {
    	
    };
    
});