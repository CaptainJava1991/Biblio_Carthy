/***************************************************************
Attribution des privil�ges sur les objets.Cr�ation des synonymes
MAJ: XH
Oracle9i Release 9.2.0.1.0 - Production
JServer Release 9.2.0.1.0 - Production
***************************************************************/
--Le propri�taire du sch�ma donne des droits aux utilisateurs
--granter les droits create public synonym � biblio
-- Cr�ez un user bibliothecaire


--Les tables
grant select on Utilisateur to public;
grant select on Employe to public;
grant select on Adherent to public;
grant select, update on Exemplaire to public;
grant select, insert, delete on EmpruntEncours to public;
grant insert on EmpruntArchive  to public;
grant select on AdherentGeneral to public;

--Les s�quences
grant select on seq_archive to public;

--Le propri�taire cre�e des PUBLIC SYNONYM sur les tables
CREATE OR REPLACE PUBLIC SYNONYM Utilisateur FOR biblio.Utilisateur;
CREATE OR REPLACE PUBLIC SYNONYM Employe FOR biblio.Employe;
CREATE OR REPLACE PUBLIC SYNONYM Adherent FOR biblio.Adherent;
CREATE OR REPLACE PUBLIC SYNONYM Exemplaire FOR biblio.Exemplaire;
CREATE OR REPLACE PUBLIC SYNONYM EmpruntEncours FOR biblio.EmpruntEncours;
CREATE OR REPLACE PUBLIC SYNONYM EmpruntArchive FOR biblio.EmpruntArchive;
CREATE OR REPLACE PUBLIC SYNONYM AdherentGeneral FOR biblio.AdherentGeneral;

--Le propri�taire cre�e des PUBLIC SYNONYM sur les sequences
CREATE OR REPLACE PUBLIC SYNONYM seq_archive FOR biblio.seq_archive;


