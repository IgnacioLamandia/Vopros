app.controller('EditarTaskCtrl', function($resource,$timeout,$state,$stateParams,Task,Users) {
	'use strict';

    var self = this;
    
    self.currentdate = new Date(); 
	self.datetime = this.currentdate.getFullYear()+"-"+(this.currentdate.getMonth()+1)  + "-" 
				+this.currentdate.getDate() 
                ;

    self.task= {};

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

	self.estado = ['NUEVO',
	'EN_PROGRESO',
	'PARA_TESTEAR',
	'CERRADO',
	'INVESTIGAR']

	self.users = [];

    self.inputfecha = document.getElementById("fecha");

    this.getTask= function(){
        Task.query({id:$stateParams.taskID},function(data){
            self.task = data;
        },errorHandler)
    }

    this.getTask();

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
    	this.task.expiracion= this.inputfecha.value.substring(0,10);
    }

    function errorHandler(error) {
        self.notificarError(error.data);
    }
    

    this.updateTask = function(){
    	this.asignarFecha();
        Task.update({id:$stateParams.taskID},this.task, function() {
            console.log('taskEditado');
            self.notificarMensaje('Task editado!');
            document.getElementById("feedback").textContent = "Tarea editada con exito";
        }, errorHandler);
    };

    this.cancel= function(){
    }


    this.inputfecha.onchange=function(){
    	var date = new Date(self.inputfecha.value);
    	if(date < new Date()){
    		document.getElementById("errorFecha").textContent="La fecha debe ser mayor o igual a la actual";
    		self.inputfecha.value= self.datetime;
    	}else{
            document.getElementById("errorFecha").textContent="";

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
