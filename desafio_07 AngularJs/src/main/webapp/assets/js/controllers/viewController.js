angular.module("app").controller("viewController", function ($scope, $stateParams, pessoasAPI){
    $scope.serviceId = $stateParams.serviceId;
    $scope.app = "Busca Pessoa";
    $scope.pessoa = {};
    $scope.excluiPessoa = function(){
        $scope.loading = true;
        $scope.error = false;
        pessoasAPI.excluiPessoa($scope.pessoa.id, JSON.stringify($scope.pessoa))
            .then(function (response){
                console.log("Conexão foi realizada");
                console.log(response.data)
                $scope.pessoa = {};
            })
            .catch(function (error) {
                console.log("Falha na conexão", error);
                $scope.error = true;
            })
            .finally(function () {
                $scope.loading = false;
                $scope.pessoa = {};
            });
    }
    $scope.editaPessoa = function(){
        $scope.loading = true;
        $scope.error = false;
        pessoasAPI.editaPessoa($scope.pessoa.id, JSON.stringify($scope.pessoa))
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
    $scope.buscaPessoa = function(id){
        $scope.loading = true;
        $scope.error = false;
        if(isNaN(id)) id = 0;
        pessoasAPI.buscaPessoa(id, JSON.stringify($scope.pessoa))
            .then(function (response){
                console.log("Conexão foi realizada");
                console.log(response.data)
                $scope.pessoa = response.data;
            })
            .catch(function (error) {
                console.log("Falha na conexão", error);
                $scope.error = false;
                $scope.pessoa = {};
            })
            .finally(function () {
                $scope.loading = false;
            });
    }
    $scope.buscaPessoa($scope.serviceId);
})