var app = angular.module('myApp');
app.controller("tableController", function($scope, 'tableService') {
	
    $scope.users = [];
    $scope.init  = function() {
    	$scope.users = [{name:'J',balance:23},{name:'Q',balance:36}]
    };
    $scope.clear = function() {
    	
    };
    $scope.save  = function() {
    	
    };
});