---
type: behaviors
feature: cursos
concerns: []
---
# Cursos — Behaviors

## Proactive Analysis

### Defaults
- `vagas_disponiveis` = `vagas_totais` ao criar turma

### Failure Modes
- Cadastrar turma com curso inexistente → erro
- Excluir curso com turmas → bloqueado
- Alterar vagas_totais após matrículas → bloqueado (não implementado na spec, verificar)

### Edge Cases
- Curso com carga_horária muito alta → permitido
- Turno como string livre (manhã, tarde, noite)
