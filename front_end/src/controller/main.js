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
  $scope.Summernote = function () {
    Summernote.summer();
  };
  
});
app.directive("myTooltip", function () {
  return {
    restrict: "A",
    scope: {
      tooltipContent: "@",
    },
    link: function (scope, element, attrs) {
      $(element).attr("data-bs-toggle", "tooltip");
      $(element).attr("title", scope.tooltipContent);
      $(element).attr("data-bs-placement", "top");
      $(element).tooltip();

      scope.$watch("tooltipContent", function (newVal) {
        $(element).tooltip("dispose"); 
        $(element).attr("data-bs-toggle", "tooltip");
        $(element).attr("title", newVal);
        $(element).attr("data-bs-placement", "top");
        $(element).tooltip(); 
      });
    },
  };
});
