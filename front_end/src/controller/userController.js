function userController($scope, $http, env){
     $scope.user = {}
     $http.get(env.API_URL+"/user/detail/"+ env.USER_ID).then(function(respone){
        $scope.user = respone.data
     })
     
}