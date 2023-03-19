var app = angular.module("myModule", ["ngRoute"]);
app.config(function ($routeProvider, $locationProvider) {
  $locationProvider.hashPrefix("");
  $routeProvider
    .when("/home", {
      templateUrl: "pages/home.html",
      //   controller: addEventListener,
    })
    // .when("/login", {
    //   templateUrl: "pages/dangnhap.html",
    //   controller: login,
    // })
    .otherwise({
      redirectTo: "/home",
    });
});
