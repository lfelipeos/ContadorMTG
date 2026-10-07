Contador de Vida MTG
Aplicativo Android para gerenciamento de pontos de vida e contadores secundários em partidas de Magic: The Gathering. Desenvolvido em Kotlin com Jetpack Compose, com suporte a orientação horizontal (Landscape) e modo imersivo (tela cheia).

Recursos Atuais
Contador de Vida: Incremento e decremento rápido da pontuação principal de vida.

Atalhos Rápidos de Vida: Botões de ação para definir a vida em 20 HP, 40 HP ou reiniciar a partida via botão Reset.

Contador de Veneno: Marcador de 0 a 10 pontos com indicador visual de preenchimento (de baixo para cima).

Dano de Comandante: Marcador de dano acumulado de comandante de 0 a 21.

Painel de Extras Alternável: Opção de ocultar ou exibir os contadores secundários (Veneno e Comandante) para limpar a interface.

Seletor de Planos de Fundo com Miniaturas: Janela flutuante em grade de 3 colunas que exibe prévias visuais de 19 artes temáticas (Guildas de Ravnica, Terrenos Básicos e Personagens) para personalizar a tela.

Modo Imersivo: Ocultação automática das barras do sistema (notificações, bateria e navegação) durante o uso do app.

Tecnologias Utilizadas
Linguagem: Kotlin

UI Framework: Jetpack Compose

Arquitetura de Layout: Compose LazyVerticalGrid, Box, Row e Column

Ícones: Material Icons Extended

Plataforma Alvo: Android (Layout otimizado em Landscape / Tela Deitada)

Funcionalidades em Desenvolvimento / A Fazer (To-Do)
[ ] Suporte para Multi-Jogadores (Divisão de Tela):

Implementação de layout dividido em grade para até 4 jogadores na mesma tela.

Inversão de interface para jogadores sentados em posições opostas na mesa.

Gerenciamento de estado de vida e contadores independentes para cada um dos 4 participantes.

[ ] Histórico de Partida: Registro de alterações de pontos de vida efetuadas durante o jogo.

[ ] Marcadores Adicionais: Suporte a contadores de energia, experiência e taxa de comandante.

Como Executar o Projeto
Clone este repositório:

```bash
# Clone o repositório
git clone [https://github.com/usuario/projeto.git](https://github.com/usuario/projeto.git)

# Acesse o diretório
cd projeto

# Instale as dependências
npm install
```

Aguarde a sincronização das dependências do Gradle.

Execute o projeto em um emulador ou dispositivo físico com suporte ao Android 7.0 (API nível 24) ou superior.
