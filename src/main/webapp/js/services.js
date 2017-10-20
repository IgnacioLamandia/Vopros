
app.factory('Issues', function($resource) {
    return $resource('/issues' , {
    	'query': { method: 'GET', isArray: true}
        
    });
});

app.factory('Issue', function($resource){
    return $resource('/issue:id', {'id': '@id'}, {
        'query': {method: 'GET'},
        'save': {method: 'POST'},
        'update': {method: 'PUT'},
        'delete': {method: 'DELETE'}
    });
});

app.factory('Users', function($resource) {
    return $resource('/users' , {
    	'query': { method: 'GET', isArray: true},
    	'save': {method: 'POST'}

        
    });
});

app.factory('Proyectos', function($resource) {
    return $resource('/proyectos' , {
    	'query': { method: 'GET', isArray: true}
        
    });
});

app.factory('Tasks', function($resource) {
    return $resource('/tasks' , {
        'query': { method: 'GET', isArray: true}
        
    });
});


app.factory('Task', function($resource) {
    return $resource('/task:id', {'id': '@id'}, {
        'save': {method: 'POST'},
        'query': {method: 'GET'},
        'update': {method: 'PUT'},
        'delete': {method: 'DELETE'}
        
    });
});

app.factory('Auth', function($http) {
    return {
        login: function(credentials){
            return $http({
              method: 'POST',
              url: '/login',
              data: credentials
            }).then(function(response) {
                //Logueado
            }, function (response) {
                //Logueado
            });
        }
    }
});