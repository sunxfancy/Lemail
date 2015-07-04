/**
 * Created by vvliebe on 15-7-3.
 */
LeMailModule.controller('homeController', ['$scope',function($scope){
    $scope.content_url = "/template/distribute/list.html";

    $scope.changeMainContent = function(url){
        $scope.content_url = url;
    }
}]);