app.controller('LoginCtrl', function($scope,$location, Auth) {
	'use strict';

    $scope.credentials = {nombre:'', contrasenha:''};

    $scope.login = function(){
        var token = Auth.login($scope.credentials);
        if(token){
            //Ingresar a la aplicacion
        }
    }

    
});