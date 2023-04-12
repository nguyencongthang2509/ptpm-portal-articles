window.searchHashtagCtrl = function ($scope, $routeParams, ArticleService) {
  $scope.listArticle = [];
  $scope.findArticleRequest = {
    hashtag: $routeParams.hashtag,
  };

  ArticleService.fetchFindByArticle($scope.findArticleRequest).then(function (
    respone
  ) {
    $scope.listArticle = ArticleService.getFindByArticle();
  });
};
