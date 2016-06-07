CREATE OR REPLACE TRIGGER t_majnbexemplaire_livre AFTER
  INSERT OR
  UPDATE OF status ON exemplaire FOR EACH ROW DECLARE v_nbexemplaire livre.nbexemplaire%TYPE := 0;
  BEGIN
    -- Récupération du nbExemplaire
    SELECT nbexemplaire INTO v_nbexemplaire FROM livre WHERE isbn = :NEW.isbn;
    -- Si nbExemplaire à null, vaut 0
    IF v_nbexemplaire IS NULL THEN v_nbexemplaire  := 0;
    END IF;
    -- Modification de nbExemplaire
    IF UPDATING AND :NEW.status = 'SUPPRIME' THEN
      UPDATE livre
      SET nbexemplaire = v_nbexemplaire - 1 WHERE isbn = :NEW.isbn;
    END IF;
    IF INSERTING THEN
      UPDATE livre
      SET nbexemplaire = v_nbexemplaire + 1 WHERE isbn = :NEW.isbn;
    END IF;
  END;
  /
  show errors
  -- ############# Tests unitaires
  SELECT * FROM livre;
  SELECT * FROM exemplaire;
  EXECUTE p_new_exemplaire('1520068789', SYSDATE);
  EXECUTE p_suppr_exemplaire(3);
  DELETE FROM empruntencours WHERE idexemplaire = 1 AND idutilisateur = 1;
  SELECT * FROM livre;
  SELECT * FROM exemplaire;
  ROLLBACK;
  
  