# Cenário 3 — Escola de Cursos Livres

Sistema de gerenciamento para escola de cursos livres com cadastro de alunos, cursos, turmas e matrículas com controle de vagas.

## Tecnologias

- Java 25
- Maven
- PostgreSQL 16
- JDBC

## Como executar

1. Suba o banco: `docker compose up -d`
2. Execute `evangelz.Main`

## Tabelas

### aluno
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| nome | VARCHAR(100) | NOT NULL |
| email | VARCHAR(100) | NOT NULL, UNIQUE |
| telefone | VARCHAR(20) | |

### curso
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| nome | VARCHAR(100) | NOT NULL |
| descricao | VARCHAR(255) | |
| carga_horaria | INTEGER | NOT NULL, CHECK (> 0) |

### turma
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| id_curso | INTEGER | NOT NULL, FK → curso |
| nome_turma | VARCHAR(100) | NOT NULL |
| sala | VARCHAR(50) | |
| turno | VARCHAR(20) | |
| vagas_totais | INTEGER | NOT NULL, CHECK (> 0) |
| vagas_disponiveis | INTEGER | NOT NULL, CHECK (>= 0) |

### matricula
| Campo | Tipo | Restrições |
|-------|------|------------|
| id | SERIAL | PK |
| id_aluno | INTEGER | NOT NULL, FK → aluno |
| id_turma | INTEGER | NOT NULL, FK → turma |
| data_matricula | DATE | NOT NULL |
| valor | DECIMAL(10,2) | NOT NULL, CHECK (>= 0) |
| status_pagamento | VARCHAR(20) | NOT NULL, DEFAULT 'PENDENTE', CHECK ('PENDENTE','PAGO') |
| | | UNIQUE (id_aluno, id_turma) |

## Regras de Negócio

| # | Regra | Local |
|---|-------|-------|
| RN1 | Nome e email do aluno obrigatórios; email único | AlunoService |
| RN2 | Nome e carga horária do curso obrigatórios | CursoService |
| RN3 | Turma não existe sem curso | TurmaService |
| RN4 | Ao criar turma, vagas_disponiveis = vagas_totais | Turma (construtor) |
| RN5 | Matrícula exige aluno e turma cadastrados | MatriculaService |
| RN6 | Aluno não pode matricular duas vezes na mesma turma | MatriculaService (UNIQUE + verificação) |
| RN7 | Matrícula só permitida se turma tiver vagas | MatriculaService |
| RN8 | Ao matricular, decrementar vagas_disponiveis | MatriculaService (transação) |
| RN9 | Valor da matrícula >= 0 | MatriculaService |
| RN10 | Exclusão de curso bloqueada se houver turmas | CursoService |
| RN11 | Exclusão de turma bloqueada se houver matrículas | TurmaService |
| RN12 | Exclusão de aluno bloqueada se houver matrículas | AlunoService |
