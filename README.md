# Fórum Hub
Este projeto foi realizado para resolver o último desafio do probrama Oracle ONE que consiste em desenvolver uma API REST com o framework Spring.
O desafio tem como objetivo principal permitir requisições GET, POST, PUT e DELETE com intuito de realizar operações CRUD em um banco de dados (MySQL) de tópicos. 
Entretanto, este projeto também permite realizar operações CRUD via requisições HTTP para usuários, no caso dos cursos estes devem ser realizados via terminal usando o próprio banco de dados.

## Uso

### Requisitos
- MySQL 8.0
- Insomnia ou Postman

### Instruções
Após instalar o banco de dados MySQL rode os seguintes comandos no terminal do seu OS:<br>
Windows:
~~~~cmd
cd "C:\Program Files\MySQL\MySQL Server 8.0\bin"
~~~~
Depois de inserir sua senha para acessar o banco de dados digite a seguinte query:
~~~sql
> CREATE TABLE forum;
> exit
~~~
Defina as váriaveis de ambiente.
- DATASOURCE_URL
- DATASOURCE_USERNAME
- DATASOURCE_PASSWORD

### Execução
Acesse o local onde se encontra o projeto e digite:
~~~~cmd
 java -Dspring.profiles.active=prod -jar target/api-0.0.1-SNAPSHOT.jar
~~~~

### Requisições via Insomnia ou Postman
- http://{host}:{port}/login
- http://{host}:{port}/topicos
- http://{host}:{port}/topicos/{id}
- http://{host}:{port}/usuarios
- http://{host}:{port}/usuarios/{id}
