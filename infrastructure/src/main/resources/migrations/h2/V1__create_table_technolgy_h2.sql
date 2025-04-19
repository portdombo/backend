CREATE TABLE t_technologies
(
    id          UUID DEFAULT RANDOM_UUID(), -- ou uuid_generate_v4() dependendo do banco
    code        BIGINT UNIQUE,
    name        VARCHAR(255),
    description VARCHAR(255),
    image       VARCHAR(255),
    highlighted BOOLEAN
);
