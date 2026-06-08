---
type: behaviors
feature: historico
concerns: []
---
# Histórico — Behaviors

## Proactive Analysis

### Defaults
- Listagem de consultas ordenada da mais recente para a mais antiga

### Failure Modes
- Animal inexistente → erro "Animal não encontrado"
- Tutor inexistente → erro "Tutor não encontrado"
- Animal sem consultas → lista vazia (não é erro)

### Edge Cases
- Animal com dezenas de consultas → listar todas sem paginação (sistema pequeno)
- Mesmo veterinário em todas as consultas → exibir normalmente
