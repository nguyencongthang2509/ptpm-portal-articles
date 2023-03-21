app.service("AlbumService", function ($http, env) {
    var simpleAlbums = [];
    var albums = [];
  
    this.getAlbums = function () {
      return albums;
    };
    this.setAlbums = function (data) {
      albums = data;
    };

    this.getSimpleAlbums = function () {
      return simpleAlbums;
    };
    this.setSimpleAlbums = function (data) {
      simpleAlbums = data;
    };

    this.fetchAlbums = function () {
      return $http.get(env.API_URL + "/album").then(
        function (response) {
          albums = response.data.data;
          console.log(response.data.data);
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };

    this.fetchSimpleAlbums = function () {
      return $http.get(env.API_URL + "/album/detail-album-user").then(
        function (response) {
          simpleAlbums = response.data.data;
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
  
    
  });
  