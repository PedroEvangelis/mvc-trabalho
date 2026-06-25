# MVC JDBC + PostgreSQL

Trabalho acad�mico com 3 sistemas independentes, cada um em sua branch.

## Branches

| Branch | Projeto |
|--------|---------|
| cenario1 | Cl�nica Veterin�ria � tutores, animais, consultas |
| cenario2 | Oficina Mec�nica � clientes, ve�culos, ordens de servi�o |
| cenario3 | Escola de Cursos Livres � alunos, cursos, matr�culas |

## Requisitos

- Java 25
- Docker (PostgreSQL 16)

## Como executar

`ash
git checkout <branch>
docker compose up -d
# Executar evangelz.Main pela IDE
`

Cada branch cont�m README.md pr�prio com as tabelas e regras de neg�cio do respectivo cen�rio.

---

**Reposit�rio:** evangelz/mvc
**Tecnologias:** Java, Maven, JDBC, PostgreSQL

            CREATE TABLE IF NOT EXISTS veterinario (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL);
            CREATE TABLE IF NOT EXISTS tutor (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, telefone VARCHAR(20) NOT NULL);
            CREATE TABLE IF NOT EXISTS animal (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, especie VARCHAR(50) NOT NULL, raca VARCHAR(50), id_tutor INTEGER NOT NULL, FOREIGN KEY (id_tutor) REFERENCES tutor(id) ON DELETE CASCADE);
            CREATE TABLE IF NOT EXISTS consulta (id SERIAL PRIMARY KEY, id_animal INTEGER NOT NULL, id_veterinario INTEGER NOT NULL, data DATE NOT NULL, motivo VARCHAR(255) NOT NULL, valor DECIMAL(10,2) NOT NULL CHECK (valor >= 0), status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE' CHECK (status_pagamento IN ('PENDENTE', 'PAGO')), FOREIGN KEY (id_animal) REFERENCES animal(id) ON DELETE CASCADE, FOREIGN KEY (id_veterinario) REFERENCES veterinario(id));
        
