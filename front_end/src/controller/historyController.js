window.historyCtrl = function (
  $scope,
  $http,
  localStorageService,
  env,
  AlbumService
) {
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

  if ($scope.listArticle != []) {
    $scope.listArticle = localStorageService.get("articles").reverse();
  }else{
    $scope.listArticle = [];
  }

  // begin save article on  localStorage
  $scope.saveArticleInLocalStorage = function (index) {
    $scope.localStorageDemo = localStorageService.get("articles");
    $scope.article = $scope.listArticle[index];
    $scope.index = $scope.localStorageDemo.findIndex(
      (element) => element.id == $scope.listArticle[index].id
    );
    if ($scope.index !== -1) {
      $scope.localStorageDemo.splice($scope.index, 1);
    }
    $scope.baiViet = $scope.listArticle[index];
    $scope.baiViet.createdDate = new Date();
    $scope.localStorageDemo.push($scope.baiViet);
    localStorageService.set("articles", $scope.localStorageDemo);
  };
  // end save article on  localStorage

  // begin delete article in  localStorage
  $scope.deleteArticleInLocalStorage = function (index) {
    $scope.localStorageDemo = localStorageService.get("articles");
    $scope.index = $scope.localStorageDemo.findIndex(
      (element) => element.id == $scope.listArticle[index].id
    );
    console.log($scope.index);
    if ($scope.index !== -1) {
      $scope.localStorageDemo.splice($scope.index, 1);
      $scope.listArticle.splice($scope.index, 1);
    }
    localStorageService.set("articles", $scope.localStorageDemo);
  };
  // end delete article in  localStorage

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
          $scope.album.countArticle = 0;
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
    document.getElementById("formThemMoi").style.display = "none";
    document.getElementById("createAlbum").style.display = "block";
    document.querySelectorAll("input:checked").forEach((item) => {
      item.checked = false;
    });
  };
  //  end album
};
