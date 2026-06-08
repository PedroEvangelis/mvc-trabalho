# STATE — Clínica Veterinária

## Status atual
DISCOVER concluído. Aguardando implementação (CP5).

## Bloqueios
_Nenhum._

## Decisões recentes
- Pagamento como campo na Consulta (status_pendente)
- Veterinário como identidade de sessão (sem senha)
- Exclusão de tutor em cascata
- Exclusão de animal bloqueada se tiver consultas

## Próximos passos
1. Implementar modelo, repositório, serviço, controlador de cada feature
2. Conectar ao PostgreSQL via Docker
3. Simular fluxo completo na Main
