---
type: spec
feature: clientes
concerns: []
---
# Clientes

Cadastro e consulta de clientes e seus veículos. Um cliente pode ter múltiplos veículos registrados.

## Requisitos Funcionais

### RF1 — Cadastrar cliente
**Input:** nome, telefone
**Output:** cliente cadastrado com ID
**Validações:** nome e telefone obrigatórios

### RF2 — Listar clientes
**Input:** (opcional) filtro por nome
**Output:** lista de clientes

### RF3 — Buscar cliente por ID
**Input:** id
**Output:** dados do cliente

### RF4 — Atualizar cliente
**Input:** id, nome, telefone
**Output:** cliente atualizado

### RF5 — Excluir cliente
**Input:** id
**Output:** cliente + veículos + OS removidos (cascata)

### RF6 — Cadastrar veículo
**Input:** placa, modelo, ano, id_cliente
**Output:** veículo cadastrado com ID
**Validações:** placa, modelo e ano obrigatórios; cliente deve existir

### RF7 — Listar veículos por cliente
**Input:** id_cliente
**Output:** lista de veículos do cliente

### RF8 — Atualizar veículo
**Input:** id, placa, modelo, ano
**Output:** veículo atualizado

### RF9 — Excluir veículo
**Input:** id
**Output:** veículo + OS removidas (cascata)

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Cliente deve ter nome e telefone obrigatórios |
| RN2 | Veículo deve ter placa, modelo e ano obrigatórios |
| RN3 | Veículo não pode existir sem cliente cadastrado |
| RN4 | Exclusão de cliente remove cascata (veículos + OS) |
