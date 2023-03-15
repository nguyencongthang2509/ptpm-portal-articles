window.detailArticleCtrl = function (
  $scope,
  $http,
  $rootScope,
  $routeParams,
  ArticleService,
  CategoryService
) {

  ArticleService.fetchArticles().then(function () {
    $scope.article = ArticleService.getArticle().filter((ar) => {
      return (ar.id = $routeParams.id);
    })[0];
  });
  ArticleService.fetchComments()
};

