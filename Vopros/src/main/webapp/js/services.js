
app.factory('Issues', function($resource) {
    return $resource('/issues' , {
    	'query': { method: 'GET', isArray: true}
        
    });
});

app.factory('Users', function($resource) {
    return $resource('/users' , {
    	'query': { method: 'GET', isArray: true}
        
    });
});
