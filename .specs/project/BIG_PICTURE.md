# BIG_PICTURE — Oficina Mecânica

## Entities

| Entidade | Definida em |
|----------|-------------|
| Cliente | [features/clientes](../features/clientes/spec.md) |
| Veiculo | [features/clientes](../features/clientes/spec.md) |
| Servico | [features/servicos](../features/servicos/spec.md) |
| OrdemServico | [features/ordens](../features/ordens/spec.md) |
| ItemOrdemServico | [features/ordens](../features/ordens/spec.md) |

## Feature Map

| Feature | Status | Artefatos |
|---------|--------|-----------|
| [clientes](../features/clientes/spec.md) | Pendente | spec.md, behaviors.md |
| [servicos](../features/servicos/spec.md) | Pendente | spec.md, behaviors.md |
| [ordens](../features/ordens/spec.md) | Pendente | spec.md, behaviors.md |
| [historico](../features/historico/spec.md) | Pendente | spec.md, behaviors.md |

## Decision Log

| Data | Decisão | Alternativas |
|------|---------|-------------|
| 2026-06-08 | Serviço como entidade separada (catálogo) | Serviço como texto na OS (rejeitado pelo usuário) |
| 2026-06-08 | OS com múltiplos itens via ItemOrdemServico | OS com um único serviço (rejeitado) |
| 2026-06-08 | Pagamento como status na OS | Tabela separada (rejeitado por simplicidade) |
| 2026-06-08 | Valor da OS calculado pela soma dos itens | Campo valor direto na OS (rejeitado) |

## Open Questions

_Nenhuma no momento._
