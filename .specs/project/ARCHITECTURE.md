# ARCHITECTURE — Oficina Mecânica

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
│   ├── Cliente.java
│   ├── Veiculo.java
│   ├── Servico.java
│   ├── OrdemServico.java
│   └── ItemOrdemServico.java
├── repository/
│   ├── ClienteRepository.java
│   ├── VeiculoRepository.java
│   ├── ServicoRepository.java
│   ├── OrdemServicoRepository.java
│   └── ItemOrdemServicoRepository.java
├── service/
│   ├── ClienteService.java
│   ├── VeiculoService.java
│   ├── ServicoService.java
│   └── OrdemServicoService.java
└── controller/
│   ├── ClienteController.java
│   ├── VeiculoController.java
│   ├── ServicoController.java
│   └── OrdemServicoController.java
```
