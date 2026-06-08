---
type: behaviors
feature: servicos
concerns: []
---
# Serviços — Behaviors

## Proactive Analysis

### Defaults
- `valor_base` define o preço sugerido, mas pode ser alterado no item da OS

### Failure Modes
- Excluir serviço com vínculo em OS → bloqueado com erro

### Edge Cases
- Serviço com valor_base = 0 → permitido (cortesia)
- Descrição opcional
