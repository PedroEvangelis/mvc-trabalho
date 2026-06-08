# BIG_PICTURE — Clínica Veterinária

## Entities

| Entidade | Definida em |
|----------|-------------|
| Veterinario | [features/sessao](../features/sessao/spec.md) |
| Tutor | [features/pacientes](../features/pacientes/spec.md) |
| Animal | [features/pacientes](../features/pacientes/spec.md) |
| Consulta | [features/atendimento](../features/atendimento/spec.md) |

## Feature Map

| Feature | Status | Artefatos |
|---------|--------|-----------|
| [sessao](../features/sessao/spec.md) | Pendente | spec.md, behaviors.md |
| [pacientes](../features/pacientes/spec.md) | Pendente | spec.md, behaviors.md |
| [atendimento](../features/atendimento/spec.md) | Pendente | spec.md, behaviors.md |
| [historico](../features/historico/spec.md) | Pendente | spec.md, behaviors.md |

## Decision Log

| Data | Decisão | Alternativas |
|------|---------|-------------|
| 2026-06-08 | Pagamento como campo `status_pagamento` na própria Consulta | Tabela separada de pagamentos (rejeitada por simplicidade) |
| 2026-06-08 | Exclusão de tutor em cascata (animais + consultas) | Bloqueio por FK (rejeitada pelo usuário) |
| 2026-06-08 | Exclusão de animal bloqueada se houver consultas | Cascata (rejeitada pelo usuário) |

## Open Questions

_Nenhuma no momento._
