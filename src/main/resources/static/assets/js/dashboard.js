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