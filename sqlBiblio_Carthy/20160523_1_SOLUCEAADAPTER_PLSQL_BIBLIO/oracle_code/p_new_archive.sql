CREATE OR REPLACE
PROCEDURE p_new_archive(
    p_dateemprunt   IN empruntarchive.dateemprunt%type,
    p_idexemplaire  IN empruntarchive.idexemplaire%type,
    p_idutilisateur IN empruntarchive.idutilisateur%type)
AS
  chiffre NUMBER;
BEGIN
  --VERIF DE L EXISTENCE DE cet exemplaire
  SELECT COUNT(*)
  INTO chiffre
  FROM exemplaire
  WHERE idexemplaire=p_idexemplaire;
  IF chiffre        <1 THEN
    ROLLBACK;
    raise_application_error(-20015,'IDEXEMPLAIRE inconnu');
  END IF;
  --VERIF DE L EXISTENCE DE cet utilisateur
  SELECT COUNT(*)
  INTO chiffre
  FROM utilisateur
  WHERE idutilisateur=p_idutilisateur;
  IF chiffre         <1 THEN
    ROLLBACK;
    raise_application_error(-20016,'IDUTILISATEUR inconnu');
  END IF;
  INSERT
  INTO empruntarchive
    (
      idEmpruntArchive,
      dateemprunt,
      daterestitutioneff,
      idexemplaire,
      idutilisateur
    )
    VALUES
    (
      seq_archive.nextval,
      p_dateemprunt,
      sysdate,
      p_idexemplaire,
      p_idutilisateur
    );
END p_new_archive;
/
show errors
-- ############# Tests unitaires
SELECT * FROM empruntarchive;
EXECUTE p_new_archive(SYSDATE, 1, 1);
SELECT * FROM empruntarchive;
ROLLBACK;
