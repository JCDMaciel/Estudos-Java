angular.module("app").controller("listController", function ($scope, pessoasAPI) {
    $scope.app = "Cadastros";
    $scope.pessoas = [];
    $scope.error = false;
    var carregarPessoas = function () {
        $scope.loading = true;
        pessoasAPI.getPessoas()
        .then(function (response) {
                console.log("Conexão foi realizada");
                console.log(response.data);
                $scope.pessoas = response.data;
            })
            .catch(function (error) {
                console.log("Falha na conexão: ", error);
                $scope.error = true;
            })
            .finally(function () {
                $scope.loading = false;
            });
    };
    carregarPessoas();
});