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
          <td>${produto.precoVenda} R$ </td>
          <td>${produto.precoCusto} R$ </td>
          <td>${produto.qntdEstoque} uni.</td>
          <td>
            <a href="/produtos/editar?id=${produto.id}" class="btn btn-edit">Editar</a>
            <button data-id="${produto.id}" class="btn btn-delete">Excluir</button>
          </td>
        `;

                corpoTabela.appendChild(linha);
            });
            // Adiciona event listeners para os botões de exclusão
            document.querySelectorAll('.btn-delete').forEach(button => {
                button.addEventListener('click', function () {
                    const produtoId = this.getAttribute('data-id');

                    if (confirm('Tem certeza que deseja excluir este produto?')) {
                        fetch(`http://localhost:8080/produtos/${produtoId}`, {
                            method: 'DELETE',
                        })
                            .then(res => {
                                if (res.status === 204) {
                                    location.reload(); // Recarrega a página
                                } else if (res.status === 404) {
                                    alert('Produto não encontrado.');
                                } else {
                                    alert('Erro ao excluir o produto.');
                                }
                            })
                            .catch(error => {
                                console.error('Erro ao excluir produto:', error);
                                alert('Ocorreu um erro na requisição.');
                            });
                    }
                });
            });
        })
        .catch(error => {
            console.error("Erro:", error);
            corpoTabela.innerHTML = `<tr><td colspan="6">Erro ao carregar produtos</td></tr>`;
        });
});