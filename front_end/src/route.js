app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/trang-chu", {
      templateUrl: "./pages/trang-chu.html",
      // controller: ChiTietDonHangController,
    })
    .when("/pages", {
      templateUrl: "./pages/pages.html",
      // controller: ChiTietDonHangController,
    })
    .when("/about", {
      templateUrl: "./pages/about.html",
      // controller: ChiTietDonHangController,
    })
    .when("/myblog", {
      templateUrl: "./pages/myblog.html",
      // controller: ChiTietDonHangController,
    })
    .when("/blog", {
      templateUrl: "./pages/blog.html",
      // controller: ChiTietDonHangController,
    })
    .when("/myarticles", {
      templateUrl: "./pages/myarticles.html",
      // controller: ChiTietDonHangController,
    })
    .when("/articles", {
      templateUrl: "./pages/articles.html",
      // controller: ChiTietDonHangController,
    })
    .when("/profile", {
      templateUrl: "./pages/profile.html",
    })
    .when("/contact", {
      templateUrl: "./pages/contact.html",
      // controller: ChiTietDonHangController,
    })
    .when("/login", {
      templateUrl: "./pages/login.html",
      // controller: ChiTietDonHangController,
    })
    .when("/signup", {
      templateUrl: "./pages/signup.html",
      // controller: ChiTietDonHangController,
    })
    .when("/album", {
      templateUrl: "./pages/album.html",
      // controller: ChiTietDonHangController,
    })
    .when("/chitiet", {
      templateUrl: "./pages/bvchitiet.html",
      // controller: ChiTietDonHangController,
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
      // controller: ChiTietDonHangController,
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
