function articleController($scope, $http, env) {
  $scope.articles = {};
  $http.get(env.API_URL + "/article").then(function (respone) {
    $scope.articles = respone.data;
    console.log($scope.articles);
  });
}
