window.myArticleCtrl = function (
  $scope,
  $http,
  MyArticleService,
  CategoryService
) {
  CategoryService.fetchCategories().then(function () {
    $scope.listCategory = CategoryService.getCategory();
  });
  $scope.listStatus = [
    {
      name: "Bản nháp",
      value: 1,
    },
    {
      name: "Chờ phê duyệt",
      value: 2,
    },
    {
      name: "Đã phê duyệt",
      value: 3,
    },
    {
      name: "Đã bị hủy",
      value: 4,
    },
  ];
  $scope.checkStatus = true;
  $scope.selectedValue = "all";
  $scope.title = "";

  $scope.findMyArticleByStatus = function (event) {
    $scope.abc = event.target.value;
    $scope.api = "";
    if ($scope.abc === "all") {
      var textSearch = $scope.title;
      $scope.api = myArticleAPI + "?title=" + textSearch;
      $scope.checkStatus = false;
    } else {
      var findMyArticleByStatusRequest = {
        title: $scope.title,
        status: $scope.abc,
      };
      $scope.api =
        myArticleByStatusAPI +
        "?title=" +
        findMyArticleByStatusRequest.title +
        `&status=` +
        findMyArticleByStatusRequest.status;
      $scope.checkStatus = true;
    }

    MyArticleService.fetchMyArticlesByStatus($scope.api, 0).then(function () {
      $scope.listMyArticleByStatus = MyArticleService.getMyArticleByStatus();
      $scope.totalPages = MyArticleService.getTotalPages();
      $scope.currentPage = MyArticleService.getCurrentPage();
      $scope.pageModel = $scope.currentPage + 1;
      // console.log($scope.listMyArticleByStatus);
      // console.log($scope.totalPages);
      // console.log($scope.currentPage);
    });
  };

  $scope.findSearch = function () {
    if ($scope.abc === "all" || $scope.abc === null) {
      var textSearch = $scope.title;
      $scope.api = myArticleAPI + "?title=" + textSearch;
    } else {
      var findMyArticleByStatusRequest = {
        title: $scope.title,
        status: $scope.abc,
      };
      $scope.api =
        myArticleByStatusAPI +
        "?title=" +
        findMyArticleByStatusRequest.title +
        `&status=` +
        findMyArticleByStatusRequest.status;
    }
    console.log($scope.api);
    MyArticleService.fetchMyArticlesByStatus($scope.api, 0).then(function () {
      $scope.listMyArticleByStatus = MyArticleService.getMyArticleByStatus();
      $scope.totalPages = MyArticleService.getTotalPages();
      $scope.currentPage = MyArticleService.getCurrentPage();
      $scope.pageModel = $scope.currentPage + 1;
      // console.log($scope.listMyArticleByStatus);
      // console.log($scope.totalPages);
      // console.log($scope.currentPage);
    });
  };

  $scope.findMyArticleByStatus({ target: { value: $scope.selectedValue } });

  $scope.nextPage = function () {
    $scope.currentPage++;
    $scope.api = "";
    var textSearch = $scope.title;
    if (($scope.checkStatus = true)) {
      $scope.api = myArticleAPI + "?title=" + textSearch;
    } else {
      var findMyArticleByStatusRequest = {
        title: $scope.title,
        status: $scope.abc,
      };
      $scope.api =
        myArticleByStatusAPI +
        "?title=" +
        findMyArticleByStatusRequest.title +
        `&status=` +
        findMyArticleByStatusRequest.status;
    }
    if ($scope.currentPage >= $scope.totalPages) {
      $scope.pageModel = $scope.totalPages;
    }
    MyArticleService.fetchMyArticlesByStatus(
      $scope.api,
      $scope.currentPage
    ).then(function (respone) {
      $scope.listMyArticleByStatus = MyArticleService.getMyArticleByStatus();
      $scope.pageModel = $scope.currentPage + 1;
    });
  };

  $scope.prevPage = function () {
    $scope.currentPage--;
    $scope.api = "";
    var textSearch = $scope.title;
    if (($scope.checkStatus = true)) {
      $scope.api = myArticleAPI + "?title=" + textSearch;
    } else {
      var findMyArticleByStatusRequest = {
        title: $scope.title,
        status: $scope.abc,
      };
      $scope.api =
        myArticleByStatusAPI +
        "?title=" +
        findMyArticleByStatusRequest.title +
        `&status=` +
        findMyArticleByStatusRequest.status;
    }
    if ($scope.currentPage <= 0) {
      $scope.currentPage = 0;
    }
    MyArticleService.fetchMyArticlesByStatus(
      $scope.api,
      $scope.currentPage
    ).then(function (respone) {
      $scope.listMyArticleByStatus = MyArticleService.getMyArticleByStatus();
      $scope.pageModel = $scope.currentPage + 1;
    });
  };
};
