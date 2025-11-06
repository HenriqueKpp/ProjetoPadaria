const formPedido = document.querySelector("#form-pedido");
const container = document.querySelector("#produtos-container");
const btnAddProduto = document.querySelector("#adicionar-produto");

// Adicionar novo campo de produto
btnAddProduto.addEventListener("click", () => {
    const div = document.createElement("div");
    div.classList.add("produto-item");
    div.innerHTML = `
        <label>ID do Produto:</label>
        <input type="number" class="produto-id" required>
        <label>Quantidade:</label>
        <input type="number" class="produto-qtd" min="1" required>
        <button type="button" class="remover-produto">Remover</button>
    `;
    container.appendChild(div);

    div.querySelector(".remover-produto").addEventListener("click", () => div.remove());
});

// Enviar pedido
formPedido.document.addEventListener("DOMContentLoaded", () => {
    const btnAddProduto = document.querySelector("#adicionar-produto");
    const container = document.querySelector("#produtos-container");

    let contador = 1; // pra gerar IDs únicos

    btnAddProduto.addEventListener("click", () => {
        const div = document.createElement("div");
        div.classList.add("produto-item");

        const idInput = `produto-id-${contador}`;
        const qtdInput = `produto-qtd-${contador}`;

        div.innerHTML = `
            <label for="${idInput}">ID do Produto:</label>
            <input type="number" id="${idInput}" name="produto-id" class="produto-id" required>

            <label for="${qtdInput}">Quantidade:</label>
            <input type="number" id="${qtdInput}" name="produto-qtd" class="produto-qtd" min="1" required>

            <button type="button" class="remover-produto">Remover</button>
        `;

        container.appendChild(div);
        contador++;

        div.querySelector(".remover-produto").addEventListener("click", () => div.remove());
    });
});
