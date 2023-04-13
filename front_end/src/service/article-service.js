app.service("ArticleService", function ($http) {
  var articles = [];
  var findByArticle = [];
  var articlesOfUser = [];
  var totalPages = [];
  var currentPage = [];
  var totalPagesArticleOfUser = [];
  var currentPageArticleOfUser = [];
  var article = {};
  var articlesByBrowseDate = [];
  var articlesSlide = [];
  var articlesByTym = [];

  this.getArticle = function () {
    return articles;
  };
  this.setArticle = function (data) {
    articles = data;
  };
  this.getArticleById = function () {
    return article;
  };
  this.setArticleById = function (data) {
    article = data;
  };
  this.getTotalPages = function () {
    return totalPages;
  };
  this.getCurrentPage = function () {
    return currentPage;
  };
  this.getArticleByBrowseDate = function () {
    return articlesByBrowseDate;
  };
  this.getArticleSlide = function () {
    return articlesSlide;
  };
  this.getArticleByTym = function () {
    return articlesByTym;
  };

  this.fetchArticles = function (page) {
    return $http.get(articleAPI + `?page=` + page).then(
      function (response) {
        if (response.status === 200) {
          articles = response.data.data.data;
          console.log(articles);
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

  this.fetchArticlesByTym = function () {
    return $http.get(articleByTymAPI).then(
      function (response) {
        if (response.status === 200) {
          articlesByTym = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchArticlesSlide = function () {
    return $http.get(articleAPI).then(
      function (response) {
        if (response.status === 200) {
          articlesSlide = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchArticlesByBrowseDate = function () {
    return $http.get(articleByBrowseDateAPI).then(
      function (response) {
        if (response.status === 200) {
          articlesByBrowseDate = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
  this.getArticlesOfUser = function () {
    return articlesOfUser;
  };
  this.setArticlesOfUser = function (data) {
    articlesOfUser = data;
  };
  this.getTotalPagesArticleOfUser = function () {
    return totalPagesArticleOfUser;
  };
  this.getCurrentPageArticleOfUser = function () {
    return currentPageArticleOfUser;
  };

  this.getFindByArticle = function () {
    return findByArticle;
  };
  this.setFindByArticle = function (data) {
    findByArticle = data;
  };

  this.fetchArticlesByAuthorId = function (userId) {
    return $http.get(articleAPI + "/author?userId=" + userId).then(
      function (response) {
        if (response.status === 200) {
          articlesOfUser = response.data.data.data;
          totalPagesArticleOfUser = response.data.data.totalPages;
          currentPageArticleOfUser = response.data.data.currentPage;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchArticlesById = function (articleId) {
    return $http.get(articleAPI + "/" + articleId).then(
      function (response) {
        if (response.status === 200) {
          article = response.data.data;
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
          `?title=` +
          findArticleRequest.title +
          `&hashtag=` +
          findArticleRequest.hashtag +
          `&category=` +
          findArticleRequest.category +
          `&categoryId=` +
          findArticleRequest.categoryId +
          `&page=` + findArticleRequest.page
      )
      .then(
        function (response) {
          if (response.status === 200) {
            findByArticle = response.data.data.data;
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

  this.fetchFindByArticleByCategory = function (findArticleRequest) {
    return $http
      .get(
        articleAPI +
          `/find-article-category?categoryId=` +
          findArticleRequest.categoryId +
          `&page=` + findArticleRequest.page
      )
      .then(
        function (response) {
          console.log("hi");
          console.log(response);
          console.log(response.data.data.totalPages);
          if (response.status === 200) {
            findByArticle = response.data.data.data;
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
