# CONVENTIONS — Oficina Mecânica

## Código
- Nomes de classes em PascalCase
- Nomes de métodos em camelCase
- Toda entity tem `@Override toString()`
- Service valida regras de negócio antes de chamar repository

## Banco
- Nomes de tabelas em minúsculo e snake_case
- Chaves estrangeiras nomeadas como `id_<entidade>`
- `status` como VARCHAR com CHECK ('ABERTA', 'CONCLUIDA')
- `status_pagamento` como VARCHAR com CHECK ('PENDENTE', 'PAGO')
- `valor_base` e `valor_unitario` como DECIMAL(10,2)

## Transações
- Abertura de OS (criar OS + inserir itens) usa `setAutoCommit(false)` + `commit()`/`rollback()`
