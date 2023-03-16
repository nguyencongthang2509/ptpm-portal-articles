window.articleCtrl = function (
  $scope,
  $http,
  $rootScope,
  ArticleService,
  CategoryService
) {
  

  CategoryService.fetchCategories().then(function () {
    $scope.listCategory = CategoryService.getCategory();
    console.log($scope.listCategory);
  });
  ArticleService.fetchArticles().then(function () {
    $scope.listArticle = ArticleService.getArticle();
    console.log($scope.listArticle);
  });



};
