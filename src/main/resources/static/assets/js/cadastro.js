const formularioCadastro = document.querySelector("#cadastro");

// CADASTRO
if (formularioCadastro) {
    const Inome = formularioCadastro.querySelector(".nome");
    const Icpf = formularioCadastro.querySelector(".cpf");
    const Isenha = formularioCadastro.querySelector(".senha");
    const Itel = formularioCadastro.querySelector(".tel");

    formularioCadastro.addEventListener('submit', function (event) {
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
            .then(res => {
                if (res.status === 201) {
                    alert("Usuário cadastrado com sucesso!");
                    // Salva o nome do usuário no localStorage
                    localStorage.setItem("nomeUsuario", nomeUsuario);
                    // Redireciona para o dashboard
                    window.location.href = "/dashboard";
                } else {
                    res.json().then(data => {
                        alert("Erro ao cadastrar usuário: " + (data.message || res.status));
                    }).catch(() => {
                        alert("Erro ao cadastrar usuário! Status: " + res.status);
                    });
                }
            })
            .catch(err => alert(err + " - Erro ao cadastrar usuário!" + res.status));

        // limpar apenas os campos do formulário de cadastro
        Inome.value = "";
        Icpf.value = "";
        Isenha.value = "";
        Itel.value = "";
    });
}
