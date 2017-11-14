app.controller('LoginCtrl', function($resource,$timeout,$location,$state,$auth) {
	'use strict';
	var self = this;

    self.credentials = {email:'', password:''};

    this.login = function(){
//    	$('.error').html("");
//        Auth.save(this.credentials,function(data){
//        	console.log(data);
 //       	self.ingresar();
 //       },errorHandler);

        $auth.login(self.credentials).then(function(response){
      //      $auth.setToken(response);
            self.ingresar();
        }).catch(function (response) {
        console.log("error response", response);
      });

    }

    this.ingresar = function(){
    	$state.go('proyectos',{username:this.credentials.email});
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
