window.createArticleCtrl = function (
  $scope,
  $rootScope,
  $http,
  CategoryService,
  $routeParams,
  MyArticleService
) {
  $scope.listTag = [];

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

  $scope.saveHTML = function (event) {
    event.preventDefault();
    var content = $("#summernote").summernote("code");
    var strippedText = content.replace(/<\/?[^>]+(>|$)/g, " ");
    var words = strippedText.split(" ");
    var first30Words = words.slice(0, 40);
    var first30WordsString = first30Words.join(" ");
    var formData = {
      title: $scope.title,
      content: content,
      descriptive: first30WordsString,
      hashtag: $scope.list_of_string,
    };
    $http.post(myArticleAPI + "/create-article", formData).then(
      function (response) {
        toastr.success("Đã gửi yêu cầu phê duyệt thành công", "Thông báo!", {
          timeOut: 5000,
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
        console.log("Thành công rồi haha");
      },
      function (error) {
        toastr.error(error, "Thông báo!", {
          timeOut: 5000,
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
        console.log(error);
        console.log("Thất bại rồi xem lại code đi");
      }
    );
  };
  $scope.saveDraftHTML = function (event) {
    var content = $("#summernote").summernote("code");
    var strippedText = content.replace(/<\/?[^>]+(>|$)/g, " ");
    var words = strippedText.split(" ");
    var first30Words = words.slice(0, 70);
    var first30WordsString = first30Words.join(" ");
    var formData = {
      title: $scope.title,
      content: content,
      descriptive: first30WordsString,
      hashtag: $scope.list_of_string,
    };
    console.log(formData);
    $http.post(myArticleAPI + "/create-draft-article", formData).then(
      function (response) {
        toastr.success("Đã gửi lưu bản nháp thành công", "Thông báo!", {
          timeOut: 5000,
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
        console.log("Thành công rồi haha");
      },
      function (error) {
        toastr.error(error, "Thông báo!", {
          timeOut: 5000,
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
        console.log(error);
        console.log("Thất bại rồi xem lại code đi");
      }
    );
  };
  if (!window.location.href.includes("create-article")) {
    $scope.showButton = true;
    var id = $routeParams.id;
    $scope.getHtml = function () {
      var filePath =
        "../../../articles-project/src/main/resources/templates/articles/" +
        id +
        "/toi-thanh-cong-roi.html";
      $http.get(filePath).then(function (response) {
        $("#summernote")
          .summernote({
            focus: true,
          })
          .summernote("code", response.data);
      });
    };

    MyArticleService.fetchUpdateMyArticleById(id).then(function () {
      $scope.myUpdateArticleById = MyArticleService.getMyUpdateArticleById();
      console.log($scope.myUpdateArticleById);
      $scope.title = $scope.myUpdateArticleById.title;
      $scope.list_of_string = $scope.myUpdateArticleById.hashtags;
      $scope.status = $scope.myUpdateArticleById.status;
    });
  }

  $scope.updateMyArticle = function (event) {
    event.preventDefault();
    var content = $("#summernote").summernote("code");
    var strippedText = content.replace(/<\/?[^>]+(>|$)/g, " ");
    var words = strippedText.split(" ");
    var first30Words = words.slice(0, 70);
    var first30WordsString = first30Words.join(" ");
    var formData = {
      title: $scope.title,
      content: content,
      descriptive: first30WordsString,
      hashtag: $scope.list_of_string,
      status: $scope.status,
    };
    console.log(formData);

    $http.put(myArticleAPI + "/update-article/" + id, formData).then(
      function (response) {
        toastr.success("Đã gửi yêu cầu phê duyệt thành công", "Thông báo!", {
          timeOut: 5000,
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
        console.log("Thành công rồi haha");
      },
      function (error) {
        toastr.error("Có lỗi xảy ra");
        console.log(error);
        console.log("Thất bại rồi xem lại code đi");
      }
    );
  };

    $scope.updateMyArticleToCensor = function (event) {
      event.preventDefault();
      var content = $("#summernote").summernote("code");
      var strippedText = content.replace(/<\/?[^>]+(>|$)/g, " ");
      var words = strippedText.split(" ");
      var first30Words = words.slice(0, 70);
      var first30WordsString = first30Words.join(" ");
      var formData = {
        title: $scope.title,
        content: content,
        descriptive: first30WordsString,
        hashtag: $scope.list_of_string,
        status: $scope.status,
      };
      console.log(formData);

      $http.put(myArticleAPI + "/update-article-to-censor/" + id, formData).then(
        function (response) {
          toastr.success("Đã gửi yêu cầu phê duyệt thành công", "Thông báo!", {
            timeOut: 5000,
            closeButton: true,
            progressBar: true,
            positionClass: "toast-top-center",
          });
          console.log("Thành công rồi haha");
        },
        function (error) {
          toastr.error("Có lỗi xảy ra");
          console.log(error);
          console.log("Thất bại rồi xem lại code đi");
        }
      );
    };
};
