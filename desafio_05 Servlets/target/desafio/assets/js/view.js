const deletePessoa = "http://localhost:8080/desafio/excluiPessoa";

const editaPessoa = "http://localhost:8080/desafio/editaPessoa";

const buscaPessoa = "http://localhost:8080/desafio/buscaPessoa"

function buscarPessoa(){
    let id = document.getElementById("id").value;

    let jsonData = {};
    jsonData.id = id;

    console.log(jsonData)

    getData(jsonData, buscaPessoa);
}

function excluirPessoa(){
    let id = document.getElementById("id").value;

    let jsonData = {};
    jsonData.id = id;

    deletar(jsonData, deletePessoa);
}

function editarPessoa(){
    const newPessoa = {id: '', nome:'', cpf: '', email:'', endereco:'', dataDeNascimento:''};

    let id = document.getElementById("id").value;
    let jsonData = {};

    jsonData.id = id;

    pessoa = getData(jsonData, buscaPessoa);

    newPessoa.id = id;
    newPessoa.nome = document.getElementById('nome').value;
    newPessoa.cpf = document.getElementById('cpf').value;
    newPessoa.email = document.getElementById('email').value;
    newPessoa.endereco = document.getElementById('endereco').value;
    newPessoa.dataDeNascimento = document.getElementById('nascimento').value;

    update(newPessoa, editaPessoa);
}

async function getData(object, url){
    const requestOptionsBusca = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(object)
    }

    let response = await fetch(url, requestOptionsBusca);
    pessoa = await response.json();

    let nome = document.getElementById('nome');
    nome.setAttribute('value', pessoa.nome);

    let cpf = document.getElementById('cpf');
    cpf.setAttribute('value', pessoa.cpf);

    let email = document.getElementById('email');
    email.setAttribute('value', pessoa.email);

    let endereco = document.getElementById('endereco');
    endereco.setAttribute('value', pessoa.endereco);

    let dataDeNascimento = document.getElementById('nascimento');
    dataDeNascimento.setAttribute('value', pessoa.dataDeNascimento);

    return pessoa;
}

async function deletar(object, url) {
    const requestOptionsDelete = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(object)
    }

    let response  = await fetch(url, requestOptionsDelete);
    pessoa = await response.json();
}

async function update(object, url){
    const requestOptionsUpdate = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(object)
    }

    let response  = await fetch(url, requestOptionsUpdate);
    pessoa = await response.json();
}