app.controller('NuevoIssueCtrl', function($resource,$state,$stateParams,Issue,$timeout) {
	'use strict';

    var self = this;

    self.issue= {"titulo":"","tipo":"","gravedad":"","prioridad":""};

    self.tipo=[ 'BUG',
    'PREGUNTA',
    'MEJORA'];

    self.gravedad=[ 'MENOR',
    'REGULAR',
    'GRAVE',
    'CRITICO'];

    self.prioridad = ['BAJA',
    'MEDIA',
    'ALTA'];



    function errorHandler(error) {
        self.notificarError(error.data);
    }
    

    this.guardarIssue = function(){
        Issue.save(this.issue, function() {
            console.log('issuecreado');
            self.notificarMensaje('Issue creado!');
        }, errorHandler);
        this.issue= {"titulo":"","tipo":"","gravedad":"","prioridad":""};
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