app.service("ArticleService", function ($http) {
  var articles = [];
  var findByArticle =[];

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

  this.fetchFindByArticle = function (findArticleRequest) {

    return $http.get(articleAPI + `?albumId=`+findArticleRequest.albumId+`&title=`+findArticleRequest.title+`&hashtag=`+ findArticleRequest.hashtag).then(
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
