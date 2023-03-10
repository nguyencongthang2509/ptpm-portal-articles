function userController($scope, $http, env) {
  $scope.user = {};
  $scope.albums = [];
  $scope.album = { title: "", id: "" };
  $scope.albumDetail = {};
  $scope.createNewAlbum = { title: "" };
  $scope.index = 0;

  $http
    .get(env.API_URL + "/user/detail/" + env.USER_ID)
    .then(function (respone) {
      respone.data.data.map((item) => {
        $scope.user = item;
      });
    });

  $http.get(env.API_URL + "/album").then(function (respone) {
    $scope.albums = respone.data.data;
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

  $scope.showConfirmDeleteAlbum = function (index, idAlbum) {
    $scope.index = index;

    $http
      .get(env.API_URL + "/album/detail/" + idAlbum)
      .then(function (respone) {
        $scope.album = respone.data.data;
      });
  };

  $scope.deleteAlbum = function () {
    console.log($scope.index);
    var modal = document.getElementById("deleteAlbumModal");
    $http
      .delete(env.API_URL + "/album/delete/" + $scope.album.id)
      .then(function (respone) {
        $scope.index = 0;
      });
    $scope.albums.splice($scope.index, 1);
    modal.classList.remove("show");
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
