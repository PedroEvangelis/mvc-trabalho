# BIG_PICTURE — Escola de Cursos Livres

## Entities

| Entidade | Definida em |
|----------|-------------|
| Aluno | [features/alunos](../features/alunos/spec.md) |
| Curso | [features/cursos](../features/cursos/spec.md) |
| Turma | [features/cursos](../features/cursos/spec.md) |
| Matricula | [features/matriculas](../features/matriculas/spec.md) |

## Feature Map

| Feature | Status | Artefatos |
|---------|--------|-----------|
| [alunos](../features/alunos/spec.md) | Pendente | spec.md, behaviors.md |
| [cursos](../features/cursos/spec.md) | Pendente | spec.md, behaviors.md |
| [matriculas](../features/matriculas/spec.md) | Pendente | spec.md, behaviors.md |
| [consultas](../features/consultas/spec.md) | Pendente | spec.md, behaviors.md |

## Decision Log

| Data | Decisão | Alternativas |
|------|---------|-------------|
| 2026-06-08 | Turma como entidade separada (curso + oferta) | Turma como campo no Curso (rejeitado) |
| 2026-06-08 | Vagas controladas na Turma, não no Curso | Vagas no Curso (rejeitado) |
| 2026-06-08 | Matrícula com UNIQUE (aluno + turma) + verificação service | Só UNIQUE (rejeitado: mensagem de erro genérica) |
| 2026-06-08 | Pagamento como status na Matrícula | Tabela separada (rejeitado por simplicidade) |

## Open Questions

_Nenhuma no momento._
