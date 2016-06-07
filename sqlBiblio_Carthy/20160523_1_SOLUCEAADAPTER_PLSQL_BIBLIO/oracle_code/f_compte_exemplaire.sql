CREATE OR REPLACE
  FUNCTION f_compte_exemplaire(
      p_isbn IN exemplaire.isbn%TYPE)
    RETURN NUMBER
  AS
    test_validite INTEGER;
    v_compteur    INTEGER;
  BEGIN
    -- Test validité 'p_isbn'
    SELECT COUNT(*)
    INTO test_validite
    FROM livre
    WHERE isbn       = p_isbn;
    IF test_validite = 0 THEN
      ROLLBACK;
      RAISE_APPLICATION_ERROR(-20014, 'Livre ISBN n° ' || p_isbn || ' inconnu');
    END IF;
    -- Recupération du nombre de tuples 'exemplaire' ayant un status différent de 'supprime'
    SELECT COUNT(*)
    INTO v_compteur
    FROM exemplaire
    WHERE isbn  = p_isbn
    AND status != 'SUPPRIME';
    -- Renvoi de la valeur
    return v_compteur;
  END f_compte_exemplaire;
  /
  show errors
  -- ############# Tests unitaires
  select * from exemplaire where isbn = '1520068789';
  SELECT '1520068789' AS ISBN, f_compte_exemplaire('1520068789') AS "Nb Exemplaires non supprimés"
  FROM dual;