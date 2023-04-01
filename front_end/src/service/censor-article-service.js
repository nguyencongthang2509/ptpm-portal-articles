app.service("CensorArticleService", function ($http) {
    var articles = [];
    var totalPages = [];
    var currentPage = [];
    var article = {};
    
  
    this.getArticles = function () {
      return articles;
    };
    this.getTotalPages = function () {
      return totalPages;
    };
    this.getCurrentPage = function () {
      return currentPage;
    };

    this.getArticleById = function () {
      return article;
    };
    
    this.fetchArticles = function (page) {
      return $http.get(censorArticleAPI + `?page=` + page).then(
        function (response) {
          if (response.status === 200) {
            articles = response.data.data.content;
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

    this.fetchArticleById = function (id) {
      return $http.get(censorArticleAPI + "/" + id).then(
        function (response) {
          if (response.status === 200) {
            article = response.data.data
          }
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
    };
  });
  