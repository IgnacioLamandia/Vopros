
app.config(function ($stateProvider, $urlRouterProvider) {

console.log("funco");
  $urlRouterProvider.otherwise("/");

  $stateProvider

    .state('login', {
      url: "/",
      templateUrl: "partials/paginaLogeo.html",
      controller: "LoginCtrl as ctrl"
    })

    .state('proyectos',{
      url: "/proyectos/:username",
      templateUrl:"partials/proyectosUser.html",
      controller: "ProyectosUserCtrl as ctrl"
    })
  
    .state('main', {
      url: "/main/:proyectoId/:username",
      templateUrl: "partials/main.html",
      controller: "AppCtrl as ctrl"
    })
  
  .state('main.home', {
      url: "/home",
      templateUrl: "partials/paginaPrincipal.html",
      controller: "AppCtrl as ctrl"
    })


    .state('main.issues', {
      url: "/issues",
      templateUrl: "partials/issues.html",
  	  controller: "IssuesCtrl as ctrl"
    })

    .state('main.users', {
      url: "/users",
      templateUrl: "partials/users.html",
      controller: "AppCtrl as ctrl"
    })
    
    .state('main.tasks', {
      url: "/tasks",
      templateUrl: "partials/tasks.html",
      controller: "TasksCtrl as ctrl"
    })

    .state('main.proyectos', {
      url: "/proyectos",
      templateUrl: "partials/proyectos.html",
      controller: "ProyectosCtrl as ctrl"
    })

    .state('nuevoUser',{
        url:"/users/nuevo",
        templateUrl:"partials/nuevoUser.html",
        controller: "NuevoUserCtrl as ctrl"
    })

    .state('main.nuevoTask',{
        url:"/task/nuevo",
        templateUrl:"partials/nuevaTarea.html",
        controller: "NuevoTaskCtrl as ctrl"
    })
    
    .state('main.nuevoIssue',{
        url:"/issue/nuevo",
        templateUrl:"partials/nuevoIssue.html",
        controller: "NuevoIssueCtrl as ctrl"
    })

    .state('nuevoProyecto',{
        url:"/proyecto/nuevo/:username",
        templateUrl:"partials/nuevoProyecto.html",
        controller: "NuevoProyectoCtrl as ctrl"
    })



});
