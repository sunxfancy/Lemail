/**
 * Created by vvliebe on 15-7-4.
 */
LeMailModule.controller('loginController', ['$scope','$http','$location',function($scope, $http, $location){
    $scope.text = "LEMAIL";
    $scope.userinfo = {
        username: '',
        password:''
    };

    $scope.submitForm = function(isValid){
        $scope.submitted = true;
        if (isValid){
             $http({
                 url: '/api/user/login',
                 method: 'POST',
                 params: $scope.userinfo
             }).success(function(response, status, headers, config){
                 console.log(response);
                 //console.log(status);
                 //console.log(headers);
                 //console.log(config);
                 if (response.status == 0){
                     // login success
                     //$location.path("/home");
                 }else if(response.status == 1000){

                 }else if(response.status == 1001){

                 }
             }).error(function(response, status, headers, config){

             });
        }
    }
}]);