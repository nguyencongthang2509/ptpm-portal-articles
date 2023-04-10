window.homeCtrl = function (
  $scope,
  $http,
  $rootScope,
  ArticleService,
  CategoryService,
  localStorageService 
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
  });

  // begin save article on  localStorage
  $scope.saveArticleInLocalStorage = function (index, flag) {
    $scope.localStorageDemo = localStorageService.get("articles");
    if ($scope.localStorageDemo != []) {
      if (flag == 0) {
        $scope.index = $scope.localStorageDemo.findIndex(
          (element) => element.id == $scope.listArticleByBrowseDate[index].id
        );
        if ($scope.index !== -1) {
          $scope.localStorageDemo.splice($scope.index, 1);
        }
        $scope.article = $scope.listArticleByBrowseDate[index];
        $scope.article.createdDate = Date.now()
        $scope.localStorageDemo.push($scope.article);
        localStorageService.set("articles", $scope.localStorageDemo);
      } else {
        $scope.index = $scope.localStorageDemo.findIndex(
          (element) => element.id == $scope.fetchArticlesByTym[index].id
        );
        if ($scope.index !== -1) {
          $scope.localStorageDemo.splice($scope.index, 1);
        }
        $scope.article = $scope.fetchArticlesByTym[index];
        $scope.article.createdDate = Date.now()
        $scope.localStorageDemo.push($scope.article);
        localStorageService.set("articles", $scope.localStorageDemo);
      }
    } else {
      localStorageService.set("articles", []);
    }
  };
  // end save article on  localStorage
};
