---
type: spec
feature: ordens
concerns: [financeiro]
---
# Ordens

Abertura, fechamento e pagamento de ordens de serviço. Uma OS contém um ou mais itens de serviço.

## Requisitos Funcionais

### RF1 — Abrir ordem de serviço
**Input:** id_veiculo, descricao_problema, lista de itens (id_servico, quantidade, valor_unitario)
**Output:** OS criada com status ABERTA
**Validações:** veículo deve existir; cada serviço deve existir; quantidade > 0; valor_unitario >= 0

### RF2 — Listar ordens de serviço
**Input:** (opcional) filtro por id_veiculo
**Output:** lista de OS com ID, veículo, data, status, status_pagamento

### RF3 — Buscar OS por ID
**Input:** id
**Output:** OS com todos os itens e valor total calculado

### RF4 — Fechar OS (concluir)
**Input:** id_os
**Output:** status alterado para CONCLUIDA
**Validações:** OS deve estar ABERTA

### RF5 — Baixar pagamento
**Input:** id_os
**Output:** status_pagamento alterado para PAGO
**Validações:** OS deve estar CONCLUIDA e com status_pagamento PENDENTE

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | OS não pode existir sem veículo cadastrado |
| RN2 | Valor total da OS = soma (quantidade * valor_unitario) dos itens |
| RN3 | Quantidade dos itens deve ser > 0 |
| RN4 | valor_unitario deve ser >= 0 |
| RN5 | Só é possível fechar OS com status ABERTA |
| RN6 | Só é possível baixar pagamento com OS CONCLUIDA e status PENDENTE |
| RN7 | Pagamento é completo (sem parcial) |
