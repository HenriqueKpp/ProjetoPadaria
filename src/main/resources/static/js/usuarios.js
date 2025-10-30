document.addEventListener("DOMContentLoaded", () => {
    const corpoTabela = document.querySelector("#tabelaUsuarios");

    fetch("http://localhost:8080/usuarios")
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
