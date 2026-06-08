# GLOBAL_BEHAVIORS — Escola de Cursos Livres

## Exclusão protegida
- Excluir curso → bloqueado se houver turmas vinculadas
- Excluir turma → bloqueado se houver matrículas vinculadas
- Excluir aluno → bloqueado se houver matrículas vinculadas

## Controle de vagas
- Ao realizar matrícula, `vagas_disponiveis` da turma é decrementada
- Ao cancelar matrícula, `vagas_disponiveis` é incrementada
- Matrícula só é permitida se `vagas_disponiveis > 0`

## Duplicidade de matrícula
- UNIQUE (id_aluno, id_turma) no banco
- Verificação explícita no service antes de inserir
