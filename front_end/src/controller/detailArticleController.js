window.detailArticleCtrl = function (
  $scope,
  $http,
  $rootScope,
  $routeParams,
  ArticleService,
  CommentService,
  CategoryService,
  MyArticleService
) {
  $scope.comment = {
    articlesId: $routeParams.id,
    content: "",
  };

  $scope.replyComment = {
    articlesId: $routeParams.id,
    content: "",
    reply: "",
  };

  var id = $routeParams.id;

  MyArticleService.fetchMyArticleById(id).then(function () {
    $scope.myArticleById = MyArticleService.getMyArticleById();
    console.log($scope.myArticleById);
  });

  CommentService.fetchComments($routeParams.id).then(function(){
    $scope.comments = CommentService.getListCommentByArticle();
    console.log( $scope.comments)
  });

  $scope.deleteMyArticle = function () {
    toastr.warning("Bạn có chắc muốn xóa?");
    if (confirm("Bạn có chắc muốn xóa?")) {
      $http.delete(myArticleAPI + "/delete-article/" + id).then(
        function (response) {
          toastr.success("Xóa thành công");
          console.log("Thành công rồi haha");
        },
        function (error) {
          toastr.error("Có lỗi xảy ra");
          console.log(error);
          console.log("Thất bại rồi xem lại code đi");
        }
      );
    } else {
      toastr.info("Đã hủy xóa");
    }
  };

  $scope.replyComment = function(id, userName){
    var reply = document.getElementById(id)
    console.log(id)
    reply.style.display = "block"
    $scope.replyComment.reply = id
    $scope.replyComment.content = "@" +userName + " "
  }
  $scope.closeForm = function(id){
    var reply = document.getElementById(id)
    reply.style.display = "none"
  }
};
