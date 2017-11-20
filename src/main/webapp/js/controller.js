var app = angular.module('voprosApp',['ngResource','ui.router','satellizer','duScroll','ui.bootstrap']);
app.controller('AppCtrl', function($resource,$state,$stateParams,$auth,$location,Issues,Users,Proyectos,Proyecto,UserByUsername) {
	'use strict';

    var self = this;

    self.user={};
    self.users = [];
    self.proyectos = [];
    self.overlay = $(".overlay");
    self.proyecto = {};

/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
this.myFunction=function(id) {
    document.getElementById(id).classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it

    this.getProyecto=function(){

        Proyecto.query({id:$stateParams.proyectoId},function(data){
            self.proyecto=data;
            console.log(data);
            self.users = data.miembros;
        },errorHandler);
    }

    this.getUsuario= function(){
        console.log($stateParams.username);
        UserByUsername.query({username:$stateParams.username},function(data){
            self.notificarMensaje("usuario encontrado");
            self.user= data;
        },errorHandler);
    };



    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.closeSideBar = function(){
        this.overlay.hide();
        
    }
    
    this.verIssues = function(){
    	$state.go('main.issues');
        this.myFunction("problemas");
    };

    this.verUsers = function(){
        $state.go('main.users');
        this.myFunction("usuarios");

    };
    
    this.verTasks = function(){
    	$state.go('main.tasks');
        this.myFunction("tareas");
    };

    //this.nuevoUser = function(){
    //    $state.go('main.nuevoUser');
    //    this.closeSideBar();

    //}

    this.nuevoTask = function(){
        $state.go('main.nuevoTask');
        this.myFunction("tareas");
    }
    
    this.nuevoIssue = function(){
        $state.go('main.nuevoIssue');
        this.myFunction("problemas");
    }



    this.verProyectos = function(){
        $state.go('proyectos',{username:$stateParams.username});

    };

	this.inicio = function(){
		$state.go('main.home');	

	}

	this.getIssues= function(){
        Issues.query(function(data) {
            self.issues = data;
        },errorHandler);
	};

    this.logout= function(){
        $location.path('/login');
        $auth.logout();
    };
	


 


    //this.getProyectos = function(){
    //    Proyectos.query(function(data){
    //        self.proyectos = data;
    //    },errorHandler);
    //};
	
    //this.getIssues();
    //this.getProyectos();
    this.getProyecto();
    this.getUsuario();

    console.log("users",self.users)
    console.log("issues",self.issues)

    this.msgs = [];
    this.notificarMensaje = function(mensaje) {
        this.msgs.push(mensaje);
        this.notificar(this.msgs);
    };

    this.errors = [];
    this.notificarError = function(mensaje) {
        this.errors.push(mensaje);
        this.notificar(this.errors);
    };

    this.notificar = function(mensajes) {
        $timeout(function() {
            while (mensajes.length > 0) mensajes.pop();
        }, 3000);
    }
});
