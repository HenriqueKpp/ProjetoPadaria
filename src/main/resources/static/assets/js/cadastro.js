const formularioCadastro = document.querySelector("#cadastro");

if (formularioCadastro) {
    const Inome = formularioCadastro.querySelector(".nome");
    const Icpf = formularioCadastro.querySelector(".cpf");
    const Isenha = formularioCadastro.querySelector(".senha");
    const Itel = formularioCadastro.querySelector(".tel");
    const IGrupo = formularioCadastro.querySelector("#grupo_id");
    const togglePassword = formularioCadastro.querySelector("#togglePassword");

    if (togglePassword) {
        togglePassword.addEventListener('click', function () {
            // Alterna o tipo do input de senha
            const type = Isenha.getAttribute('type') === 'password' ? 'text' : 'password';
            Isenha.setAttribute('type', type);
            // Alterna a classe do ícone
            this.classList.toggle('bi-eye');
            this.classList.toggle('bi-eye-slash');
        });
    }

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
                telefone: Itel.value,
                grupo_id: Number(IGrupo.value)
            })
        })
            .then(res => {
                if (res.status === 201) {
                    alert("Usuário cadastrado com sucesso!");
                    localStorage.setItem("nomeUsuario", Inome.value);
                } else {
                    res.json().then(data => {
                        alert("Erro ao cadastrar usuário: " + (data.message || res.status));
                    });
                }
            })
            .catch(err => alert("Erro ao cadastrar usuário: " + err));

        Inome.value = "";
        Icpf.value = "";
        Isenha.value = "";
        Itel.value = "";
        IGrupo.value = "1"; // volta ao padrão
    });
}
