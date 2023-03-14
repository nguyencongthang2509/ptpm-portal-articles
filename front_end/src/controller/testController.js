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
  $scope.categories = [];

  $http.get("http://localhost:8080/api/category").then(function (response) {
    $scope.categories = response.data.data;
  });

  $scope.saveHTML = function () {
    var content = quill.root.innerHTML;
    var formData = {
      title: $scope.title,
      content: content,
      categoryId: $scope.category,
    };
    $http
      .post("http://localhost:8080/api/article/create-article", formData)
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
