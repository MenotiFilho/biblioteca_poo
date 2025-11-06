# Sistema de Gerenciamento de Biblioteca - POO

## 1. Introdução

Este projeto foi desenvolvido como avaliação para a disciplina de **Programação Orientada a Objetos (POO)**, do 4º semestre do curso de Análise e Desenvolvimento de Sistemas da **FATEC - Mococa**.

O objetivo principal é aplicar os conceitos fundamentais da Programação Orientada a Objetos em um projeto prático, construindo um sistema web para o gerenciamento de uma biblioteca. A aplicação permite controlar o acervo de livros, cadastrar clientes e gerenciar empréstimos e devoluções.

## 2. Análise do Projeto e Conceitos Aplicados

O sistema foi estruturado utilizando o padrão **Model-View-Controller (MVC)**, que separa as responsabilidades em camadas lógicas, facilitando a manutenção e a evolução do código.

- **Model:** Representa os dados e a lógica de negócio. As entidades como `Cliente`, `ItemAcervo`, `LivroFisico` e `Emprestimo` encapsulam os atributos e comportamentos dos objetos do mundo real. Conceitos de POO aplicados aqui:
    - **Encapsulamento:** Cada classe protege seus dados (atributos privados) e expõe operações através de métodos públicos (`getters`/`setters` e métodos de negócio como `calcularMulta()`).
    - **Herança:** A classe `LivroFisico` herda da classe abstrata `ItemAcervo`, reutilizando atributos e métodos comuns e especializando-se com seus próprios campos, como `isbn`.
    - **Abstração:** A classe `ItemAcervo` define um contrato comum para todos os itens que podem compor o acervo da biblioteca, abstraindo detalhes específicos.

- **View:** É a camada de apresentação, responsável por exibir os dados ao usuário. Utiliza **Thymeleaf** com **Tailwind CSS** para criar interfaces modernas e responsivas. O uso de **HTMX** permite a criação de interações dinâmicas (como buscas assíncronas) sem a necessidade de escrever JavaScript complexo, melhorando a experiência do usuário.

- **Controller:** Atua como intermediário, recebendo as requisições do usuário, acionando a lógica de negócio nos `Services` e direcionando para a `View` apropriada.

A camada de **Serviços** (`EmprestimoService`, `AcervoService`, etc.) abstrai a lógica de negócio e as interações com o banco de dados, enquanto os **Repositórios** (`JpaRepository`) cuidam da persistência dos dados de forma eficiente.

## 3. Funcionalidades Implementadas

O sistema conta com os seguintes módulos:

1.  **Dashboard:**
    *   Página inicial que apresenta um resumo estatístico da biblioteca:
        *   Total de clientes cadastrados.
        *   Total de títulos no acervo.
        *   Total de empréstimos ativos.

2.  **Gerenciamento de Acervo (Livros):**
    *   CRUD completo (Criar, Ler, Atualizar, Deletar) para os livros.
    *   Busca dinâmica por título, autor ou ISBN.
    *   Exibição da capa do livro e do status de disponibilidade (Disponíveis / Total).

3.  **Gerenciamento de Clientes:**
    *   CRUD completo para os clientes da biblioteca.
    *   Busca dinâmica por nome, email ou telefone.

4.  **Gerenciamento de Empréstimos:**
    *   **Realizar Empréstimo:** Interface para emprestar um livro disponível a um cliente (com busca assíncrona de clientes).
    *   **Realizar Devolução:** Funcionalidade para registrar a devolução de um item, atualizando automaticamente a disponibilidade no acervo.
    *   **Histórico por Cliente:** Tela que exibe todos os empréstimos (ativos e devolvidos) de um cliente específico.
    *   **Cálculo de Multa:** O sistema calcula e exibe automaticamente o valor de multas por atraso na devolução.

## 4. Tecnologias Utilizadas

- **Backend:**
    - **Java 17**
    - **Spring Boot 3:** Framework principal para a construção da aplicação.
    - **Spring Data JPA:** Para persistência de dados e comunicação com o banco.
    - **Maven:** Gerenciador de dependências e build do projeto.
    - **H2 Database:** Banco de dados em memória para ambiente de desenvolvimento.

- **Frontend:**
    - **Thymeleaf:** Motor de templates para renderizar as páginas no servidor.
    - **Tailwind CSS:** Framework CSS para estilização rápida e moderna.
    - **HTMX:** Para adicionar interatividade e dinamismo às páginas com requisições AJAX declarativas.

## 5. Como Executar o Projeto

1.  **Pré-requisitos:**
    *   JDK 17 ou superior.
    *   Apache Maven instalado e configurado no PATH.

2.  **Passos:**
    *   Clone este repositório.
    *   Abra um terminal na pasta raiz do projeto.
    *   Execute o comando:
        ```bash
        mvn spring-boot:run
        ```
    *   Após a inicialização, acesse a aplicação em seu navegador: `http://localhost:8080`

3.  **Acesso:**
    *   O sistema é pré-configurado com um usuário administrador:
        - **Login:** `admin`
        - **Senha:** `admin`
    *   Os dados iniciais (livros, clientes e empréstimos de exemplo) são criados automaticamente pela classe `DataInitializer`.
