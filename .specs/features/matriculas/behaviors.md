---
type: behaviors
feature: matriculas
concerns: [financeiro]
---
# Matrículas — Behaviors

## Proactive Analysis

### Defaults
- `status_pagamento` = 'PENDENTE'
- `data_matricula` = data atual do sistema

### Failure Modes
- Matricular aluno inexistente → erro
- Matricular em turma inexistente → erro
- Matricular aluno já matriculado na turma → erro "Aluno já matriculado nesta turma"
- Matricular em turma sem vagas → erro "Turma sem vagas disponíveis"
- Baixar pagamento de matrícula já PAGA → erro

### Side Effects
- Matrícula bem-sucedida → vagas_disponiveis decrementada
- Matrícula cancelada → vagas_disponiveis incrementada

### Transações
- O processo de matrícula é transacional:
  1. Verificar vagas (SELECT)
  2. Verificar duplicidade (SELECT)
  3. Inserir matrícula (INSERT)
  4. Decrementar vagas (UPDATE)
- Se qualquer passo falhar, rollback total

### Edge Cases
- Valor = 0 → permitido (matrícula gratuita/cortesia)
- Tentar matricular após última vaga → erro
