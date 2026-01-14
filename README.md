# Tarefas
- remover a tag **<repositories>** de todos o modulos para reverificar se o projeto é redistribuido, mesmo sem elas.

---
# Modulos
Este projeto foi criado no propósito de ter seus recursos construidos de maneira modulada.

## Criando um modulo com 'create_new_module.bat'
Foi criado o **create_new_module.bat** para autogerar um novo modulo. Abaixo esta um exemplo de uso:
1. na raiz do projeto, entre no diretorio **resourses** e execute o .bat **create_new_module.bat**
- Insira o nome do novo modulo separando cada palavras com um hifem '**-**' (não use letras maiusculas). Ex: **nome-modulo**
- Insira o nome do pacote principal (geralmente é o mesmo nome mas sem os hifens '**-**') (não use letras maiusculas) Ex: **nomemodulo**.
- Adicione no **pom.xml** do projeto pai na tag **<modules>** a tag **<module>modules/nome-modulo</module>**. 

## Criando o modulo manualmente

Crie a estrutura do pacote em **./modules/**:
- ./modules/**nome-do-novo-modulo/pom.xml**
- ./modules/**nome-do-novo-modulo/src/main/java/br.com.pegasus.module.<nome-do-modulo>**
- ./modules/**nome-do-novo-modulo/src/main/java/resources/**
- ./modules/**nome-do-novo-modulo/src/test/java/br.com.pegasus.module.<nome-do-modulo>**
- ./modules/**nome-do-novo-modulo/src/test/java/resources/**

Crie o pom.xml em **./modules/nome-modulo/** e adicione o conteudo abaixo (Obs.: altere a tag **artifactId** para o nome do novo modulo).

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>br.com.pegasus.modules.lib</groupId>
        <artifactId>pegasus-modular-lib</artifactId>
        <version>1.0.0</version>
    </parent>
    <artifactId>module-name</artifactId>
    <version>1.0.0</version>
    <groupId>br.com.pegasus.modules.lib</groupId>
    <packaging>jar</packaging>
    <dependencies>
    </dependencies>
    <repositories>
        <repository>
            <id>github</id>
            <url>https://maven.pkg.github.com/wpoliveiragit/maven-repository</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
</project>

```
- Adicione no **pom.xml** do projeto pai na tag **<modules>** a tag **<module>modules/nome-modulo</module>**. 

---
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
