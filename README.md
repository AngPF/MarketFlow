# MovieBox

## Requisitos

- [ ] CRUD de Avaliação 
- [ ] CRUD de Titulos
- [ ] Dashboar
- [ ] Autenticação

## EndPoints

### Categorias

1. *Usuários:*
   - POST /usuarios: Criar um novo usuário.
   - GET /usuarios/{id}: Obter detalhes de um usuário específico.
   - PUT /usuarios/{id}: Atualizar informações de um usuário.
   - DELETE /usuarios/{id}: Excluir um usuário.

2. *Filmes:*
   - GET /filmes: Obter uma lista de todos os filmes.
   - GET /filmes/{id}: Obter detalhes de um filme específico.
   - POST /filmes: Adicionar um novo filme à lista.
   - PUT /filmes/{id}: Atualizar informações de um filme.
   - DELETE /filmes/{id}: Remover um filme da lista.

3. *Avaliações:*
   - POST /avaliacoes: Adicionar uma nova avaliação de um filme.
   - GET /avaliacoes/{id}: Obter detalhes de uma avaliação específica.
   - PUT /avaliacoes/{id}: Atualizar uma avaliação existente.
   - DELETE /avaliacoes/{id}: Remover uma avaliação.

4. *Listas de Assistidos/Favoritos:*
   - GET /usuarios/{id}/assistidos: Obter a lista de filmes assistidos por um usuário.
   - GET /usuarios/{id}/favoritos: Obter a lista de filmes favoritos de um usuário.
   - POST /usuarios/{id}/assistidos: Adicionar um filme à lista de assistidos de um usuário.
   - POST /usuarios/{id}/favoritos: Adicionar um filme à lista de favoritos de um usuário.
   - DELETE /usuarios/{id}/assistidos/{filme_id}: Remover um filme da lista de assistidos de um usuário.
   - DELETE /usuarios/{id}/favoritos/{filme_id}: Remover um filme da lista de favoritos de um usuário.

5. *Comentários:*
   - POST /comentarios: Adicionar um novo comentário a um filme ou avaliação.
   - GET /comentarios/{id}: Obter detalhes de um comentário específico.
   - PUT /comentarios/{id}: Atualizar um comentário existente.
   - DELETE /comentarios/{id}: Remover um comentário.

