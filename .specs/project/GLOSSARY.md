# GLOSSARY — Oficina Mecânica

| Termo | Definição |
|-------|-----------|
| Cliente | Dono do veículo. Pessoa física ou jurídica. |
| Veículo | Carro registrado na oficina. Pertence a um cliente. |
| Serviço | Tipo de serviço oferecido (ex: troca de óleo, alinhamento). Possui valor base. |
| Ordem de Serviço (OS) | Registro de um atendimento. Contém itens de serviço, status e pagamento. |
| Item de OS | Serviço específico realizado dentro de uma OS, com quantidade e valor. |

## Relacionamentos
- **Cliente** (1) → (N) **Veículo**: um cliente pode ter vários veículos
- **Veículo** (1) → (N) **OrdemServico**: um veículo pode ter várias OS
- **OrdemServico** (1) → (N) **ItemOrdemServico**: uma OS contém vários serviços
- **Serviço** (1) → (N) **ItemOrdemServico**: um serviço pode estar em várias OS
