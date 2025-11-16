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
DROP DATABASE IF exists FinanceiroPadaria;
CREATE DATABASE IF NOT EXISTS FinanceiroPadaria;
USE FinanceiroPadaria;

-- Criação das Tabelas ----------------------------------------------------------------------------------


CREATE TABLE grupo_usuarios (
                              id INT PRIMARY KEY AUTO_INCREMENT,
                              nome_grupo VARCHAR(100) NOT NULL,           -- 'gerente','vendedor'
                              nivel_permissao INT NOT NULL DEFAULT 1      -- 1,2,3 
);
INSERT INTO grupo_usuarios (nome_grupo, nivel_permissao) VALUES
                                                           ('Vendedor', 1),
                                                           ('Gerente', 2);





CREATE TABLE usuario (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       nome VARCHAR(255) NOT NULL,
                       cpf VARCHAR(11) UNIQUE NOT NULL,
                       senha VARCHAR(255) NOT NULL,
                       telefone VARCHAR(25) NOT NULL,
                       grupo_id INT NOT NULL,
                       CONSTRAINT fk_usuario_grupo FOREIGN KEY (grupo_id) REFERENCES grupo_usuarios(id)
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
                      pedido_id INT PRIMARY KEY auto_increment,
                      valor_final DECIMAL(12,2) DEFAULT 0,
                      funcionario_id CHAR(11),
                      data_pedido DATETIME DEFAULT CURRENT_TIMESTAMP,
                      forma_pagamento VARCHAR(255),
                      cpf_cliente VARCHAR(11)
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


-- INSERTS ----------------------------------------------------------------------------------------------


INSERT INTO produto (nome, preco_custo, preco_venda, qntd_estoque) VALUES
                                                                     ('Pão Francês', 0.30, 0.60, 500),
                                                                     ('Café em Pó 500g', 8.50, 12.00, 100),
                                                                     ('Leite Integral 1L', 3.20, 4.50, 200),
                                                                     ('Bolo de Chocolate', 10.00, 18.00, 25),
                                                                     ('Queijo Mussarela 1kg', 22.00, 35.00, 50),
                                                                     ('Presunto 1kg', 18.00, 30.00, 40),
                                                                     ('Coca-Cola 2L', 6.00, 9.50, 60),
                                                                     ('Suco de Laranja 1L', 4.50, 7.00, 80),
                                                                     ('Pão de Queijo 1kg', 12.00, 20.00, 30),
                                                                     ('Manteiga 500g', 9.00, 14.00, 70);

-- USUARIO ADMIN PADRÃO (LOGIN: 0000 / SENHA: admin)
INSERT INTO usuario (nome, cpf, senha, telefone, grupo_id) VALUES
  ('Administrador', '0000', '$2a$10$19m1Pr8LlIC2ibyZpCy7tOpWKhtvHxkjloyQo1Yx0rdE3Slb1rPAK', '00000000000', '2');



-- TRIGERRS --------------------------------------------------------------------------------------

-- CALCULA OS SUBTOTAIS E PEGA OS VALORES DOS PRODUTOS PARA COLOCAR NO "preco_unitario"
DROP TRIGGER IF EXISTS trg_pedido_item_bi;
DELIMITER $$
CREATE TRIGGER trg_pedido_item_bi
  BEFORE INSERT ON Pedido_Item
  FOR EACH ROW
BEGIN
  DECLARE preco DECIMAL(10,2);

  SELECT preco_venda INTO preco
  FROM Produto
  WHERE id = NEW.produto_id;

  SET NEW.preco_unitario = preco;
    SET NEW.subtotal = NEW.quantidade * preco;
END$$
  DELIMITER ;



-- CALCULA A SOMA TOTAL DE UM PEDIDO
  DROP TRIGGER IF EXISTS trg_pedido_item_ai;
  DELIMITER $$
  CREATE TRIGGER trg_pedido_item_ai
    AFTER INSERT ON Pedido_Item
    FOR EACH ROW
  BEGIN
    UPDATE Pedido
    SET valor_final = (
      SELECT COALESCE(SUM(subtotal),0)
      FROM Pedido_Item
      WHERE pedido_id = NEW.pedido_id
    )
    WHERE pedido_id = NEW.pedido_id;
    END$$
    DELIMITER ;


-- CHAMA A FUNÇÃO DE GERAR ID DE USUARIO
DELIMITER $$
    CREATE TRIGGER trg_usuario_id
      BEFORE INSERT ON usuario
      FOR EACH ROW
    BEGIN
      SET NEW.id = gerar_id_usuario();
END$$
      DELIMITER ;



-- CHAMA A procedure DE DIMINUIR ESTIQUE APOS VENDA
      DROP TRIGGER IF EXISTS trg_reduzir_estoque_item;
      DELIMITER $$
      CREATE TRIGGER trg_reduzir_estoque_item
        AFTER INSERT ON Pedido_Item
        FOR EACH ROW
      BEGIN
        CALL reduzir_estoque(NEW.produto_id, NEW.quantidade);
        END$$
        DELIMITER ;


-- INDICES -----------------------------------------------------------------------------------------------

-- CASO QUEIRA FAZER UMA PESQUISA PELO SQL, ESSES SÃO OS PRINCIPAIS QUE VÃO AJUDAR NAS BUSCAS (OS MAIS UTEIS)

-- acelera JOIN e cálculo do total
        CREATE INDEX idx_item_pedido_id ON Pedido_Item (pedido_id);
--  consultas por produto
        CREATE INDEX idx_item_produto_id ON Pedido_Item (produto_id);
--  para listar pedidos de um cliente específico
        CREATE INDEX idx_pedido_cpf_cliente ON Pedido (cpf_cliente);
--  para buscas de produtos por nome (usado em vendas)
        CREATE INDEX idx_produto_nome ON Produto (nome);


        -- EXEMPLOS DE BUSCA QUE USA OS INDEX CRIADOS

--  COMPRAS DE UM CLIENTE X
        SELECT p.pedido_id, p.data_pedido, pr.nome, i.quantidade, i.subtotal
        FROM Pedido p
               JOIN Pedido_Item i ON p.pedido_id = i.pedido_id
               JOIN Produto pr ON i.produto_id = pr.id
        WHERE p.cpf_cliente = '123';

-- VENDAS DE UM PRODUTO X (PELO ID)
        SELECT p.pedido_id, p.data_pedido, pr.nome, i.quantidade, i.subtotal
        FROM Pedido p
               JOIN Pedido_Item i ON p.pedido_id = i.pedido_id
               JOIN Produto pr ON i.produto_id = pr.id
        WHERE pr.id = '4';


-- VENDAS DE UM PRODUTO X (PELO NOME)
        SELECT p.pedido_id, p.data_pedido, pr.nome, i.quantidade, i.subtotal
        FROM Pedido p
               JOIN Pedido_Item i ON p.pedido_id = i.pedido_id
               JOIN Produto pr ON i.produto_id = pr.id
        WHERE pr.nome = 'Pão Francês';


-- VIEWS -------------------------------------------------------------------------------------------

        -- MOSTRAR O VALOR TOTAL DE VENDAS O DASHBOARD
        CREATE VIEW total_vendas_view AS
        SELECT 1 AS dummy_id,
               COALESCE(SUM(valor_final),0) AS total_vendido
        FROM Pedido;
-- SELECT * FROM total_vendas_view;



        -- MOSTRAR AS VENDAS POR DIA NO DASHBOARD
        CREATE VIEW vendas_por_dia AS
        SELECT DATE(data_pedido) AS dia,
                COALESCE(SUM(valor_final), 0) AS total_vendido
                FROM Pedido
                GROUP BY DATE(data_pedido)
                ORDER BY dia DESC;
        -- SELECT * FROM vendas_por_dia;


-- FUNCTIONS----------------------------------------------------------------------------------------


-- OUTRA FUNCTION DE GERAR ID DE USUARIO + TESTE PRA VER SE JA EXISTE
        DELIMITER $$
        CREATE FUNCTION gerar_id_usuario()
          RETURNS INT
          DETERMINISTIC
        BEGIN
    DECLARE novo_id INT;

    tentativa: LOOP
        SET novo_id = FLOOR(1 + (RAND() * 999));

        -- verifica se já existe
        IF NOT EXISTS (SELECT 1 FROM usuario WHERE id = novo_id) THEN   --  Procura na tabela usuario
																		-- Uma linha cujo id seja igual ao número novo_id
																		-- Se encontrar, retorna o número 1
																		-- Se não encontrar, não retorna nada
            LEAVE tentativa; -- ID válido encontrado
      END IF;
    END LOOP;

    RETURN novo_id;
    END$$
    DELIMITER ;



-- PROCEDURE -------------------------------------------------------------------------------------

-- PROCEDURAL DE REMOVER DO ESTOQUE APOS FAZER UM PEDIDO
DELIMITER $$
    CREATE PROCEDURE reduzir_estoque(IN prod_id INT, IN qtd INT)
    BEGIN
    UPDATE Produto
    SET qntd_estoque = qntd_estoque - qtd
    WHERE id = prod_id;
    END$$
    DELIMITER ;
```

## 6. Implementação do Banco NoSQL MongoDB, Logs

### 6.1 Escolha e justificativa
Foi adotado o **MongoDB** como banco **NoSQL** exclusivamente para **registro de logs** da aplicação (requisições HTTP e eventos de negócio).
Justificativas: **schema flexível**, **baixa fricção de escrita**, **TTL nativo** para expurgo automático e **separação de responsabilidades** (MySQL para o domínio; Mongo para observabilidade).

### 6.2 Modelagem e collections
- **Database**: `padaria`
- **Collection**: `logs`
- **Documento** (`LogEntry`): `level` (INFO/WARN/ERROR), `category` (http/negocio), `message`, `meta` (Map), `timestamp`, `expiresAt`.
- **Índice TTL**: em `expiresAt` com `expireAfterSeconds: 0` (remoção automática quando a data é alcançada).

### 6.3 Integração no backend (Spring Boot)
- `LogEntry` (`@Document("logs")`) com `@Indexed(expireAfterSeconds = 0)` em `expiresAt`.
- `LogRepository` (Spring Data Mongo) e `LogService` (`info/warn/error`) — retenções: INFO 7d, WARN 30d, ERROR 90d (ajustável).
- `HttpLogFilter` (extends `OncePerRequestFilter`) grava **toda** requisição HTTP (método, rota, status, duração).
> O uso do Mongo **não altera** o MySQL/JPA do domínio.

### 6.4 Configuração
**`pom.xml`**:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
