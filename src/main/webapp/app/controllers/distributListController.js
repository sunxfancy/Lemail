/**
 * Created by sxf on 15-7-4.
 */

LeMailModule.controller('distributListController', ['$scope','$http',function($scope, $http){
    $scope.sum_mail = 20;
    $scope.messages = [
        {
            id : 1,
            label : "李乐乐已退回了您的邮件",
            content : "这份邮件也不归我管,你丫的没事给我瞎发啥?(⊙o⊙)？"
        },
        {
            id : 2,
            label : "马连韬将您分发的邮件转交给了孙笑凡",
            content : "抱歉,今天我不想处理"
        }
    ];
    $scope.mail_list = [
        {
            id : 1,
            subject: "乐乐乐一乐",
            state: 0,
            date: "2015-07-04 19:00:00",
            attachment: "这是附件",
            from: "sunxfancy@gmail.com",
            review: 0,
            tag: "战书",
            belong_user_id : 0
        }
    ];
    $scope.onPageLoad = function () {
        $http({
            url: '/api/dispatcher/getall',
            method: 'GET',
            params: { page : 1 }
        }).success(function(response, status, headers, config){
            console.log(response);
            if (response.status == 0){
                //$scope.mail_list = response.data;
            }else{
                alert(response.message);
            }
        }).error(function(response, status, headers, config){
            console.log(response);
        });
    };

    $scope.distribute = function(){
        $scope.$emit('changeMainContent', '/template/distribute/distribute.html');
    }

    $scope.inform = function($event){
        alert("大圣，快去实现distributeController的inform方法");
        $event.stopPropagation();
    }
}]);