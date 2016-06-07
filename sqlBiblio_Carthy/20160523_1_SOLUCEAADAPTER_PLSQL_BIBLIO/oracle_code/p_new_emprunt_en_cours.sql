CREATE OR REPLACE
PROCEDURE p_new_emprunt_en_cours(
    p_idexemplaire  IN empruntencours.idexemplaire%TYPE ,
    p_idutilisateur IN empruntencours.idutilisateur%TYPE )
AS
  test_validite             INTEGER;
  exemplaire_non_disponible EXCEPTION;
  exemplaire_inconnu        EXCEPTION;
  utilisateur_inconnu       EXCEPTION;
  v_resultat exemplaire.status%TYPE;
BEGIN
  -- Test validité 'p_idExemplaire'
  SELECT COUNT(*)
  INTO test_validite
  FROM exemplaire
  WHERE idExemplaire = p_idExemplaire;
  IF test_validite   = 0 THEN
    RAISE exemplaire_inconnu;
  END IF;
  -- Test validité 'p_idUtilisateur'
  SELECT COUNT(*)
  INTO test_validite
  FROM utilisateur
  WHERE idutilisateur = p_idutilisateur;
  IF test_validite    = 0 THEN
    RAISE utilisateur_inconnu;
  END IF;
  -- Insertion du nouvel 'empruntencours'
  INSERT
  INTO empruntencours VALUES
    (
      p_idexemplaire,
      p_idutilisateur,
      SYSDATE
    );
  -- Vérification du status de 'empruntencours'
  SELECT status
  INTO v_resultat
  FROM exemplaire
  WHERE idexemplaire = p_idexemplaire;
  IF v_resultat      = 'DISPONIBLE' THEN
    -- Si 'status' de 'exemplaire' disponible, update 'status' de 'exemplaire'
    UPDATE exemplaire
    SET status         = 'PRETE'
    WHERE idexemplaire = p_idexemplaire;
  ELSE
    -- Sinon, levée de l'exception 'exemplaire_non_disponible' et rollback
    RAISE exemplaire_non_disponible;
  END IF;
EXCEPTION
WHEN exemplaire_inconnu THEN
  ROLLBACK;
  RAISE_APPLICATION_ERROR(-20015, 'Exemplaire n° ' || p_idexemplaire || ' inconnu', true);
WHEN utilisateur_inconnu THEN
  ROLLBACK;
  RAISE_APPLICATION_ERROR(-20016, 'Utilisateur n° ' || p_idutilisateur || ' inconnu', true);
WHEN exemplaire_non_disponible THEN
  ROLLBACK;
  raise_application_error(-20017, 'Exemplaire n° ' || p_idexemplaire || ' ne peut être emprunté. Status: ' || v_resultat, TRUE);
END p_new_emprunt_en_cours;
/
show errors
-- ############# Tests unitaires
SELECT * FROM empruntencours;
EXECUTE p_new_emprunt_en_cours(4, 1);
EXECUTE p_new_emprunt_en_cours(5, 1);
SELECT * FROM empruntencours;
--EXECUTE p_new_emprunt(6, 2);
ROLLBACK;