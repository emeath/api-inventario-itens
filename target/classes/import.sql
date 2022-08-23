INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(3.0, 'Força', NOW());
INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(6.0, 'Força', NOW());
INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(9.0, 'Força', NOW());
INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(35.0, 'Agilidade', NOW());
INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(35.0, '(%) Esquiva', NOW());
INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(30.0, 'Velocidade de Ataque', NOW());
INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(250.0, 'Vida', NOW());
INSERT INTO tb_atributos(beneficio, descricao, created_at) VALUES(27.0, 'Inteligência', NOW());

INSERT INTO tb_itens(nome, descricao, custo, created_at) VALUES('Dagon 4', 'Lança uma poderosa explosão de dano mágico na unidade inimiga alvo. Aprimorável.', 5200, NOW());
INSERT INTO tb_itens(nome, descricao, custo, created_at) VALUES('Vanguarda', 'Um escudo poderoso que protege o seu usuário até do mais perverso dos ataques.', 2150, NOW());
INSERT INTO tb_itens(nome, descricao, custo, created_at) VALUES('Borboleta', 'Apenas os mais poderosos e experientes guerreiros podem usar a Borboleta, que concede uma incrível destreza em combate.', 5475, NOW());

INSERT INTO tb_itens_atributos(atributo_id, item_id) VALUES (4, 3);
INSERT INTO tb_itens_atributos(atributo_id, item_id) VALUES (5, 3);
INSERT INTO tb_itens_atributos(atributo_id, item_id) VALUES (6, 3);
INSERT INTO tb_itens_atributos(atributo_id, item_id) VALUES (7, 2);
INSERT INTO tb_itens_atributos(atributo_id, item_id) VALUES (8, 1);
