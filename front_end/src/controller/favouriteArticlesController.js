window.favouriteArticleCtrl = function (
    $scope,
    $http,
    env,
    $rootScope,
    AlbumService
  ) {
    
    $scope.listArticleFavorite ={};

    AlbumService.fetchArticleFavorite().then(function () {
      $scope.listArticleFavorite = AlbumService.getArticleFavorite();
    });

    $scope.unfavoriteArticle = function(key, index,articleId){
      $http.delete(env.API_URL + "/album/unfavorite-article/" + articleId).then(
        function (response) {
          $scope.listArticleFavorite[key].splice(index,1)
          var isEmpty = $scope.listArticleFavorite[key].filter(function(val) {
            return val !== null || val !== '';
          }).length;
          if (isEmpty === 0) {
            delete  $scope.listArticleFavorite[key];
          } 
        }
      );
    }
    
    
    

    
  
  };