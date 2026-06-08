# CONVENTIONS — Escola de Cursos Livres

## Código
- Nomes de classes em PascalCase
- Nomes de métodos em camelCase
- Toda entity tem `@Override toString()`

## Banco
- Nomes de tabelas em minúsculo e snake_case
- Chaves estrangeiras como `id_<entidade>`
- `vagas_totais` e `vagas_disponiveis` como INTEGER
- `status_pagamento` como VARCHAR com CHECK ('PENDENTE', 'PAGO')
- `valor` como DECIMAL(10,2)

## Transações
- Matrícula usa transação: verificar vaga → verificar duplicidade → inserir matrícula → decrementar vagas
- Em caso de erro, rollback de todas as operações
