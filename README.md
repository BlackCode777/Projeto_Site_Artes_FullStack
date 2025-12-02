🎨 SiteDeArtistas - API Backend
Este repositório contém o Backend da plataforma profissional desenvolvida para exposição de obras de arte (Aerografia, Pintura a Óleo, Acrílica) e gestão de portfólio artístico.

O sistema consiste em uma API REST robusta responsável por gerenciar o acervo, categorização de obras e dados do artista, servindo os dados necessários para futuras interfaces clientes.

🚀 Tecnologias Utilizadas
O projeto utiliza uma stack moderna baseada no ecossistema Spring para garantir performance e escalabilidade.

Core & Frameworks
Java 21: Linguagem base (LTS) para aproveitar os recursos mais modernos da JVM.

Spring Boot 3.5+: Framework principal para configuração e injeção de dependências.

Spring Web: Para construção dos endpoints RESTful.

Spring Data JPA: Camada de persistência e ORM (Object-Relational Mapping).

Maven: Gerenciamento de dependências e build do projeto.

Banco de Dados
PostgreSQL: Banco de dados relacional robusto utilizado para armazenar informações das obras, categorias e mídias.

Arquitetura
DTO Pattern (Data Transfer Object): Utilizado para desacoplar as entidades de banco da camada de exposição (API), garantindo segurança e flexibilidade nos dados trafegados.

Camadas Bem Definidas: Separação clara entre Controllers, Services e Repositories.

📋 Funcionalidades da API
A API expõe endpoints para atender dois perfis de uso:

🔓 Endpoints Públicos (Leitura)
Listagem de Obras: Filtros para buscar obras finalizadas ou esboços (WIP - Work In Process).

Detalhes da Obra: Recuperação de informações completas de um quadro específico (técnica, dimensões, ano).

Informações do Artista: Endpoint para recuperar biografia e dados de contato.

🔐 Endpoints Administrativos (Gestão)
CRUD de Obras: Cadastro, Atualização e Remoção de obras do portfólio.

Gestão de Categorias: Criação de novas técnicas (ex: "Aerografia", "Óleo sobre Tela").

Associação de Mídias: Vínculo de URLs/Caminhos de imagens às obras cadastradas.

🗄️ Modelo de Dados (Schema)
O banco de dados PostgreSQL foi modelado para sustentar o portfólio, com foco inicial na relação entre o Artista e suas criações.

✅ Entidades Implementadas
Artista: Entidade raiz do sistema. Armazena os dados do perfil profissional, biografia, estilo predominante e informações de contato.

Relacionamento: 1:N (Um Artista possui muitas Obras).

Obra: Tabela central do acervo. Contém título, descrição detalhada, dimensões, data de criação, preço e status (ex: Disponível, Acervo Pessoal, WIP).

Relacionamento: N:1 (Cada Obra está vinculada a um único Artista).

🚧 Roadmap de Modelagem (Em Breve)
Categoria: Entidade planejada para categorização dinâmica das técnicas (ex: "Aerografia", "Óleo sobre Tela", "Carvão"), permitindo filtros avançados na API.

Midia: Tabela auxiliar planejada para armazenar múltiplas referências de imagem por obra (ex: foto principal, ângulo lateral, detalhe de textura), enriquecendo a galeria visual.

⚙️ Configuração e Execução
Como o projeto foca no backend, siga os passos abaixo para levantar o servidor localmente.

Pré-requisitos
Java JDK 21 instalado.

Maven instalado (ou usar o wrapper mvnw incluso).

PostgreSQL instalado e rodando.

Passos para Rodar
Clone o repositório:

Bash

git clone https://github.com/seu-usuario/site-de-artistas-backend.git
cd site-de-artistas-backend
Configure o Banco de Dados: Abra o arquivo src/main/resources/application.properties e ajuste as credenciais do seu PostgreSQL local:

Properties

spring.datasource.url=jdbc:postgresql://localhost:5432/site_artistas_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Cria/Atualiza as tabelas automaticamente baseadas nas classes Java
spring.jpa.hibernate.ddl-auto=update
Compile e Execute:

Bash

mvn spring-boot:run
Teste a API: Após iniciar, a API estará disponível em http://localhost:8080.

🤝 Contribuição
Este é um projeto pessoal focado em portfólio artístico e excelência técnica no desenvolvimento Backend Java. Sugestões de melhoria na arquitetura ou otimizações de queries são bem-vindas.

Desenvolvido por BlackCode77 - Aerografia & Code
