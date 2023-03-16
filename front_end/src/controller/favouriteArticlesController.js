window.favouriteArticleCtrl = function (
    $scope,
    $http,
    $rootScope,
    AlbumService
  ) {
    
  
    AlbumService.fetchArticleFavorite().then(function () {
      $scope.listArticleFavorite = AlbumService.getArticleFavorite();
      console.log($scope.listArticleFavorite);
    });

  
  
  
  };