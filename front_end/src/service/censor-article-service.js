app.service("CensorArticleService", function ($http) {
    var articles = [];
    var totalPages = 0;
    var currentPage = 0;
    
  
    this.getArticle = function () {
      return articles;
    };
    this.setArticle = function (data) {
      articles = data;
    };
    
    this.fetchArticles = function (page) {
      return $http.get(censorArticleAPI + `?page=` + page).then(
        function (response) {
          if (response.status === 200) {
            articles = response.data.data.data;
            console.log(articles)
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
  