/**
 * Created by sxf on 15-7-5.
 */



LeMailModule.controller('managerConfController',
    ['$scope','$http','$location', function($scope, $http, $location) {
        $scope.conf = {
            username : '',
            password : '',
            hostname_smtp : '',
            hostname_imap : ''
        };

        $scope.onPageLoad = function() {
            $http({
                url: '/api/manager/getconf',
                method: 'GET',
                params: { page : 0 }
            }).success(function(response, status, headers, config){
                console.log(response);
                if (response.status == 0){
                    $scope.conf = response.data;
                }else{
                    alert(response.message);
                }
            }).error(function(response, status, headers, config){
                console.log(response);
            });
        };
}]);