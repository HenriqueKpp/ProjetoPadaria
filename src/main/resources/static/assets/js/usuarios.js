//ESCREVE OS USUARIOS
document.addEventListener("DOMContentLoaded", () => {
    const corpoTabela = document.querySelector("#tabelaUsuarios");
    const loggedInUserName = localStorage.getItem('nomeUsuario'); // Pega o nome do usuário logado

    fetch("http://localhost:8080/usuarios/info")
        .then(res => {
            if (!res.ok) {
                throw new Error("Erro ao carregar usuários");
            }
            return res.json();
        })
        .then(usuarios => {
            usuarios.forEach(usuario => {
                const linha = document.createElement("tr");

                // Verifica se o usuário da linha é o mesmo que está logado
                const isCurrentUser = loggedInUserName && usuario.nome === loggedInUserName;

                // O botão só é criado se não for o usuário atual
                const deleteButtonHtml = !isCurrentUser
                    ? `<button data-id="${usuario.id}" class="btn btn-delete">Excluir</button>`
                    : ''; // Deixa a célula vazia se for o usuário logado

                linha.innerHTML = `
                  <td>${usuario.id}</td>
                  <td>${usuario.nome}</td>
                  <td>${usuario.cpf}</td>
                  <td>${usuario.telefone}</td>
                  <td>${deleteButtonHtml}</td>
                `;

                corpoTabela.appendChild(linha);
            });

            // Adiciona event listeners apenas para os botões de exclusão que foram criados
            document.querySelectorAll('.btn-delete').forEach(button => {
                button.addEventListener('click', function () {
                    const usuarioId = this.getAttribute('data-id');

                    if (confirm('Tem certeza que deseja excluir este usuário?')) {
                        fetch(`http://localhost:8080/usuarios/${usuarioId}`, {
                            method: 'DELETE',
                        })
                            .then(res => {
                                if (res.status === 204) {
                                    location.reload(); // Recarrega a página
                                } else if (res.status === 404) {
                                    alert('Usuário não encontrado.');
                                } else {
                                    alert('Erro ao excluir o usuário.');
                                }
                            })
                            .catch(error => {
                                console.error('Erro ao excluir usuário:', error);
                                alert('Ocorreu um erro na requisição.');
                            });
                    }
                });
            });
        })
        .catch(error => {
            console.error("Erro:", error);
            corpoTabela.innerHTML = `<tr><td colspan="5">Erro ao carregar usuários</td></tr>`;
        });
});
