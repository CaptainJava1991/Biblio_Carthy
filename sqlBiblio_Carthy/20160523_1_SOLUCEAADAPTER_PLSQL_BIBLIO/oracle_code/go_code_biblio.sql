-- Ecriture de l'affichage dans un fichier de log
SPOOL go_code.log

-- echo des commandes et des commentaires dans SQL*Plus
SET ECHO ON

-- Pas d'affichage des substitutions de variable
SET VERIFY OFF

-- Sortir en cas d'erreur SQL
WHENEVER SQLERROR EXIT ROLLBACK
WHENEVER OSERROR EXIT ROLLBACK

@p_new_archive
@p_new_exemplaire
@p_suppr_exemplaire
@p_suppr_utilisateur
@f_compte_exemplaire
@p_new_emprunt_en_cours
@t_suppr_emprunt_en_cours
--@t_majNbExemplaire_Livre

PROMPT     FIN NORMALE DU SCRIPT

-- Comportement par défaut
spool off
set echo off
set verify on

-- Comportement par défaut: ne pas sortir en cas d'erreur SQL
WHENEVER SQLERROR continue none