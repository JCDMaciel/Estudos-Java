const deletePessoa = "http://localhost:8080/desafio/rest/cadastro/excluiPessoa";

const editaPessoa = "http://localhost:8080/desafio/rest/cadastro/editaPessoa";

const buscaPessoa = "http://localhost:8080/desafio/rest/cadastro/buscaPessoa"

function buscarPessoa(){
    let id = document.getElementById("id").value;

    let jsonData = {};
    jsonData.id = id;

    getData(jsonData, buscaPessoa, id);
}

function excluirPessoa(){
    let id = document.getElementById("id").value;

    let jsonData = {};
    jsonData.id = id;

    deletar(jsonData, deletePessoa, id);
}

function editarPessoa(){
    const newPessoa = {nome:'', cpf: '', email:'', endereco:'', dataDeNascimento:''};

    let id = document.getElementById("id").value;

    let jsonData = {};
    jsonData.id = id;

    pessoa = getData(jsonData, buscaPessoa, id);

    newPessoa.nome = document.getElementById('nome').value;
    newPessoa.cpf = document.getElementById('cpf').value;
    newPessoa.email = document.getElementById('email').value;
    newPessoa.endereco = document.getElementById('endereco').value;
    newPessoa.dataDeNascimento = document.getElementById('nascimento').value;

    update(newPessoa, editaPessoa, id);
}

async function getData(object, url, id){
    animar(url + "/" + id);

    const requestOptionsBusca = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(object)
    }

    let response = await fetch(url + "/" + id, requestOptionsBusca);
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

async function deletar(object, url, id) {
    animar(url + "/" + id);

    const requestOptionsDelete = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(object)
    }

    let response  = await fetch(url + "/" + id, requestOptionsDelete);
    pessoa = await response.json();
}

async function update(object, url, id){
    animar(url + "/" + id);

    const requestOptionsUpdate = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json;charset=UTF-8",
            'Access-Control-Allow-Origin': '*'
        },
        body: JSON.stringify(object)
    }

    let response  = await fetch(url + "/" + id, requestOptionsUpdate);
    pessoa = await response.json();
}

function animar(url) {
    document.getElementById('loader').style.display = 'block';

    var xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) document.getElementById('loader').style.display = 'none';
    };
    xhr.send();
}