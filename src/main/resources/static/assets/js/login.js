document.addEventListener('DOMContentLoaded', function() {
    // Se o usuário já está "lembrado", redireciona para o dashboard
    if (localStorage.getItem("lembrar") === "true" && localStorage.getItem("nomeUsuario")) {
        window.location.href = "/dashboard";
        return; // Impede a execução do resto do script
    }

    const formularioLogin = document.querySelector("#login");
    if (formularioLogin) {
        const Icpf = formularioLogin.querySelector(".cpf");
        const Isenha = formularioLogin.querySelector(".senha");
        const Ilembrar = formularioLogin.querySelector("#lembrar");

        formularioLogin.addEventListener('submit', function(event) {
            event.preventDefault();

            fetch("http://localhost:8080/usuarios/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    cpf: Icpf.value,
                    senha: Isenha.value
                })
            })
                .then(res => {
                    if (res.ok) { // Status 200-299
                        return res.json();
                    } else {
                        throw new Error("CPF ou senha inválidos.");
                    }
                })
                .then(usuario => {
                    // Salva o nome do usuário para exibir no dashboard
                    localStorage.setItem("nomeUsuario", usuario.nome);

                    // Verifica se "Lembrar de mim" está marcado
                    if (Ilembrar.checked) {
                        localStorage.setItem("lembrar", "true");
                    } else {
                        localStorage.removeItem("lembrar");
                    }

                    // Redireciona para o dashboard
                    window.location.href = "/dashboard";
                })
                .catch(err => {
                    console.error("Erro no login:", err);
                    alert(err.message || "Ocorreu um erro ao tentar fazer login.");
                });
        });
    }
});
