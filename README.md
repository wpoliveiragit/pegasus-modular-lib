# DEPLOY

## Verificação de deploy no github
Para verificar as versões de cada biblioteca publicada no github siga o processo abaixo.

1. Entre no site do github `https://github.com/wpoliveiragit`.
1. Entre na aba `Packages`, 
	- Esta aba mostra todos os pacotes que foram publicados.
1. Entre no pacote desejado para mostrar todas as informações dele como:
	- versão corrente.
	- dependencia a ser usada no projeto.
	- verções publicadas.

## Deletando um pacote
1. Entre no site do github `https://github.com/wpoliveiragit`.
1. Entre na aba `Packages`, 
1. Entre no pacote desejado.
1. Vá em `Package settings` no canto inferior direito da pagina.
1. Clique no botão `Delete this package`.
1. Digite o texto em negrito na caixa de texto q representa o pacote do projeto (para habilitar o botão `I understand the consequences, delete this package`).

## Efetuando o deploy
Para publicar um novo pacote siga os passos abaixo
1. abra um terminal.
1. navegue até o raiz do projeto.
1. execute o comando abaixo

```java
mvn clean deploy -DaltDeploymentRepository=github::default::https://maven.pkg.github.com/wpoliveiragit/maven-repository
```
