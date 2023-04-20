const listaPessoas = "http://localhost:8080/desafio/rest/cadastro/listaPessoas"
const requestOptions = {
    method: 'GET',
    headers: {
        "Content-Type": "application/json;charset=UTF-8",
        'Access-Control-Allow-Origin': '*'
    }
}
async function criarLinhaDaTabela() {
    const response = await fetch(listaPessoas, requestOptions);
    const data = await response.json();
    
    Object.keys(data).forEach((key) => {

        const novaLinha = document.createElement("tr");
        novaLinha.setAttribute("class", "linha");
        
        const novoID = document.createElement("td");
        novaLinha.appendChild(novoID);
        novoID.innerHTML = data[key].id;
        novoID.setAttribute("class", "tabela-item");

        const novoNome = document.createElement("td");
        novaLinha.appendChild(novoNome);
        novoNome.innerHTML = data[key].nome;
        novoNome.setAttribute("class", "tabela-item");

        const novoCpf = document.createElement("td");
        novaLinha.appendChild(novoCpf);
        novoCpf.innerHTML = data[key].cpf;
        novoCpf.setAttribute("class", "tabela-item");

        const novoEmail = document.createElement("td");
        novaLinha.appendChild(novoEmail);
        novoEmail.innerHTML = data[key].email;
        novoEmail.setAttribute("class", "tabela-item");

        const novoEndereco = document.createElement("td");
        novaLinha.appendChild(novoEndereco);
        novoEndereco.innerHTML = data[key].endereco;
        novoEndereco.setAttribute("class", "tabela-item");

        const novoNascimento = document.createElement("td");
        novaLinha.appendChild(novoNascimento);
        novoNascimento.innerHTML = data[key].dataDeNascimento;
        novoNascimento.setAttribute("class", "tabela-item");

        tabela.appendChild(novaLinha);
    });
}

criarLinhaDaTabela()