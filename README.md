# Explorando Marte
Projeto realizado como parte do processo seletivo do Elo7
Repositório de referência: https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c

### Contexto: 
A NASA envia um conjunto de sondas a um planalto em Marte. Na entrada de dados, a primeira linha identifica as dimensões do planalto a ser explorado e só aparece uma vez, as próximas serão referentes às sondas. Nesse sentido, a próxima linha refere-se às coordenadas iniciais dessa sonda, representa por x e y, acompanhada do sentido posicional dela (podendo ser Norte (N), Sul (S), Leste (E) ou Oeste (W)). Nessa linha de entrada foi feita uma mudança em relação à documentação, assim, antes eram 3 caracteres de entrada e agora são 4, onde o último refere-se ao número identificador da sonda.
Exemplo:
`2 2 E 1` -> X=2, Y=2, sentidoAtual= Leste, numeroSonda=1

Em seguida, a próxima linha propõe-se a trazer as instruções a serem enviadas à sonda pousada pela linha anterior, podendo então ser de 3 tipos:
- L: faz a sonda virar 90 graus para a esquerda, sem mover a sonda.
- R: faz a sonda virar 90 graus para a direita, sem mover a sonda.
- M: faz com que a sonda mova-se para a frente um ponto da malha, mantendo a mesma direção.

### Classes:
Main: Classe onde o método main está localizado, responsável por chamar as funcionalidades da classe Leitura.
    
Leitura: Classe responsável por ler o arquivo de entrada (input.txt), passá-lo para uma lista de Strings onde cada uma é uma linha do arquivo. Em seguida, na função `explorarMarte()` é onde os comandos são interpretados, seja de demarcar um planalto, pousar sonda, movê-la pelo solo ou a decolar. Por fim, a função `exibirResultado()` tem a função de imprimir para o usuário o resultado de toda a interação realizada com as sondas no planalto.

Planalto: Classe responsável por guardar as coordenadas limites do planalto e controlar as interações que ocorrem em seu solo, como as principais: `pousarSonda()`, `moverSonda()` e `decolarSonda()`; e as auxiliares como: certificar que a sonda não ultrapasse os limites do planalto por meio da `limiteValido()`, `checarDestino()` para verificar se a movimentação desejada para a sonda pode ser executada ou se há impedimento (outra sonda no lugar ou limite ultrapassado), `adicionarSonda()` ou `removerSonda()` na lista de sondas em solo, `getSonda()` que retorna o objeto da Sonda desejada por meio de seu número identificador, e métodos getters para as coordenadas do planalto e a lista de sondas em solo. 

Sonda: Classe responsável por guardar as informações referentes a uma sonda, como coordenadas X e Y, sentido atual para o qual ela está apontada (N, S, W ou E) e seu número identificador. Além disso, possui a função `alterarSentido()` que gerencia o atributo de sentidoAtual conforme intruções L ou R são recebidas.

### Detalhes:
#### Observação:
No método `moverSonda()` da classe Planalto há um comando de sleep ao final de cada interação no for, isso porque sem este comando a lógica dava certo ao debugar, mas ao rodar sem depuração o valor das variáveis apresentou um comportamento esquisito. A suspeita é que aconteceu alguma condição de corrida onde teve a tentativa de recuperar o valor da variável, mas ela já foi mudada no meio-tempo, algo nesse sentido. Por esse motivo, adicionei a função  `Thread.currentThread().sleep(0,1)` que recebe o tempo como parâmetro em milissegundos. Isso resolveu o problema, embora o ideal fosse adotar alguma solução que não precisasse do sleep.
  
#### Mudanças:
1. Número identificador da sonda: esse número precisa ser inserido juntamente com as coordenadas de pouso da sonda, após o sentido dela. A motivação para sua implementação foi para facilitar a recuperação do objeto Sonda desejado, bem como, pensando em uma melhoria futura, caso haja a necessidade de se enviar comandos para sondas já em solos em outro momento que não o logo após o pouso, por meio do identificador ficará mais fácil localizar essa sonda e realizar sua movimentação.

2. Função de decolar: Essa função tem por objetivo tirar uma sonda do solo do planalto em Marte, para chamá-la basta colocar o D (de decolar/departure) e o número identificador da sonda desejada. O tópico "Entrada" mostra um exemplo de utilização dessa função. Comentarei disso mais adiante, mas é importante destacar que se o comando de decolar for dado após um de coordenadas e esse apontar algum erro, o algoritmo não executará o de decolar pois por padrão ele pula uma linha assumindo que a seguinte às coordenadas refere-se às instruções para a sonda recém pousada.

#### Tratamentos de Erros
-  Ultrapassar os limites do Planalto: caso a isntrução de pouso ultrapasse os limites, não será realizada e retornará uma mensagem de erro. Porém, caso durante a movimentação a sonda ultrapasse o limite devido a alguma instrução, essa instrução será ignorada. Ou seja, a sonda estará parada em sua última posição válida e seguirá suas instruções a partir dali.

-  Tentar movimentar uma sonda para uma posição já ocupada (colisão): O algoritmo impedirá que isso ocorra e a sonda ficará parada em sua última posição válida, seguindo então as próximas instruções a partir desse ponto, sempre checando se sua movimentação é válida no plano.
-  Tentar pousar uma sonda já em solo: Como o identificador foi pensando para ser algo único, até para não gerar confusão ao se enviar um comando de movimentação, o programa retornará um erro e não permitirá o pouso.

-  Tentar decolar uma sonda inexistente em solo: Como as sondas são identificadas por um número único (mitigando assim que, por exemplo, tire do solo uma sonda diferente da desejada), o programa não permitirá que essa requisição ocorra, tanto porque ele não conseguirá localizar a sonda já que ela não está no mapeamento de sondas no solo do Planalto.

### Entrada:
#### Exemplo:
`5 5`        -> dimensões do Planato

`1 2 N 1`     -> coordenadas de pouso da primeira sonda + sentido + numero identificador

`LMLMLMLMM`   -> instruções para a sonda 1

`3 3 E 2`     -> coordenadas de pouso da segunda sonda + sentido + numero identificador

`MMRMMRMRRM`  -> instruções para a sonda 2

`4 1 N 2`     -> tentativa de pouso de sonda com identificador já existente

`MMRMMRMRRM`  -> linha de intruções q será ignorada devido ao fator citado na anterior

`D 3`         -> tentativa de decolar/tirar do solo a sonda de número 3, porém ela não estava em solo, o que apontará erro

### Saída:
Presente no arquivo output.txt e refere-se ao status do que ocorreu com as entradas fornecidas no arquivo input.txt

### Como Executar:
Para compilar, rodar e passar a saída para um arquivo .txt:
`javac *.java && java Main > nomeArquivo.txt`

### Possíveis melhorias:
1. O sistema de entrada atual é lendo todas as linhas passadas de uma só vez em um documento, uma melhoria possível é tornar esse processo mais dinâmico permitindo que o usuário veja o avanço da sonda passo a passo e mande comando a partir disso. Isso pode ser feito, por exemplo, ao implementar uma interface gráfica para melhorar essa experiência do usuário.

2. O tratamento de exceções nesse progama está bem simples e em apenas em alguns momentos do código é mais personalizado (enviando mensagens que facilitem ao usuário identificar o motivo do erro), mas seria interessante ter uma classe dedicada a tratar exceções, não só essas já presentes no código como também de outros casos de erros não tratados e que serão comentados adiante.

3. Alguns erros de entrada podem ocorrer por parte do usuário e que poderiam, mas não estão sendo tratados em código, são eles:
- Número de Identificador fornecido fora do padrão: Convencionalmente, números identificadores são inteiros diferentes de 0, porém nessa implementação não há tratamento para caso o usuário forneça um identificador negativo por exemplo, nesse ponto, então, conta-se com a boa utilização e atenção do usuário (o que está longe de ser ideal). Na classe Leitura, por exemplo, já é convencionado que o `IDENTIFICADOR_INVALIDO` utilizado para inicializar a variável numeroSonda (por questões de interação no laço) é -1, embora, novamente, não há nada que impeça o usuário de fornecer o número de uma sonda como -1 também. 
- Inputs fora de ordem e/ou de padrão: Pela documentação, os inputs seguem a ordem descrita no tópico de "Contexto" (primeiro coordenadas do planalto, depois coordenadas de pouso, em seguida instruções de movimentação e essas duas últimas se repetem sucessivamente) e nessa implematação poderia ser adicionado algumas funcionalidades que verificassem a integridade da ordem/padrão dos comandos fornecidos ou que permitissem que os comandos fossem dados em ordem mais flexível. Talvez, por exemplo, adicionado o identificado no inicio ou no final da cadeia de instruções L,R ou M, dentre outras possibilidades.

4. Sobre a organização do repositório, ela poderia contar com mais branches, ao menos separando a main de uma dev por exemplo. Criando a partir disso uma branch para implementar cada futura feature desejada. Essa abordagem apenas não foi adotada nesse projeto por questões de agilizar o tempo hábil que eu possuia para o desenvolvimento, bem como só estar uma pessoa desenvolvendo e subindo atualizações nele, além de que sua complexidade era previsível, ou seja, não necessariamente o projeto expandiria muito além. Porém, apesar desses porquês, ainda sim seria importante a separação das branches.

5. Embora eu tenha refatorado alguns pontos no código ao longo do desenvolvimento, seria interessante rever esse ponto de refatoração pois creio que há sim diversos pontos a serem melhorados, como por exemplo o fato de que codei em português e, pensando em um cenário open source no GIT, seria uma melhor prática manter a linguagem em inglês. 

Opiniões e sugestões são sempre bem-vindas!
Obrigada :)