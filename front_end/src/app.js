// var app = angular.module("myModule", ["ngResource","ngRoute", "ngStorage","ngFileUpload", "ngLocale"]);
var app = angular.module("myModule", ["ngResource","ngRoute", "ngStorage","ngFileUpload", "ngLocale"]);
app.constant('env', {
    API_URL: "http://localhost:8080/api",
    USER_ID: "e6f35130-e61d-4c4c-b3b8-c2be033d1d90"
  });
app.controller("userController", userController);
app.controller("articleController", blogController);

