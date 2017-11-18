app.controller('IssuesCtrl', function($resource,$timeout,$state,$stateParams,Proyecto,Issue) {
	'use strict';

    var self = this;

    self.issues = [];
    self.issue = null;


    this.getIssues=function(){

        Proyecto.query({id:$stateParams.proyectoId},function(data){
            self.issues=data.issues;
            console.log(data.issues);
        },errorHandler);
    }


    function errorHandler(error) {
        self.notificarError(error.data);
    }

    
	//this.getIssues= function(){
    //    Issues.query(function(data) {
    //        self.issues = data;
    //    },errorHandler);
	//};
	


    this.seleccionar= function(problema){
        console.log(problema.issue);
 //       var tr =document.getElementById(problema.titulo);
        if(problema.issue == this.issue){
        	this.issue = null;
//			tr.style.backgroundColor = 'white';
        }else{

 //       	if(this.issue !== null){
 //       		document.getElementById(this.issue.titulo).style.backgroundColor='white';
 //       	}
        	
        	this.issue = problema.issue;
   //     	tr.style.backgroundColor = 'red';

        }



    }

	this.editarIssue= function(){
        $state.go("main.editarIssue",{issueID:this.issue.id})
    }


    this.delete= function(){
        Issue.delete({id:this.issue.id,idProyecto:$stateParams.proyectoId},function(){ 
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
