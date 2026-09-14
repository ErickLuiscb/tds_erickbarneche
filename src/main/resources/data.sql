INSERT INTO categorias (id, nome, descricao)
VALUES (1, 'Filme', 'Sugestões de filmes'),
       (2, 'Série', 'Sugestões de séries'),
       (3, 'Livro', 'Sugestões de livros'),
       (4, 'Jogo', 'Sugestões de jogos'),
       (5, 'Música', 'Sugestões de músicas ou álbuns');


INSERT INTO usuarios (id, nome, email, senha, imagem_perfil, crp, tipo, is_admin)
VALUES (1, 'Erick Barneche', 'erick@exemplo.com', '123456', NULL, NULL, 'admin', true),
       (2, 'Maria Silva', 'maria@exemplo.com', 'senha123', NULL, 'CRP-12345', 'psicologo', false),
       (3, 'João Souza', 'joao@exemplo.com', 'senha456', NULL, NULL, 'comum', false);

