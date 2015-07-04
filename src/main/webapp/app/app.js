/**
 * Created by vvliebe on 6/29/15.
 */

var LeMailModule = angular.module('LeMailModule', ['ngRoute']);

angular.element(document).ready(function(){
    $http({
        url: '/api/user/getuser',
        method: 'POST',
        params: $scope.userinfo
    }).success(function(response, status, headers, config){
        console.log(response);
        //console.log(status);
        //console.log(headers);
        //console.log(config);
        if (response.status == 0){
            // login success
            var $injector = angular.bootstrap(document, ['LeMailModule']);
            var $controller = $injector.get('$controller');
            var LeMailController = $controller('LeMailController');
            LeMailController.user = data;
            $templateCache.removeAll();
        }else if(response.status == 1000){

        }else if(response.status == 1001){

        }
    }).error(function(response, status, headers, config){

    });
});

LeMailModule.config(['$routeProvider', function($routeProvider){
    $routeProvider.when('/login',{
        templateUrl: '/template/login.html'
    }).otherwise({
        templateUrl: '/template/home.html'
    });
}]);

LeMailModule.controller('LeMailController',['$scope', function($scope){
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

}]);


