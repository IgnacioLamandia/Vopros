
app.factory('Issues', function($resource) {
    return $resource('/issues' , {
    	'query': { method: 'GET', isArray: true}
        
    });
});
