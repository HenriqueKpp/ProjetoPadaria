//ESCREVE OS USUARIOS
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


//ESCREVE OS PRODUTOS
document.addEventListener("DOMContentLoaded", () => {
    const corpoTabela = document.querySelector("#tabelaProdutos");

    fetch("http://localhost:8080/produtos")
        .then(res => {
            if (!res.ok) {
                throw new Error("Erro ao carregar produtos");
            }
            return res.json();
        })
        .then(produto => {
            produto.forEach(produto => {
                const linha = document.createElement("tr");

                linha.innerHTML = `
          <td>${produto.id}</td>
          <td>${produto.nome}</td>
          <td>${produto.preco_venda} R$ </td>
          <td>${produto.qntd_estoque} uni.</td>
        `;

                corpoTabela.appendChild(linha);
            });
        })
        .catch(error => {
            console.error("Erro:", error);
            corpoTabela.innerHTML = `<tr><td colspan="4">Erro ao carregar produtos</td></tr>`;
        });
});



