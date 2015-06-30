/**
 * Created by vvliebe on 6/29/15.
 */

var LeMailModule = angular.module('LeMailModule', ['ngRoute']);

LeMailModule.controller('LeMailController',['$scope', function($scope){
    $scope.url = "login.html";
}]);