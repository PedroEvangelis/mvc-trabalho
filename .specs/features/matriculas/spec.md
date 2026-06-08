---
type: spec
feature: matriculas
concerns: [financeiro]
---
# Matrículas

Realização de matrículas com controle de vagas, duplicidade e pagamento.

## Requisitos Funcionais

### RF1 — Realizar matrícula
**Input:** id_aluno, id_turma, data_matricula, valor
**Output:** matrícula registrada com status PENDENTE
**Validações:**
- Aluno deve existir
- Turma deve existir
- Aluno não pode estar matriculado nesta turma
- Turma deve ter vagas disponíveis (vagas_disponiveis > 0)
- Valor deve ser >= 0
**Efeitos:** decrementa vagas_disponiveis da turma

### RF2 — Listar matrículas
**Input:** (opcional) filtro por id_turma ou id_aluno
**Output:** lista de matrículas

### RF3 — Baixar pagamento
**Input:** id_matricula
**Output:** status_pagamento alterado para PAGO
**Validações:** matrícula deve existir e estar PENDENTE

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Matrícula não existe sem aluno e turma cadastrados |
| RN2 | Aluno não pode matricular duas vezes na mesma turma |
| RN3 | Matrícula só permitida se turma tiver vagas disponíveis |
| RN4 | Ao matricular, decrementar vagas_disponiveis |
| RN5 | Ao cancelar matrícula (excluir), incrementar vagas_disponiveis |
| RN6 | Valor deve ser >= 0 |
| RN7 | Status padrão é PENDENTE |
