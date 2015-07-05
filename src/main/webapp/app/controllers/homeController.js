/**
 * Created by vvliebe on 15-7-3.
 */
LeMailModule.controller('homeController', ['$scope','$templateCache', function($scope, $templateCache){
    $scope.content_url = "";

    $scope.$on('changeMainContent', function(event,data){
        $scope.content_url = data;
        //$templateCache.removeAll();
    });

    $scope.onload = function(url){
        // 更新界面
        console.log(url);
        $scope.changeMainContent(url);
        $templateCache.removeAll();
    };

    $scope.changeMainContent = function(url){
        $scope.content_url = url;
    }
}]);