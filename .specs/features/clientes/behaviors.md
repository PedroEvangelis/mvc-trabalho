---
type: behaviors
feature: clientes
concerns: []
---
# Clientes — Behaviors

## Proactive Analysis

### Defaults
- Telefone aceita qualquer formato

### Failure Modes
- Cadastrar veículo com cliente inexistente → erro
- Excluir cliente → cascata exclui veículos e OS

### Edge Cases
- Cliente com mesmo nome → permitido (identificado por ID)
- Placa duplicada → permitido entre clientes diferentes (?) — verificar regra. Sem restrição de unicidade definida.
