app.controller('TasksCtrl', function($resource,$timeout,$state,$stateParams,Tasks,Task,Proyecto) {
	'use strict';

    var self = this;

    self.tasks = [];
    self.task = null;

    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.getTasks= function(){
        Proyecto.query({id:$stateParams.proyectoId},function(data){
            self.tasks = data.tasks;
        },errorHandler);
	};


	this.seleccionar= function(tarea){
		 var tr =document.getElementById(tarea.nombre);
        if(tarea == this.task){
            this.task = null;
            tr.style.backgroundColor = 'white';
        }else{

            if(this.task !== null){
                document.getElementById(this.task.nombre).style.backgroundColor='white';
            }
            
            this.task = tarea;
            tr.style.backgroundColor = 'red';

        }
        console.log(tarea);

	}

	this.editarTask= function(){
        $state.go("main.editarTask",{taskID:this.task.id})
    }

	this.delete= function(){
		Task.delete({id:this.task.id,idProyecto:$stateParams.proyectoId},function(){
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
