
app.factory('Issues', function($resource) {
    return $resource('/issues' , {
    	'query': { method: 'GET', isArray: true}
        
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
