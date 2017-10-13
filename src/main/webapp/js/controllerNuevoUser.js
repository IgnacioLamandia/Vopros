app.controller('NuevoUserCtrl', function($resource,$state,$stateParams,Users) {
	'use strict';

    var self = this;

    self.user= {"nombre":"","apellido":"","email":""};





    function errorHandler(error) {
        self.notificarError(error.data);
    }
    

    this.guardarUser = function(){
        Users.save(this.user, function() {
            self.notificarMensaje('Usuario creado!');
        }, errorHandler);
        this.user= {"nombre":"","apellido":"","email":""};
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
