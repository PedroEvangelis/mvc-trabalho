# GLOSSARY — Clínica Veterinária

| Termo | Definição |
|-------|-----------|
| Veterinário | Profissional que usa o sistema. Identidade da sessão atual. |
| Tutor | Pessoa responsável pelo animal. Dono do pet. |
| Paciente | Animal atendido na clínica. Pertence a um tutor. |
| Consulta | Atendimento veterinário. Possui data, motivo, valor e status de pagamento. |
| Sessão | Contexto ativo do veterinário que está operando o sistema. |

## Relacionamentos
- **Tutor** (1) → (N) **Animal**: um tutor pode ter vários animais
- **Animal** (1) → (N) **Consulta**: um animal pode ter várias consultas
- **Veterinário** (1) → (N) **Consulta**: um veterinário realiza várias consultas
