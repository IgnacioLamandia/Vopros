app.controller('NuevoProyectoCtrl', function($resource,$scope,$state,$stateParams,$timeout,UserSearch,Proyecto,Users) {
	'use strict';

    var self = this;

    self.proyecto= {"nombre":"","miembros":[]};
    self.users = [];
    self.creador={};
    self.nombreABuscar = "";
    self.resultados = [];







    function errorHandler(error) {
        self.notificarError(error.data);
    }
    

    this.guardarProyecto = function(){
        Proyecto.save({id:this.creador.id},this.proyecto, function() {
            self.notificarMensaje('Proyecto creado!');
        }, errorHandler);
        this.proyecto = {"nombre":"","miembros":[]};
        this.resultados = [];
        this.nombreABuscar = "";
        

    };

    this.search = function(){
        console.log("aca llegue");
        UserSearch.query({val:this.nombreABuscar},function(data){
            console.log("aca no :v");
            self.notificarMensaje("allahu akbar");
            self.resultados = data;
        },errorHandler);


    }

    this.addMiembro = function(usuario){
        this.proyecto.miembros.push(usuario);
    }

    this.getUsers= function(){
        Users.query(function(data){
            console.log(data);
            self.users = data;
        },errorHandler);
    }

    this.getUsers();

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
