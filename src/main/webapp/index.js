var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = process.env.PORT || 3000;

let usuariosConectados = {}; 
io.on('connection', function(socket){
  socket.on('chat message', function(msg){
    io.emit('chat message', msg);
    console.log("socket: ", socket);
  });

  socket.on('conectame', function(username){
	socket.username = username
	usuariosConectados[socket.username] = socket.id;    	
	console.log("Se conecto el socket: ", usuariosConectados);
  });

  socket.on('mensajePara', function(mensaje){
  	if(usuariosConectados[mensaje.receptor]){
  		let socketid = usuariosConectados[mensaje.receptor];
  		console.log("socketid: ",socketid);
  		io.sockets.connected[socketid].emit('nuevoMensaje', mensaje);
    	console.log("Se envio un mensaje at: ", mensaje.receptor);
  	}
  });

});

http.listen(port, function(){
  console.log('listening on *:' + port);
});

 