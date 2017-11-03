app.controller('NuevoIssueCtrl', function($resource,$state,$stateParams,Issue,$timeout,Users) {
	'use strict';

    var self = this;

    self.currentdate = new Date(); 
	self.datetime = this.currentdate.getFullYear()+"-"+(this.currentdate.getMonth()+1)  + "-" 
				+this.currentdate.getDate() 
                ;

    self.issue= {"titulo":"","tipo":"","gravedad":"","prioridad":"", "expiracion":"", "asignado":""};

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


    self.users = [];

    self.inputfecha = document.getElementById("fecha");


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
    

    this.guardarIssue = function(){
    	this.asignarFecha();
        Issue.save(this.issue, function() {
            console.log('issuecreado');
            self.notificarMensaje('Issue creado!');
        }, errorHandler);
        this.issue= {"titulo":"","tipo":"","gravedad":"","prioridad":""};
    };

    this.cancel= function(){
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
