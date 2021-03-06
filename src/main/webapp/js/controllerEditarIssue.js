app.controller('EditarIssueCtrl', function($resource,$state,$stateParams,Issue,$timeout,Users) {
	'use strict';

    var self = this;

    self.currentdate = new Date(); 
	self.datetime = this.currentdate.getFullYear()+"-"+(this.currentdate.getMonth()+1)  + "-" 
				+this.currentdate.getDate() 
                ;

    self.issue= {};

    self.tipo=[ 'BUG',
    'PREGUNTA',
    'MEJORA'];

    self.gravedad=[ 'MENOR',
    'REGULAR',
    'GRAVE',
    'CRITICO'];

    self.prioridad = ['BAJA',
    'MEDIA',
    'ALTA'];

	self.estado = ['NUEVO',
	'EN_PROGRESO',
	'PARA_TESTEAR',
	'CERRADO',
	'INVESTIGAR',
	'RECHAZADO',
	'POSTPUESTO']

    self.users = [];

    self.inputfecha = document.getElementById("fecha");

    this.getIssue= function(){
        Issue.query({id:$stateParams.issueID},function(data){
            self.issue = data;
        },errorHandler)
    }

    this.getIssue();

    this.getUsers= function(){
        Users.query(function(data) {
            self.users = data;
        },errorHandler);
    };

    this.getUsers();


    this.inputFecha=function() {
    

    console.log(this.datetime);
    this.inputfecha.setAttribute("value", this.datetime);
    this.inputfecha.value = this.datetime;
}

	this.inputFecha();

    this.asignarFecha=function() {
    this.issue.expiracion= this.inputfecha.value.substring(0,10);
}


    function errorHandler(error) {
        self.notificarError(error.data);
    }
    

    this.updateIssue = function(){
    	this.asignarFecha();
        Issue.update({id:$stateParams.issueID},this.issue, function() {
            console.log('issuecreado');
            self.notificarMensaje('Issue creado!');
            document.getElementById("feedback").textContent = "Problema editado con exito";
        }, errorHandler);
    };

    this.cancel= function(){
    }


    this.inputfecha.onchange=function(){
    	var date = new Date(self.inputfecha.value);
    	if(date < new Date()){
    		document.getElementById("errorFecha").textContent ="La fecha debe ser mayor o igual a la actual";
    		self.inputfecha.value= self.datetime;
    	}else{
    		document.getElementById("errorFecha").textContent ="";

    	}
    }

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
