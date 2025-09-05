CREATE TABLE CURSO (
                       id INT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL,
                       area VARCHAR(255)
);

CREATE TABLE ALUNO (
                       id INT PRIMARY KEY,
                       nome VARCHAR(255) NOT NULL,
                       matricula VARCHAR(50),
                       curso_id INT,
                       idade INT,
                       email VARCHAR(255),
                       telefone VARCHAR(50),
                       FOREIGN KEY (curso_id) REFERENCES CURSO(id)
);