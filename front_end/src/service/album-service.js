app.service("AlbumService", function ($http, env) {
    var simpleAlbums = [];
    var albumOfAuthor = [];
    var albums = [];
    var album = {};
    var checkAlbumOfAuthor = true;
  
    this.getCheckAlbumOfAuthor = function () {
      return checkAlbumOfAuthor;
    };
    this.setCheckAlbumOfAuthor = function (data) {
      checkAlbumOfAuthor = data;
    };

    this.getAlbums = function () {
      return albums;
    };
    this.setAlbums = function (data) {
      albums = data;
    };

    this.getAlbum = function () {
      return album;
    };
    this.setAlbum = function (data) {
      album = data;
    };

    this.getSimpleAlbums = function () {
      return simpleAlbums;
    };
    this.setSimpleAlbums = function (data) {
      simpleAlbums = data;
    };

    this.getAlbumOfAuthor = function () {
      return albumOfAuthor;
    };
    this.setAlbumOfAuthor = function (data) {
      albumOfAuthor = data;
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

    this.fetchSimpleAlbums = function (articleId) {
      return $http.get(env.API_URL + "/album/detail-album-user/"+articleId).then(
        function (response) {
          simpleAlbums = response.data.data;
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
  
    this.fetchAlbum = function (id) {
      return $http.get(env.API_URL + "/album/detail/"+id).then(
        function (response) {
          album = response.data.data;
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
    
    this.fetchAlbumOfAuthor = function (userId) {
      return $http.get(env.API_URL + "/album/"+userId).then(
        function (response) {
          albumOfAuthor = response.data.data;
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };

    this.fetchCheckAlbumOfAuthor = function (id) {
      return $http.get(env.API_URL + "/album/find-album-user/"+id).then(
        function (response) {
          if(response.data.data != null){
            checkAlbumOfAuthor = true;
          }else{
            checkAlbumOfAuthor = false;
          }
          
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };

  });
  