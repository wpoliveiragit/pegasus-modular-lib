@echo off
setlocal enabledelayedexpansion

:: Solicita o nome do novo modulo
set /p moduleName=Digite o nome do novo modulo: 

:: Solicita o nome do pacote principal
set /p packageName=Digite o nome do pacote principal (sem espacos): 

:: Obtem o diretorio onde o script esta localizado (diretorio atual)
set "scriptDir=%~dp0"

:: Define o caminho base onde o modulo sera criado (diretorio acima de onde o script esta)
set "moduleDir=%scriptDir%\..\modules\%moduleName%"

:: Converte pontos para barras no nome do pacote (caso seja informado com pontos)
set "packagePath=%packageName:.=\%"

:: Cria a estrutura de diretorios
mkdir "%moduleDir%\src\main\java\br\com\pegasus\module\%packagePath%"
mkdir "%moduleDir%\src\main\resources"
mkdir "%moduleDir%\src\test\java\br\com\pegasus\module\%packagePath%"
mkdir "%moduleDir%\src\test\resources"

:: Cria o arquivo pom.xml com o conteudo correto
(
    echo ^<?xml version="1.0" encoding="UTF-8"?^>
    echo ^<project xmlns="http://maven.apache.org/POM/4.0.0"
    echo     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    echo     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd"^>
    echo     ^<modelVersion^>4.0.0^</modelVersion^>
    echo     ^<parent^>
    echo         ^<groupId^>br.com.pegasus.modules.lib^</groupId^>
    echo         ^<artifactId^>pegasus-modular-lib^</artifactId^>
    echo         ^<version^>1.0.0^</version^>
    echo     ^</parent^>
    echo     ^<artifactId^>%moduleName%^</artifactId^>
    echo     ^<version^>1.0.0^</version^>
    echo     ^<groupId^>br.com.pegasus.modules.lib^</groupId^>
    echo     ^<packaging^>jar^</packaging^>
    echo     ^<dependencies^>
    echo     ^</dependencies^>
    echo     ^<repositories^>
    echo         ^<repository^>
    echo             ^<id^>github^</id^>
    echo             ^<url^>https://maven.pkg.github.com/wpoliveiragit/maven-repository^</url^>
    echo             ^<snapshots^>
    echo                 ^<enabled^>false^</enabled^>
    echo             ^</snapshots^>
    echo         ^</repository^>
    echo     ^</repositories^>
    echo ^</project^>
) > "%moduleDir%\pom.xml"

echo Modulo %moduleName% criado com sucesso!
exit /b
