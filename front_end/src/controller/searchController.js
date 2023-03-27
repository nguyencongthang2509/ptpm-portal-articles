window.searchCtrl = function ($scope, $http, $rootScope, ArticleService) {
  $scope.listArticle = [];

  ArticleService.fetchFindByArticle().then(function (respone) {
    $scope.listArticle = ArticleService.getFindByArticle();
    console.log($scope.listArticle);
  });
};
