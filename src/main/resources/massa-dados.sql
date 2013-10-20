INSERT INTO parametro (id, lq, ld, nome, metodo, unidadeMedida, preco) VALUES (default, '', '', 'Sulfetos', '', '', 20.00);
INSERT INTO parametro (id, lq, ld, nome, metodo, unidadeMedida, preco) VALUES (default, '', '', 'Sulfitos', '', '', 28.00);
INSERT INTO parametro (id, lq, ld, nome, metodo, unidadeMedida, preco) VALUES (default, '', '', 'Surfactantes', '', '', 30.00);
INSERT INTO parametro (id, lq, ld, nome, metodo, unidadeMedida, preco) VALUES (default, '', '', 'Urânio', '', '', 280.00);
INSERT INTO parametro (id, lq, ld, nome, metodo, unidadeMedida, preco) VALUES (default, '', '', 'Pentaclorofenol', '', '', 720.00);

INSERT INTO cliente (id, cidade, cpfCnpj, email, endereco, estado, nome, telefone, tipoPessoa) 
	VALUES 
	(default, 'Goiânia', '024.281.221-00', 'yuripx@outlook.com', 'Rua PL9 Qd. J Lt. 7', 8, 'Yuri Martins da Paixão', '(62) 8302 - 1091', 'PF');

INSERT INTO cliente (id, cidade, cpfCnpj, email, endereco, estado, nome, telefone, tipoPessoa) 
	VALUES 
	(default, 'Goiânia', '57.848.462/0001-80', 'kadao@kadao.com', 'Rua fire in the bomb esq c/ Babilônia, St. Jaómaica', 8, 'Kadão Larica\'s', '(62) 3245 - 1234', 'PJ');

INSERT INTO cliente (id, cidade, cpfCnpj, email, endereco, estado, nome, telefone, tipoPessoa) 
	VALUES 
	(default, 'Goiânia', '64.232.358/0001-22', 'kadao@kadao.com', 'Rua ABC Qd 001 Lt 002, St. CDEF, ', 8, 'Empresa  KYZ', '(62) 9876 - 4321', 'PJ');
