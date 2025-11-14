document.addEventListener("DOMContentLoaded", carregarVendasPorDia);

async function carregarVendasPorDia() {
    const tbody = document.getElementById("tabelaVendasDia");

    try {
        const resp = await fetch("http://localhost:8080/vendas_por_dia");

        if (!resp.ok) throw new Error("Erro HTTP " + resp.status);

        const lista = await resp.json();

        // Se não tiver dados
        if (lista.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="2">Nenhum dado encontrado</td>
                </tr>
            `;
            return;
        }

        tbody.innerHTML = ""; // limpa o "Carregando..."

        lista.forEach(item => {
            const linha = `
                <tr>
                    <td>${item.dia}</td>
                    <td>R$ ${Number(item.totalVendido).toFixed(2)}</td>
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
