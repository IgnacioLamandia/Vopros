var app = angular.module('voprosApp',['ngResource','ui.router']);
app.controller('AppCtrl', function($resource,$state,$stateParams,Issues,Users) {
	'use strict';

    var self = this;

    self.issues = [];
    self.users = [];





    function errorHandler(error) {
        self.notificarError(error.data);
    }
    
    this.verIssues = function(){
    	$state.go('issues');
    };

    this.verUsers = function(){
        $state.go('users');
    };

	this.inicio = function(){
		$state.go('main');	
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
	
    this.getIssues();
    this.getUsers();

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
