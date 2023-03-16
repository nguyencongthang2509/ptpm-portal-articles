app.service("MyArticleService", function ($http, env, $routeParams) {
  var myArticle = [];
  var myArticleById = {};
  this.getMyArticle = function () {
    return myArticle;
  };
  this.getMyArticleById = function () {
    return myArticleById;
  };
  this.setMyArticle = function (data) {
    myArticle = data;
  };
  this.fetchMyArticles = function () {
    return $http.get(myArticleAPI).then(
      function (response) {
        if (response.status === 200) {
          myArticle = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
  this.fetchMyArticleById = function (id) {
    return $http.get(myArticleAPI + "/detail-update-my-article/" + id).then(
      function (response) {
        if (response.status === 200) {
          myArticleById = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
