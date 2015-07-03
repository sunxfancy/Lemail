/**
 * Created by vvliebe on 15-7-4.
 */
LeMailModule.controller('loginController', ['$scope',function($scope){
    $scope.text = "LEMAIL";
    $scope.userinfo = {
        username: '',
        password:''
    };

    $scope.submitForm = function(isValid){
        $scope.submitted = true;
        if (isValid){

        }
    }
}]);