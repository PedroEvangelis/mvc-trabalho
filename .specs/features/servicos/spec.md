---
type: spec
feature: servicos
concerns: []
---
# Serviços

Catálogo de serviços oferecidos pela oficina. Cada serviço possui um valor base que pode ser ajustado na OS.

## Requisitos Funcionais

### RF1 — Cadastrar serviço
**Input:** nome, descricao, valor_base
**Output:** serviço cadastrado com ID
**Validações:** nome e valor_base obrigatórios; valor_base >= 0

### RF2 — Listar serviços
**Input:** (opcional) filtro por nome
**Output:** lista de serviços

### RF3 — Buscar serviço por ID
**Input:** id
**Output:** dados do serviço

### RF4 — Atualizar serviço
**Input:** id, nome, descricao, valor_base
**Output:** serviço atualizado

### RF5 — Excluir serviço
**Input:** id
**Output:** erro se serviço estiver vinculado a alguma OS; exclui caso contrário

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Nome e valor_base são obrigatórios |
| RN2 | valor_base deve ser >= 0 |
| RN3 | Serviço vinculado a OS não pode ser excluído |
