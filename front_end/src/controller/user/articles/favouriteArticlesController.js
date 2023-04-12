window.favouriteArticleCtrl = function (
  $scope,
  $http,
  env,
  $rootScope,
  TymService
) {
  $scope.listArticleFavorite = {};

  TymService.fetchArticleFavorite().then(function () {
    $scope.listArticleFavorite = TymService.getArticleFavorite();
  });
  document
    .getElementById("defaultCheck1")
    .addEventListener("click", function () {
      var checkboxes = document.querySelectorAll(
        ".form-check-input:not(#defaultCheck1)"
      );
      if (this.checked) {
        for (var i = 0; i < checkboxes.length; i++) {
          checkboxes[i].checked = true;
        }
      } else {
        for (var i = 0; i < checkboxes.length; i++) {
          checkboxes[i].checked = false;
        }
      }
      $scope.checkboxChanged();
    });
  $scope.checkboxChanged = function () {
    var count = 0;
    var checkboxes = document.querySelectorAll("table input[type=checkbox]");
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i].checked) {
        count++;
        console.log(count);
      }
    }
    if (count >= 2) {
      document.getElementById("deleteBtn").style.display = "block";
    } else {
      document.getElementById("deleteBtn").style.display = "none";
    }
  };
  $scope.deleteSelected = function () {
    alert("Đã xóa tất cả bài viết đã tym");
  };
  $scope.unfavoriteArticle = function (key, index, articleId) {
    $http
      .delete(env.API_URL + "/tym/unfavorite-article/" + articleId)
      .then(function (response) {
        $scope.listArticleFavorite[key].splice(index, 1);
        var isEmpty = $scope.listArticleFavorite[key].filter(function (val) {
          return val !== null || val !== "";
        }).length;
        if (isEmpty === 0) {
          delete $scope.listArticleFavorite[key];
        }
      });
  };
};
