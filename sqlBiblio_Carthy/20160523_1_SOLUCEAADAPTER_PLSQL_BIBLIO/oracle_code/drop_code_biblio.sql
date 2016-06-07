-- Comportement par défaut: ne pas sortir en cas d'erreur SQL
WHENEVER SQLERROR continue none

Prompt *************************************
Prompt SUPPRESSION DES PROCEDURES, FONCTIONS
Prompt *************************************

DROP PROCEDURE p_new_archive;
DROP PROCEDURE p_new_exemplaire;
DROP PROCEDURE p_suppr_exemplaire;
DROP PROCEDURE p_suppr_utilisateur;
DROP FUNCTION f_compte_exemplaire;
DROP PROCEDURE p_new_emprunt_en_cours;
DROP TRIGGER t_suppr_emprunt_en_cours;
DROP TRIGGER t_majNbExemplaire_Livre;

PURGE RECYCLEBIN;
