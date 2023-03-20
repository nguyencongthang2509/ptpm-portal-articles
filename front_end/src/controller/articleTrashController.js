window.articleTrashCtrl = function (
  $scope,
  $http,
  $rootScope,
  ArticleTrashService
) {

  ArticleTrashService.fetchTrashes().then(function () {
    $scope.listArticleTrash = ArticleTrashService.getTrash();
    console.log($scope.listArticleTrash);
  });
};
