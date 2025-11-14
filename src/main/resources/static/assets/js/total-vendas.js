document.addEventListener("DOMContentLoaded", function () {
    const campo = document.getElementById("totalVendas");

    fetch("/api/total-vendas") // Deve ser IGUAL ao @GetMapping do controller
        .then(response => {
            if (!response.ok) {
                throw new Error("HTTP " + response.status);
            }
            return response.json();
        })
        .then(valor => {
            campo.textContent = "R$ " + valor;
        })
        .catch(error => {
            console.error("Erro:", error);
            campo.textContent = "Erro ao carregar";
        });
});
