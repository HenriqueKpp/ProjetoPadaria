document.addEventListener("DOMContentLoaded", () => {
    const btnAdicionar = document.getElementById("btnAdicionarProduto");
    const container = document.getElementById("produtosContainer");
    const btnFinalizar = document.getElementById("finalizarPedido");

    // ➕ Adicionar produto dinamicamente
    btnAdicionar.addEventListener("click", () => {
        const novoProduto = document.createElement("div");
        novoProduto.classList.add("produto-item", "row", "g-3", "align-items-end", "smooth-subcard");
        novoProduto.innerHTML = `
        <div class="col-md-5">
            <label class="form-label">ID do Produto:</label>
            <input type="number" class="form-control produto-id" required>
        </div>

        <div class="col-md-5">
            <label class="form-label">Quantidade:</label>
            <input type="number" class="form-control produto-qtd" min="1" required>
        </div>

        <div class="col-md-2 text-end">
            <button type="button" class="btn btn-danger remover-produto">Remover</button>
        </div>
    `;
        container.appendChild(novoProduto);

        novoProduto.querySelector(".remover-produto").addEventListener("click", () => {
            novoProduto.remove();
        });
    });

    //  Finalizar pedido
    btnFinalizar.addEventListener("click", async () => {
        const funcionario_id = document.getElementById("cpfFuncionario").value.trim();
        const cpf_cliente = document.getElementById("cpfCliente").value.trim();
        const forma_pagamento = document.getElementById("formaPagamento").value;

        const itens = [...document.querySelectorAll(".produto-item")].map(item => ({
            produto: { id: parseInt(item.querySelector(".produto-id").value) },
            quantidade: parseInt(item.querySelector(".produto-qtd").value)
        }));

        const pedido = { funcionario_id, cpf_cliente, forma_pagamento, itens };

        try {
            const resposta = await fetch("http://localhost:8080/pedidos", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(pedido)
            });

            if (!resposta.ok) throw new Error("Erro ao salvar pedido!");

            alert(" Pedido registrado com sucesso!");
            document.getElementById("form-pedido").reset();
            container.innerHTML = "";
            carregarPedidos(); // recarrega lista após salvar
        } catch (erro) {
            alert(" Erro ao registrar pedido: " + erro.message);
        }
    });

    //  Função para carregar pedidos de forma organizada
    async function carregarPedidos() {
        const listaPedidosDiv = document.getElementById("lista-pedidos");
        if (!listaPedidosDiv) {
            console.error("Elemento #lista-pedidos não encontrado no HTML!");
            return;
        }

        listaPedidosDiv.innerHTML = "<p>Carregando pedidos...</p>";

        try {
            const resposta = await fetch("http://localhost:8080/pedidos");
            if (!resposta.ok) throw new Error("Erro ao buscar pedidos");

            const pedidos = await resposta.json();
            listaPedidosDiv.innerHTML = "";

            if (pedidos.length === 0) {
                listaPedidosDiv.innerHTML = "<p>Nenhum pedido registrado.</p>";
                return;
            }

            pedidos.forEach(pedido => {
                const pedidoDiv = document.createElement("div");
                pedidoDiv.classList.add("pedido-card");
                pedidoDiv.style.marginBottom = "20px";
                pedidoDiv.style.padding = "15px";
                pedidoDiv.style.border = "1px solid #ccc";
                pedidoDiv.style.borderRadius = "10px";
                pedidoDiv.style.backgroundColor = "white";
                pedidoDiv.style.boxShadow = "0 2px 4px rgba(0,0,0,0.1)";

                const dataFormatada = pedido.data_pedido
                    ? new Date(pedido.data_pedido).toLocaleString("pt-BR")
                    : "—";

                // Cabeçalho do pedido
                const cabecalho = `
                    <h3 style="margin-bottom: 10px; color: #b91c1c;">Pedido #${pedido.id || pedido.pedido_id || pedido.id_pedido || "?"}</h3>
                    <p><strong>Funcionário:</strong> ${pedido.funcionario_id || "—"}</p>
                    <p><strong>Cliente:</strong> ${pedido.cpf_cliente || "—"}</p>
                    <p><strong>Forma de Pagamento:</strong> ${pedido.forma_pagamento || "—"}</p>
                    <p><strong>Data:</strong> ${dataFormatada}</p>
                    <p><strong>Valor Final:</strong> R$ ${pedido.valor_final?.toFixed(2) || "0.00"}</p>
                    <hr style="margin: 10px 0;">
                `;

                // Lista de produtos
                let produtosHTML = "<h4 style='margin-bottom: 5px;'>Produtos:</h4>";
                if (pedido.itens && pedido.itens.length > 0) {
                    produtosHTML += `
                        <ul style="list-style: none; padding-left: 0;">
                            ${pedido.itens
                        .map(
                            item => `
                                    <li style="background:#f9f9f9; margin:4px 0; padding:6px; border-radius:6px;">
                                        <strong>ID:</strong> ${item.produto.id} — 
                                        <strong>Nome:</strong> ${item.produto.nome || "Sem nome"} — 
                                        <strong>Qtd:</strong> ${item.quantidade} — 
                                        <strong>Subtotal:</strong> R$ ${item.subtotal?.toFixed(2) || "0.00"}
                                    </li>`
                        )
                        .join("")}
                        </ul>`;
                } else {
                    produtosHTML += "<p>Sem produtos neste pedido.</p>";
                }

                pedidoDiv.innerHTML = cabecalho + produtosHTML;
                listaPedidosDiv.appendChild(pedidoDiv);
            });
        } catch (erro) {
            console.error("Erro ao carregar pedidos:", erro);
            listaPedidosDiv.innerHTML = "<p>Erro ao carregar pedidos.</p>";
        }
    }

    //  Chama ao abrir a página
    carregarPedidos();
});
