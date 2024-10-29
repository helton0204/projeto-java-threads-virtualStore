CREATE TABLE produtos(
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(100) NOT NULL UNIQUE,
                         descricao VARCHAR(255) NOT NULL,
                         categoria VARCHAR(100) NOT NULL,
                         preco DECIMAL(10,2) NOT NULL,
                         ativo BOOLEAN NOT NULL DEFAULT 1,

                         PRIMARY KEY(id)
);