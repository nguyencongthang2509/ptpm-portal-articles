app.service("CategoryService", function ($http) {
  var categories = [];

  this.getCategory = function () {
    return categories;
  };

  this.setCategory = function (data) {
    categories = data;
  };

  this.fetchCategories = function () {
    return $http.get(categoryAPI).then(
      function (response) {
        if (response.status === 200) {
          categories = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
