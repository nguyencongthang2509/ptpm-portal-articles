window.articleCtrl = function (
  $scope,
  $http,
  $rootScope,
  ArticleService,
  CategoryService,
  env,
  AlbumService
) {

  $scope.listAlbum = [];
  $scope.listAlbumDefault = []
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
    $scope.listArticle = ArticleService.getArticle();
  });

  // begin album
  AlbumService.fetchAlbums().then(function () {
    $scope.listAlbum = AlbumService.getAlbums();
    $scope.listAlbumDefault =  AlbumService.getAlbums();
  });

  $scope.showModalAddArticleToAlbum = function (id) {
    $scope.UserCreateArticle.articlesId = id;
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
          $scope.listAlbum.push(respone.data.data);
          $scope.listAlbumDefault.push(respone.data.data);
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
    $scope.listAlbum = $scope.listAlbumDefault 
    if ($scope.nameAlbum.trim() == "") {
      $scope.listAlbum = $scope.listAlbumDefault 
    } else {
      var albums = []
      $scope.listAlbum.map(item =>{
        if(item.title !== null && item.title.includes($scope.nameAlbum)){
          albums.push(item);
        }
      })
      $scope.listAlbum = albums
    }
  };
//  end album
};
