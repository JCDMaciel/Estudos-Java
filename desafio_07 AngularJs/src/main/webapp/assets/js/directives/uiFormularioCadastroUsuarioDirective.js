angular.module("app").directive("uiFormularioCadastroUsuario", function(){
    return {
        templateUrl: "../view/formularioCadastroUsuario.html",
        restrict: "E",
        transclude: true
    }
})