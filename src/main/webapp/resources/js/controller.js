var cartApp = angular.module('cartApp', []); // [] : cartApp module이 의존하는 module

// 생성자
// scope : model 객체 의존성 주입
// http : service 객체 의존성 주입
cartApp.controller("cartCtrl", function($scope, $http) { // controller 정의. cartCtrl : controller name
														// scope, http는 동적으로 주입
	$scope.initCartId = function(cartId) { // scope에 메소드 등록
		$scope.cartId = cartId;
		$scope.refreshCart();
	};

	// shrot form
	// getCartById
	$scope.refreshCart = function() { // scope에 메소드 등록
		$http.get('/eStore/api/cart/' + $scope.cartId).then(
				function successCallback(response) {
					$scope.cart = response.data;
				}
		);
	};

	// long form
	// clearCart
	$scope.clearCart = function() { // scope에 메소드 등록

		$scope.setCsrfToken(); // http request message header 부분에 넣어줌

		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/' + $scope.cartId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	// addItem
	$scope.addToCart = function(productId) { // scope에 메소드 등록

		$scope.setCsrfToken(); // http request message header 부분에 넣어줌

		$http.put('/eStore/api/cart/add/' + productId).then(
				function successCallback() {
					alert("Product successfully added to the cart!");
				}, function errorCallback() {
					alert("Adding to the cart failed!");
				}
		);
	};

	// removeItem
	$scope.removeFromCart = function(productId) { // scope에 메소드 등록

		$scope.setCsrfToken(); // http request message header 부분에 넣어줌

		$http({
			method : 'DELETE',
			url : '/eStore/api/cart/cartitem/' + productId
		}).then(function successCallback() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			console.log(response.data);
		});
	};

	$scope.calGrandTotal = function() { // scope에 메소드 등록

		var grandTotal = 0;

		for (var i = 0; i < $scope.cart.cartItems.length; i++) {
			grandTotal += $scope.cart.cartItems[i].totalPrice;
		}

		return grandTotal;
	};

	$scope.setCsrfToken = function() { // scope에 메소드 등록

		var csrfToken = $("meta[name='_csrf']").attr("content");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");

		$http.defaults.headers.common[csrfHeader] = csrfToken;
	};
	
	$scope.plusQuantity = function(productId, quantity) { // scope에 메소드 등록
		
		$scope.setCsrfToken();
		
		$http({
			method : 'PUT',
			url : '/eStore/api/cart/edit/' + productId + '/' + quantity
		}).then(function successCallbak() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			alert("plus to the cart failed!");
		});
	};
		
	$scope.minusQuantity = function(productId, quantity) { // scope에 메소드 등록
		
		$scope.setCsrfToken();
		
		$http({
				method : 'PUT',
				url : '/eStore/api/cart/edit/' + productId + '/' + quantity
		}).then(function successCallbak() {
			$scope.refreshCart();
		}, function errorCallback(response) {
			alert("minus to the cart failed!");
//			console.log(response.data);
		});
	};

});
