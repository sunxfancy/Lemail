/**
 * Created by vvliebe on 15-7-3.
 */
LeMailModule.controller('homeController', ['$scope', function($scope){
    $scope.content_url = "";

    $scope.$on('changeMainContent', function(event,data){
        console.log(data+"aaa");
        $scope.content_url = data;
    });

    $scope.onload = function(url){
        //alert($scope.$parent.user.default_role_url);
        //$scope.content_url = $scope.$parent.user.default_role_url;
    };

    $scope.changeMainContent = function(url){
        $scope.content_url = url;
    }
}]);