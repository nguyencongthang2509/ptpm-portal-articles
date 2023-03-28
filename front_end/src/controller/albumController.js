window.albumCtrl = function (
  $scope,
  $routeParams,
  AlbumService,
  ArticleService,
  env,
  $http
) {
    
  $scope.album = [];

  //get all album
  AlbumService.fetchAlbums().then(function () {
    $scope.albums = AlbumService.getAlbums();
    console.log($scope.albums);
  });

  
};
