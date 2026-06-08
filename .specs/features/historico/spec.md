---
type: spec
feature: historico
concerns: []
---
# Histórico

Consultas ao histórico de manutenções de um veículo.

## Requisitos Funcionais

### RF1 — Histórico por veículo
**Input:** id_veiculo
**Output:** lista de OS ordenadas por data (decrescente) com status, valor total e pagamento

### RF2 — Detalhes da OS
**Input:** id_os
**Output:** dados completos da OS + itens (serviço, quantidade, valor) + dados do veículo + dados do cliente

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Lista exibe apenas OS do veículo consultado |
| RN2 | Ordenação da mais recente para a mais antiga |
