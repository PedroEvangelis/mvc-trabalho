---
type: behaviors
feature: pacientes
concerns: []
---
# Pacientes — Behaviors

## Proactive Analysis

### Defaults
- Telefone aceita qualquer formato (sem máscara)
- Raça do animal é opcional (pode ser nula)

### Failure Modes
- Excluir tutor com animais → executa cascata (animals + consultas deletados)
- Excluir animal com consultas → operação bloqueada com mensagem explicativa
- Cadastrar animal com tutor inexistente → erro "Tutor não encontrado"

### Edge Cases
- Tutor com mesmo nome de outro → permitido (identificado por ID)
- Animal com mesmo nome de outro do mesmo tutor → permitido
- Telefone duplicado entre tutores → permitido
