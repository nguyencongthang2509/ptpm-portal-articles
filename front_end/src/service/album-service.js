app.service("AlbumService", function ($http, env) {
  var simpleAlbums = [];
  var albumOfAuthor = [];
  var albums = [];
  var album = [];
  var checkAlbumOfAuthor = true;
  var articleByAlbum = [];

  this.getCheckAlbumOfAuthor = function () {
    return checkAlbumOfAuthor;
  };

  this.getAlbums = function () {
    return albums;
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

  this.getAlbumOfAuthor = function () {
    return albumOfAuthor;
  };
  
  this.getArticleByAlbum = function () {
    return articleByAlbum;
  };

  this.fetchAlbums = function () {
    return $http.get(env.API_URL + "/album").then(
      function (response) {
        albums = response.data.data;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchSimpleAlbums = function (articleId) {
    return $http
      .get(env.API_URL + "/album/detail-album-user/" + articleId)
      .then(
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
    return $http.get(env.API_URL + "/album/detail/" + id).then(
      function (response) {
        console.log(response)
        album = response.data.data;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchAlbumOfAuthor = function (userId) {
    return $http.get(env.API_URL + "/album/" + userId).then(
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
    return $http.get(env.API_URL + "/album/find-album-user/" + id).then(
      function (response) {
        if (response.data.data != null) {
          checkAlbumOfAuthor = true;
        } else {
          checkAlbumOfAuthor = false;
        }

        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchArticleByAlbum = function (id) {
    return $http
      .get(env.API_URL + "/album/detail-article-by-album?albumId=" + id)
      .then(
        function (response) {
          articleByAlbum = response.data.data.data;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };
});
