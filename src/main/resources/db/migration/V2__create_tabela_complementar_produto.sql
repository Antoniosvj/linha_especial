CREATE TABLE IF NOT EXISTS estoque (
    id BIGSERIAL PRIMARY KEY,
    nome_cor VARCHAR(20) NOT NULL,
    exadecimal_cor VARCHAR(7) NOT NULL,
    tamanho VARCHAR(6) NOT NULL,
    quantidade INT,
    id_produto BIGINT,
    FOREIGN KEY (id_produto) REFERENCES produtos(id)
);