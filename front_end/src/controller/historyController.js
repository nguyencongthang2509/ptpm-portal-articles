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
  var groupByTimePeriod = function (obj, timestamp) {
    var objPeriod = {};
    var oneDay = 24 * 60 * 60 * 1000; // hours * minutes * seconds * milliseconds
    for (var i = 0; i < obj.length; i++) {
     var d = Math.floor(obj[i][timestamp] *1000 / oneDay);
      objPeriod[d] = objPeriod[d] || [];
      objPeriod[d].push(obj[i]);
    }
    return objPeriod;
  };
  $scope.listHistoryArticle = {};
  if (localStorageService.get("articles") != []) {
    $scope.listHistoryArticle = groupByTimePeriod(
      localStorageService.get("articles"),
      "createdDate"
    );
  }
  // begin save article on  localStorage
  $scope.saveArticleInLocalStorage = function (id) {
    if ($scope.localStorageDemo != []) {
      $scope.article = {};
      $scope.localStorageDemo = localStorageService.get("articles");
      $scope.index = $scope.localStorageDemo.findIndex(
        (element) => element.id == id
      );
      if ($scope.index !== -1) {
        $scope.article = $scope.localStorageDemo[$scope.index];
        $scope.localStorageDemo.splice($scope.index, 1);
      }

      $scope.article.createdDate = Date.now();
      $scope.localStorageDemo.push($scope.article);
      localStorageService.set("articles", $scope.localStorageDemo);
    } else {
      localStorageService.set("articles", []);
    }
  };
  // end save article on  localStorage
  // begin delete article in  localStorage
  $scope.deleteArticleInLocalStorage = function (key, index, id) {
    $scope.localStorageDemo = localStorageService.get("articles");
    $scope.index = $scope.localStorageDemo.findIndex(
      (element) => element.id == id
    );

    if ($scope.index !== -1) {
      $scope.localStorageDemo.splice($scope.index, 1);
      $scope.listHistoryArticle[key].splice(index, 1);
      var isEmpty = $scope.listHistoryArticle[key].filter(function (val) {
        return val !== null || val !== "";
      }).length;
      if (isEmpty === 0) {
        delete $scope.listHistoryArticle[key];
      }
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
