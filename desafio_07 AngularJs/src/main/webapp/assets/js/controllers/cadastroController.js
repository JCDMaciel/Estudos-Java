angular.module("app").controller("cadastroController", function ($scope, pessoasAPI){
    $scope.pessoas = {};
    $scope.pessoas.contatos = []
    $scope.contato = {};
    $scope.numeroContato = 1;
    $scope.app = "Cadastrar"
    $scope.adicionaPessoa = function(){
        $scope.loading = true;
        $scope.error = false;
        $scope.pessoas.contatos.push($scope.contato);
        pessoasAPI.postPessoas(JSON.stringify($scope.pessoas))
            .then(function (response){
                console.log("Conexão foi realizada");
                console.log(response.data)
            })
            .catch(function (error) {
                console.log("Falha na conexão", error);
                $scope.error = true;
            })
            .finally(function () {
                $scope.loading = false;
            });
    }
}) 