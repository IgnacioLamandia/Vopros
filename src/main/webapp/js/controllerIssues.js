app.controller('IssuesCtrl', function($resource,$timeout,$state,$stateParams,Issues,Issue) {
	'use strict';

    var self = this;

    self.issues = [];
    self.issue = null;




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
        var tr =document.getElementById(problema.titulo);
        if(problema == this.issue){
        	this.issue = null;
			tr.style.backgroundColor = 'white';
        }else{

        	if(this.issue !== null){
        		document.getElementById(this.issue.titulo).style.backgroundColor='white';
        	}
        	
        	this.issue = problema;
        	tr.style.backgroundColor = 'red';

        }


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
