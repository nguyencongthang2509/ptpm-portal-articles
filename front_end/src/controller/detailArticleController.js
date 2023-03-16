window.detailArticleCtrl = function (
  $scope,
  $http,
  $rootScope,
  $routeParams,
  ArticleService,
  CommentService,
  CategoryService
) {
  $scope.comment = {
    articlesId: $routeParams.id,
    content: "",
    reply:""
  }
  ArticleService.fetchArticles().then(function () {
    $scope.article = ArticleService.getArticle().filter((ar) => {
      return (ar.id = $routeParams.id);
    })[0];
  });
  
  CommentService.fetchComments($routeParams.id);

  $scope.commentArticle = CommentService.getCommentArticle().then(function(response){

  })
};
