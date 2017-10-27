app.controller('NuevoTaskCtrl', function($resource,$state,$stateParams,Task,Users) {
	'use strict';

    var self = this;

    self.task= {"nombre":"","descripcion":"","dificultad":"","prioridad":"","asignado":""};


    this.getUsers= function(){
        Users.query(function(data) {
            self.users = data;
        },errorHandler);
    };

    this.getUsers();



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
