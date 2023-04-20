angular.module("app").factory("pessoasAPI", function($http){
    var _postPessoas = function(data){
        return $http.post("http://localhost:8080/desafio/rest/cadastro/adicionaPessoa", data)
    };
    var _getPessoas = function(){
        return $http.get('http://localhost:8080/desafio/rest/cadastro/listaPessoas');
    };
    var _buscaPessoa = function(id, data){
        return $http.post("http://localhost:8080/desafio/rest/cadastro/buscaPessoa/" + id, data)
    };
    var _excluiPessoa = function(id, data){
        return $http.post("http://localhost:8080/desafio/rest/cadastro/excluiPessoa/" + id, data)
    };
    var _editaPessoa = function(id, data){
        return $http.post("http://localhost:8080/desafio/rest/cadastro/editaPessoa/" + id, data)
    };
    return {
        postPessoas: _postPessoas,
        getPessoas: _getPessoas,
        buscaPessoa: _buscaPessoa,
        excluiPessoa: _excluiPessoa,
        editaPessoa: _editaPessoa
    };
});