angular.module("app").directive("uiAlert", function(){
    return {
        templateUrl: "/view/alert.html",
        restrict: "E",
        scope: {
            title: "@",
            message: "@"
        }
    }
})