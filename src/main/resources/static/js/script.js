// selecionar os formulários (pode retornar null)
const formularioCadastro = document.querySelector("#cadastro");
const formularioLogin = document.querySelector("#login");

// CADASTRO
if (formularioCadastro) {
    const Inome = formularioCadastro.querySelector(".nome");
    const Icpf = formularioCadastro.querySelector(".cpf");
    const Isenha = formularioCadastro.querySelector(".senha");
    const Itel = formularioCadastro.querySelector(".tel");

    formularioCadastro.addEventListener('submit', function(event){
        event.preventDefault();

        fetch("http://localhost:8080/usuarios", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                nome: Inome.value,
                cpf: Icpf.value,
                senha: Isenha.value,
                telefone: Itel.value
            })
        })
            .then(res => console.log("Cadastro status:", res.status))
            .catch(err => console.error(err));

        // limpar apenas os campos do formulário de cadastro
        Inome.value = "";
        Icpf.value = "";
        Isenha.value = "";
        Itel.value = "";
    });
}

// LOGIN
if (formularioLogin) {
    const IcpfLogin = formularioLogin.querySelector(".cpf");
    const IsenhaLogin = formularioLogin.querySelector(".senha");

    formularioLogin.addEventListener('submit', function(event){
        event.preventDefault();

        fetch("http://localhost:8080/usuarios/login", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                cpf: IcpfLogin.value,
                senha: IsenhaLogin.value
            })
        })
            .then(res => {
                console.log("Login status:", res.status);
                return res.json().catch(() => {});
            })
            .then(data => console.log("Resposta:", data))
            .catch(err => console.error("Erro de rede:", err));

        IcpfLogin.value = "";
        IsenhaLogin.value = "";
    });
}
