app.controller('ProyectosCtrl', function($resource,$timeout,$state,$stateParams,Proyectos,Proyecto) {
	'use strict';

    var self = this;

    self.proyectos = [];
    self.proyecto = null;

    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.getProyectos= function(){
        Proyectos.query(function(data) {
            self.proyectos = data;
        },errorHandler);
	};


	this.seleccionar= function(proyecto){
        var tr =document.getElementById(proyecto.nombre);
        if(proyecto == this.proyecto){
            this.proyecto = null;
            tr.style.backgroundColor = 'white';
        }else{

            if(this.proyecto !== null){
                document.getElementById(this.proyecto.nombre).style.backgroundColor='white';
            }
            
            this.proyecto = proyecto;
            tr.style.backgroundColor = 'red';

        }
                console.log(proyecto);

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
