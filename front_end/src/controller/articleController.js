window.articleCtrl = function (
  $scope,
  $http,
  $rootScope,
  ArticleService,
  CategoryService,
  env,
  AlbumService
) {
  $scope.listArticle = [];
  $scope.listAlbum = [];
  $scope.listAlbumDefault = [];
  $scope.nameAlbum = "";
  $scope.UserCreateArticle = {
    articlesId: "",
    albumId: "",
  };

  $scope.createAlbumRequest = {
    title: "",
    status: true,
  };

  CategoryService.fetchCategories().then(function () {
    $scope.listCategory = CategoryService.getCategory();
  });
  ArticleService.fetchArticles().then(function () {
    // $scope.listArticle = ArticleService.getArticle();
    // $scope.totalPages = ArticleService.getTotalPages();
    // $scope.currentPage = ArticleService.getCurrentPage();
    // console.log($scope.listArticle);
    // console.log($scope.totalPages);
    // console.log($scope.currentPage);
    // $scope.setPage = function (page) {
    //   $scope.currentPage = page;
    //   ArticleService.setPage(page);
    //   $scope.listArticle = ArticleService.getArticle();
    // };
    $scope.itemsPerPage = 2;
    $scope.totalPages = Math.ceil(
      ArticleService.getTotalPages() / $scope.itemsPerPage
    );

    console.log($scope.totalPages);
    $scope.currentPage = ArticleService.getCurrentPage() + 1;
    console.log($scope.currentPage);
    $scope.listArticle = [];

    $scope.$watch("currentPage", function () {
      var startIndex = ($scope.currentPage - 1) * $scope.itemsPerPage;
      var endIndex = $scope.currentPage * $scope.itemsPerPage;
      $scope.listArticle = ArticleService.getArticle().slice(
        startIndex,
        endIndex
      );
    });

    $scope.pages = [];
    for (var i = 1; i <= $scope.totalPages; i++) {
      $scope.pages.push(i);
    }
  });

  // begin album

  $scope.showModalAddArticleToAlbum = function (id) {
    $scope.UserCreateArticle.articlesId = id;
    AlbumService.fetchSimpleAlbums(id).then(function () {
      $scope.listAlbum = AlbumService.getSimpleAlbums();
      $scope.listAlbumDefault = AlbumService.getSimpleAlbums();
    });
  };

  $scope.addArticleToAlbum = function (id) {
    $scope.UserCreateArticle.albumId = id;
    if (document.getElementById(id).checked) {
      $http
        .post(env.API_URL + "/album/add-article", $scope.UserCreateArticle)
        .then(function (response) {
          toastr.success("thêm thành công");
        });
    } else {
      $http
        .delete(
          env.API_URL +
            "/album/delete-all-article?articleId=" +
            $scope.UserCreateArticle.articlesId +
            "&albumId=" +
            id
        )
        .then(function (response) {
          toastr.error("xóa thành công");
        });
    }
  };

  $scope.createAlbum = function (event) {
    event.preventDefault();
    if ($scope.createAlbumRequest.title != "") {
      $http
        .post(env.API_URL + "/album/create", $scope.createAlbumRequest)
        .then(function (respone) {
          $scope.album = respone.data.data;
          $scope.album.countArticle = 0
          $scope.listAlbum.push($scope.album);
          $scope.createAlbumRequest = { title: "" };
        });
    }
    document.getElementById("formThemMoi").style.display = "none";
    document.getElementById("createAlbum").style.display = "block";
  };

  $scope.showCreateAlbum = function () {
    document.getElementById("formThemMoi").style.display = "block";
    document.getElementById("createAlbum").style.display = "none";
  };

  $scope.searchAlbum = function () {
    $scope.listAlbum = $scope.listAlbumDefault;
    if ($scope.nameAlbum.trim() == "") {
      $scope.listAlbum = $scope.listAlbumDefault;
    } else {
      var albums = [];
      $scope.listAlbum.map((item) => {
        if (item.title !== null && item.title.includes($scope.nameAlbum)) {
          albums.push(item);
        }
      });
      $scope.listAlbum = albums;
    }
  };
  $scope.closeFormAddAlbum = function () {
    document.querySelectorAll("input:checked").forEach((item) => {
      item.checked = false;
    });
  };
  //  end album

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

  // begin page article
  // ArticleService.fetchPageArticles().then(function () {
  //   $scope.listPageArticle = ArticleService.getPageArticle();
  //   console.log($scope.listPageArticle.currentPage);
  //   console.log($scope.listPageArticle.totalPages);
  // });
};
