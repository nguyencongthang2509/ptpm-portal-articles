app.service("ArticleTrashService", function ($http) {
  var trashes = [];
  this.getTrash = function () {
    return trashes;
  };
  this.setTrash = function (data) {
    trashes = data;
  };

  this.fetchTrashes = function () {
    return $http.get(articleTrashAPI).then(
      function (response) {
        if (response.status === 200) {
          trashes = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
