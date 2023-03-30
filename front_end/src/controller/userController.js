window.profileController = function (
  $scope,
  $http,
  $rootScope,
  env,
  AlbumService,
  UserService,
  MyArticleService
) {

  $scope.user = {};
  $scope.albums = [];
  $scope.album = { title: "", id: "" };
  $scope.albumDetail = {};
  $scope.createNewAlbum = { title: "" ,status: true};
  $scope.index = 0;
  $scope.authen = true
  $scope.listArticle = [];
  $scope.listAlbum = [];
  $scope.listAlbumDefault = [];
  $scope.search = "";
  $scope.UserCreateArticle ={}

    UserService.fetchDetailUser().then(function(respone){
      $scope.user = UserService.getUser()
    })

    AlbumService.fetchAlbums().then(function(respone){
      $scope.albums = AlbumService.getAlbums()
    })

    MyArticleService.fetchMyArticles().then(function (respone) {
      $scope.listArticle = MyArticleService.getMyArticle();
      console.log($scope.listArticle);
      // $scope.currentPage = MyArticleService.getCurrentPageArticleOfUser();
      // $scope.totalPages = MyArticleService.getTotalPagesArticleOfUser();
    });


  $scope.createAlbum = function (event) {
    event.preventDefault();
    if ($scope.createNewAlbum.title != "") {
      $http
        .post(env.API_URL + "/album/create", $scope.createNewAlbum)
        .then(function (respone) {
          $scope.albums.push(respone.data.data);
          $scope.createNewAlbum = { title: "" };
        });
    }
  };
   

  $scope.detailAlbum = function (index, idAlbum) {
    $scope.index = index
    $http
      .get(env.API_URL + "/album/detail/" + idAlbum)
      .then(function (respone) {
        // respone.data.data.map(item =>{
        $scope.album = respone.data.data;
        // })
      });
  };


  $scope.deleteAlbum = function () {
    console.log($scope.index);
    $http
      .delete(env.API_URL + "/album/delete/" + $scope.album.id)
      .then(function (respone) {
        $scope.index = 0;
      });
    $scope.albums.splice($scope.index, 1);
  };

  $scope.updateAlbum = function (event) {
    event.preventDefault();
    $scope.UpdateAlbum = { id: $scope.album.id, title: $scope.album.title, status:  $scope.album.status};
    if ($scope.album.title != "") {
      $http
        .put(env.API_URL + "/album/update", $scope.UpdateAlbum)
        .then(function (respone) {
          respone.data.data.countArticle =  $scope.albums[$scope.index].countArticle
          $scope.albums.splice($scope.index, 1, respone.data.data);
          $scope.index = 0;

        });
    }
  };
  
   // begin quick  add album

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

  $scope.createAlbumQuick  = function (event) {
    event.preventDefault();
    if ($scope.createNewAlbum.title != "") {
      $http
        .post(env.API_URL + "/album/create", $scope.createNewAlbum)
        .then(function (respone) {
          $scope.album = respone.data.data;
          $scope.album.countArticle = 0
          $scope.listAlbum.push($scope.album);
          $scope.createNewAlbum = { title: "" , status: true};
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
    if ($scope.search.trim() == "") {
      $scope.listAlbum = $scope.listAlbumDefault;
    } else {
      var albums = [];
      $scope.listAlbum.map((item) => {
        if (item.title !== null && item.title.includes($scope.search)) {
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
  //  end quick  add album

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
}
