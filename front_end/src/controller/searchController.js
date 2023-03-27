window.searchCtrl = function ($scope, $http, $rootScope, $routeParams,ArticleService) {
  $scope.listArticle = [];
  

  $scope.findArticleRequest = {
    albumId: "",
    categoryId:"",
    title: $routeParams.key,
    hashtag: $routeParams.key,
    category: $routeParams.key,
  };

  ArticleService.fetchFindByArticle($scope.findArticleRequest).then(function (respone) {
    $scope.listArticle = ArticleService.getFindByArticle();
    // console.log($scope.listArticle);
  });
};
