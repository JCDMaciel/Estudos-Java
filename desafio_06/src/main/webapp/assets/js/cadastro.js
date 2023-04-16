    function adicionarNovoContato() {
        const contatos = document.getElementById('contatos');
        const novoContato = document.createElement("div");
        const contatorDeContato = document.getElementsByName("contatorDeContato").length + 1;

        console.log(contatorDeContato);

        novoContato.setAttribute("class", "contatos");

        const novoTitulo = document.createElement("p");
        novoTitulo.innerHTML = "Contato " + contatorDeContato;
        novoTitulo.setAttribute("name", "contatorDeContato");

        const novoNome = document.createElement("input");
        novoNome.setAttribute("type", "text");
        novoNome.setAttribute("placeholder", "Nome");
        novoNome.setAttribute("class", "container-formulario-item");
        novoNome.setAttribute("id", "nomeDoContato");
        novoNome.setAttribute("required", "required");

        const novoTelefone = document.createElement("input");
        novoTelefone.setAttribute("type", "tel");
        novoTelefone.setAttribute("placeholder", "Telefone");
        novoTelefone.setAttribute("class", "container-formulario-item");
        novoTelefone.setAttribute("id", "telefoneDoContato");
        novoTelefone.setAttribute("required", "required");

        const novoEmail = document.createElement("input");
        novoEmail.setAttribute("type", "email");
        novoEmail.setAttribute("placeholder", "Email");
        novoEmail.setAttribute("class", "container-formulario-item");
        novoEmail.setAttribute("id", "emailDoContato");
        novoEmail.setAttribute("required", "required");

        contatos.appendChild(novoContato);
        novoContato.appendChild(novoTitulo);
        novoContato.appendChild(novoNome);
        novoContato.appendChild(novoTelefone);
        novoContato.appendChild(novoEmail);
    }

    const url = "http://localhost:8080/desafio/rest/cadastro/adicionaPessoa";

    async function persist(object, url) {
        const requestOptions = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json;charset=UTF-8",
                'Access-Control-Allow-Origin': '*'
            },
            body: JSON.stringify(object)
        }

        let response = await fetch(url, requestOptions);
        let data = await response.json();
        return data;
    }
    function submit() {
        var formulario = document.getElementById('formulario');
        var jsonFormulario = obterValoresFormulario(formulario);

        persist(jsonFormulario, url)
            .then(resposta => {
                console.log(resposta);
            })
            .catch(erro => {
                console.error(erro);
            });
    }
    function obterValoresFormulario() {
        var jsonData = {};

        jsonData.nome = document.getElementById('nome').value;
        jsonData.cpf = document.getElementById('cpf').value;
        jsonData.email = document.getElementById('email').value;
        jsonData.endereco = document.getElementById('endereco').value;
        jsonData.dataDeNascimento = document.getElementById('nascimento').value;

        jsonData.contatos = [];
        var contatos = document.getElementsByClassName('contatos');

        for (var i = 1; i < contatos.length; i++) {
            var contato = {};
            contato.nomeDoContato = contatos[i].querySelector('#nomeDoContato').value;
            contato.telefoneDoContato = contatos[i].querySelector('#telefoneDoContato').value;
            contato.emailDoContato = contatos[i].querySelector('#emailDoContato').value;
            jsonData.contatos.push(contato);
        }

        return jsonData;
    }