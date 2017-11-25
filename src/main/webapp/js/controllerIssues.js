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


    this.cambiarEstado = function(issueDropped,estadoD){
        issueDropped.estado=estadoD;
        Issue.update({id:issueDropped.id},issueDropped,function(){
            console.log("Estado actualizado");
        },errorHandler)
    }

    this.ordenarIssues=function(){
        for(var i=0;i<this.issues.length;i++){
            console.log(this.issues[i].titulo);
            var t=document.getElementById(this.issues[i].titulo);
            console.log(t);
            var tr = document.getElementById(this.issues[i].estado);
            console.log(tr);
            tr.appendChild(t);
            
        }
    }

      window.allowDrop = function(ev) {
    ev.preventDefault();
    if (ev.target.getAttribute("draggable") == "true")
        ev.dataTransfer.dropEffect = "none"; // dropping is not allowed
    else
        ev.dataTransfer.dropEffect = "all"; // drop it like it's hot
};


window.drag = function(ev) {
    ev.dataTransfer.setData("id", ev.target.id);
};

window.drop = function(ev) {
    ev.preventDefault();
    var id = ev.dataTransfer.getData("id");

    var dragged = document.getElementById(id);
    var issue = angular.element(dragged).scope().issue;
    console.log(angular.element(document.getElementById("control")).scope().ctrl);
    var ctrl = angular.element(document.getElementById("control")).scope().ctrl;
    ctrl.cambiarEstado(issue,ev.target.id);
    ev.target.appendChild(dragged);

    //dragged.className += " dropped";
};


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
