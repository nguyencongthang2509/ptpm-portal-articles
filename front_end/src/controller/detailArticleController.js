window.detailArticleCtrl = function (
  $scope,
  $http,
  $rootScope,
  $routeParams,
  ArticleService,
  CommentService,
  CategoryService,
  env,
  MyArticleService,
  UserService
) {
  $scope.comment = {
    articlesId: $routeParams.id,
    content: "",
  };

  $scope.replyCommentUser = {
    articlesId: $routeParams.id,
    content: "",
    reply: "",
  };

  MyArticleService.fetchMyArticleById($routeParams.id).then(function () {
    $scope.myArticleById = MyArticleService.getMyArticleById();
  });

  CommentService.fetchComments($routeParams.id).then(function () {
    $scope.comments = CommentService.getListCommentByArticle();
  });

  UserService.fetchDetailUser().then(function (respone) {
    $scope.detailUser = UserService.getUser();
    console.log($scope.detailUser);
  });

  $scope.deleteMyArticle = function () {
    if (confirm("Bạn có chắc muốn xóa?")) {
      $http.delete(myArticleAPI + "/delete-article/" + $routeParams.id).then(
        function (response) {
          toastr.success("Xóa thành công", "Thông báo!", {
            timeOut: 5000,
            closeButton: true,
            progressBar: true,
            positionClass: "toast-top-center",
          });
          console.log("Thành công rồi haha");
        },
        function (error) {
          toastr.error("Có lỗi xảy ra", "Thông báo!", {
            timeOut: 5000,
            closeButton: true,
            progressBar: true,
            positionClass: "toast-top-center",
          });
          console.log(error);
          console.log("Thất bại rồi xem lại code đi");
        }
      );
    } else {
      toastr.info("Đã hủy xóa");
    }
  };

  $scope.replyComment = function (id, userName) {
    [...document.querySelectorAll(".fromReplyComment")].map((item) => {
      item.style.display = "none";
    });
    var reply = document.getElementById(id);
    reply.style.display = "block";
    $scope.replyCommentUser.reply = id;
    $scope.replyCommentUser.content = "@" + userName + " ";
  };
  $scope.closeForm = function (id) {
    var reply = document.getElementById(id);
    reply.style.display = "none";
  };

  $scope.createComment = function () {
    $http
      .post(env.API_URL + "/comment/create", $scope.comment)
      .then(function (response) {
        $scope.comment = {
          articlesId: $routeParams.id,
          content: "",
        };
      });
  };
  $scope.CreateReplyComment = function () {
    $http
      .post(env.API_URL + "/comment/create", $scope.replyCommentUser)
      .then(function (response) {
        $scope.replyCommentUser = {
          articlesId: $routeParams.id,
          content: "",
          reply: "",
        };
      });
  };
};
