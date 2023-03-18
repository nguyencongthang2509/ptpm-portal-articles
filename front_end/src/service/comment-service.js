app.service("CommentService", function ($http, env) {
  var comment = {};
  this.getCommentArticle = function () {
    return comment;
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
          let roots = [];
          let byId = new Map();

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
          var codeHtml = "";
          function show(replies, depth = 0) {
            replies.map((item) => {
              codeHtml +=
                `
              <div class="d-flex flex-start" style= " margin-left:` +
                depth +
                `%">
              <img class="rounded-circle shadow-1-strong me-3"
              ` +
                `src="` +
                item.userImg +
                `" alt="avatar" style="width: 5%;height: 5%;" />
                        <div class="flex-grow-1 flex-shrink-1">
                          <div>
                            <div class="d-flex justify-content-between align-items-center">
                              <p class="mb-1">
                                Maria Smantha <span class="small">` +
                `</span>
                              </p>
                              <a href="#!"><i class="fas fa-reply fa-xs"></i><span class="small"> reply</span></a>
                            </div>
                            <p class="small mb-0">` +
                item.content +
                `</p>
                          </div>
                        </div>
              </div>
              `;
              show(item.children || [], depth + 5);
            });
          }
          show(roots);
          var renderHtml = document.querySelector(".comments");
          renderHtml.innerHTML = codeHtml;
        },
        function (errors) {
          console.log(errors);
        }
      );
  };
});
