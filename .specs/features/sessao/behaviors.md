---
type: behaviors
feature: sessao
concerns: []
---
# Sessão — Behaviors

## Proactive Analysis

### Defaults
- Ao iniciar o sistema, nenhum veterinário está selecionado
- A sessão só permite acesso às demais features após selecionar/cadastrar veterinário

### Failure Modes
- Se não houver veterinários cadastrados, exibe apenas a opção de cadastrar novo
- Se o usuário digitar um ID inválido na seleção, exibe erro e repete a pergunta

### Edge Cases
- Nome do veterinário com caracteres especiais ou muito longo (aceitar até 100 caracteres)
- Lista vazia de veterinários → ir direto para cadastro

## Discovered During Implementation

_Esta seção será preenchida durante a implementação._
