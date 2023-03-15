app.service("ArticleService", function ($http) {
  var articles = [];
  this.getArticle = function () {
    return articles;
  };
  this.setArticle = function (data) {
    articles = data;
  };
  
  this.fetchArticles = function () {
    return $http.get(articleAPI + "?_expand=category").then(
      function (response) {
        if (response.status === 200) {
          articles = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
