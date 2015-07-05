/**
 * Created by vvliebe on 15-7-5.
 */
LeMailModule.controller('distributeListController',  ['$scope','$http',function($scope, $http){
    $scope.mail = {
        id : 1,
        subject: "唐山铁魂有限公司：手机模型材料不足需延迟提交",
        state: 0,
        date: "2015-07-05 12:00:00",
        attachment: "这是附件",
        from: "lilelr@163.com",
        review: 0,
        tag: "延期",
        content : "小熊把您的邮件转交给徐宇楠",
        belong : {
            id : 1,
            name : "侠客"
        },
        readers : []
    };

    $scope.handler = null;
    $scope.users = [
        {id: 1, name:"侠客"},
        {id: 2, name:"熊"}
    ];
    $scope.onPageLoad = function(){

    }
}]);