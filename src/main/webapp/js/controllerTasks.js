app.controller('TasksCtrl', function($resource,$state,$stateParams,Tasks) {
	'use strict';

    var self = this;

    self.tasks = [];

    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.getTasks= function(){
        Tasks.query(function(data) {
            self.tasks = data;
        },errorHandler);
	};

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
