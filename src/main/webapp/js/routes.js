
app.config(function ($stateProvider, $urlRouterProvider) {

console.log("funco");
  $urlRouterProvider.otherwise("/");

  $stateProvider
  
  
  .state('main', {
      url: "/",
      templateUrl: "partials/paginaPrincipal.html",
      controller: "AppCtrl as ctrl"
    })

    .state('login', {
      url: "/login",
      templateUrl: "partials/login.html",
      controller: "LoginCtrl as ctrl"
    })

    .state('issues', {
      url: "/issues",
      templateUrl: "partials/issues.html",
  	  controller: "AppCtrl as ctrl"
    })

    .state('users', {
      url: "/users",
      templateUrl: "partials/users.html",
      controller: "AppCtrl as ctrl"
    })
    
    .state('tasks', {
      url: "/tasks",
      templateUrl: "partials/tasks.html",
      controller: "TasksCtrl as ctrl"
    })

    .state('proyectos', {
      url: "/proyectos",
      templateUrl: "partials/proyectos.html",
      controller: "AppCtrl as ctrl"
    })

    .state('nuevoUser',{
        url:"/users/nuevo",
        templateUrl:"partials/nuevoUser.html",
        controller: "NuevoUserCtrl as ctrl"
    })

    .state('nuevoTask',{
        url:"/task/nuevo",
        templateUrl:"partials/nuevaTarea.html",
        controller: "NuevoTaskCtrl as ctrl"
    })
    
    .state('nuevoIssue',{
        url:"/issue/nuevo",
        templateUrl:"partials/nuevoIssue.html",
        controller: "NuevoIssueCtrl as ctrl"
    })



});
