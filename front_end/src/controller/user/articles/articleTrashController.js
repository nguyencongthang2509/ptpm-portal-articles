window.articleTrashCtrl = function (
  $scope,
  $http,
  $rootScope,
  ArticleTrashService
) {
  $scope.listArticleTrash = []
  ArticleTrashService.fetchTrashes().then(function () {
    $scope.listArticleTrash = ArticleTrashService.getTrash();
  });

  $scope.deleteArticle = function(index, id){
    $http.delete(articleTrashAPI +"/"+id).then(function(respone){
      $scope.listArticleTrash.splice(index,1)
    })
  }

  $scope.restoreArticle =  function(index, id){
    $http.put(articleTrashAPI +"/restore/"+id).then(function(respone){
      $scope.listArticleTrash.splice(index,1)
    })
  }
};
