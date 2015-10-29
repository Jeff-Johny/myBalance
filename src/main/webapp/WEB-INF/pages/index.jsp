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
			<div class="pure-u-2-3">
				<table class="pure-table">
					<tr ng-repeat="order in orders">
						<td>
							<input type="checkbox" ng-model="order.needFood" >
						</td>
						<td disabled=true>{{order.name}}</td>
						<td>
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.isShare" value="share" ng-change="updateAmount()"/> Share
							<input ng-disabled="!order.needFood" type="radio" ng-model="order.isShare" value="full" ng-change="updateAmount()"/> Full
						</td>
						<td>
							<input ng-disabled="!order.needFood" disabled={{order.needFood}} type="radio" ng-model="order.item" value="veg" ng-change="updateAmount()"/> Veg
							<input ng-disabled="!order.needFood" disabled={{order.needFood}} type="radio" ng-model="order.item" value="chicken" ng-change="updateAmount()"/> Chicken
							<input ng-disabled="!order.needFood" disabled={{order.needFood}} type="radio" ng-model="order.item" value="beef" ng-change="updateAmount()"/> Beef
							<input ng-disabled="!order.needFood" disabled={{order.needFood}} type="radio" ng-model="order.item" value="fish" ng-change="updateAmount()"/> Fish
						</td>
						<td>
							{{order.amount}}
						</td>
					</tr>
				</table>
				<br>
				<button class="button-xlarge pure-button" ng-click="updateOrder()">Update Order</button>
			</div>
			<div class="pure-u-1-3">
				<table class="pure-table">
					<tr ng-repeat="user in users">
						<td>{{user.name}}</td>
						<td>{{user.balance}}</td>
					</tr>
				</table>
			</div>
		</div>
	</body>
</html>