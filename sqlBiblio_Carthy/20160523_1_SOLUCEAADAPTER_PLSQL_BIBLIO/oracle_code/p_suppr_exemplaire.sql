CREATE OR REPLACE
PROCEDURE p_suppr_exemplaire(
    p_idExemplaire IN exemplaire.idExemplaire%TYPE)
AS
  test_validite INTEGER;
BEGIN
  -- Test validité 'p_idExemplaire'
  SELECT COUNT(*)
  INTO test_validite
  FROM exemplaire
  WHERE idExemplaire = p_idExemplaire;
  IF test_validite   = 0 THEN
    ROLLBACK;
    RAISE_APPLICATION_ERROR(-20015, 'Exemplaire n° ' || p_idExemplaire || ' inconnu');
  END IF;
  -- Suppression des 'empruntencours' et 'empruntarchive'
  DELETE
  FROM empruntencours
  WHERE idexemplaire = p_idexemplaire;
  DELETE FROM empruntarchive WHERE idexemplaire = p_idexemplaire;
  -- Update du 'status de 'exemplaire' a 'supprime'
  UPDATE exemplaire
  SET status         = 'SUPPRIME'
  WHERE idexemplaire = p_idexemplaire;
END p_suppr_exemplaire;
/
show errors
-- ############# Tests unitaires
SELECT *
FROM exemplaire
WHERE idexemplaire = 1
OR idexemplaire    = 4;
SELECT * FROM empruntencours WHERE idexemplaire = 1 OR idexemplaire = 4;
SELECT * FROM empruntarchive WHERE idexemplaire = 1 OR idexemplaire = 4;
EXECUTE p_suppr_exemplaire(1);
EXECUTE p_suppr_exemplaire(4);
SELECT * FROM exemplaire WHERE idexemplaire = 1 OR idexemplaire = 4;
SELECT * FROM empruntencours WHERE idexemplaire = 1 OR idexemplaire = 4;
SELECT * FROM empruntarchive WHERE idexemplaire = 1 OR idexemplaire = 4;
ROLLBACK;