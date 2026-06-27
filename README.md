# Sistema da escola de cursos livres

## Regras de negócio
Permitir a inserção de novos alunos no sistema onde nome, e-mail e telefone são obrigatórios.
Permitir a inserção de novos cursos informando obrigatóriamente nome, descrição, carga horária e limite de vagas.
Permitir vincular os alunos a um curso existente, registrando a data e o valor pago.
Permitir que seja selecionado um curso e visualize a lista de todos os alunos matriculados nele.
Permitir que seja selecionado um aluno e visualize a lista de todos os cursos em que ele está matriculado.
O sistema não deve permitir que um aluno seja cadastrado duas vezes o mesmo curso.
O sistema não deve permitir cadastrar mais alunos do que o limite do curso.
O sistema deve exigir a seleção de um Aluno e de um Curso previamente cadastrados.
O valor pago não pode ser negativo.


## Entidades
| 			               |
|-------------------|
| Aluno           |
| Curso           |
| Turma           |
| Matrícula  |

## Tabelas necessarias
Para o banco de dados, foi utilizado o usuário "admin", com a senha "fofis", na porta padrão '5432' e na database "escola".<br>
Os seguntes comandos foram utilizados para criar as tabelas:

    CREATE TABLE IF NOT EXISTS aluno (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, telefone VARCHAR(20) NOT NULL);
    CREATE TABLE IF NOT EXISTS curso (id SERIAL PRIMARY KEY, nome VARCHAR(100) NOT NULL, descricao VARCHAR(255), carga_horaria INTEGER NOT NULL CHECK (carga_horaria > 0));
    CREATE TABLE IF NOT EXISTS turma (id SERIAL PRIMARY KEY, id_curso INTEGER NOT NULL, nome_turma VARCHAR(100) NOT NULL, sala VARCHAR(50), turno VARCHAR(20), vagas_totais INTEGER NOT NULL CHECK (vagas_totais > 0), vagas_disponiveis INTEGER NOT NULL CHECK (vagas_disponiveis >= 0), FOREIGN KEY (id_curso) REFERENCES curso(id));
    CREATE TABLE IF NOT EXISTS matricula (id SERIAL PRIMARY KEY, id_aluno INTEGER NOT NULL, id_turma INTEGER NOT NULL, data_matricula DATE NOT NULL, valor DECIMAL(10,2) NOT NULL CHECK (valor >= 0), status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE' CHECK (status_pagamento IN ('PENDENTE', 'PAGO')), FOREIGN KEY (id_aluno) REFERENCES aluno(id), FOREIGN KEY (id_turma) REFERENCES turma(id), UNIQUE (id_aluno, id_turma));
        