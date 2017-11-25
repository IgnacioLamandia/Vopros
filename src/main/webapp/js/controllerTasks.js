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
            //self.ordenarTasks();
        },errorHandler);
	};

    this.ordenarTasks=function(){
        for(var i=0;i<this.tasks.length;i++){
            console.log(this.tasks[i].nombre);
            var t=document.getElementById(this.tasks[i].nombre);
            console.log(t);
            var tr = document.getElementById(this.tasks[i].estado);
            console.log(tr);
            tr.appendChild(t);

            
        }
    }


	this.seleccionar= function(tarea){
		 var tr =document.getElementById(tarea.nombre);
        if(tarea.task == this.task){
            this.task = null;
 //           tr.style.backgroundColor = 'white';
        }else{

//            if(this.task !== null){
//                document.getElementById(this.task.nombre).style.backgroundColor='white';
//            }
            
            this.task = tarea.task;
//            tr.style.backgroundColor = 'red';

        }
        console.log(tarea.task);

	}

    this.cambiarEstado = function(taskDropped,estadoD){
        taskDropped.estado=estadoD;
        Task.update({id:taskDropped.id},taskDropped,function(){
            console.log("Estado actualizado");
        },errorHandler)
    }





    window.allowDrop = function(ev) {
    ev.preventDefault();
    if (ev.target.getAttribute("draggable") == "true")
        ev.dataTransfer.dropEffect = "none"; // dropping is not allowed
    else
        ev.dataTransfer.dropEffect = "all"; // drop it like it's hot
};


window.drag = function(ev) {
    ev.dataTransfer.setData("id", ev.target.id);
};

window.drop = function(ev) {
    ev.preventDefault();
    var id = ev.dataTransfer.getData("id");

    var dragged = document.getElementById(id);
    var task = angular.element(dragged).scope().task;
    console.log(angular.element(document.getElementById("control")).scope().ctrl);
    var ctrl = angular.element(document.getElementById("control")).scope().ctrl;
    ctrl.cambiarEstado(task,ev.target.id);
    ev.target.appendChild(dragged);

    //dragged.className += " dropped";
};

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
