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
    reply: "",
  };

  var id = $routeParams.id;

  MyArticleService.fetchMyArticleById(id).then(function () {
    $scope.myArticleById = MyArticleService.getMyArticleById();
    console.log($scope.myArticleById);
  });

  CommentService.fetchComments($routeParams.id);

  // $scope.commentArticle = CommentService.getCommentArticle().then(function(response){

  // })
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
};
