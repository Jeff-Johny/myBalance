<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="<c:url value="/resources/styles/pure-min.css" />" type="text/css" media="screen" />
		<link rel="stylesheet" href="<c:url value="/resources/styles/myStyle.css" />" type="text/css" media="screen" />
		<script type="text/javascript" src="<c:url value="/resources/lib/resources/angular.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/items/app.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/items/controllers/tableController.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/items/services/tableService.js" />"></script>
	</head>
	<body ng-app="myApp" ng-controller="tableController" ng-init="init()">
		<div class="pure-g">
			<div class="pure-u-5-5">
				{{date}}
			</div>
			<div class="pure-u-4-5">
				<table class="pure-table">
					<tr>
					   <th>Need Food</th>
					   <th>Name</th>
					   <th>Meal</th>
					   <th>Special</th>
					   <th>Your share</th>
					   <th>You paid</th>
					</tr>
					<tr ng-repeat="order in orders">
						<td>
							<input type="checkbox" ng-model="order.needFood" ng-click="updateAmount()">
						</td>
						<td>{{order.name}}</td>
						<td>
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.isShare" value="share" ng-change="updateAmount()"/> Share
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.isShare" value="full" ng-change="updateAmount()"/> Full
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.isShare" value="nothing" ng-change="updateAmount()"/> Nothing
						</td>
						<td>
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.item" value="veg" ng-change="updateAmount()"/> Veg
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.item" value="chicken" ng-change="updateAmount()"/> Chicken
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.item" value="beef" ng-change="updateAmount()"/> Beef
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.item" value="fish" ng-change="updateAmount()"/> Fish
						</td>
						<td>
							{{order.amount}}
						</td>
						<td>
							<input ng-disabled="!order.needFood" type="number" ng-model="order.paid" />
						</td>
					</tr>
				</table>
				<br>
				<button class="button-xlarge pure-button" ng-click="updateOrder()">Update Order</button>
			</div>
			<div class="pure-u-1-5">
				<h1>Lunch eaters:  {{totalLunchers}}</h1>
				<br>		
				<h1>Meals:  {{totalMeals}}</h1>
				<br>
				<h1>Beef :  {{totalBeef}}</h1>
				<br>
				<h1>Chicken:  {{totalChicken}}</h1>
				<br>
				<h1>Fish:  {{totalFish}}</h1>
			</div>
		</div>
	</body>
</html>