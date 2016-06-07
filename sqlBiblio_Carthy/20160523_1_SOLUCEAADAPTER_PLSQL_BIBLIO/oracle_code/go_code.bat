REM @echo off
prompt CMD$g
REM
REM
REM NAME
REM   go.bat
REM
REM DESCRIPTION
REM   Commande de création des bases de données
REM USAGE
REM
REM   Pour rendre le script facilement réutilisable,
REM   Les variables d'environnement doivent être positionnées
REM
REM   Creation : Xavier HER
REM        MAJ : 

REM Vérification de la présence des variables d'environnement
if (%ORACLE_HOME%) == () goto nooraclehome


REM set SOURCE=%PROJETS_HOME%\...\Oracle
set SOURCE=.

REM - lancement de SQL*Plus en mode ligne avec le script 
%ORACLE_HOME%\bin\SQLPLUS biblio/biblio @"%SOURCE%\go_code_biblio.sql"

goto exit

:nooraclehome
echo ORACLE_HOME variable d environement non positionnee
pause
exit 1

:nojavahome
echo JAVA_HOME variable d environement non positionnee
pause
exit 2
:exit
echo SCRIPT TERMINE SANS ERREUR
pause


