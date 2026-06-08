---
type: spec
feature: consultas
concerns: []
---
# Consultas

Consultas transversais para visualizar alunos de uma turma e cursos de um aluno.

## Requisitos Funcionais

### RF1 — Alunos por turma
**Input:** id_turma
**Output:** lista de alunos matriculados na turma com dados da matrícula (data, valor, status_pagamento)

### RF2 — Cursos por aluno
**Input:** id_aluno
**Output:** lista de turmas/cursos em que o aluno está matriculado com dados da matrícula

### RF3 — Detalhes da matrícula
**Input:** id_matricula
**Output:** dados completos: aluno, turma, curso, data, valor, status_pagamento

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Listas são somente leitura (não alteram dados) |
| RN2 | Turma ou aluno inexistente → erro |
