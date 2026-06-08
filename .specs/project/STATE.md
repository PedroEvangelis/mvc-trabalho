# STATE — Escola de Cursos Livres

## Status atual
DISCOVER concluído. Aguardando implementação (CP5).

## Bloqueios
_Nenhum._

## Decisões recentes
- Turma como entidade separada com vagas próprias
- Matrícula com UNIQUE (aluno + turma)
- Matrícula com transação (várias operações atômicas)
- Pagamento como status na Matrícula

## Próximos passos
1. Implementar modelo, repositório, serviço, controlador de cada feature
2. Conectar ao PostgreSQL via Docker
3. Simular fluxo completo na Main com matrículas válidas e inválidas
