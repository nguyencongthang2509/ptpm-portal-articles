window.articleCtrl = function (
    $scope,
    $http,
    $rootScope,
    ArticleService,
    CategoryService,
    env,
    AlbumService
) {

  $scope.UserCreateArticle = {
    articlesId: "",
    albumId:""
  }

  CategoryService.fetchCategories().then(function () {
    $scope.listCategory = CategoryService.getCategory();
  });
  ArticleService.fetchArticles().then(function () {
    $scope.listArticle = ArticleService.getArticle();
  });

  AlbumService.fetchAlbums().then(function(){
    $scope.listAlbum = AlbumService.getAlbums();
    console.log($scope.listAlbum)
  })

  $scope.showModalAddArticleToAlbum = function(id){
    $scope.UserCreateArticle.articlesId = id
  }

  $scope.addArticleToAlbum = function(id){
    $scope.UserCreateArticle.albumId = id
    if(document.getElementById(id).checked){
      $http.post(env.API_URL + "/album/add-article", $scope.UserCreateArticle).then(function(response){
        toastr.success("thêm thành công");
      })
    }else{
      $http.delete(env.API_URL + "/album/delete-all-article?articleId="+$scope.UserCreateArticle.articlesId+"&albumId="+id ).then(function(response){
        toastr.error("xóa thành công");
      })
    }
  }
};
