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
  USER_ID: "73b4867d-48eb-46f5-a398-89dc4196728d",
});
// app.controller("userController", userController);
// app.controller("articleController", blogController);
