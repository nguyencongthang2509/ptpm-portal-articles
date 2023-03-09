// var app = angular.module("myModule", ["ngResource","ngRoute", "ngStorage","ngFileUpload", "ngLocale"]);
var app = angular.module("myModule", ["ngResource","ngRoute", "ngStorage","ngFileUpload", "ngLocale"]);
app.constant('env', {
    API_URL: "http://localhost:8080/api",
    USER_ID: "f249b9dd-4eb1-429e-b8f5-7eab507d4552"
  });
app.controller("userController", userController);
// app.controller("articleController", blogController);

