var app = angular.module('voprosApp',['ngResource','ui.router','satellizer']);
app.controller('AppCtrl', function($resource,$state,$stateParams,Issues,Users,Proyectos,Proyecto) {
	'use strict';

    var self = this;

    self.users = [];
    self.proyectos = [];
    self.overlay = $(".overlay");
    self.proyecto = {};

    this.getProyecto=function(){

        Proyecto.query({id:$stateParams.proyectoId},function(data){
            self.proyecto=data;
            console.log(data);
            self.users = data.miembros;
        },errorHandler);
    }



    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.closeSideBar = function(){
        this.overlay.hide();
        
    }
    
    this.verIssues = function(){
    	$state.go('main.issues');
        this.closeSideBar();
    };

    this.verUsers = function(){
        $state.go('main.users');
        this.closeSideBar();

    };
    
    this.verTasks = function(){
    	$state.go('main.tasks');
        this.closeSideBar();
    };

    //this.nuevoUser = function(){
    //    $state.go('main.nuevoUser');
    //    this.closeSideBar();

    //}

    this.nuevoTask = function(){
        $state.go('main.nuevoTask');
        this.closeSideBar();
    }
    
    this.nuevoIssue = function(){
        $state.go('main.nuevoIssue');
        this.closeSideBar();
    }



    this.verProyectos = function(){
        $state.go('proyectos',{username:$stateParams.username});

    };

	this.inicio = function(){
		$state.go('main.home');	
        this.closeSideBar();

	}

	this.getIssues= function(){
        Issues.query(function(data) {
            self.issues = data;
        },errorHandler);
	};
	


 


    //this.getProyectos = function(){
    //    Proyectos.query(function(data){
    //        self.proyectos = data;
    //    },errorHandler);
    //};
	
    //this.getIssues();
    //this.getProyectos();
    this.getProyecto();

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
