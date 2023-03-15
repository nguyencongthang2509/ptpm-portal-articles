app.controller("myCtrl", function ($scope) {
  // window.onload = function () {
  //   allHaha.allHihi();
  //   $scope.$on("$includeContentLoaded", function () {
  //     document
  //       .querySelector(".toggle-sidebar-btn")
  //       .addEventListener("click", function () {
  //         document.querySelector("body").classList.toggle("toggle-sidebar");
  //       });
  //     document
  //       .querySelector(".search-bar-toggle")
  //       .addEventListener("click", function () {
  //         document
  //           .querySelector(".search-bar")
  //           .classList.toggle("search-bar-show");
  //       });
  //   });
  // };
  $scope.ToolBar = function () {
    Toolbar.allHihi();
  };
});
