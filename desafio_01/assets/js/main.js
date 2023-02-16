const tabela = document.getElementById('tabela');
const cadastrar = document.getElementById('cadastrar');

let campoNome = "";
let campoCpf = "";
let campoEmail = "";
let campoEndereco = "";
let campoNascimento = "";
let campoPrimeiroContato = "";
let campoSegundoContato = "";

function pegaValorDaVariavel () {
    campoNome = document.getElementById('nome').value;
    campoCpf = document.getElementById('cpf').value;
    campoEmail = document.getElementById('email').value;
    campoEndereco = document.getElementById('endereco').value;
    campoNascimento = document.getElementById('nascimento').value;
    campoPrimeiroContato = document.getElementById('primeiro_contato').value;
    campoSegundoContato = document.getElementById('segundo_contato').value;
}

function validarCPF(inputCPF){
    var soma = 0;
    var resto;
    var inputCPF = document.getElementById('cpf').value;

    if(inputCPF == '00000000000') return false;
    for(i=1; i<=9; i++) soma = soma + parseInt(inputCPF.substring(i-1, i)) * (11 - i);
    resto = (soma * 10) % 11;

    if((resto == 10) || (resto == 11)) resto = 0;
    if(resto != parseInt(inputCPF.substring(9, 10))) return false;

    soma = 0;
    for(i = 1; i <= 10; i++) soma = soma + parseInt(inputCPF.substring(i-1, i))*(12-i);
    resto = (soma * 10) % 11;

    if((resto == 10) || (resto == 11)) resto = 0;
    if(resto != parseInt(inputCPF.substring(10, 11))) return false;
    return true;
}

function removerBotaoDaLinhaAnterior() {
    const botaoApagarAntigo = document.getElementsByClassName('apagar');
    const botaoEditarAntigo = document.getElementsByClassName('editar');

    if (botaoApagarAntigo.length >= 2 && botaoEditarAntigo.length >= 2) {
        const botaoApagarInvisivel = botaoApagarAntigo[botaoApagarAntigo.length -2];
        botaoApagarInvisivel.setAttribute("hidden", "hidden");

        const botaoRecado = botaoEditarAntigo[botaoEditarAntigo.length -2];
        botaoRecado.removeAttribute("onclick");
        botaoRecado.removeAttribute("class");
        botaoRecado.setAttribute("value", "Salvo");
        botaoRecado.style.width = "5.5rem";
        botaoRecado.style.textAlign = "center";
    }
}

function validaVariavelEmBranco (){
    if (campoNome == "" || campoCpf == "" || campoEmail == "" || campoEndereco == "" || campoNascimento == "" ) {
        alert("Favor preencher todos os campos do formulário");
    } else {
        if (validarCPF(campoCpf) == true) {   
            criarLinhaDaTabela ();
            removerBotaoDaLinhaAnterior();
            limpaFormulario ();
        } else {
            alert("Favor preencher CPF corretamente");
        }
    } 
}

function limpaFormulario () {
    document.getElementById('nome').value = "";
    document.getElementById('cpf').value = "";
    document.getElementById('email').value = "";
    document.getElementById('endereco').value = "";
    document.getElementById('nascimento').value = "";
    document.getElementById('primeiro_contato').value = "";
    document.getElementById('segundo_contato').value = "";
}

function criarLinhaDaTabela () {
    const novaLinha = document.createElement("tr");
    novaLinha.setAttribute("class", "linha");

    const novoNome = document.createElement("td");
    novaLinha.appendChild(novoNome);
    novoNome.innerHTML = campoNome;
    novoNome.setAttribute("class", "tabela-item");

    const novoCpf = document.createElement("td");
    novaLinha.appendChild(novoCpf);
    novoCpf.innerHTML = campoCpf;
    novoCpf.setAttribute("class", "tabela-item");

    const novoEmail = document.createElement("td");
    novaLinha.appendChild(novoEmail);
    novoEmail.innerHTML = campoEmail;
    novoEmail.setAttribute("class", "tabela-item");

    const novoEndereco = document.createElement("td");
    novaLinha.appendChild(novoEndereco);
    novoEndereco.innerHTML = campoEndereco;
    novoEndereco.setAttribute("class", "tabela-item");

    const novoNascimento = document.createElement("td");
    novaLinha.appendChild(novoNascimento);
    novoNascimento.innerHTML = campoNascimento;
    novoNascimento.setAttribute("class", "tabela-item");

    const botaoExcluir = document.createElement("input");
    novaLinha.appendChild(botaoExcluir);
    botaoExcluir.setAttribute("onclick", "removerUltimaLinhaDaTabela ();");
    botaoExcluir.setAttribute("class", "apagar");
    botaoExcluir.setAttribute("value", "excluir");
    botaoExcluir.style.width = "2.5rem";

    const botaoEditar = document.createElement("input");
    novaLinha.appendChild(botaoEditar);
    botaoEditar.setAttribute("onclick", "editarUltimaLinhaDaTabela ();");
    botaoEditar.setAttribute("class", "editar");
    botaoEditar.setAttribute("value", "editar");
    botaoEditar.style.width = "2.5rem";

    tabela.appendChild(novaLinha);
}

function removerUltimaLinhaDaTabela () {
    const linhas = document.getElementsByClassName("linha");
    const ultimaLinha = linhas[linhas.length -1];
    tabela.removeChild(ultimaLinha);
}

function editarUltimaLinhaDaTabela () {
    removerUltimaLinhaDaTabela ();
    document.getElementById('nome').value = campoNome; 
    document.getElementById('cpf').value = campoCpf;
    document.getElementById('email').value = campoEmail;
    document.getElementById('endereco').value = campoEndereco; 
    document.getElementById('nascimento').value = campoNascimento;
    document.getElementById('primeiro_contato').value = campoPrimeiroContato;
    document.getElementById('segundo_contato').value = campoSegundoContato;
}

cadastrar.addEventListener('click', (evento)=>{
    evento.preventDefault();
    pegaValorDaVariavel ();
    validaVariavelEmBranco ();
})
