---
type: spec
feature: atendimento
concerns: [financeiro]
---
# Atendimento

Registro de consultas veterinárias e controle de pagamento. Esta feature cobre o lançamento do atendimento e a baixa do pagamento.

## Requisitos Funcionais

### RF1 — Registrar consulta
**Input:** id_animal, id_veterinario (sessão), data, motivo, valor, status_pagamento (default PENDENTE)
**Output:** consulta registrada com ID
**Validações:** animal e veterinário devem existir, valor >= 0

### RF2 — Listar consultas
**Input:** (opcional) filtro por id_animal
**Output:** lista de consultas

### RF3 — Buscar consulta por ID
**Input:** id
**Output:** dados da consulta

### RF4 — Baixar pagamento
**Input:** id_consulta
**Output:** status_pagamento alterado para PAGO
**Validações:** consulta deve existir e estar com status PENDENTE

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Valor da consulta deve ser >= 0 (permite cortesia) |
| RN2 | Status de pagamento deve ser PENDENTE ou PAGO |
| RN3 | Ao criar, status padrão é PENDENTE |
| RN4 | Só é permitido baixar pagamento se status atual for PENDENTE |
| RN5 | Consulta não pode existir sem animal e veterinário cadastrados |
