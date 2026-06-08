# GLOBAL_BEHAVIORS — Oficina Mecânica

## Exclusão em cascata (Cliente → Veículo → OS)
- Ao excluir um cliente, todos os seus veículos e respectivas OS são excluídos.
- Ao excluir um veículo, todas as suas OS são excluídas.

## Valor da OS
- O valor total da OS é calculado pela soma de `quantidade * valor_unitario` dos itens.
- Não há campo `valor` diretamente na OS.

## Status da OS
- `ABERTA` — OS criada, serviços em andamento
- `CONCLUIDA` — todos os serviços finalizados
- Só é possível baixar pagamento se a OS estiver CONCLUIDA
