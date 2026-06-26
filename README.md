# Sistema de oficina mecânica

## Regras de negócio
    O sistema deve permitir o cadastro de clientes, sendo nome e telefone obrigatórios.
    O sistema deve permitir o cadastro de veículos, vinculando-os a um cliente cadastrado.
    O sistema deve permitir a abertura de uma ordem de serviço, sempre vinculada a um veículo.
    A descrição do problema e o valor do serviço são obrigatórios.
    O sistema deve criar a ordem como "Aberta" e permitir alterar o status da ordem para "Concluída".
    O sistema deve permitir buscar e visualizar todas as ordens de serviço passadas de um veículo específico.
    Um cliente pode ter múltiplos veículos cadastrados, mas um veículo deve pertencer a apenas um cliente.
    É proibido abrir uma ordem de serviço para um veículo que não esteja previamente cadastrado.
    O valor do serviço inserido na ordem de serviço não pode ser menor que zero


## Entidades
| 			               |
|-------------------|
| Cliente           |
| Veículo           |
| Serviço           |
| Ordem de serviço  |

## Tabelas necessarias
Para o banco de dados, foi utilizado o usuário "admin", com a senha "fofis", na porta padrão '5432' e na database "oficina".<br>
Os seguntes comandos foram utilizados para criar as tabelas:

    CREATE TABLE IF NOT EXISTS cliente (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, telefone VARCHAR(20) NOT NULL);
    CREATE TABLE IF NOT EXISTS veiculo (id SERIAL PRIMARY KEY, placa VARCHAR(10) NOT NULL, modelo VARCHAR(100) NOT NULL, ano INTEGER NOT NULL, id_cliente INTEGER NOT NULL, FOREIGN KEY (id_cliente) REFERENCES cliente(id) ON DELETE CASCADE);
    CREATE TABLE IF NOT EXISTS servico (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, descricao VARCHAR(255), valor_base DECIMAL(10,2) NOT NULL CHECK (valor_base >= 0));
    CREATE TABLE IF NOT EXISTS ordem_servico (id SERIAL PRIMARY KEY, id_veiculo INTEGER NOT NULL, data_abertura DATE NOT NULL, descricao_problema VARCHAR(255) NOT NULL, status VARCHAR(20) NOT NULL DEFAULT 'ABERTA' CHECK (status IN ('ABERTA', 'CONCLUIDA')), status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE' CHECK (status_pagamento IN ('PENDENTE', 'PAGO')), FOREIGN KEY (id_veiculo) REFERENCES veiculo(id) ON DELETE CASCADE);
    CREATE TABLE IF NOT EXISTS item_ordem_servico (id SERIAL PRIMARY KEY, id_ordem_servico INTEGER NOT NULL, id_servico INTEGER NOT NULL, quantidade INTEGER NOT NULL CHECK (quantidade > 0), valor_unitario DECIMAL(10,2) NOT NULL CHECK (valor_unitario >= 0), FOREIGN KEY (id_ordem_servico) REFERENCES ordem_servico(id) ON DELETE CASCADE, FOREIGN KEY (id_servico) REFERENCES servico(id));
           

    