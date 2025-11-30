// =======================================================
// VARIÁVEIS GLOBAIS
// =======================================================
const API_URL = "http://localhost:8081/pacientes"; 
const USERS_URL = "http://localhost:8081/auth"; // Endpoint que lista os usuários (profissionais)
const LOGIN_URL = "http://localhost:8081/auth/login";

// =======================================================
// 1. CARREGAR O DROPDOWN (Profissionais)
// =======================================================
async function carregarProfissionais() {
    try {
        const resposta = await fetch(USERS_URL);
        if (resposta.ok) {
            const listaProfissionais = await resposta.json();
            const select = document.getElementById('usuario_responsavel');
            
            // Limpa e mantém a opção padrão
            select.innerHTML = '<option value="" disabled selected>Selecione um profissional...</option>';
            console.log(listaProfissionais)
            listaProfissionais.forEach(user => {
                // Cria uma opção: <option value="1">Gustavo Admin (ADMIN)</option>
                const option = document.createElement('option');
                option.value = user.id;
                option.textContent = `${user.nome} (${user.cargo || 'Funcionario'})`;
                select.appendChild(option);
            });
        } else {
            console.error("Erro ao buscar profissionais:", resposta.status);
        }
    } catch (erro) {
        console.error("Erro de conexão ao carregar profissionais:", erro);
    }
}

// =======================================================
// 2. LISTAR PACIENTES (GET)
// =======================================================
async function getUserList() {
    try {
        let resposta = await fetch(API_URL);
        if (resposta.ok) {
            userList = await resposta.json();
            renderUserList(userList); 
        } else {
            console.error("Erro ao buscar pacientes:", resposta.status);
        }
    } catch (erro) {
        console.error("Erro de conexão:", erro);
    }
}

// =======================================================
// 3. CADASTRAR PACIENTE (POST)
// =======================================================
async function addUser(nome, dataNascimento) {
    
    // Pega o ID direto do Dropdown (Select)
    const select = document.getElementById('usuario_responsavel');
    const usuarioIdSelecionado = select.value;

    // Validação extra: Obriga a selecionar alguém
    if (!usuarioIdSelecionado) {
        alert("Por favor, selecione um profissional responsável!");
        return;
    }

    const novoPaciente = {
        nome: nome,
        data_nascimento: dataNascimento,
        usuarioId: usuarioIdSelecionado // <--- Manda o ID do dropdown pro Java
    };

    try {
        const resposta = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(novoPaciente)
        });

        if (resposta.status === 201) {
            alert("Paciente cadastrado com sucesso!");
            getUserList(); // Atualiza a lista na tela
            
            // Limpa o formulário
            document.getElementById('cadastro-usuario').reset();
        } else {
            alert("Erro ao cadastrar. Verifique os dados.");
        }
    } catch (erro) {
        console.error("Erro:", erro);
        alert("Erro ao conectar com o servidor.");
    }
}

// =======================================================
// 4. DELETAR PACIENTE (DELETE)
// =======================================================
async function deleteUser(userId) {
    if (!confirm("Tem certeza que deseja excluir este paciente?")) return;

    try {
        const resposta = await fetch(`${API_URL}/${userId}`, {
            method: "DELETE"
        });

        if (resposta.ok || resposta.status === 204) {
            alert("Paciente excluído!");
            getUserList(); // Recarrega a lista
        } else {
            alert("Erro ao excluir.");
        }
    } catch (erro) {
        console.error("Erro ao deletar:", erro);
    }
}

// =======================================================
// LÓGICA DE LOGIN E SEGURANÇA
// =======================================================

// 1. Função para enviar email e senha para o Java
async function fazerLogin(email, senha) {
    try {
        const resposta = await fetch(LOGIN_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email: email, senha: senha })
        });

        if (resposta.ok) {
            const usuario = await resposta.json();
            
            // Salva o usuário no navegador para usar depois no cadastro
            localStorage.setItem('usuarioLogado', JSON.stringify(usuario));
            
            alert("Login realizado com sucesso!");
            window.location.href = "admin.html"; // Manda para a tela de admin
        } else {
            alert("E-mail ou senha incorretos!");
        }
    } catch (erro) {
        console.error("Erro no login:", erro);
        alert("Erro de conexão com o servidor.");
    }
}

// 2. Função para proteger a página (Chuta quem não tá logado)
function verificarAutenticacao() {
    // Só verifica se estivermos na página de admin
    if (window.location.pathname.endsWith('admin.html')) {
        const usuarioLogado = localStorage.getItem('usuarioLogado');
        
        if (!usuarioLogado) {
            alert("Você precisa fazer login para acessar esta página!");
            window.location.href = "login.html"; // Manda pro login
        }
    }
}

// =======================================================
// 5. RENDERIZAR NA TELA (HTML)
// =======================================================
function renderUserList(lista) {
    var userListContainer = document.getElementById('userListContainer');
    
    // Se o container não estiver visível ou criado, cria/mostra
    if (!userListContainer) return;

    userListContainer.innerHTML = '<ul id="userList"></ul>';
    var userListElement = document.getElementById('userList');

    if (!lista || lista.length === 0) {
        userListElement.innerHTML = '<li>Nenhum paciente cadastrado.</li>';
        return;
    }

    lista.forEach(function (paciente) {
        var listItem = document.createElement('li');
        
        // Formata a data (opcional, mas fica mais bonito)
        // const dataFormatada = new Date(paciente.data_nascimento).toLocaleDateString('pt-BR');

        listItem.innerHTML = `
            <div class="user-info">
                <strong>Paciente:</strong> ${paciente.nome} <br>
                <strong>Nascimento:</strong> ${paciente.data_nascimento} <br>
                <small style="color: gray;">
                   Funcionário Responsável: ${paciente.nomeProfissional || 'Desconhecido'} 
                </small>
            </div>
            <button class="delete-button" onclick="deleteUser(${paciente.id})" title="Excluir">
                &times; 
            </button>
        `;
        userListElement.appendChild(listItem);
    });
}


// =======================================================
// INICIALIZAÇÃO
// =======================================================
document.addEventListener('DOMContentLoaded', function() {
    
    // 1. Carrega os dados iniciais
    carregarProfissionais(); // Preenche o dropdown
    getUserList();           // Preenche a tabela (se já estiver visível)
    
    const form = document.getElementById('cadastro-usuario');
    const listarButton = document.getElementById('btnListar');

    // 2. Evento do Botão Cadastrar
    if (form) {
        form.addEventListener('submit', function (event) {
            event.preventDefault(); 
            const nomeInput = document.getElementById('nome').value;
            const dataNascimentoInput = document.getElementById('data_nascimento').value;
            addUser(nomeInput, dataNascimentoInput);
        });
    }
    
    // 3. Evento do Botão Listar
    if (listarButton) {
        listarButton.addEventListener('click', function() {
            const userListContainer = document.getElementById('userListContainer');
            
            if (userListContainer.style.display === 'none' || userListContainer.innerHTML === '') {
                userListContainer.style.display = 'block';
                listarButton.textContent = 'Fechar Lista';
                getUserList(); // Busca dados atualizados
            } else {
                userListContainer.style.display = 'none';
                listarButton.textContent = 'Listar';
            }
        });
    }

    // --- NOVO: Protege a página assim que carrega ---
    verificarAutenticacao(); 

    // --- NOVO: Lógica do Formulário de Login ---
    const formLogin = document.getElementById('form-login');
    if (formLogin) {
        formLogin.addEventListener('submit', function(e) {
            e.preventDefault();
            const email = document.getElementById('email').value;
            const senha = document.getElementById('senha').value;
            fazerLogin(email, senha);
        });
    }
});