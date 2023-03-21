window.profileController = function (
  $scope,
  $http,
  $rootScope,
  env,
  AlbumService,
  UserService
) {

  $scope.user = {};
  $scope.albums = [];
  $scope.album = { title: "", id: "" };
  $scope.albumDetail = {};
  $scope.createNewAlbum = { title: "" };
  $scope.index = 0;
  $scope.authen = true

    UserService.fetchDetailUser().then(function(respone){
      $scope.user = UserService.getUser()
    })

    AlbumService.fetchAlbums().then(function(respone){
      $scope.albums = AlbumService.getAlbums()
    })


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
    $scope.UpdateAlbum = { id: $scope.album.id, title: $scope.album.title };
    if ($scope.album.title != "") {
      $http
        .put(env.API_URL + "/album/update", $scope.UpdateAlbum)
        .then(function (respone) {
          $scope.index = 0;
          $scope.albums.splice($scope.index, 1, respone.data.data);
        });
    }
  };
  
}
