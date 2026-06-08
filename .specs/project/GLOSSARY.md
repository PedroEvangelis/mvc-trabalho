# GLOSSARY — Escola de Cursos Livres

| Termo | Definição |
|-------|-----------|
| Aluno | Pessoa matriculada em um ou mais cursos. |
| Curso | Programa de ensino com nome, descrição e carga horária. |
| Turma | Oferta específica de um curso em uma sala e turno. Possui limite de vagas. |
| Matrícula | Vínculo de um aluno a uma turma. Possui data, valor e status de pagamento. |

## Relacionamentos
- **Curso** (1) → (N) **Turma**: um curso pode ter várias turmas
- **Turma** (1) → (N) **Matricula**: uma turma pode ter vários alunos
- **Aluno** (1) → (N) **Matricula**: um aluno pode estar em várias turmas
