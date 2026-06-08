---
type: behaviors
feature: atendimento
concerns: [financeiro]
---
# Atendimento — Behaviors

## Proactive Analysis

### Defaults
- `status_pagamento` inicia como 'PENDENTE'
- `data` usa a data do sistema se não fornecida

### Failure Modes
- Registrar consulta com animal inexistente → erro
- Registrar consulta com valor negativo → erro
- Baixar pagamento de consulta já PAGO → erro "Consulta já está paga"
- Baixar pagamento de consulta inexistente → erro

### Side Effects
- Ao registrar consulta, o histórico fica disponível imediatamente em [features/historico](../historico/spec.md)
- Baixar pagamento é uma operação irreversível (não tem "estornar")

### Edge Cases
- Valor = 0 → permitido (consulta de cortesia)
- Motivo muito longo → truncar em 255 caracteres
- Data futura → permitido (consulta agendada)
