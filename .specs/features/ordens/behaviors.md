---
type: behaviors
feature: ordens
concerns: [financeiro]
---
# Ordens — Behaviors

## Proactive Analysis

### Defaults
- `status` = 'ABERTA' ao criar
- `status_pagamento` = 'PENDENTE' ao criar
- `data_abertura` = data atual do sistema

### Failure Modes
- Abrir OS com veículo inexistente → erro
- Adicionar item com serviço inexistente → erro
- Fechar OS já CONCLUIDA → erro
- Baixar pagamento de OS ABERTA → erro "Finalize a OS primeiro"
- Baixar pagamento de OS já PAGA → erro

### Side Effects
- Fechar OS é irreversível (não reabrir)
- Baixar pagamento é irreversível (não estornar)

### Edge Cases
- OS sem itens → não permitido (deve ter ao menos 1 item)
- Quantidade fracionada → usar inteiros (não existe 0.5 serviço)
