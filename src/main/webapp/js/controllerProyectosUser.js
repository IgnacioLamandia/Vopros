app.controller('ProyectosUserCtrl', function($resource,$timeout,$state,$stateParams,UserByUsername,Proyectos,Proyecto) {
	'use strict';

    var self = this;

    self.user={};
    self.proyectos=[];

    function errorHandler(error) {
        self.notificarError(error.data);
    }

    this.getUsuario= function(){
        console.log($stateParams.username);
        UserByUsername.query({username:$stateParams.username},function(data){
            self.notificarMensaje("usuario encontrado");
            self.user= data;
            self.getProyectos();
        },errorHandler);
	};

    this.getProyectos= function(){
        console.log(this.user.proyectos);
        for(var i=0;i<this.user.proyectos.length;i++){
            Proyecto.query({id:this.user.proyectos[i]},function(data){
                self.proyectos.push(data);
            },errorHandler);
        }
    };

    this.nuevoProyecto = function(){
        $state.go('nuevoProyecto',{username:$stateParams.username});
    }




    this.abrirProyecto=function(project){
        $state.go('main.home',{proyectoId:project.id,username:$stateParams.username})
    };



	this.getUsuario();

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
