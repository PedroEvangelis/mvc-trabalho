---
type: spec
feature: alunos
concerns: []
---
# Alunos

Cadastro e consulta de alunos.

## Requisitos Funcionais

### RF1 — Cadastrar aluno
**Input:** nome, email, telefone (opcional)
**Output:** aluno cadastrado com ID
**Validações:** nome e email obrigatórios; email único

### RF2 — Listar alunos
**Input:** (opcional) filtro por nome
**Output:** lista de alunos

### RF3 — Buscar aluno por ID
**Input:** id
**Output:** dados do aluno

### RF4 — Atualizar aluno
**Input:** id, nome, email, telefone
**Output:** aluno atualizado

### RF5 — Excluir aluno
**Input:** id
**Output:** bloqueado se aluno tiver matrículas; exclui caso contrário

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Nome e email são obrigatórios |
| RN2 | Email deve ser único no sistema |
| RN3 | Exclusão bloqueada se aluno possuir matrículas |
