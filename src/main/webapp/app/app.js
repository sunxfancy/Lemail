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

LeMailModule.controller('LeMailController',['$scope', function($scope){
    $scope.title = "test title";
    $scope.user = {
        name: '',
        password:''
    };
    $scope.roles = {

    };

}]);


