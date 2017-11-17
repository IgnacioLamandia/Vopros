app.controller('LoginCtrl', function($resource,$timeout,$location,$state,Auth,$document, $anchorScroll) {
	'use strict';
	var self = this;

    self.credentials = {usuario:'', contrasenha:''};

    this.login = function(){
    	$('.error').html("");
        Auth.save(this.credentials,function(data){
        	self.ingresar();
        },errorHandler);

    }

    this.ingresar = function(){
    	$state.go('proyectos',{username:this.credentials.usuario});
    }

    this.about = function(){
        $location.hash("about");
        $anchorScroll();
    }

    this.registro = function(){
        $state.go('nuevoUser');
    }

        function errorHandler(error) {
        $('.error').append("<h4 id='errorMsg'>Usuario o contraseña invalido/a</h4>");
        self.notificarError(error.data);
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
