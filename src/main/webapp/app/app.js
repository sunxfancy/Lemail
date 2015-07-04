/**
 * Created by vvliebe on 6/29/15.
 */

var LeMailModule = angular.module('LeMailModule', ['ngRoute']);


LeMailModule.config(['$routeProvider', function($routeProvider){
    $routeProvider.when('/login',{
        templateUrl: '/template/login.html'
    }).otherwise({
        templateUrl: '/template/home.html'
    });
}]);

LeMailModule.controller('LeMailController',['$scope', '$http', '$location', function($scope, $http, $location){
    $scope.title = "登陆";

    $scope.user = {
        id: 0,
        name: '',
        username: '',
        roles:{
            dispatcher: 0,
            reviewer: 0,
            handler: 0,
            manager: 0
        },
        checker: null
    };

    $scope.$on('login', function(event,data){
        $scope.user = data;
        $scope.title = "欢迎使用Lemail";
    });

    $scope.signout = function(){
        $http({
            url: '/api/user/logout',
            method: 'POST'
        }).success(function(response, status, headers, config){
            console.log(response);
            if(response.status == 0){
                $location.path("/login");
                $scope.title = "登陆";
            }
        }).error(function(response, status, headers, config){

        });
    };

    $scope.load = function(){
        $http({
            url: '/api/user/getuser',
            method: 'POST'
        }).success(function(response, status, headers, config){
            console.log(response);
            if (response.status == 0){
                $scope.user = response.data;
                $scope.title = "欢迎使用Lemail";
                $templateCache.removeAll();
            }else if(response.status == 401){
                $location.path("/login");
                $scope.title = "登陆";
            }
        }).error(function(response, status, headers, config){

        });
    }

}]);


