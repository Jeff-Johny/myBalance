<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="<c:url value="/resources/styles/myStyle.css" />" type="text/css" media="screen" />
		<script type="text/javascript" src="<c:url value="/resources/lib/resources/angular.min.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/items/app.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/items/controllers/tableController.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/items/services/tableService.js" />"></script>
	</head>
	<body ng-app="myApp">
		<div ng-controller="tableController" ng-init="init()">
			<table>
				<tr ng-repeat="user in users">
					<td>{{user.name}}</td>
					<td>{{user.balance}}</td>
				</tr>
			</table>
		</div>
	</body>
</html>