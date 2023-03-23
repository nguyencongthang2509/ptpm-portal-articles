app.service("MyArticleService", function ($http, env, $routeParams) {
  var myArticle = [];
  var myArticleById = {};
  var myUpdateArticleById = {};

  this.getMyArticle = function () {
    return myArticle;
  };
  this.getMyUpdateArticleById = function () {
    return myUpdateArticleById;
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
          myArticle = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
  this.fetchUpdateMyArticleById = function (id) {
    return $http.get(myArticleAPI + "/detail-update-my-article/" + id).then(
      function (response) {
        if (response.status === 200) {
          myUpdateArticleById = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
  this.fetchMyArticleById = function (id) {
    return $http.get(myArticleAPI + "/detail-my-article/" + id).then(
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
