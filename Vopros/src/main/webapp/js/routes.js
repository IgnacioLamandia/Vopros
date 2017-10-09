
app.config(function ($stateProvider, $urlRouterProvider) {

console.log("funco");
  $urlRouterProvider.otherwise("/");

  $stateProvider
  
  
  .state('main', {
      url: "/",
      templateUrl: "partials/paginaPrincipal.html",
      controller: "AppCtrl as ctrl"
    })

    .state('issues', {
      url: "/issues",
      templateUrl: "partials/issues.html",
  	controller: "AppCtrl as ctrl"
    })


});
