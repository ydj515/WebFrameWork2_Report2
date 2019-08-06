<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/controller.js"/> "></script>

<div class="container-wrapper">
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h2>Cart</h2>
				<p>All the selected products in your shopping cart</p>
			</div>
		</div>

		<section class="container" ng-app="cartApp"> <!-- angular module load -->
		
			<!-- CartController에서 model에 cartId를 붙혔고, 그 값을 가지고 initCartId를 수행 -->
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId}')">
				<a class="btn btn-warning pull-left" ng-click="clearCart()">
					<i class="fa fa-trash"></i> Clear Cart
				</a>
				
				<br/>
				
				<table class="table table-hover">
					<tr>
						<th>Product</th>
						<th>Unit Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
						<th>Action</th>
					</tr>
					
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.product.name}}</td> <!-- $scope에 있는  내용을 가져온다-->
						<td>{{item.product.price}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						<td>
							<a class="btn btn-danger" ng-click="minusQuantity(item.product.id, -1)" ><i class="fa fa-minus"></i>minus</a>
							<a class="btn btn-danger" ng-click="plusQuantity(item.product.id, 1)"><i class="fa fa-plus"></i>plus</a>
							<a class="btn btn-danger" ng-click="removeFromCart(item.product.id)"><i class="fa fa-times"></i>remove</a>
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td></td>
						<td>Grand Total</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>	
					</tr>
				</table>

				<a class="btn btn-info" href="<c:url value="/products" />" class="btn btn-default">Continue Shopping</a>
			</div>
		</section>

	</div>
</div>