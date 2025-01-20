CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    ativo TINYINT
);

CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL
);

CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(128) NOT NULL,
    mensagem VARCHAR(500),
    data_de_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(128),
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,


    CONSTRAINT fk_topicos_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);


CREATE TABLE respostas (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    mensagem VARCHAR(500) NOT NULL,
    data_de_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,


    CONSTRAINT fk_respostas_topico FOREIGN KEY (topico_id) REFERENCES topicos(id) ON DELETE CASCADE,
    CONSTRAINT fk_respostas_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);
