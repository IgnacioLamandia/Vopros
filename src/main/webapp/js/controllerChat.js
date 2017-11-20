app.controller('ChatCtrl', function($stateParams, Conversacion) {
	'use strict';

    var self = this;
    var myUsername = $stateParams.username;
    self.yourUsername = '';

    self.usuarios = $stateParams.miembros;

    self.conversacion = [];
    self.texto = '';

    self.abrirConver = function(yourUsername){
        self.conversacion = [];
        self.yourUsername = yourUsername;
        Conversacion.getConversacion(myUsername, yourUsername).then(function(response){
            self.conversacion = response.data;
        },function(error){});
    }

    self.enviarMensaje = function(){
        let mensaje = {};
        mensaje.emisor = {usuario:myUsername};
        mensaje.receptor = {usuario:self.yourUsername};
        mensaje.texto = self.texto;
        
        if(self.texto){
            Conversacion.sendMensaje(mensaje).then(function(response){
                self.appendMensaje(mensaje);
            },function(error){});
        } else {
            console.log("ESCRIBI ALGO");
        }
    }

    self.appendMensaje = function(mensaje){
        var newMensaje =
        '<li  class="media" ng-repeat="mensaje in ctrl.conversacion">'+
                        '<div class="media-body">'+
                            '<div class="media">'+
                                '<a class="pull-left" href="#">'+
                                    '<img class="media-object img-circle " src="http://download.seaicons.com/icons/icons8/ios7/512/Users-User-Male-2-icon.png" style="max-height:40px;" />'+
                                '</a>'+
                                '<medium class="text-muted">'+mensaje.emisor.usuario+':</medium>'+
                                '<br />  '+
                                '<div class="media-body" ><br/>'+
                                    mensaje.texto +                
                                    '<hr />'+
                                '</div>'+
                            '</div>'+

                        '</div>'+
                    '</li>';


        $('#Conver').append(newMensaje);
    }
});
