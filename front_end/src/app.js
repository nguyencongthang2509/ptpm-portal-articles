// var app = angular.module("myModule", ["ngResource",'ngColorThief'"ngRoute", "ngStorage","ngFileUpload", "ngLocale"]);
var app = angular.module("myModule", [
  "ngResource",
  "ngRoute",
  "ngStorage",
  "ngFileUpload",
  "ngLocale",
  "ui.select2",
  "LocalStorageModule"
]);
app.constant("env", {
  API_URL: "http://localhost:8080/api",
  USER_ID: "73b4867d-48eb-46f5-a398-89dc4196728d",
});
app.config(function (localStorageServiceProvider) {
  localStorageServiceProvider
    .setPrefix('yourAppName');
});
// app.config(function ($colorThiefProvider) {
//   // Set the default quality
//   $colorThiefProvider.setDefaultQuality(50);

//   // Set the default palette color count
//   $colorThiefProvider.setDefaultColorCount(4);

//   // Set wether to return arrays (ColorThief's default) or
//   // objects like {r: 242, g: 124, b: 91} (false by default).
//   $colorThiefProvider.setReturnObjects(true);
// });
// app.controller("userController", userController);
// app.controller("articleController", blogController);
