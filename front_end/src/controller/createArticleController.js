window.createArticleCtrl = function (
  $scope,
  $http,
  CategoryService,
  $routeParams,
  MyArticleService
) {
  $scope.listTag = [];
  var quill = new Quill("#editor-container", {
    modules: {
      formula: true,
      syntax: true,
      toolbar: "#toolbar-container",
    },
    placeholder: "What are you going to write today...?",
    theme: "snow",
  });
  $scope.select2Options = {
    multiple: true,
    simple_tags: true,
    tags: $scope.listTag,
    tokenSeparators: ["/", ",", ";"],
  };
  $scope.isMenuOpen = false;
  $scope.toggleMenu = function () {
    $scope.isMenuOpen = !$scope.isMenuOpen;
  };

  $http.get(hashtagAPI).then(function (response) {
    response.data.data.map((item) => {
      $scope.listTag.push(item.title);
    });
    $scope.select2Options = {
      multiple: true,
      simple_tags: true,
      tags: $scope.listTag,
      tokenSeparators: ["/", ",", ";"],
    };
  });
  $scope.list_of_string = [];

  CategoryService.fetchCategories().then(function () {
    $scope.listCategory = CategoryService.getCategory();
  });

  $scope.saveHTML = function () {
    var content = quill.root.innerHTML;
    var formData = {
      title: $scope.title,
      content: content,
      categoryId: $scope.category,
      hashtag: $scope.list_of_string,
    };
    $http.post(articleAPI + "/create-article", formData).then(
      function (response) {
        console.log("Thành công rồi haha");
      },
      function (error) {
        console.log(error);
        console.log("Thất bại rồi xem lại code đi");
      }
    );
  };

  // $scope.detailMyArticle = [];

  var id = $routeParams.id;
  MyArticleService.fetchMyArticleById(id).then(function () {
    $scope.myArticleById = MyArticleService.getMyArticleById();
    $scope.title = $scope.myArticleById.title;

    // console.log($scope.myArticleById);
  });

  $scope.updateMyArticle = function () {
    var content = quill.root.innerHTML;
    var formData = {
      title: $scope.title,
      content: content,
      categoryId: $scope.category,
      hashtag: $scope.list_of_string,
    };
    $http.post(articleAPI + "/create-article", formData).then(
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
