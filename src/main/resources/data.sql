INSERT INTO categorias (id, nome, descricao)
VALUES (1, 'Filme', 'Sugestões de filmes'),
       (2, 'Série', 'Sugestões de séries'),
       (3, 'Livro', 'Sugestões de livros'),
       (4, 'Jogo', 'Sugestões de jogos'),
       (5, 'Música', 'Sugestões de músicas ou álbuns');

INSERT INTO usuarios (id, nome, email, senha, imagem_perfil, crp, tipo, is_admin)
VALUES (1, 'Erick0 Luis', 'admin@email.com', '$2b$12$0WXhFcPcikwbfNU3P/lDgescg77FG2GhNUmaxQaSm.7/FaFyrOe4.', NULL, 'CRP-00001', 'psicologo', true),
       (2, 'Maria Silveira', 'maria@email.com', '$2b$12$0WXhFcPcikwbfNU3P/lDgescg77FG2GhNUmaxQaSm.7/FaFyrOe4.', NULL, NULL, 'comum', false),
       (3, 'João Gomes', 'joao@email.com', '$2b$12$0WXhFcPcikwbfNU3P/lDgescg77FG2GhNUmaxQaSm.7/FaFyrOe4.', NULL, NULL, 'comum', false);

