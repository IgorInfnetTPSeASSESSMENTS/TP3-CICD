# actions-explicacao.md

## Diferença entre workflows e actions

No GitHub Actions, um **workflow** é o arquivo `.yml` que define a automação completa do projeto. É nele que dizemos **quando** a pipeline deve rodar, como por exemplo em um `push` ou manualmente com `workflow_dispatch`, e também **quais etapas** serão executadas.

Já uma **action** é uma peça reutilizável usada dentro do workflow para executar uma tarefa específica. Em vez de escrever tudo do zero, o workflow pode aproveitar actions prontas do Marketplace ou actions do próprio GitHub.

De forma simples:

- **workflow** = processo completo da automação
- **action** = tarefa específica usada dentro desse processo

## Como uma action é estruturada

Uma action normalmente possui um arquivo chamado `action.yml`. Esse arquivo funciona como a definição interna da action, informando:

- nome e descrição
- entradas (`inputs`)
- saídas (`outputs`)
- forma de execução (`runs`)

Ou seja, o `action.yml` explica como a action deve ser usada e o que ela faz.

## Exemplo do projeto

No projeto foi utilizada a action `actions/setup-java@v4`, que prepara o Java no ambiente do workflow.

Exemplo de uso:

```yaml
- name: Set up Java 17
  uses: actions/setup-java@v4
  with:
    distribution: temurin
    java-version: '17'
    cache: maven
```

Nesse exemplo, o workflow chama a action com uses: e passa os parâmetros com with:. Assim, a action sabe qual distribuição do Java instalar, qual versão usar e que tipo de cache deve configurar.

###Resumindo

O workflow organiza toda a pipeline, enquanto a action executa uma tarefa específica dentro dela. Já o arquivo action.yml que fica no repositório da própria action define como essa action funciona, quais dados recebe e como será executada.
