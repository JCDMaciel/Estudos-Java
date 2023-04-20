angular.module('app').config(function ($stateProvider){
    $stateProvider
    .state('list', {
        url: '/list',
        templateUrl: '/view/list.html',
        controller: 'listController'
    })
    .state('view', {
        url: '/view/:serviceId',
        templateUrl: '/view/view.html',
        controller: 'viewController'
    })
    .state('cadastro', {
        url: '/cadastro',
        templateUrl: '/view/cadastro.html',
        controller: 'cadastroController'
    })
    .state('index', {
        url: '/',
        template: ''
    })
})