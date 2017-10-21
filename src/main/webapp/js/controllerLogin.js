app.controller('LoginCtrl', function($scope,$location,$state, Auth) {
	'use strict';

    $scope.credentials = {nombre:'', contrasenha:''};

    $scope.login = function(){
        var token = Auth.login($scope.credentials);
        if(token){
            $state.go('main.home');
        }
    }

    
});