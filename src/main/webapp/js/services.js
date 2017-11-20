
app.factory('Issues', function($resource) {
    return $resource('/issues' , {
    	'query': { method: 'GET', isArray: true}
        
    });
});

app.factory('Issue', function($resource){
    return $resource('/issue/:id/:idProyecto', {'id': '@id','idProyecto':'@idProyecto'}, {
        'query': {method: 'GET'},
        'save': {method: 'POST'},
        'update': {method: 'PUT'},
        'delete': {method: 'DELETE'}
    });
});

app.factory('Users', function($resource) {
    return $resource('/users' , {
    	'query': { method: 'GET', isArray: true},


        
    });
});

app.factory('UserByUsername', function($resource) {
    return $resource('/user/byUsername/:username',{'username':'@username'} , {
        'query': { method: 'GET'}


        
    });
});

app.factory('User', function($resource) {
    return $resource('/user/:id', {'id': '@id'}, {
    	'save': {method: 'POST'}


        
    });
});


app.factory('UserSearch', function($resource) {
    return $resource('/user/search/:val', {'val':'@val'} ,{
        'query': {method: 'GET',isArray: true}
        

        
    });
});

app.factory('Proyectos', function($resource) {
    return $resource('/proyectos' , {
    	'query': { method: 'GET', isArray: true}
        
    });
});

app.factory('Proyecto', function($resource) {
    return $resource('/proyecto/:id', {'id': '@id'} , {
        'save': {method: 'POST'},
        'query': {method: 'GET'},
        'update': {method: 'PUT'},
        'delete': {method: 'DELETE'}
        
    });
});

app.factory('Tasks', function($resource) {
    return $resource('/tasks' , {
        'query': { method: 'GET', isArray: true}
        
    });
});


app.factory('Task', function($resource) {
    return $resource('/task/:id/:idProyecto', {'id': '@id','idProyecto':'@idProyecto'}, {
        'save': {method: 'POST'},
        'query': {method: 'GET'},
        'update': {method: 'PUT'},
        'delete': {method: 'DELETE'}
        
    });
});


app.factory('Auth', function($resource) {
    return $resource('/login',{
        'save': {method: 'POST'}
    });
}); 



app.factory('Conversacion', function($http) {
    return {
        getConversacion: function(emisorUsername, receptorUsername){
            var url = '/conversacion/'+emisorUsername+'/'+receptorUsername+'';
            return $http.get(url).then(function(response) {
                return response;
            }, function (response) {
                //No logueado
            });
        },
        sendMensaje: function(mensaje){
            var url = '/mensaje';
            return $http.post(url, mensaje).then(function(response) {
                console.log(response);
            }, function (response) {
                //No logueado
            });
        }
    }
}); 

