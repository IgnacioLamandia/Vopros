app.controller('NuevoTaskCtrl', function($resource,$timeout,$state,$stateParams,Task,Users) {
	'use strict';

    var self = this;
    
    self.currentdate = new Date(); 
	self.datetime = this.currentdate.getFullYear()+"-"+(this.currentdate.getMonth()+1)  + "-" 
				+this.currentdate.getDate() 
                ;

    self.task= {"nombre":"","descripcion":"","dificultad":"","prioridad":"","asignado":"", "expiracion":""};
    self.dificultad=[   'XXS',
    'XS',
    'S',
    'M',
    'L',
    'XL',
    'XXL'];


    self.prioridad = ['BAJA',
    'MEDIA',
    'ALTA'];

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
    

    this.guardarTask = function(){
        Task.save(this.task, function() {
            self.notificarMensaje('Tarea creada!');
        }, errorHandler);
        this.task= {"nombre":"","descripcion":""};
    };

    this.cancel= function(){
    }


    this.inputfecha.onchange=function(){
    	var date = new Date(self.inputfecha.value);
    	if(date < new Date()){
    		console.log("La fecha debe ser mayor o igual a la actual");
    		self.inputfecha.value= datetime;
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
