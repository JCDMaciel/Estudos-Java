angular.module("app").directive("uiFormularioCadastroContato", function(){
    return {
        templateUrl: "../view/formularioCadastroContato.html",
        restrict: "AEC",
        transclude: true
    }
})