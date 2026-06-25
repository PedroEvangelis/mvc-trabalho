# Sistema de clínica veterinária

## Regras de negócio
	Um tutor pode ter mais de um animal cadastrado, mas cada animal deve estar obrigatoriamente vinculado a um tutor.
	O cadastro do tutor deve conter, pelo menos, nome, endereço e telefone.
	O cadastro do animal deve conter, pelo menos, nome e espécie, raça deve ser opcional.
	Não é permitido registrar uma consulta para um animal que não esteja previamente cadastrado no sistema.
	O valor cobrado por uma consulta não pode ser negativo.
	Cada registro de consulta deve conter obrigatoriamente o animal atendido, a data do atendimento, o motivo e o valor.
	O sistema deve permitir buscar e listar todas as consultas de um animal específico.
	O sistema deve permitir buscar um tutor e visualizar a lista de todos os animais vinculados a ele.
	
## Entidades
|			|
|-----------|
|Veterinario|
|Tutor      |
|Animal     |
|Consulta   |

## Tabelas necessarias
 Para o banco de dados, foi utilizado o usuário "admin", com a senha "fofis", na porta padrão '5432' e na database "clinica".<br>
 Os seguntes comandos foram utilizados para criar as tabelas:
 
	CREATE TABLE IF NOT EXISTS veterinario (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL);
	CREATE TABLE IF NOT EXISTS tutor (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, telefone VARCHAR(20) NOT NULL), endereco VARCHAR(100) NOT NULL);
	CREATE TABLE IF NOT EXISTS animal (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, especie VARCHAR(50) NOT NULL, raca VARCHAR(50), id_tutor INTEGER NOT NULL, FOREIGN KEY (id_tutor) REFERENCES tutor(id) ON DELETE CASCADE);
	CREATE TABLE IF NOT EXISTS consulta (id SERIAL PRIMARY KEY, id_animal INTEGER NOT NULL, id_veterinario INTEGER NOT NULL, data DATE NOT NULL, motivo VARCHAR(255) NOT NULL, valor DECIMAL(10,2) NOT NULL CHECK (valor >= 0), status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE' CHECK (status_pagamento IN ('PENDENTE', 'PAGO')), FOREIGN KEY (id_animal) REFERENCES animal(id) ON DELETE CASCADE, FOREIGN KEY (id_veterinario) REFERENCES veterinario(id));
        
