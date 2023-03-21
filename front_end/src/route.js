app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/trang-chu", {
      templateUrl: "./pages/trang-chu.html",
    })
    .when("/pages", {
      templateUrl: "./pages/pages.html",
    })
    .when("/myblog", {
      templateUrl: "./pages/my-blog.html",
    })
    .when("/blog", {
      templateUrl: "./pages/blog.html",
    })
    .when("/my-article", {
      templateUrl: "./pages/my-article.html",
      controller: myArticleCtrl,
    })
    .when("/articles", {
      templateUrl: "./pages/articles.html",
      controller: articleCtrl,
    })
    .when("/favourite-articles", {
      templateUrl: "./pages/favourite-articles.html",
      controller: favouriteArticleCtrl,
    })
    .when("/profile", {
      templateUrl: "./pages/profile.html",
      controller: profileController,
    })
    .when("/contact", {
      templateUrl: "./pages/contact.html",
    })
    .when("/login", {
      templateUrl: "./pages/login.html",
    })
    .when("/signup", {
      templateUrl: "./pages/signup.html",
    })
    .when("/album", {
      templateUrl: "./pages/album.html",
    })
    .when("/album/:id", {
      templateUrl: "./pages/detailAlbum.html",
      controller: detailAlbumCtrl
    })
    .when("/article-trash", {
      templateUrl: "./pages/article-trash.html",
      controller: articleTrashCtrl,
    })
    .when("/article/:id", {
      templateUrl: "./pages/detail-article.html",
      controller: detailArticleCtrl,
    })
    .when("/my-article/:id", {
      templateUrl: "./pages/detail-my-article.html",
      controller: detailArticleCtrl,
    })
    .when("/my-article/update-article/:id", {
      templateUrl: "./pages/create-article.html",
      controller: createArticleCtrl,
    })
    .when("/my-article/delete-article/:id", {
      templateUrl: "./pages/my-article.html",
      controller: createArticleCtrl,
    })
    .when("/create-article", {
      templateUrl: "./pages/create-article.html",
      controller: createArticleCtrl,
    })
    .when("/setting", {
      templateUrl: "./pages/setting.html",
    })
    .when("/users/:id", {
      templateUrl: "./pages/profile.html",
      controller: detailUserCtrl
    })
    .when("/users", {
      templateUrl: "./pages/users.html",
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
