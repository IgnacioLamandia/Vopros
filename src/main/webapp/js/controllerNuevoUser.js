app.controller('NuevoUserCtrl', function($resource,$state,$stateParams,User,$timeout) {
	'use strict';

    var self = this;

    self.user= {"usuario":"","nombre":"","apellido":"","email":"","contrasenha":""};





    function errorHandler(error) {
        self.notificarError(error.data);
    }
    

    this.guardarUser = function(){
        User.save(this.user, function() {
            self.notificarMensaje('Usuario creado!');
        }, errorHandler);
        this.user= {"usuario":"","nombre":"","apellido":"","email":""};
        $state.go('login');

    };

    this.cancel= function(){
        this.user= {"usuario":"","nombre":"","apellido":"","email":""};

    }


    this.volver=function(){
        $state.go('login');
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
