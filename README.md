# MVC — JDBC + PostgreSQL

Trabalho acadêmico com 3 sistemas independentes, cada um em sua branch.

## Branches

| Branch | Projeto |
|--------|---------|
| `cenario1` | Clínica Veterinária — tutores, animais, consultas |
| `cenario2` | Oficina Mecânica — clientes, veículos, ordens de serviço |
| `cenario3` | Escola de Cursos Livres — alunos, cursos, matrículas |

## Requisitos

- Java 25
- Docker (PostgreSQL 16)

## Como executar

```bash
git checkout <branch>
docker compose up -d
# Executar evangelz.Main pela IDE
```

Cada branch contém `README.md` próprio com as tabelas e regras de negócio do respectivo cenário.

---

**Tecnologias:** Java, Maven, JDBC, PostgreSQL
