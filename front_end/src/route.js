app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/trang-chu", {
      templateUrl: "./pages/trang-chu.html",
      
    })
    .when("/pages", {
      templateUrl: "./pages/pages.html",
      
    })
    .when("/about", {
      templateUrl: "./pages/about.html",
      
    })
    .when("/myblog", {
      templateUrl: "./pages/myblog.html",
      
    })
    .when("/blog", {
      templateUrl: "./pages/blog.html",
      
    })
    .when("/myarticles", {
      templateUrl: "./pages/myarticles.html",
      
    })
    .when("/articles", {
      templateUrl: "./pages/articles.html",
      
    })
    .when("/favourite_articles", {
      templateUrl: "./pages/favourite_articles.html",
      // controller: ChiTietDonHangController,
    })
    .when("/profile", {
      templateUrl: "./pages/profile.html",
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
    .when("/chitiet", {
      templateUrl: "./pages/bvchitiet.html",
      
    })
    .when("/test", {
      templateUrl: "./pages/test.html",
      controller: testCtrl,
    })
    .when("/setting", {
      templateUrl: "./pages/setting.html",
    })
    .when("/users", {
      templateUrl: "./pages/users.html",
      
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
