# Trabalho Final – Laboratório de Banco de Dados

## Objetivo

Desenvolver uma aplicação completa que envolva a criação e utilização de um banco de dados relacional (MySQL), integrando frontend e backend, com funcionalidades que demonstrem o domínio dos principais conceitos e recursos de um Sistema Gerenciador de Banco de Dados (SGBD). Além disso, o trabalho deve incluir o uso de um banco de dados NoSQL, com justificativa clara para sua escolha e aplicação.

## Tema
O tema da aplicação é **livre**, desde que o escopo permita a inclusão de todos os itens obrigatórios descritos neste documento.

## Entrega
Os alunos deverão entregar:

1. **Sistema completo** (código-fonte do backend, frontend e scripts do banco de dados).
Códigos em repositórios GIT.
2. **Documento técnico** em formato de **artigo acadêmico.**
3. **Apresentação oral** de até **5 minutos.**

## 1. Requisitos Técnicos do Sistema

### Banco de Dados Relacional (MySQL)
O sistema deve conter:

- Estrutura completa de tabelas com relacionamentos.
- **Índices** (justificar a escolha e uso).
- Triggers (mínimo de 2, com explicação de sua função).
- **Views** (mínimo de 2, com justificativa).
- **Procedures e Functions** (mínimo de 2, com explicação de uso).
- **Usuários e controle de acesso** (definir diferentes níveis de acesso e justificar).
    - Não será permitido acesso ao banco via **root.**
- **Tabelas obrigatórias:**
*usuarios:* contendo informações básicas dos usuários do sistema.
*grupos_usuarios:* para controle de permissões e agrupamento de usuários.
- **Geração de IDs:**
    - Criar **funções específicas** para geração de IDs.
    - O uso de *AUTO_INCREMENT* só será aceito quando **justificado.**
    - Para dados críticos, utilizar **regras próprias** de geração de IDs.

### Banco de Dados NoSQL

- Escolher um banco NoSQL (ex: MongoDB, Redis, Cassandra, etc.).
- Explicar o funcionamento básico do banco escolhido.
- Justificar sua escolha e **demonstrar sua aplicação** dentro do sistema.

## 2. Desenvolvimento da Aplicação

### Frontend

- Interface funcional e intuitiva.
- Deve permitir o login de usuários com base nas tabelas usuarios e grupos_usuarios.
- Operações básicas de CRUD para as principais entidades do sistema.
- Pode ser simples (HTML, CSS e JS) ou feita com alguma framework.
- Web ou Mobile.

### Backend

- API ou lógica de servidor que se comunique com o banco de dados relacional e o NoSQL.
- Implementar autenticação e controle de acesso com base nas permissões dos usuários.
    - A autenticação pode ser simples e manual, mas precisa existir.
- Utilizem boas práticas.
- Como vocês estão fazendo POO, sugiro fazer em Java com alguma framework (**Spring Boot**, Micronaut ou outra).

## 3. Documento Técnico (Artigo Acadêmico)

O documento deve seguir o formato de artigo acadêmico e conter:

### Estrutura

- **Título**
- **Autores**
- **Resumo**
- **Palavras-chave**
- **Introdução**
- **Objetivos**
- **Metodologia**
- **Descrição do Sistema**
    - Funcionalidades
    - Tecnologias utilizadas (frontend, backend, SGBD relacional e NoSQL)
    - Justificativa de cada escolha tecnológica
- **Modelagem do Banco de Dados**
    - **Diagrama Entidade-Relacionamento (DER)** – obrigatório
    - Explicação das entidades e relacionamentos
    - Justificativa para uso de índices, triggers, views, procedures/functions
- **Controle de Acesso**
    - Descrição dos usuários e grupos
    - Regras de acesso
- **Uso do Banco NoSQL**
    - Explicação técnica do banco escolhido
    - Justificativa da aplicação no sistema
- **Conclusão**
- **Referências**

### Critério de Avaliação do Documento

- Clareza e organização.
    - Podem utilizar outro modelo de documento, mas prezem pela organização e clareza.
- Explicação **detalhada e justificada** de cada item utilizado.
- A **ausência de justificativa** para qualquer item solicitado poderá acarretar **penalização de até 50% da nota** daquele item.

## 4. Apresentação Oral

- Tempo máximo: **5 minutos.**
- Deve conter:
    - Demonstração do sistema.
    - Explicação das principais escolhas técnicas.
    - Destaque para o uso do banco relacional e do NoSQL.
- **Penalização** da nota da apresentação em caso de **extrapolação do tempo** ou ausência de explicações.

## 5. Informações Complementares
Mais informações sobre a avaliação:

- A nota final poderá ser penalizada em 50% com a ausência de algum item do trabalho.
- Mais informações poderão ser adicionadas aqui, também serão anunciadas em sala.


### Scripts

```sql

CREATE DATABASE IF NOT EXISTS FinanceiroPadaria;
USE FinanceiroPadaria;

-- Criação das Tabelas

CREATE TABLE usuario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    telefone VARCHAR(25) NOT NULL
);

CREATE TABLE grupo_usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_grupo VARCHAR(100) NOT NULL,           -- 'gerente','vendedor'
    nivel_permissao INT NOT NULL DEFAULT 1      -- 1,2,3
);

-- PRODUTO
CREATE TABLE Produto (
    id INT PRIMARY KEY AUTO_INCREMENT, 
    nome VARCHAR(150) NOT NULL,
    preco_custo DECIMAL(10,2),
    preco_venda DECIMAL(10,2),
    qntd_estoque INT
);


-- PEDIDO
CREATE TABLE Pedido (
    pedido_id INT PRIMARY KEY AUTO_INCREMENT,
    valor DECIMAL(12,2),
    data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
    forma_pagamento varchar(255),
    cpf_cliente INT(11)
);


-- PEDIDO_ITEM (ASSOCIATIVA = PRODUTO X PEDIDO)
CREATE TABLE Pedido_Item (
    pedido_item_id INT PRIMARY KEY AUTO_INCREMENT,
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10,2),
    subtotal DECIMAL(12,2),
    CONSTRAINT fk_item_pedido FOREIGN KEY (pedido_id) REFERENCES Pedido(pedido_id) ON DELETE CASCADE,
    CONSTRAINT fk_item_produto FOREIGN KEY (produto_id) REFERENCES Produto(id)
);
```