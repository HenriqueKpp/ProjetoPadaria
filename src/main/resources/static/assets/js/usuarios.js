//ESCREVE OS USUARIOS
document.addEventListener("DOMContentLoaded", () => {
    const corpoTabela = document.querySelector("#tabelaUsuarios");

    fetch("http://localhost:8080/usuarios/info")
        .then(res => {
            if (!res.ok) {
                throw new Error("Erro ao carregar usuários");
            }
            return res.json();
        })
        .then(usuarios => {
            usuarios.forEach(usuario => {
                const linha = document.createElement("tr");

                linha.innerHTML = `
          <td>${usuario.id}</td>
          <td>${usuario.nome}</td>
          <td>${usuario.cpf}</td>
          <td>${usuario.telefone}</td>
        `;

                corpoTabela.appendChild(linha);
            });
        })
        .catch(error => {
            console.error("Erro:", error);
            corpoTabela.innerHTML = `<tr><td colspan="4">Erro ao carregar usuários</td></tr>`;
        });
});



//DELETAR USUARIOS

// FORMULÁRIO DE REMOVER PRODUTO
const formularioDelUsuarios = document.querySelector("#usuarios_del");

if (formularioDelUsuarios) {

    const Iid = formularioDelUsuarios.querySelector(".id_usuario_del");
    formularioDelUsuarios.addEventListener('submit', function (event) {
        event.preventDefault();

        const id = Iid.value;

        fetch(`http://localhost:8080/usuarios/${id}`, {
            method: 'DELETE'
        })
            .then(res => {
                console.log("Remover usuario status:", res.status);
                if (res.status === 204) {
                    alert("Usuario removido com sucesso!");
                } else if (res.status === 404) {
                    alert("Usuario não encontrado!");
                } else {
                    alert("Erro ao remover usuario.");
                }
            })
            .catch(err => console.error(err));

        Iid.value = "";
    });
}