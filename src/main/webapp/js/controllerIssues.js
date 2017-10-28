app.controller('IssuesCtrl', function($resource,$timeout,$state,$stateParams,Issues,Issue) {
	'use strict';

    var self = this;

    self.issues = [];
    self.issue = {};




    function errorHandler(error) {
        self.notificarError(error.data);
    }

    
	this.getIssues= function(){
        Issues.query(function(data) {
            self.issues = data;
        },errorHandler);
	};
	


    this.seleccionar= function(problema){
        console.log(problema);
        this.issue = problema;
    }

    this.delete= function(){
        Issue.delete({id:this.issue.id},function(){
            self.notificarMensaje("Issue borrado");
            self.getIssues();

        },errorHandler);
    }

 
	
    this.getIssues();

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
