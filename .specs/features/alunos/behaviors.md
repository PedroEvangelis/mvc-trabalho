---
type: behaviors
feature: alunos
concerns: []
---
# Alunos — Behaviors

## Proactive Analysis

### Defaults
- Telefone é opcional

### Failure Modes
- Cadastrar com email já existente → erro "Email já cadastrado"
- Excluir aluno com matrículas → bloqueado

### Edge Cases
- Alunos com mesmo nome → permitido (email é único)
