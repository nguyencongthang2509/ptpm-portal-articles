window.historyCtrl = function (
  $scope,
  $http,
  localStorageService,
  env,
  AlbumService,
  HistoryService
) {
  $scope.listHistory = {};
  HistoryService.fetchHistories().then(function () {
    $scope.listHistory = HistoryService.getHistory();
    console.log($scope.listHistory);
  });
};
