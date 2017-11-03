app.controller('NuevoIssueCtrl', function($resource,$state,$stateParams,Issue,$timeout,Users) {
	'use strict';

    var self = this;

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

    self.fecha;

	self.days = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31];

	self.months = [1,2,3,4,5,6,7,8,9,10,11,12];

	self.years = [2017,2018,2019,2020,2021,2022,2023,2024,2025];

    self.users = [];

    self.inputfecha = document.createElement("INPUT");


    this.getUsers= function(){
        Users.query(function(data) {
            self.users = data;
        },errorHandler);
    };

    this.getUsers();


    this.inputFecha=function() {
    
    this.inputfecha.setAttribute("type", "date");
    this.inputfecha.setAttribute("value", "2017-01-01");
    document.getElementById("fecha").append(this.inputfecha);
}

	this.inputFecha();

   this.asignarFecha=function() {
    this.issue.expiracion= this.inputfecha.value;
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
