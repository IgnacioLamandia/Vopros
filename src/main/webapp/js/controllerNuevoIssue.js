app.controller('NuevoIssueCtrl', function($resource,$state,$stateParams,Issue) {
	'use strict';

    var self = this;

    self.issue= {"titulo":""};





    function errorHandler(error) {
        self.notificarError(error.data);
    }
    

    this.guardarIssue = function(){
        Issue.save(this.task, function() {
            self.notificarMensaje('Issue creado!');
        }, errorHandler);
        this.task= {"titulo":""};
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