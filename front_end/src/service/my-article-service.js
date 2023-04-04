app.service("MyArticleService", function ($http, env, $routeParams) {
  var myArticle = [];
  var myArticleById = {};
  var myUpdateArticleById = {};
  var myArticleByStatus = [];
  var totalPages = [];
  var currentPage = [];

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
  this.getMyArticleByStatus = function () {
    return myArticleByStatus;
  };
  this.getTotalPages = function () {
    return totalPages;
  };
  this.getCurrentPage = function () {
    return currentPage;
  };

  this.fetchMyArticles = function () {
    return $http.get(myArticleAPI).then(
      function (response) {
        if (response.status === 200) {
          myArticle = response.data.data.data;
          console.log(myArticle);
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

  this.fetchMyArticlesByStatus = function (api, page) {
    return $http.get(api + "&page=" + page).then(
      function (response) {
        if (response.status === 200) {
          myArticleByStatus = response.data.data.data;
          console.log(myArticleByStatus);
          totalPages = response.data.data.totalPages;
          currentPage = response.data.data.currentPage;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
