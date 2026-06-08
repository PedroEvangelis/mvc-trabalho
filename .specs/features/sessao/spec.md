---
type: spec
feature: sessao
concerns: []
---
# Sessão

Identificação do veterinário no início da aplicação. Define o contexto ativo para todas as operações seguintes.

## Requisitos Funcionais

### RF1 — Selecionar veterinário
**Input:** lista de veterinários cadastrados
**Output:** veterinário selecionado vira contexto da sessão
**Fluxo:** sistema exibe lista → usuário escolhe pelo ID

### RF2 — Cadastrar novo veterinário
**Input:** nome do veterinário
**Output:** veterinário cadastrado e definido como contexto da sessão
**Validações:** nome obrigatório, não pode ser vazio

### RF3 — Exibir sessão atual
**Input:** —
**Output:** nome do veterinário logado

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Ao iniciar o sistema, o veterinário deve se identificar antes de qualquer operação |
| RN2 | Não é necessário senha — apenas seleção ou cadastro por nome |
