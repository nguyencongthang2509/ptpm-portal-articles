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
    if (confirm("Bạn có chắc muốn xóa?")) {
      $http.delete(myArticleAPI + "/delete-article/" + id).then(
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
};
