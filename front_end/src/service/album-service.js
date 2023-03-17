app.service("AlbumService", function ($http, env) {
  var ArticleFavorite = {};

  
  this.getArticleFavorite = function () {
    return ArticleFavorite;
  };
  this.setArticleFavorite = function (data) {
    ArticleFavorite = data;
  };

  var groupByTimePeriod = function (obj, timestamp) {
    var objPeriod = {};
    var oneDay = 24 * 60 * 60 * 1000; // hours * minutes * seconds * milliseconds
    for (var i = 0; i < obj.length; i++) {
        var d = new Date(obj[i][timestamp] * 1000);
        d = Math.floor(d.getTime() / oneDay);
        objPeriod[d] = objPeriod[d] || [];
        objPeriod[d].push(obj[i]);
    }
    return objPeriod;
};

  this.fetchArticleFavorite = function () {
    return $http.get(env.API_URL + "/album/all-article-favorite").then(
      function (response) {
        ArticleFavorite = groupByTimePeriod(response.data.data, 'createdDate');
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  
});

