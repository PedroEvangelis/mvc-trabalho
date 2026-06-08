---
type: spec
feature: historico
concerns: []
---
# Histórico

Consultas ao histórico de atendimentos. Permite visualizar a linha do tempo de um animal e os animais de um tutor.

## Requisitos Funcionais

### RF1 — Histórico de consultas por animal
**Input:** id_animal
**Output:** lista de consultas ordenadas por data (decrescente) com dados do animal e veterinário
**Exibe:** data, motivo, valor, status_pagamento, nome do veterinário

### RF2 — Animais por tutor
**Input:** id_tutor
**Output:** lista de animais do tutor com nome, espécie e raça

### RF3 — Consulta detalhada
**Input:** id_consulta
**Output:** todos os dados da consulta + nome do animal + nome do tutor + nome do veterinário

## Regras de Negócio

| RN | Descrição |
|----|-----------|
| RN1 | Histórico exibe apenas consultas do animal selecionado |
| RN2 | Listagem de animais por tutor permite navegar para o histórico de cada animal |
