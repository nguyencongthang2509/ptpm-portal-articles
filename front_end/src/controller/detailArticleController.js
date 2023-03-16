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
};
