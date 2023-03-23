window.myArticleCtrl = function (
  $scope,
  $http,
  MyArticleService,
  CategoryService
) {
    
  CategoryService.fetchCategories().then(function () {
    $scope.listCategory = CategoryService.getCategory();
  });

  MyArticleService.fetchMyArticles().then(function () {
    $scope.listMyArticle = MyArticleService.getMyArticle();
    console.log($scope.listMyArticle);
  });
};
