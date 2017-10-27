app.controller('TasksCtrl', function($resource,$timeout,$state,$stateParams,Tasks,Task) {
	'use strict';

    var self = this;

    self.tasks = [];
    self.task = {};

    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.getTasks= function(){
        Tasks.query(function(data) {
            self.tasks = data;
        },errorHandler);
	};


	this.seleccionar= function(tarea){
		console.log(tarea);
		this.task = tarea;
	}

	this.delete= function(){
		Task.delete({id:this.task.id},function(){
			self.notificarMensaje("Task borrado");
			self.getTasks();

		},errorHandler);
	}


	this.getTasks();

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
