var myApp = angular.module("myApp", []);
myApp.controller("myController", ['$scope', function($scope){
  $scope.gmail= {
    username: "",
    email: ""

  };
  scope.onGoogleLogin = function(){
    var params = {
      'clientid': '516688365112-1v367fes7clp6f3bqnr9c1ff09mak6h9.apps.googleusercontent.com',
      'cookiepolicy': 'single_host_origin',
      'callback': function(result){

      },
      'approvalprompt': 'force',
      'scope': 'https:://www.googleapis.com/auth/plus.login https:://www.googleapis.com/auth/plus.profile.emails.read '

    }

    gapi.auth.signIn(params);
  }

}]);

