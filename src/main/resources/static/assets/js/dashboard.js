// MOSTRAR O VALOR TOTAL SOMENTE PARA GERENTES
document.addEventListener("DOMContentLoaded", function () {

    const grupoId = Number(localStorage.getItem("grupoId")); // 1 = Funcionário, 2 = Gerente

    const cardTotalVendas = document.getElementById("cardTotalVendas");

    // Usuário é funcionário? (grupo 1)
    if (grupoId === 1) {
        if (cardTotalVendas) {
            cardTotalVendas.style.display = "none";
        }
    }

    // Usuário é gerente? (grupo 2)
    else if (grupoId === 2) {
        if (cardTotalVendas) {
            cardTotalVendas.style.display = "block";
        }
    }

});



// MOSTRAR NOME DO USUÁRIO NA DASHBOARD
const nome = localStorage.getItem("nomeUsuario");
if (nome) {
    document.querySelector("#boasVindas").textContent = `Bem-vindo, ${nome}! `;
}

// VALOR TOTAL DE VENDAS
document.addEventListener('DOMContentLoaded', function() {
    const el = document.getElementById('totalVendas');
    if (!el) return;

    fetch('/api/total-vendas')
        .then(response => {
            if (!response.ok) throw new Error('Erro ao obter total de vendas');
            return response.json(); // espera número (Double) do backend
        })
        .then(total => {
            const valor = typeof total === 'number' ? total : parseFloat(total) || 0;

            // Sempre mostra duas casas decimais
            const formatado = new Intl.NumberFormat('pt-BR', {
                style: 'currency',
                currency: 'BRL',
                minimumFractionDigits: 2,
                maximumFractionDigits: 2
            }).format(valor);

            el.textContent = formatado;
        })
        .catch(err => {
            console.error(err);
            el.textContent = 'R$\u00a00,00';
        });
});

// VENDA TOTAL POR DIA
document.addEventListener("DOMContentLoaded", () => {
    // Carrega os dados iniciais sem filtro
    carregarVendasPorDia();

    const filtrarBtn = document.getElementById("filtrarBtn");
    const limparFiltroBtn = document.getElementById("limparFiltroBtn");
    const dataInicioInput = document.getElementById("dataInicio");
    const dataFimInput = document.getElementById("dataFim");

    filtrarBtn.addEventListener("click", () => {
        const dataInicio = dataInicioInput.value;
        const dataFim = dataFimInput.value;
        carregarVendasPorDia(dataInicio, dataFim);
    });

    limparFiltroBtn.addEventListener("click", () => {
        dataInicioInput.value = "";
        dataFimInput.value = "";
        carregarVendasPorDia();
    });
});


async function carregarVendasPorDia(dataInicio, dataFim) {
    const tbody = document.getElementById("tabelaVendasDia");
    tbody.innerHTML = `<tr><td colspan="2">Carregando...</td></tr>`;

    let url = "http://localhost:8080/vendas_por_dia";
    const params = new URLSearchParams();

    if (dataInicio) {
        params.append('inicio', dataInicio);
    }
    if (dataFim) {
        params.append('fim', dataFim);
    }

    if (params.toString()) {
        url += `?${params.toString()}`;
    }


    try {
        const resp = await fetch(url);

        if (!resp.ok) throw new Error("Erro HTTP " + resp.status);

        const lista = await resp.json();

        // Se não tiver dados
        if (lista.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="2">Nenhum dado encontrado para o período selecionado.</td>
                </tr>
            `;
            return;
        }

        tbody.innerHTML = ""; // limpa o "Carregando..."

        lista.forEach(item => {
            // Formata a data para o padrão brasileiro (dd/MM/yyyy)
            const [ano, mes, dia] = item.dia.split('-');
            const dataFormatada = `${dia}/${mes}/${ano}`;

            const linha = `
                <tr>
                    <td>${dataFormatada}</td>
                    <td>${new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(item.totalVendido)}</td>
                </tr>
            `;
            tbody.innerHTML += linha;
        });

    } catch (e) {
        console.error("Erro ao carregar vendas:", e);

        tbody.innerHTML = `
            <tr>
                <td colspan="2">Erro ao carregar dados</td>
            </tr>
        `;
    }
}
