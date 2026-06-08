# ARCHITECTURE — Clínica Veterinária

## Padrão
MVC (Model-View-Controller) com JDBC puro e PostgreSQL.

## Stack
- Java 25
- Maven
- PostgreSQL 16
- JDBC (sem frameworks ou ORMs)

## Estrutura de pacotes
```
src/main/java/evangelz/
├── Main.java
├── util/
│   └── Conexao.java
├── model/
│   ├── Veterinario.java
│   ├── Tutor.java
│   ├── Animal.java
│   └── Consulta.java
├── repository/
│   ├── VeterinarioRepository.java
│   ├── TutorRepository.java
│   ├── AnimalRepository.java
│   └── ConsultaRepository.java
├── service/
│   ├── SessaoService.java
│   ├── TutorService.java
│   ├── AnimalService.java
│   └── ConsultaService.java
└── controller/
    ├── SessaoController.java
    ├── TutorController.java
    ├── AnimalController.java
    └── ConsultaController.java
```
