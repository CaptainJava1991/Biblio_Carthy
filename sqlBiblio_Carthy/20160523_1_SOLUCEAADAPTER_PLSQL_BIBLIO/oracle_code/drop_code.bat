REM @echo off
prompt CMD$g
REM
REM
REM NAME
REM   drop.bat
REM
REM DESCRIPTION
REM   Commande de destruction de la BD
REM USAGE
REM
REM   Pour rendre le script facilement réutilisable,
REM   Les variables d'environnement suivantes
REM   doivent être positionnées : ...
REM
REM
REM   Creation : Xavier HER
REM        MAJ : 


REM Vérification de la présence des variables d'environnement
if (%ORACLE_HOME%) == () goto nooraclehome


set SOURCE=.

REM - lancement de SQL*Plus en mode ligne avec le script 
%ORACLE_HOME%\bin\SQLPLUS biblio/biblio @"%SOURCE%\drop_code_biblio"


goto exit

:nooraclehome
echo ORACLE_HOME variable d environement non positionnee
pause
exit 1

:nojavahome
echo JAVA_HOME variable d environement non positionnee
pause
exit2

:exit
echo SCRIPT TERMINE SANS ERREUR

pause

