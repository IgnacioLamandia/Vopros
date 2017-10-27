var app = angular.module('voprosApp',['ngResource','ui.router']);
app.controller('AppCtrl', function($resource,$state,$stateParams,Issues,Users,Proyectos) {
	'use strict';

    var self = this;

    self.issues = [];
    self.users = [];
    self.proyectos = [];
    self.overlay = $(".overlay");




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

    this.nuevoUser = function(){
        $state.go('main.nuevoUser');
        this.closeSideBar();

    }

    this.nuevoTask = function(){
        $state.go('main.nuevoTask');
        this.closeSideBar();
    }
    
    this.nuevoIssue = function(){
        $state.go('main.nuevoIssue');
        this.closeSideBar();
    }
    
    this.borrarIssue = function(){
        $state.go('main.borrarIssue');
        this.closeSideBar();
    }
    
    this.borrarTask = function(){
        $state.go('main.borrarTask');
        this.closeSideBar();
    }

    this.verProyectos = function(){
        $state.go('main.proyectos');
        this.closeSideBar();

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
	


    this.getUsers = function(){
        Users.query(function(data) {
            self.users = data;
        },errorHandler);
    };


    this.getProyectos = function(){
        Proyectos.query(function(data){
            self.proyectos = data;
        },errorHandler);
    };
	
    this.getIssues();
    this.getUsers();
    this.getProyectos();

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
