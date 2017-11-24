app.controller('ChatCtrl', function($stateParams,Conversacion,proyectData) {
	'use strict';

    var self = this;
    
    var myUsername = $stateParams.username;
    self.yourUsername = '';

    self.usuarios = proyectData.miembros || $stateParams.miembros;

    self.texto = '';

    //  SOCKETS
    var socket = io.connect('http://localhost:3000');
    socket.emit('conectame', myUsername);

    self.enviarMensajeSocket = function(yourUsername){
        var mensaje = {emisor:myUsername,receptor:yourUsername ,texto:self.texto};
        socket.emit('mensajePara',mensaje);
    }

    socket.on('nuevoMensaje', function(mensaje){
        console.log("NEWWWW MESSAGE: ",mensaje);
        var newMensaje = {emisor:{usuario:mensaje.emisor},receptor:{usuario:myUsername},texto:mensaje.texto};
        self.appendMensaje(newMensaje);
    });
    //  SOCKETS

    $("#texto").on('keyup', function (e) {
        if (e.keyCode == 13) {
            self.enviarMensaje();
        }
    });

    self.abrirConver = function(yourUsername){
        self.yourUsername = yourUsername;
        Conversacion.getConversacion(myUsername, yourUsername).then(function(response){
            self.mostrarMensajes(response.data);
        },function(error){});
    }

    self.enviarMensaje = function(){
        
        let mensaje = {};
        mensaje.emisor = {usuario:myUsername};
        mensaje.receptor = {usuario:self.yourUsername};
        mensaje.texto = self.texto;
        
        if(self.texto){
            self.enviarMensajeSocket(self.yourUsername);
            Conversacion.sendMensaje(mensaje).then(function(response){
                self.appendMensaje(mensaje);
                self.limpiarInput();
            },function(error){});
        } else {
            console.log("ESCRIBI ALGO");
        }
    }


    self.limpiarInput = function(){
        $('#texto').val('');
        self.texto = '';
    }

    self.mostrarMensajes = function(mensajes){
        $('#panelChat').html('<ul class="media-list" id="'+ self.yourUsername +'Conver"></ul>');
        angular.forEach(mensajes, function(mensaje) {
          self.appendMensaje(mensaje);
        });
    }
    
    let id = 0;
    self.appendMensaje = function(mensaje){
        var newMensaje =
        '<div id="li'+id+'" style="padding:10px;"><li class="media" ng-repeat="mensaje in ctrl.conversacion">'+
                        '<div class="media-body">'+
                            '<div class="media">'+
                                '<a class="pull-left" href="#">'+
                                    '<img class="media-object img-circle " src="http://download.seaicons.com/icons/icons8/ios7/512/Users-User-Male-2-icon.png" style="max-height:40px;" />'+
                                '</a>'+
                                '<medium class="text-muted">'+mensaje.emisor.usuario+':</medium>'+
                                '<br />  '+
                                '<div class="media-body" ><br/>'+
                                    mensaje.texto +                
                                '</div>'+
                            '</div>'+
                            '<div class="line-separator"></div>'+

                        '</div>'+
                    '</li>';

        $('#'+self.yourUsername + 'Conver').prepend(newMensaje);

        var $el = $('#li'+id),
            x = 300,
            originalOpacity = $el.css("opacity");

        //$el.fadeTo( "slow", 0.33 );
        $el.css("opacity", 0.3);
        setTimeout(function(){
          $el.css("opacity", originalOpacity);
        }, x);
        id = id+1;
    }
});
