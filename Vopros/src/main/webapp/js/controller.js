var app = angular.module('voprosApp',['ngResource','ui.router']).controller('AppCtrl', function($resource,$state,$stateParams,Issues) {
	'use strict';

    var self = this;

self.issues = [];





   function errorHandler(error) {
        self.notificarError(error.data);
    }
    this.verIssues = function(){
	console.log("funcoTransicion");
    	$state.go('issues');
    };

this.getIssues= function(){
    Issues.query(function(data) {
        self.issues = data;
    },errorHandler);
};
this.getIssues();

console.log(self.issues);

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
