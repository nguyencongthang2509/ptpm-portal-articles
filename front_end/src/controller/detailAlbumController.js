window.detailAlbumCtrl = function (
  $scope,
  $routeParams,
  AlbumService,
  ArticleService,
  env,
  $http,
  $colorThief
) {
  $scope.listArticle = [];
  $scope.album = {};
  var image = document.querySelector("#myImage");

  $scope.dominant = $colorThief.getColor(image);
  $scope.palette = $colorThief.getPalette(image);

  $scope.idArticle = "";
  //get detail album
  AlbumService.fetchAlbum($routeParams.id).then(function () {
    $scope.album = AlbumService.getAlbum();
    console.log($scope.album);
  });

  //get bài viết trong album
  AlbumService.fetchArticleByAlbum($routeParams.id).then(function () {
    $scope.listArticle = AlbumService.getArticleByAlbum();
    if ($scope.listArticle != null) {
      $scope.idArticle = $scope.listArticle[0].id;
      console.log($scope.idArticle);
    }
    console.log($scope.listArticle);
  });

  AlbumService.fetchCheckAlbumOfAuthor($routeParams.id).then(function () {
    $scope.author = AlbumService.getCheckAlbumOfAuthor();
  });

  // begin tym article
  $scope.favoriteArticle = function (id, index) {
    $scope.createTymRequest = {
      articlesId: id,
    };
    $http
      .post(env.API_URL + "/tym/favorite-article", $scope.createTymRequest)
      .then(function (respone) {
        $scope.article = $scope.listArticle[index];
        $scope.article.tym += 1;
        $scope.article.favorite = 1;
        $scope.listArticle.splice(index, 1, $scope.article);
      });
  };
  $scope.unfavoriteArticle = function (id, index) {
    if ($scope.listArticle[index].tym >= 0) {
      $http
        .delete(env.API_URL + "/tym/unfavorite-article/" + id)
        .then(function (respone) {
          $scope.article = $scope.listArticle[index];
          $scope.article.tym -= 1;
          $scope.article.favorite = 0;
          $scope.listArticle.splice(index, 1, $scope.article);
        });
    }
  };
  // end tym article
  // begin article
  $scope.indexArticle = -1;
  $scope.showModalAddArticleToAlbum = function (index) {
    $scope.indexArticle = index;
    $scope.article = $scope.listArticle[index];
  };
  $scope.deleteArticleInAlbum = function (id) {
    $http
      .delete(
        env.API_URL +
          "/album/delete-all-article?articleId=" +
          id +
          "&albumId=" +
          $scope.album.id
      )
      .then(function (response) {
        toastr.error("xóa thành công");
        $scope.listArticle.splice($scope.indexArticle, 1);
        $scope.indexArticle = -1;
      });
  };
  // end article
};
