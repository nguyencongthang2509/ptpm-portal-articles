// var app = angular.module("myModule", ["ngResource","ngRoute", "ngStorage","ngFileUpload", "ngLocale"]);
var app = angular.module("myModule", [
  "ngResource",
  "ngRoute",
  "ngStorage",
  "ngFileUpload",
  "ngLocale",
  "ui.select2"
]);
app.constant("env", {
  API_URL: "http://localhost:8080/api",
  USER_ID: "309da9d4-d692-4697-a9e0-e42c670a20b3",
});
// app.controller("userController", userController);
// app.controller("articleController", blogController);
