app.service("AlbumService", function ($http, env) {
    var Albums = {};
  
    
    this.getAlbums = function () {
      return Albums;
    };
    this.setAlbums = function (data) {
      Albums = data;
    };

    this.fetchAlbums = function () {
      return $http.get(env.API_URL + "/album/detail-album-user").then(
        function (response) {
            Albums = response.data.data;
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
  
    
  });
  