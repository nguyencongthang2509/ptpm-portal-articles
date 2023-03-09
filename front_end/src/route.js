app.config(function ($routeProvider, $locationProvider) {
<<<<<<< HEAD
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
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
=======
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
        }).when("/profile", {
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
        .otherwise({
            redirectTo: "/trang-chu",
        });
})
;
>>>>>>> 40d24ddbea49bdf549572fd818075cc10d80eb17
