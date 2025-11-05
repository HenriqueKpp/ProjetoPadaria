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


//FORMULÁRIO ADICIONAR OS PRODUTOS
const formularioProdutos = document.querySelector("#produtos");

if (formularioProdutos) {
    const Inomeproduto = formularioProdutos.querySelector(".nome_produto");
    const Icusto = formularioProdutos.querySelector(".preço_custo");
    const Ivenda = formularioProdutos.querySelector(".preço_venda");
    const Iestoque = formularioProdutos.querySelector(".estoque");

    formularioProdutos.addEventListener('submit', function (event) {
        event.preventDefault();

        fetch("http://localhost:8080/produtos", {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                nome: Inomeproduto.value,
                preco_custo: Icusto.value,
                preco_venda: Ivenda.value,
                qntd_estoque: Iestoque.value
            })
        })
            .then(res => console.log("Adicionar produtos status:", res.status))
            .catch(err => console.error(err));

        // limpar os campos do formulário
        Inomeproduto.value = "";
        Icusto.value = "";
        Ivenda.value = "";
        Iestoque.value = "";
    });
}


// FORMULÁRIO DE REMOVER PRODUTO
const formularioDelProdutos = document.querySelector("#produtos_del");

if (formularioDelProdutos) {

    const Iid = formularioDelProdutos.querySelector(".id_produto_del");
    formularioDelProdutos.addEventListener('submit', function (event) {
        event.preventDefault();
        const id = Iid.value;

        fetch(`http://localhost:8080/produtos/${id}`, {
            method: 'DELETE'
        })
            .then(res => {
                console.log("Remover produto status:", res.status);
                if (res.status === 204) {
                    alert("Produto removido com sucesso!");
                } else if (res.status === 404) {
                    alert("Produto não encontrado!");
                } else {
                    alert("Erro ao remover produto.");
                }
            })
            .catch(err => console.error(err));

        // limpar o campo do formulário
        Iid.value = "";
    });
}
