[JAVASCRIPT__BADGE]: https://img.shields.io/badge/Javascript-000?style=for-the-badge&logo=javascript
[TYPESCRIPT__BADGE]: https://img.shields.io/badge/typescript-D4FAFF?style=for-the-badge&logo=typescript
[EXPRESS__BADGE]: https://img.shields.io/badge/express-005CFE?style=for-the-badge&logo=express
[VUE__BADGE]: https://img.shields.io/badge/VueJS-fff?style=for-the-badge&logo=vue
[NEST__BADGE]: https://img.shields.io/badge/nest-7026b9?style=for-the-badge&logo=nest
[GRAPHQL__BADGE]: https://img.shields.io/badge/GraphQL-e10098?style=for-the-badge&logo=graphql
[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MONGO_BADGE]:https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white
[AWS_BADGE]:https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white


<h1 align="center" style="font-weight: bold;">iBank - Backend 💻</h1>

<p align="center">
 <a href="#about">Sobre</a> • 
  <a href="#prerequisites">Pré Requisitos</a> •
 <a href="#routes">API Endpoints</a> •
 <a href="#contato">Contato</a>
</p>

<h2 id="about">Sobre o Projeto</h2>

Aplicação backend desenvolvida com Java e Spring Framework, que oferece todas as operações CRUD (GET, POST, DELETE) para as entidades "Transferencia", "Usuario" e "Cartão". Esta API autoral se conecta a um banco de dados PostgreSQL para armazenar dados dos usuários que possuem transferências e cartão de credito e debito. Este README fornece instruções para instalar e executar a aplicação localmente.

[Website on Vercel](https://ibank-frontend.vercel.app/)

[Frontend Repository](https://github.com/kaualimadesouza/ibank-frontend)

<h3>Tecnologias</h3>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

<h3 id="prerequisites">Pré Requisitos</h3>

Você deve ter o Java 22 instalado em sua máquina e o git para clonar o repositório.

- [Java](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR)
- [Git](https://git-scm.com/downloads)

<h3>Cloning</h3>

Primeiro Clone o repositorio

```bash
git clone https://github.com/kaualimadesouza/ibank-backend.git
```

<h3>Variáveis de ambiente</h2>

Importe o projeto para a sua IDE (Eclipse, Intelij ou VSCode) e altere o seu application.properties de acordo com sua senha e usuario do banco de dados

```yaml
spring.datasource.url=jdbc:postgresql://localhost:5432/bank
spring.datasource.username=postgres
spring.datasource.password=password
```

Após isso, instale as dependências do projeto clicando no 'package':

![image](https://github.com/user-attachments/assets/71b32a57-e709-46ac-ac78-e280e6fa4dd7)

Com todas essas alterações concluídas, já é possivel iniciar o projeto com sucesso.

<h2 id="routes">API Endpoints</h2>

​
| Rota               | Descrição (Usuário)                                         
|----------------------|-----------------------------------------------------
| <kbd>GET /user</kbd>     | Retorna todos os usuários
| <kbd>GET /user/{userId}</kbd>     | Retorna um usuário de acordo com o Id
| <kbd>GET /user/{userCPF}/cpf</kbd>     | Retorna um usuário de acordo com o CPF
| <kbd>GET /user/{email}/email</kbd>     | Retorna um usuário de acordo com o email
| <kbd>POST /user</kbd>     | Insere um usuário no banco de dados a partir de um arquivo JSON enviado no corpo da requisição.
| <kbd>DELETE /user/delete/{userId}</kbd>     | Deleta um usuário com base no seu 'id'.
| <kbd>UPDATE /user/update/{userId}</kbd>     | Altera um usuário com base no seu ID, utilizando as informações fornecidas no corpo da requisição.


| Rota               | Descrição (Usuário)                                         
|----------------------|-----------------------------------------------------
| <kbd>GET /transfer</kbd>     | Retorna todas as transferências de todos os usuários
| <kbd>GET /transfer/{userId}</kbd>     | Retorna todas as transferências de um usuário pelo Id
| <kbd>GET /transfer/date</kbd>     | Retorna todas as transferências de acordo com o ano "year" e mês "month" passado no corpo da requisição
| <kbd>GET /transfer/sender/{userId}</kbd>     | Retorna todas as transferências enviadas de um usuário pelo Id
| <kbd>GET /transfer/receiver/{userId}</kbd>     | Retorna todas as transferências recebidas de um usuário pelo Id
| <kbd>POST /transfer</kbd>     | Realiza uma transferências passando o "id_sender", "id_receiver", quantia "amount" e tipo de transferência "type" no corpo da requisição
| <kbd>DELETE /transfer</kbd>     | Deleta todas as transferências do banco de dados.



| Rota               | Descrição (Cartão)                                         
|----------------------|-----------------------------------------------------
| <kbd>GET /user/{userId}/cards/credit</kbd>     | Retorna os dados do cartão de credito de um usuário (caso ele tiver ativado o cartão)
| <kbd>GET /user/{userId}/cards/debit</kbd>     | Retorna os dados do cartão de débito de um usuário (caso ele tiver ativado o cartão)
| <kbd>POST /user/{userId}/cards/credit/active</kbd>     | Cria um cartão de crédito para o usuário
| <kbd>POST /user/{userId}/cards/debit/active</kbd>     | Cria um cartão de débito para o usuário

<h3>POST /user</h3>

**REQUEST**
```json
{
  "first_name": "Alex",
  "last_name": "Martins",
  "amount": 2000.00,
  "cpf": "311.333.248-52",
  "email": "alexm@gmail.com",
  "password": "123"
}

- O CPF DEVE SER VALIDO -
```


<h3>POST /transfer</h3>

**REQUEST**
```json
{
  "id_sender" : "36b4a490-2a8d-4a5a-afac-3468f52f1675",
  "id_receiver" : "16a93d58-fc15-4aa6-9c62-8f4040134a59",
  "amount" : 10,
  "type" : "DEBIT"
}
```


<h3>POST /user/{userId}/cards/credit/active e /user/{userId}/cards/debit/active</h3>

**REQUEST**
```json
{
  "password" : "123"
}
```


<h2 id="contato">Contato</h2>

Linkedin [@kaualimadesouza](https://www.linkedin.com/in/kaualimadesouza/) &nbsp;&middot;&nbsp;
