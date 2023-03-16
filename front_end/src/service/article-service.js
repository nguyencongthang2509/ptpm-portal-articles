app.service("ArticleService", function ($http, env, $routeParams) {
  var articles = [];
  this.getArticle = function () {
    return articles;
  };
  this.setArticle = function (data) {
    articles = data;
  };
  
  this.fetchArticles = function () {
    return $http.get(articleAPI + "?_expand=category").then(
      function (response) {
        if (response.status === 200) {
          articles = response.data.data.data;
        }
        return response;
      },
      function (errors) {
        console.log(errors);
      }
    );
  };

  this.fetchComments = function(){
    $http.get(env.API_URL +"/comment/detail-comment-article/"+ $routeParams.id).then(function (response) {
      var replies = response.data.data
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
      var codeHtml =""
      // console.log(roots);
      function show(replies, depth = 0) {
        replies.map(item =>{
          // console.log(" ".repeat(depth) + item.content);
          // <img class="rounded-circle shadow-1-strong me-3"
          //             `+` data-ng-src="data:image/png;base64,`+item.userImg+`" alt="avatar" width="65"
          //             height="65" />
          codeHtml += `
          <div class="d-flex flex-start">
                    
                    <div class="flex-grow-1 flex-shrink-1">
                      <div>
                        <div class="d-flex justify-content-between align-items-center">
                          <p class="mb-1">
                            Maria Smantha <span class="small">- 2 hours ago</span>
                          </p>
                          <a href="#!"><i class="fas fa-reply fa-xs"></i><span class="small"> reply</span></a>
                        </div>
                        <p class="small mb-0">`+
                          item.content
                        +`</p>
                      </div>
          `
          show(item.children || [], depth + 0.1);
        })
      }
     
      show(roots);
      var renderHtml = document.querySelector(".comments")
          renderHtml.innerHTML = codeHtml
    });
  }
});
