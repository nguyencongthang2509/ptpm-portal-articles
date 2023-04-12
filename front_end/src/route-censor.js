app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/home", {
      templateUrl: "./pages/censor/approve-article.html",
    })
    .when("/approve-article", {
      templateUrl: "./pages/censor/censor-article.html",
      controller: articleCtrl,
    })
    .when("/article/:id", {
      templateUrl: "./pages/censor/censor-detail-article.html",
      controller: detailArticleCtrl,
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
app.run(function ($rootScope) {
  $rootScope.$on("$routeChangeStart", function () {
    $rootScope.loading = true;
  });
  $rootScope.$on("$routeChangeSuccess", function () {
    $rootScope.loading = false;
  });
  $rootScope.$on("$routeChangeError", function () {
    $rootScope.loading = false;
    alert("Lỗi, Không tải được template");
  });
});
