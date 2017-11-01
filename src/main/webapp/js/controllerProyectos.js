app.controller('ProyectosCtrl', function($resource,$timeout,$state,$stateParams,Proyectos,Proyecto) {
	'use strict';

    var self = this;

    self.proyectos = [];
    self.proyecto = {};

    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.getProyectos= function(){
        Proyectos.query(function(data) {
            self.proyectos = data;
        },errorHandler);
	};


	this.seleccionar= function(proyecto){
		this.proyecto = proyecto;
	}

	this.delete= function(){
		Proyecto.delete({id:this.proyecto.id},function(){
			self.notificarMensaje("Proyecto borrado");
			self.getProyectos();

		},errorHandler);
	}


	this.getProyectos();

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
