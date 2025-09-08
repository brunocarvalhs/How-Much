# GitHub Actions Secrets Documentation

Este documento descreve os secrets necessários para o funcionamento dos workflows do GitHub Actions neste repositório. Certifique-se de configurar todos os secrets corretamente para evitar falhas nas execuções.

## Secrets Necessários

### 1. `TOKEN`
- **Descrição**: Token de autenticação do GitHub usado para operações como criação de PRs, comentários automáticos e aprovações.
- **Uso**: Presente em quase todos os workflows, como `tests.yml`, `revert-pr.yml`, `release-pr.yml`, etc.
- **Como configurar**: Vá para `Settings > Secrets and variables > Actions` e adicione o secret com o nome `TOKEN`.

### 2. `GOOGLE_SERVICE_JSON`
- **Descrição**: Arquivo `google-services.json` codificado em Base64, necessário para configurar o Firebase no projeto Android.
- **Uso**: Utilizado nos workflows `tests.yml`, `build.yml`, e `release.yml` para criar o arquivo `google-services.json`.
- **Como configurar**: Codifique o arquivo `google-services.json` em Base64 e adicione o valor como um secret chamado `GOOGLE_SERVICE_JSON`.

### 3. `KEYSTORE_PASSWORD`
- **Descrição**: Senha do keystore usado para assinar o APK/AAB.
- **Uso**: Necessário no workflow `release.yml` para assinar o bundle de lançamento.
- **Como configurar**: Adicione a senha do keystore como um secret chamado `KEYSTORE_PASSWORD`.

### 4. `KEYSTORE_ALIAS`
- **Descrição**: Alias do keystore usado para assinar o APK/AAB.
- **Uso**: Necessário no workflow `release.yml` para assinar o bundle de lançamento.
- **Como configurar**: Adicione o alias do keystore como um secret chamado `KEYSTORE_ALIAS`.

### 5. `KEY_PASSWORD`
- **Descrição**: Senha da chave dentro do keystore.
- **Uso**: Necessário no workflow `release.yml` para assinar o bundle de lançamento.
- **Como configurar**: Adicione a senha da chave como um secret chamado `KEY_PASSWORD`.

### 6. `FIREBASE_AUTH_TOKEN`
- **Descrição**: Token de autenticação para o Firebase CLI.
- **Uso**: Utilizado no workflow `build.yml` para fazer o upload do APK para o Firebase App Distribution.
- **Como configurar**: Gere um token de autenticação com o comando `firebase login:ci` e adicione como um secret chamado `FIREBASE_AUTH_TOKEN`.

### 7. `FIREBASE_APP_ID`
- **Descrição**: ID do aplicativo no Firebase.
- **Uso**: Utilizado no workflow `build.yml` para identificar o aplicativo no Firebase App Distribution.
- **Como configurar**: Adicione o ID do aplicativo como um secret chamado `FIREBASE_APP_ID`.

### 8. `DEPLOY_SERVICE_ACCOUNT_JSON`
- **Descrição**: Credenciais do serviço para deploy no Google Play, codificadas em Base64.
- **Uso**: Necessário no workflow `release.yml` para fazer o upload do AAB para o Google Play.
- **Como configurar**: Codifique o arquivo JSON da conta de serviço em Base64 e adicione como um secret chamado `DEPLOY_SERVICE_ACCOUNT_JSON`.

---

Certifique-se de que todos os secrets estejam configurados corretamente antes de executar os workflows. Para mais informações, consulte a [documentação oficial do GitHub Actions](https://docs.github.com/en/actions).