-- Verificar e criar o banco de dados 'portdombo'
SELECT 'CREATE DATABASE portdombo'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'portdombo')
\gexec
