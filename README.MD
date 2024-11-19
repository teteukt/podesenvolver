### Podesevolver

Aplicativo Android voltado para consumo de repositórios RSS públicos de Podcasts que seguem o padrão da Apple. (https://podcasters.apple.com/support/823-podcast-requirements)

Arquitetura: MVVM

Tecnologias utilizadas:
- Jetpack Compose (Ceclaração de UI)
- Ktor (Cliente HTTP para consumo de dados vindos da Internet)
- Koin (Motor de injeção de dependências)
- KtRssReader (Serializador RSS/XML)
- Material3 (Componentes nativos que seguem o Design System do Material)
- Media3 (Reprodução de media audiovisual e carregamento de conteúdo remoto de forma asíncrona)
- Room (Banco de dados local SQLite)

## Checklist

- [X] Definir arquitetura MVVM voltado para Compose
- [X] Configurar um HTTP Client para consumo de dados remotos
- [X] Configurar o HTTP Client para receber dados em formato RSS/XML seguindo as especificações da Apple.
- [ ] Configurar banco de dados local com Room
- [X] Tela 1 - Fonte de RSS
  - [X] Buscar RSS de um Podcast com sucesso e direcionar para tela de detalhe do podcast
  - [X] Persistir RSS do podcast no banco de dados em caso de busca com sucesso
  - [ ] Histórico de URL usadas
  - [ ] Limpar histórico
  - [ ] Limpar registro de histórico
- [X] Tela 2 - Detalhes do Podcast
  - [X] Utilizar dados do podcast vindo do banco de dados local.
  - [X] Tela mostra título do podcast
  - [X] Tela mostra imagem do podcast
  - [X] Tela mostra descrição do podcast
  - [X] Tela mostra autores do podcast
  - [ ] Tela mostra duração do podcast
  - [X] Tela mostra gênero do podcast
  - [X] Tela mostra episódios do podcast
  - [X] Redireciona para tela de Player do episódio
- [X] Tela 3 - Player
  - [X] Utilizar dados do podcast vindo do banco de dados local.
  - [X] Reproduzir episódio ao entrar na tela
  - [X] Reproduzir episódio ao tocar no botão de reprodução
  - [X] Pausar episódio ao tocar no botão de pausar
  - [X] Reproduzir próximo episódio ao termino do episódio atual
  - [X] Voltar ao episódio inicial do podcast ao término do último episódio
  - [ ] Atualizar progresso do episódio periódicamente conforme o tempo
  - [X] Mostrar tempo e duração do episódio
  - [X] Voltar ou retroceder no tempo do episódio ao interagir com o slider
  - [X] Mostrar título do episódio
  - [ ] Mostrar descrição do episódio

## Maiores dificuldades

1. Serializar XML seja com Retrofit ou Ktor
2. Sincronizar informações do Exo Player com a tela
  
