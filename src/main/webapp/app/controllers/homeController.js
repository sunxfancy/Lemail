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
        // 更新界面
        console.log(url);
        $scope.changeMainContent(url);
    };

    $scope.changeMainContent = function(url){
        $scope.content_url = url;
    }
}]);