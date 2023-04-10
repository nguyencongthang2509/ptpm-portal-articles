window.detailAlbumCtrl = function (
  $scope,
  $routeParams,
  AlbumService,
  ArticleService,
  localStorageService,
  env,
  $http
) {
  $scope.listArticle = [];
  $scope.album = {};

  $scope.idArticle = "";
  //get detail album
  AlbumService.fetchAlbum($routeParams.id).then(function () {
    $scope.album = AlbumService.getAlbum();
  });

  //get bài viết trong album
  AlbumService.fetchArticleByAlbum($routeParams.id).then(function () {
    $scope.listArticle = AlbumService.getArticleByAlbum();
    if ($scope.listArticle.length > 0) {
      $scope.idArticle = $scope.listArticle[0].id;
    }
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

  //delete article in album

  $scope.deleteArticleInAlbum = function (idArticle, idAlbum) {
    if (confirm("Bạn có chắc muốn xóa?")) {
      $http
        .delete(
          albumAPI +
            "/delete-all-article?articleId=" +
            idArticle +
            "&albumId=" +
            idAlbum
        )
        .then(
          function (response) {
            toastr.success("Xóa thành công", "Thông báo!", {
              timeOut: 3000,
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
            location.reload();
          },
          function (error) {
            toastr.error("Có lỗi xảy ra", "Thông báo!", {
              timeOut: 3000,
              closeButton: true,
              progressBar: true,
              positionClass: "toast-top-center",
            });
            console.log(error);
          }
        );
    } else {
      toastr.info("Đã hủy xóa", {
        timeOut: 3000,
        closeButton: true,
        progressBar: true,
        positionClass: "toast-top-center",
      });
    }
  };

   // begin save article on  localStorage
   $scope.saveArticleInLocalStorage = function (index) {
    $scope.localStorageDemo = localStorageService.get("articles");
    if ($scope.localStorageDemo != []) {
      $scope.index = $scope.localStorageDemo.findIndex(
        (element) => element.id == $scope.listArticle[index].id
      );
      if ($scope.index !== -1) {
        $scope.localStorageDemo.splice($scope.index, 1);
      }
      $scope.article = $scope.listArticle[index];
      $scope.article.createdDate = Date.now()
      $scope.localStorageDemo.push($scope.article);
      localStorageService.set("articles", $scope.localStorageDemo);
    }else{
      localStorageService.set("articles", []);
    }
  };
  // end save article on  localStorage
};
