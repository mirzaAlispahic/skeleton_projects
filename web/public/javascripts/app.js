var myApp = angular.module('myApp', ["ngRoute"]);

myApp.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "assets/routes/home.html",
        controller: "mainController"
    }).when("/route", {
        templateUrl: "assets/routes/route1.html",
        controller: "secondController"
    })
}]);

myApp.controller('mainController', ['$scope', '$http', function ($scope, $http) {
    $scope.message = '';
    $scope.buttonClicked = function(){
        var req = {
            method: 'GET',
            url: '/getMessage',
            headers: {
                'Content-Type': 'application/json'
            }
        };


        $http(req).then(response => {
            $scope.message = response.data;
        }, response => {
            console.log(response.data || 'Request failed');
            $scope.message = 'Request failed';
        });
    };
}]);

myApp.controller('secondController', ['$scope', '$http',function ($scope, $http)  {
    $scope.message = '';
    $scope.buttonClicked = function(){
        var req = {
            method: 'GET',
            url: '/getMessageFromRoute',
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http(req).then(response => {
            $scope.message = response.data;
        }, response => {
                console.log(response.data || 'Request failed');
                $scope.message = 'Request failed';
        });
    };
}]);

