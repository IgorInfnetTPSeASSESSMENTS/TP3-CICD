## TP3 — Controle, segurança e personalização do pipeline

Neste TP3, o pipeline foi ampliado com foco em controle de execução, segurança, separação de ambientes e evolução da aplicação.

### Etapa 1 — Runner auto-hospedado
Foi configurado um runner auto-hospedado vinculado ao repositório e criado um workflow para validar sua execução local. O job foi executado com `runs-on: self-hosted`, exibindo informações do sistema e realizando a instalação de um software adicional durante a execução.

### Etapa 2 — Uso de variáveis e secrets
Foram criadas duas variáveis não sensíveis no repositório, `APP_MODE` e `SUPPORT_EMAIL`, além do secret `PROD_TOKEN`. Essas informações foram utilizadas em um workflow por meio dos contextos `${{ vars.NOME }}` e `${{ secrets.NOME }}`, demonstrando a separação correta entre dados públicos de configuração e dados sensíveis.

### Etapa 3 — Contextos e escopos de variáveis de ambiente
Foi criado o workflow `env-context-demo.yml` para demonstrar o uso de contextos e variáveis de ambiente em diferentes níveis. Na execução, foram exibidos o usuário que acionou o workflow (`github.actor`), o sistema operacional do runner (`runner.os`) e a variável `STAGE`, definida em nível de workflow, sobrescrita no job e novamente no step.

### Etapa 4 — Controle de permissões e uso do GITHUB_TOKEN
Foi criado um workflow com configuração explícita de permissões do `GITHUB_TOKEN`, utilizando `contents: read` e `issues: write`. Esse token foi utilizado para criar automaticamente uma issue no repositório quando uma condição de erro foi detectada, demonstrando o uso controlado do token padrão do GitHub Actions.

### Etapa 5 — Ambientes de deploy para dev e prod
Foram configurados dois ambientes no GitHub: `dev` e `prod`. O ambiente `dev` possui liberação automática e variável própria, sendo usado em pushes para a branch `dev`. Já o ambiente `prod` possui secret específico e regra de proteção com aprovação manual, sendo acionado apenas em pushes para a branch `main`.

### Etapa 6 — Implementação e integração de nova funcionalidade na API
Como o repositório da API DevCalc citado no enunciado não foi disponibilizado, esta etapa foi adaptada para a Rick and Morty API desenvolvida anteriormente. Foi implementada uma nova funcionalidade compatível com o domínio da aplicação, permitindo a busca de personagens por nome por meio do endpoint `GET /api/characters/search?name=...`. Também foram adicionados testes automatizados para validar essa nova funcionalidade, e a integração foi confirmada com sucesso no pipeline.

## Como reexecutar os workflows do TP3

- **Self-Hosted Runner Demo**: acessar a aba **Actions**, selecionar o workflow e clicar em **Run workflow**. O runner auto-hospedado deve estar ativo na máquina configurada.
- **Variables and Secrets Demo**: executar manualmente pela aba **Actions** após configurar `APP_MODE`, `SUPPORT_EMAIL` e `PROD_TOKEN` em **Settings > Secrets and variables > Actions**.
- **Env Context Demo**: executar manualmente pela aba **Actions** para visualizar os contextos e os escopos das variáveis de ambiente.
- **GITHUB_TOKEN Issue Demo**: executar manualmente pela aba **Actions**. Caso a variável esperada esteja ausente, uma issue será criada automaticamente.
- **Deploy**: ocorre automaticamente em pushes para as branches `dev` e `main`, respeitando as regras configuradas nos environments `dev` e `prod`.
- **CI**: continua sendo executado nos pushes para a branch principal e valida a integração da aplicação com os testes automatizados e o ambiente em containers.

## Evidências
As evidências de execução, capturas de tela, logs e trechos relevantes dos arquivos YAML utilizados neste TP estão organizadas na pasta `evidencias/`.
