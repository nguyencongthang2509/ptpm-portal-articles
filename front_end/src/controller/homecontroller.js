window.homeCtrl = function (
  $scope,
  $http,
  $rootScope,
  ArticleService,
  CategoryService
) {
  $scope.key = "";

  $scope.listArticleByBrowseDate = [];
  CategoryService.fetchCategories().then(function () {
    $scope.listCategory = CategoryService.getCategory();
  });

  ArticleService.fetchArticlesByBrowseDate().then(function (respone) {
    $scope.listArticleByBrowseDate = ArticleService.getArticleByBrowseDate();
  });

  ArticleService.fetchArticlesSlide().then(function (respone) {
    $scope.listArticleSlide = ArticleService.getArticleSlide();
    
  });

  ArticleService.fetchArticlesByTym().then(function (respone) {
    $scope.listArticleByTym = ArticleService.getArticleByTym();
    console.log($scope.listArticleByTym);
  });
};
