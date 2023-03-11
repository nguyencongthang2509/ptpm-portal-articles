window.testCtrl = function ($scope, $http) {
  var quill = new Quill("#editor-container", {
    modules: {
      formula: true,
      syntax: true,
      toolbar: "#toolbar-container",
    },
    placeholder: "What are you going to write today...?",
    theme: "snow",
  });
  $scope.isMenuOpen = false;
  $scope.toggleMenu = function () {
    $scope.isMenuOpen = !$scope.isMenuOpen;
  };
  $scope.saveHTML = function () {
    var content = quill.root.innerHTML;
    var fileContent = new Blob([content], { type: "text/html" });

    // create file object
    var file = new File([fileContent], "huhuh.html", { type: "text/html" });
    console.log(file.name);
    // create form data with file
    var formData = new FormData();
    formData.append("file", file, file.name);

    $http
      .post("http://localhost:8080/api/article/download", formData, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined }, // set content type header
      })
      .then(
        function (response) {
          console.log("Thành công rồi haha");
        },
        function (error) {
          console.log(error);
          console.log("Thất bại rồi xem lại code đi");
        }
      );
  };
};
