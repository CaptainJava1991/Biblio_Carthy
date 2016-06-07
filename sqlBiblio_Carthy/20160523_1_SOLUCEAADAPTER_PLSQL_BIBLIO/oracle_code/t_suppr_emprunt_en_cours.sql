CREATE OR REPLACE TRIGGER t_suppr_emprunt_en_cours BEFORE
  DELETE ON empruntencours FOR EACH ROW BEGIN
  -- Update 'status' de 'exemplaire'
  UPDATE exemplaire
  SET status         = 'DISPONIBLE'
  WHERE idExemplaire = :old.idExemplaire;
  -- Conversion en 'empruntarchive'
  p_new_archive(:OLD.dateEmprunt, :OLD.idExemplaire, :OLD.idUtilisateur);
END;
/
show errors
-- ############# Tests unitaires
SELECT *
FROM exemplaire
WHERE idexemplaire = 1;
SELECT * FROM empruntencours;
DELETE FROM empruntencours WHERE idexemplaire = 1 AND idutilisateur = 1;
SELECT * FROM exemplaire WHERE idexemplaire = 1;
SELECT * FROM empruntencours;
ROLLBACK;