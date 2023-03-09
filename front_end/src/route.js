app.config(function ($routeProvider, $locationProvider) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> c031a90e85a0410e1f8bc3ce0808ef0d94c14e29
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
<<<<<<< HEAD
    .when("/myblog", {
      templateUrl: "./pages/myblog.html",
      // controller: ChiTietDonHangController,
    })
    .when("/blog", {
      templateUrl: "./pages/blog.html",
      // controller: ChiTietDonHangController,
    })
=======
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
>>>>>>> c031a90e85a0410e1f8bc3ce0808ef0d94c14e29
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
<<<<<<< HEAD
    .when("/test", {
      templateUrl: "./pages/test.html",
=======
    .when("/users", {
      templateUrl: "./pages/users.html",
      // controller: ChiTietDonHangController,
>>>>>>> c031a90e85a0410e1f8bc3ce0808ef0d94c14e29
    })
    .otherwise({
      redirectTo: "/trang-chu",
    });
});
<<<<<<< HEAD
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
=======
>>>>>>> c031a90e85a0410e1f8bc3ce0808ef0d94c14e29
