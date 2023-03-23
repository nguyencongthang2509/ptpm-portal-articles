app.service("ArticleService", function ($http) {
  var articles = [];
  var findByArticle = [];
  var totalPages = [];
  var currentPage = [];

  this.getArticle = function () {
    return articles;
  };
  this.setArticle = function (data) {
    articles = data;
  };

  this.getFindByArticle = function () {
    return findByArticle;
  };
  this.setFindByArticle = function (data) {
    findByArticle = data;
  };
  this.getTotalPages = function () {
    return totalPages;
  };
  this.getCurrentPage = function () {
    return currentPage;
  };
  this.fetchArticles = function (page) {
    return $http.get(articleAPI + `?page=` + page).then(
      function (response) {
        if (response.status === 200) {
          articles = response.data.data.data;
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

  this.fetchFindByArticle = function (findArticleRequest) {
    return $http
      .get(
        articleAPI +
          `?albumId=` +
          findArticleRequest.albumId +
          `&title=` +
          findArticleRequest.title +
          `&hashtag=` +
          findArticleRequest.hashtag
      )
      .then(
        function (response) {
          if (response.status === 200) {
            findByArticle = response.data.data.data;
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };
});
