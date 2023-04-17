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

  $scope.index = -1;

  $scope.replyCommentUser = {
    articlesId: $routeParams.id,
    content: "",
    reply: "",
  };
  MyArticleService.fetchMyArticleById($routeParams.id).then(function () {
    $scope.myArticleById = MyArticleService.getMyArticleById();
    if ($scope.myArticleById.hashtag == null) {
    } else {
      $scope.select2Options = {
        multiple: true,
        simple_tags: true,
        tags: $scope.myArticleById.hashtag.split(","),
        tokenSeparators: ["/", ",", ";"],
      };
    }
  });

  CommentService.fetchComments($routeParams.id).then(function () {
    $scope.comments = CommentService.getListCommentByArticle();
  });

  UserService.fetchDetailUser().then(function (respone) {
    $scope.detailUser = UserService.getUser();
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

  const socket = new SockJS(
    "http://localhost:8080/portal-articles-websocket-endpoint"
  );

  const stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    let sessionId = /\/([^\/]+)\/websocket/.exec(
      stompClient.ws._transport.url
    )[1];
    stompClient.subscribe(
      "/portal-articles/create-comment/" + $routeParams.id,
      function (message) {
        let objComment = JSON.parse(message.body).data.comment;
        let objUser = JSON.parse(message.body).data.user;
        let newObj = {
          id: objComment.id,
          content: objComment.content,
          reply: objComment.reply,
          userID: objUser.id,
          userImg: objUser.img,
          userName: objUser.name,
          createdDate: objComment.createdDate,
        };
        if ($scope.index == -1) {
          $scope.comments.push(newObj);
        } else {
          $scope.comments[$scope.index].children.push(newObj);
        }
        $scope.$apply();
      }
    );
  });
  $scope.createComment = function () {
    stompClient.send(
      "/action/create-comment/" + $routeParams.id,
      {},
      JSON.stringify($scope.comment)
    );
  };

  $scope.contentOfReplyValue = {
    value: "",
  };
  $scope.replyComment = function (index, id, userName) {
    [...document.querySelectorAll(".fromReplyComment")].map((item) => {
      item.style.display = "none";
    });
    var reply = document.getElementById(id);
    reply.style.display = "block";
    $scope.replyCommentUser.reply = id;
    $scope.replyCommentUser.content = "@" + userName + " ";
    $scope.index = index;
  };
  $scope.closeForm = function (id) {
    var reply = document.getElementById(id);
    reply.style.display = "none";
    $scope.index = -1;
  };

  // stompClient.connect({}, function (frame) {
  //   let sessionId = /\/([^\/]+)\/websocket/.exec(
  //     stompClient.ws._transport.url
  //   )[1];
  //   stompClient.subscribe(
  //     "/portal-articles/create-comment/" + $routeParams.id,
  //     function (message) {
  //       console.log(message);
  //       let objComment = JSON.parse(message.body).data.comment;
  //       let objUser = JSON.parse(message.body).data.user;
  //       let newObj = {
  //         id: objComment.id,
  //         content: objComment.content,
  //         reply: objComment.reply,
  //         userID: objUser.id,
  //         userImg: objUser.img,
  //         userName: objUser.name,
  //         createdDate: objComment.createdDate,
  //       };
  //       console.log(newObj);
  //       $scope.comments.push(newObj);
  //       $scope.$apply();
  //     }
  //   );
  // });
  $scope.CreateReplyComment = function (id) {
    console.log(id);
    var reply = document.getElementById(id);
    reply.style.display = "none";
    $scope.index = -1;
    stompClient.send(
      "/action/create-comment/" + $routeParams.id,
      {},
      JSON.stringify($scope.replyCommentUser)
    );
  };
};
