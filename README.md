# Cenário 2 — Oficina Mecânica

Sistema de gerenciamento para oficina mecânica com cadastro de clientes, veículos, serviços e ordens de serviço.

## Tecnologias

- Java 25
- Maven
- PostgreSQL 16
- JDBC

## Como executar

1. Suba o banco: `docker compose up -d`
2. Execute `evangelz.Main`

## Tabelas

### cliente
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| nome | VARCHAR(100) | NOT NULL |
| telefone | VARCHAR(20) | NOT NULL |

### veiculo
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| placa | VARCHAR(10) | NOT NULL |
| modelo | VARCHAR(100) | NOT NULL |
| ano | INTEGER | NOT NULL |
| id_cliente | INTEGER | NOT NULL, FK → cliente ON DELETE CASCADE |

### servico
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| nome | VARCHAR(100) | NOT NULL |
| descricao | VARCHAR(255) | |
| valor_base | DECIMAL(10,2) | NOT NULL, CHECK (>= 0) |

### ordem_servico
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| id_veiculo | INTEGER | NOT NULL, FK → veiculo ON DELETE CASCADE |
| data_abertura | DATE | NOT NULL |
| descricao_problema | VARCHAR(255) | NOT NULL |
| status | VARCHAR(20) | NOT NULL, DEFAULT 'ABERTA', CHECK ('ABERTA','CONCLUIDA') |
| status_pagamento | VARCHAR(20) | NOT NULL, DEFAULT 'PENDENTE', CHECK ('PENDENTE','PAGO') |

### item_ordem_servico
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| id_ordem_servico | INTEGER | NOT NULL, FK → ordem_servico ON DELETE CASCADE |
| id_servico | INTEGER | NOT NULL, FK → servico |
| quantidade | INTEGER | NOT NULL, CHECK (> 0) |
| valor_unitario | DECIMAL(10,2) | NOT NULL, CHECK (>= 0) |

## Regras de Negócio

| # | Regra | Local |
|---|-------|-------|
| RN1 | Cliente deve ter nome e telefone obrigatórios | ClienteService |
| RN2 | Veículo não existe sem cliente | VeiculoService |
| RN3 | OS não existe sem veículo e serviços | OrdemServicoService |
| RN4 | Valor da OS = soma (quantidade * valor_unitario) dos itens | OrdemServicoService |
| RN5 | Quantidade dos itens deve ser > 0 | OrdemServicoService |
| RN6 | Valor unitário >= 0 | OrdemServicoService |
| RN7 | Só fechar OS com status ABERTA | OrdemServicoService |
| RN8 | Só baixar pagamento com OS CONCLUIDA e PENDENTE | OrdemServicoService |
