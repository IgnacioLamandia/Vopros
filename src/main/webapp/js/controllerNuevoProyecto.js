app.controller('NuevoProyectoCtrl', function($resource,$scope,$state,$stateParams,$timeout,UserSearch,Proyecto,Users,UserByUsername) {
	'use strict';

    var self = this;

    self.proyecto= {"nombre":"","miembros":[],"dibujos":[]};
    self.creador={};
    self.nombreABuscar = "";
    self.resultados = [];
    self.miembro="";








    function errorHandler(error) {
        self.notificarError(error.data);
    }

        this.getUsuario= function(){
        console.log($stateParams.username);
        UserByUsername.query({username:$stateParams.username},function(data){
            self.notificarMensaje("usuario encontrado");
            self.creador= data;
        },errorHandler);
    };
    

    this.guardarProyecto = function(){
        Proyecto.save({id:this.creador.id},this.proyecto, function() {
            self.notificarMensaje('Proyecto creado!');
        }, errorHandler);
        this.proyecto = {"nombre":"","miembros":[],"dibujos":[]};
        this.resultados = [];
        this.nombreABuscar = "";
        

    };

    this.onSelect=function($item){
        this.proyecto.miembros.push($item);
    }

    this.search = function(nombre){
        console.log("aca llegue");
        UserSearch.query({val:nombre},function(data){
            console.log("aca no :v");
            self.notificarMensaje("allahu akbar");
            self.resultados = data;
        },errorHandler);
        return this.resultados;


    }
    this.proyectos=function(){
        $state.go('proyectos',{username:this.creador.usuario});
    }

    this.addMiembro = function(usuario){
        this.proyecto.miembros.push(usuario);
    }



    this.getUsuario();

    this.cancel= function(){
        this.proyecto = {"nombre":"","miembros":[]};
        this.resultados = [];
        this.nombreABuscar = "";
        
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
