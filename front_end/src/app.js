// var app = angular.module("myModule", ["ngResource","ngRoute", "ngStorage","ngFileUpload", "ngLocale"]);
var app = angular.module("myModule", [
  "ngResource",
  "ngRoute",
  "ngStorage",
  "ngFileUpload",
  "ngLocale",
]);
app.constant("env", {
  API_URL: "http://localhost:8080/api",
  USER_ID: "9cfdd250-21bd-4e05-abff-639b70bc848a",
});
app.controller("userController", userController);
// app.controller("articleController", blogController);
