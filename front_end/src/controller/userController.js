function userController($scope, $http, env){
     $scope.user = {}
     $scope.albums = []
     $scope.album ={title: ""}
     $scope.showModalCreate = false

     $scope.openModalCreate = function(){
      $scope.showModalCreate = true
     }

     $scope.closeModalCreate = function(){
      $scope.showModalCreate = false
     }

     $http.get(env.API_URL+"/user/detail/"+ env.USER_ID).then(function(respone){
          respone.data.data.map(item =>{
            $scope.user = item
          })
        console.log($scope.user)
     })

     $http.get(env.API_URL+"/album").then(function(respone){
      $scope.albums = respone.data.data
 })

 $scope.createAlbum = function(event){
  event.preventDefault()
  if($scope.formThemMoi.$valid){
    $http.post(env.API_URL+"/album/create", $scope.album).then(function(respone){
      $scope.albums.push(respone.data.data)
      // $('#createAlbum').modal('hide');
   }
   )}
  }
  
     
}