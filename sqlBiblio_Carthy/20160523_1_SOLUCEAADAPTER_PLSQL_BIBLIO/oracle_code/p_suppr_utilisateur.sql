CREATE OR REPLACE
PROCEDURE p_suppr_utilisateur(
    p_idUtilisateur IN utilisateur.idutilisateur%TYPE)
AS
  test_validite INTEGER;
BEGIN
  -- Test validité 'p_idUtilisateur'
  SELECT COUNT(*)
  INTO test_validite
  FROM utilisateur
  WHERE idUtilisateur = p_idUtilisateur;
  IF test_validite    = 0 THEN
    ROLLBACK;
    RAISE_APPLICATION_ERROR(-20016, 'Utilisateur n° ' || p_idUtilisateur || ' inconnu');
  END IF;
  -- Suppression des emprunts de l'utilisateur
  DELETE
  FROM empruntencours
  WHERE idutilisateur = p_idutilisateur;
  DELETE FROM empruntarchive WHERE idutilisateur = p_idutilisateur;
  -- Suppression des dépendances
  DELETE
  FROM adherent
  WHERE idutilisateur = p_idutilisateur;
  DELETE FROM employe WHERE idutilisateur = p_idutilisateur;
  -- Suppression de l'utilisateur
  DELETE
  FROM utilisateur
  where idutilisateur = p_idutilisateur;
END p_suppr_utilisateur;
/
show errors
-- ############# Tests unitaires
SELECT * FROM utilisateur;
EXECUTE p_suppr_utilisateur(1);
execute p_suppr_utilisateur(2);
SELECT * FROM utilisateur;
ROLLBACK;