---
type: spec
feature: pacientes
concerns: []
---
# Pacientes

Cadastro e consulta de tutores e seus animais. Esta feature une dois fluxos complementares: o tutor (dono) e seus pets.

## Requisitos Funcionais

### RF1 — Cadastrar tutor
**Input:** nome, telefone
**Output:** tutor cadastrado com ID
**Validações:** nome e telefone obrigatórios

### RF2 — Listar tutores
**Input:** (opcional) filtro por nome
**Output:** lista de tutores

### RF3 — Buscar tutor por ID
**Input:** id
**Output:** dados do tutor
**Erro:** "Tutor não encontrado"

### RF4 — Atualizar tutor
**Input:** id, nome, telefone
**Output:** tutor atualizado

### RF5 — Excluir tutor
**Input:** id
**Output:** tutor + seus animais + consultas removidos (cascata)

### RF6 — Cadastrar animal
**Input:** nome, especie, raca (opcional), id_tutor
**Output:** animal cadastrado com ID
**Validações:** nome e espécie obrigatórios, tutor deve existir

### RF7 — Listar animais por tutor
**Input:** id_tutor
**Output:** lista de animais do tutor

### RF8 — Atualizar animal
**Input:** id, nome, especie, raca
**Output:** animal atualizado

### RF9 — Excluir animal
**Input:** id
**Output:** erro se animal tiver consultas; exclui caso contrário

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Tutor deve ter nome e telefone obrigatórios |
| RN2 | Animal deve ter nome e espécie obrigatórios |
| RN3 | Animal não pode existir sem tutor cadastrado |
| RN4 | Exclusão de tutor remove em cascata seus animais e consultas |
| RN5 | Exclusão de animal é bloqueada se houver consultas registradas |
