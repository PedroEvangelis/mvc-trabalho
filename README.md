# Cenário 1 — Clínica Veterinária

Sistema de gerenciamento para clínica veterinária com cadastro de tutores, animais, consultas e controle de pagamento.

## Tecnologias

- Java 25
- Maven
- PostgreSQL 16
- JDBC (sem frameworks)

## Como executar

1. Suba o banco com Docker:
   ```bash
   docker compose up -d
   ```

2. Execute a classe `Main.java` pela sua IDE ou via Maven:
   ```bash
   mvn compile exec:java -Dexec.mainClass="evangelz.Main"
   ```

## Estrutura MVC

```
src/main/java/evangelz/
├── Main.java                  # Inicialização + menu principal
├── util/Conexao.java          # Conexão JDBC com PostgreSQL
├── model/                     # Entidades
│   ├── Veterinario.java
│   ├── Tutor.java
│   ├── Animal.java
│   └── Consulta.java
├── repository/                # CRUD com SQL via JDBC
│   ├── VeterinarioRepository.java
│   ├── TutorRepository.java
│   ├── AnimalRepository.java
│   └── ConsultaRepository.java
├── service/                   # Regras de negócio
│   ├── SessaoService.java
│   ├── TutorService.java
│   ├── AnimalService.java
│   └── ConsultaService.java
└── controller/                # Interface com usuário (Scanner)
    ├── SessaoController.java
    ├── TutorController.java
    ├── AnimalController.java
    └── ConsultaController.java
```

## Tabelas

### veterinario
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PRIMARY KEY |
| nome | VARCHAR(100) | NOT NULL |

### tutor
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PRIMARY KEY |
| nome | VARCHAR(100) | NOT NULL |
| telefone | VARCHAR(20) | NOT NULL |

### animal
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PRIMARY KEY |
| nome | VARCHAR(100) | NOT NULL |
| especie | VARCHAR(50) | NOT NULL |
| raca | VARCHAR(50) | |
| id_tutor | INTEGER | NOT NULL, FK → tutor(id) ON DELETE CASCADE |

### consulta
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PRIMARY KEY |
| id_animal | INTEGER | NOT NULL, FK → animal(id) ON DELETE CASCADE |
| id_veterinario | INTEGER | NOT NULL, FK → veterinario(id) |
| data | DATE | NOT NULL |
| motivo | VARCHAR(255) | NOT NULL |
| valor | DECIMAL(10,2) | NOT NULL, CHECK (valor >= 0) |
| status_pagamento | VARCHAR(20) | NOT NULL DEFAULT 'PENDENTE', CHECK ('PENDENTE', 'PAGO') |

## Regras de Negócio

| # | Regra | Local |
|---|-------|-------|
| RN1 | Tutor deve ter nome e telefone obrigatórios | TutorService.cadastrar() |
| RN2 | Animal deve ter nome e espécie obrigatórios | AnimalService.cadastrar() |
| RN3 | Animal não pode existir sem tutor cadastrado | AnimalService.cadastrar() |
| RN4 | Consulta não pode existir sem animal e veterinário cadastrados | ConsultaService.registrar() |
| RN5 | Valor da consulta não pode ser negativo | ConsultaService.registrar() |
| RN6 | Status de pagamento padrão é PENDENTE | Consulta.java (construtor) |
| RN7 | Só é possível baixar pagamento de consulta PENDENTE | ConsultaService.baixarPagamento() |
| RN8 | Exclusão de tutor remove em cascata animais e consultas | Main.java (SQL: ON DELETE CASCADE) |
| RN9 | Exclusão de animal é bloqueada se houver consultas | AnimalService.deletar() |
| RN10 | Veterinário deve se identificar antes de qualquer operação | SessaoController.iniciarSessao() |
