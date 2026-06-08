---
type: spec
feature: cursos
concerns: []
---
# Cursos

Catálogo de cursos e suas turmas (ofertas).

## Requisitos Funcionais — Curso

### RF1 — Cadastrar curso
**Input:** nome, descricao, carga_horaria
**Output:** curso cadastrado com ID
**Validações:** nome e carga_horaria obrigatórios; carga_horaria > 0

### RF2 — Listar cursos
**Input:** (opcional) filtro por nome
**Output:** lista de cursos

### RF3 — Buscar curso por ID
**Input:** id
**Output:** dados do curso

### RF4 — Atualizar curso
**Input:** id, nome, descricao, carga_horaria
**Output:** curso atualizado

### RF5 — Excluir curso
**Input:** id
**Output:** bloqueado se houver turmas vinculadas

## Requisitos Funcionais — Turma

### RF6 — Cadastrar turma
**Input:** id_curso, nome_turma, sala, turno, vagas_totais
**Output:** turma cadastrada com vagas_disponiveis = vagas_totais
**Validações:** curso deve existir; vagas_totais > 0

### RF7 — Listar turmas por curso
**Input:** id_curso
**Output:** lista de turmas com vagas disponíveis

### RF8 — Atualizar turma
**Input:** id, nome_turma, sala, turno
**Output:** turma atualizada
**Nota:** vagas_totais não pode ser alterada se houver matrículas

### RF9 — Excluir turma
**Input:** id
**Output:** bloqueado se houver matrículas

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Curso deve ter nome e carga_horária obrigatórios |
| RN2 | Turma não existe sem curso |
| RN3 | Ao criar turma, vagas_disponiveis = vagas_totais |
| RN4 | Exclusão de curso bloqueada se houver turmas |
| RN5 | Exclusão de turma bloqueada se houver matrículas |
