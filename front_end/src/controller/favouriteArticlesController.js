window.favouriteArticleCtrl = function (
    $scope,
    $http,
    env,
    $rootScope,
    TymService
  ) {
    
    $scope.listArticleFavorite ={};

    TymService.fetchArticleFavorite().then(function () {
      $scope.listArticleFavorite = TymService.getArticleFavorite();
    });

    $scope.unfavoriteArticle = function(key, index,idTym){

      $http.delete(env.API_URL + "/tym/unfavorite-article/"+ idTym ).then(
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