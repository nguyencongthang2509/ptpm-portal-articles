window.detailArticleCtrl = function (
    $scope,
    $http,
    $rootScope,
    $routeParams,
    CensorArticleService
  ) {

    $scope.approve = {
      id: $routeParams.id,
      feedback: ""
    }

    $scope.refuse = {
      id: $routeParams.id,
      feedback: ""
    }

    CensorArticleService.fetchArticleById($routeParams.id).then(function () {
      $scope.myArticleById = CensorArticleService.getArticleById();
    });

    $scope.approveArticle = function(event){
      event.preventDefault()
      $http.put(censorArticleAPI+"/approve-article",$scope.approve ).then(function(response){
        toastr.success("Successful approval", "Thông báo!", {
          timeOut: 3000,
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
      })
    }
    
    $scope.refuseArticle = function(event){
      event.preventDefault()
      $http.put(censorArticleAPI+"/refuse-article",$scope.refuse ).then(function(response){
        toastr.success("Successful refuse", "Thông báo!", {
          timeOut: 3000,
          closeButton: true,
          progressBar: true,
          positionClass: "toast-top-center",
        });
      })
    }
  
  };
  