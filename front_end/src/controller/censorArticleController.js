window.articleCtrl = function (
    $scope,
    $http,
    $rootScope,
    CensorArticleService,
    env
  ) {
    $scope.listArticle = [];
    CensorArticleService.fetchArticles(0).then(function (respone) {
        $scope.listArticle = CensorArticleService.getArticles();
        $scope.totalPages = CensorArticleService.getTotalPages();
        $scope.currentPage = CensorArticleService.getCurrentPage();
        $scope.pageModel = $scope.currentPage + 1;
      });
  
    $scope.pageArticle = function () {
        CensorArticleService.fetchArticles(0).then(function (respone) {
        $scope.listArticle = CensorArticleService.getArticles();
        $scope.totalPages = CensorArticleService.getTotalPages();
        $scope.currentPage = CensorArticleService.getCurrentPage();
        $scope.pageModel = $scope.currentPage + 1;
      });
    };
  
    $scope.pageArticle();
  
    $scope.nextPage = function () {
      $scope.currentPage++;
      if ($scope.currentPage >= $scope.totalPages) {
        $scope.pageModel = $scope.totalPages;
      }
      CensorArticleService.fetchArticles($scope.currentPage).then(function (respone) {
        $scope.listArticle = CensorArticleService.getArticles();
        $scope.pageModel = $scope.currentPage + 1;
      });
    };
  
    $scope.prevPage = function () {
      $scope.currentPage--;
      if ($scope.currentPage <= 0) {
        $scope.currentPage = 0;
      }
      CensorArticleService.fetchArticles($scope.currentPage).then(function (respone) {
        $scope.listArticle = CensorArticleService.getArticles();
        $scope.pageModel = $scope.currentPage + 1;
      });
    };
  
    $scope.inputChangeEvent = function () {
        CensorArticleService.fetchArticles($scope.pageModel - 1).then(function (respone) {
        $scope.listArticle = ArticleSCensorArticleServiceervice.getArticles();
      });
    };
  
  };
  