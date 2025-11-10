// selecionar os formulários (pode retornar null)
const formularioLogin = document.querySelector("#login");

// LOGIN
if (formularioLogin) {
    const IcpfLogin = formularioLogin.querySelector(".cpf");
    const IsenhaLogin = formularioLogin.querySelector(".senha");

    formularioLogin.addEventListener('submit', function(event) {
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

            .then(res => { //LOGIN VALIDO
                if (res.status === 200) {
                    return res.json();

                } else if (res.status === 401) {   //LOGIN INVALIDO
                    alert("CPF ou senha incorretos!");  //AVISO DE ERRO

                } else {
                    alert("Erro ao tentar fazer login. Código: " + res.status); //AVISO DE ERRO
                }
            })
            .catch(error => {
                console.error("Erro na requisição:", error);
                alert("Erro ao conectar ao servidor!"); //AVISO DE ERRO
            })

            .then(usuario => {
                localStorage.setItem("nomeUsuario", usuario.nome);  //PEGA O NOME E GUARDA NO STORAGE
                window.location.href = "/dashboard";
            })
            .catch(error => {
                console.error("Erro:", error);
            });
    })
}
