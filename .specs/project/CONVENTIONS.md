# CONVENTIONS — Clínica Veterinária

## Código
- Nomes de classes em PascalCase
- Nomes de métodos em camelCase
- Nomes de constantes em UPPER_SNAKE_CASE
- Setters retornam `this` para encadeamento
- Toda entity tem `@Override toString()`

## Banco
- Nomes de tabelas em minúsculo e snake_case
- Chaves estrangeiras nomeadas como `id_<entidade>`
- `status_pagamento` como VARCHAR com CHECK ('PENDENTE', 'PAGO')
- `status` como VARCHAR com CHECK ('ABERTA', 'CONCLUIDA') quando aplicável
- `valor` como DECIMAL(10,2)

## Repositórios
- Um arquivo por entidade
- Métodos: `cadastrar`, `buscarPorId`, `listar`, `atualizar`, `deletar`
- SQL puro via `PreparedStatement`

## Service
- Validações de negócio antes de chamar o repository
- Métodos que envolvem transações usam `setAutoCommit(false)` + `commit()`/`rollback()`
