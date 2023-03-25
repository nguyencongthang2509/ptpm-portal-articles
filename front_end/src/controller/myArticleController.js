window.myArticleCtrl = function (
  $scope,
  $http,
  MyArticleService,
  CategoryService,
  AlbumService,env
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
  $scope.listAlbum = [];
  $scope.listAlbumDefault = [];
  $scope.nameAlbum = "";
  $scope.createAlbumRequest = {
    title: "",
    status: true,
  };
  $scope.UserCreateArticle = {
    articlesId: "",
    albumId: "",
  };

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

   // begin album

   $scope.showModalAddArticleToAlbum = function (id) {
    $scope.UserCreateArticle.articlesId = id;
    AlbumService.fetchSimpleAlbums(id).then(function () {
      $scope.listAlbum = AlbumService.getSimpleAlbums();
      $scope.listAlbumDefault = AlbumService.getSimpleAlbums();
    });
  };

  $scope.addArticleToAlbum = function (id) {
    $scope.UserCreateArticle.albumId = id;
    if (document.getElementById(id).checked) {
      $http
        .post(env.API_URL + "/album/add-article", $scope.UserCreateArticle)
        .then(function (response) {
          toastr.success("thêm thành công");
        });
    } else {
      $http
        .delete(
          env.API_URL +
            "/album/delete-all-article?articleId=" +
            $scope.UserCreateArticle.articlesId +
            "&albumId=" +
            id
        )
        .then(function (response) {
          toastr.error("xóa thành công");
        });
    }
  };

  $scope.createAlbum = function (event) {
    event.preventDefault();
    if ($scope.createAlbumRequest.title != "") {
      $http
        .post(env.API_URL + "/album/create", $scope.createAlbumRequest)
        .then(function (respone) {
          $scope.album = respone.data.data;
          $scope.album.countArticle = 0;
          $scope.listAlbum.push($scope.album);
          $scope.createAlbumRequest = { title: "" };
        });
    }
    document.getElementById("formThemMoi").style.display = "none";
    document.getElementById("createAlbum").style.display = "block";
  };

  $scope.showCreateAlbum = function () {
    document.getElementById("formThemMoi").style.display = "block";
    document.getElementById("createAlbum").style.display = "none";
  };

  $scope.searchAlbum = function () {
    $scope.listAlbum = $scope.listAlbumDefault;
    if ($scope.nameAlbum.trim() == "") {
      $scope.listAlbum = $scope.listAlbumDefault;
    } else {
      var albums = [];
      $scope.listAlbum.map((item) => {
        if (item.title !== null && item.title.includes($scope.nameAlbum)) {
          albums.push(item);
        }
      });
      $scope.listAlbum = albums;
    }
  };
  $scope.closeFormAddAlbum = function () {
    document.querySelectorAll("input:checked").forEach((item) => {
      item.checked = false;
    });
  };
  //  end album
};
