window.detailUserCtrl = function (
    $scope,
    $http,
    $rootScope,
    UserService,
    $routeParams,
    AlbumService
  ) {
    $scope.authen = false
    UserService.fetchProfileAuthor($routeParams.id).then(function () {
        $scope.user = UserService.getProfileAuthor();
      console.log($scope.listArticleTrash);
    });
    AlbumService.fetchAlbumOfAuthor($routeParams.id).then(function(respone){
        $scope.albums = AlbumService.getAlbumOfAuthor()
        console.log( $scope.albums);
      })
  };
  