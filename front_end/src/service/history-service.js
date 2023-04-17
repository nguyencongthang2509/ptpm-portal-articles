app.service("HistoryService", function ($http) {
  var histories = {};

  this.getHistory = function () {
    return histories;
  };

  // const obj = { a: 1, b: 2, c: 3 };
  // const reversedObj = Object.fromEntries(
  //   Object.entries(obj)
  //     .map(([key, value]) => [key, value])
  //     .reverse()
  // );
  // console.log(reversedObj);
  var groupByTimePeriod = function (obj, timestamp) {
    var objPeriod = {};
    var oneDay = 24 * 60 * 60 * 1000; // hours * minutes * seconds * milliseconds
    for (var i = 0; i < obj.length; i++) {
      var d = Math.floor(obj[i][timestamp] / oneDay) * 1000;
      objPeriod[d] = objPeriod[d] || [];
      objPeriod[d].push(obj[i]);
    }
    return objPeriod;
  };

  this.fetchHistories = function () {
    return $http.get(historyAPI).then(
      function (response) {
        if (response.status === 200) {
          histories = groupByTimePeriod(response.data.data, "createAt");
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
});
