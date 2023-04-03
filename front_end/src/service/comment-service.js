app.service("CommentService", function ($http, env) {
  var comment = {};
  var listCommentByArticle = [];
  this.getCommentArticle = function () {
    return comment;
  };
  this.getListCommentByArticle = function () {
    return listCommentByArticle;
  };
  this.commentArticle = function () {
    var comment = {};
    return $http.post(env.API_URL + "/comment/create", comment).then(
      function (response) {
        if (response.status === 200) {
          comment = response.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };
  this.fetchComments = function (id) {
    return $http
      .get(env.API_URL + "/comment/detail-comment-article/" + id)
      .then(
        function (response) {
          var replies = response.data.data;
          let byId = new Map();
          var roots = [];

          replies.forEach((reply) => {
            byId.set(reply.id, reply);
          });

          replies.forEach((reply) => {
            if (reply.reply) {
              let parent = byId.get(reply.reply);
              if (parent.children) {
                parent.children.push(reply);
              } else {
                parent.children = [reply];
              }
            } else {
              roots.push(reply);
            }
          });
          listCommentByArticle = roots;
          return response;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };
});
