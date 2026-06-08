# ARCHITECTURE — Escola de Cursos Livres

## Padrão
MVC (Model-View-Controller) com JDBC puro e PostgreSQL.

## Stack
- Java 25
- Maven
- PostgreSQL 16
- JDBC

## Estrutura de pacotes
```
src/main/java/evangelz/
├── Main.java
├── util/
│   └── Conexao.java
├── model/
│   ├── Aluno.java
│   ├── Curso.java
│   ├── Turma.java
│   └── Matricula.java
├── repository/
│   ├── AlunoRepository.java
│   ├── CursoRepository.java
│   ├── TurmaRepository.java
│   └── MatriculaRepository.java
├── service/
│   ├── AlunoService.java
│   ├── CursoService.java
│   ├── TurmaService.java
│   └── MatriculaService.java
└── controller/
    ├── AlunoController.java
    ├── CursoController.java
    ├── TurmaController.java
    └── MatriculaController.java
```
