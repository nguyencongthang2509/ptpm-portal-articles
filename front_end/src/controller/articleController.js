window.articleCtrl = function ($scope, $http) {
  $scope.articles = [];
  $http.get(apiURL + "/article").then(function (respone) {
    $scope.articles = respone.data.data.data;
    console.log($scope.articles);
  });
  $scope.categories = [];
  $http.get(apiURL + "/category").then(function (response) {
    $scope.categories = response.data.data;
  });

  // $http.get(apiURL + "/article").then(function (response) {
  //   $scope.articles = response.data.data;
  //   var content = response.data.data[3].content;
  //   var $html = $($.parseHTML(content));
  //   console.log($html);
  //   var images = $html.find("img");
  //   console.log(images.length);
  //   $scope.imgs = [];
  //   for (var i = 0; i < images.length; i++) {
  //     var src = images[i].src;
  //     $scope.imgs.push(src);
  //   }
  //   console.log($scope.imgs);
  // });
};
