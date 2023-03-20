app.service("UserService", function ($http, env) {
    var user = {};


    this.getUser = function () {
        return user;
    };
    this.setUser = function (data) {
        user = data;
    };

    this.fetchDetailUser = function () {
        return $http.get(env.API_URL + "/user/detail/"+ env.USER_ID).then(
            function (response) {
                response.data.data.map(item =>{
                    user = item;
                })
                return response;
            },
            function (errors) {
                console.log(errors);
            }
        );
    };


});

