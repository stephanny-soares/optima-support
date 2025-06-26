# Optima Support

Optima Support é uma aplicação backend para gerenciamento de tickets de suporte técnico, que oferece autenticação segura, controle de usuários, categorias e tickets. A aplicação foi desenvolvida com foco na organização do código, segurança, boas práticas e experiência do usuário via API RESTful.

---

## Como a aplicação funciona

A aplicação permite que usuários se cadastrem e façam login com autenticação segura via Spring Security. Uma vez autenticado, o usuário pode criar, visualizar, atualizar e deletar tickets de suporte, associando cada ticket a uma categoria específica e ao autor que o criou.

Os tickets possuem status e data de criação automáticos e só podem ser editados ou excluídos pelo seu autor, garantindo segurança e integridade dos dados.

Além disso, há uma estrutura básica para categorias que organizam os tickets, e tratamento personalizado de exceções para respostas claras e amigáveis.

---

## Tecnologias utilizadas

- **Java 17** — Linguagem principal
- **Spring Boot** — Framework para construção da aplicação backend
- **Spring Security** — Controle de autenticação e autorização
- **Spring Data JPA (Hibernate)** — Persistência e mapeamento objeto-relacional
- **PostgreSQL** — Banco de dados relacional
- **Maven** — Gerenciamento de dependências e build

---

## Desafios enfrentados

Durante o desenvolvimento, os principais desafios foram:

- Garantir que apenas o autor do ticket pudesse editar ou deletar, implementando regras claras de segurança
- Configurar corretamente a autenticação JWT para proteger a API e validar usuários
- Criar um fluxo de exceções customizadas para melhorar o feedback ao cliente da API
- Organizar o código de forma modular e limpa, facilitando manutenção e extensibilidade futura
- Ajustar as entidades para refletir as relações corretas no banco, como a associação entre tickets e usuários

---

## O que o usuário pode esperar da aplicação

- Uma API RESTful segura e funcional para gerenciamento de tickets de suporte
- Controle de usuários com login e autenticação
- Criação, leitura, atualização e exclusão de tickets vinculados a categorias
- Respostas claras e adequadas em caso de erros ou recursos não encontrados
- Estrutura preparada para evolução e inclusão de novas funcionalidades
