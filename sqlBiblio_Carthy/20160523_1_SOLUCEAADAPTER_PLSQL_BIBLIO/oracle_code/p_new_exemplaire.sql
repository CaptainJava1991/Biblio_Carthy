CREATE OR REPLACE
PROCEDURE p_new_exemplaire(
    p_isbn      IN exemplaire.isbn%TYPE ,
    p_dateachat IN exemplaire.dateachat%TYPE )
AS
  test_validite INTEGER;
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
  -- Insertion de 'exemplaire'
  INSERT
  INTO exemplaire VALUES
    (
      seq_exemplaire.nextval,
      p_dateachat,
	  'DISPONIBLE',
	  p_isbn
    );
END p_new_exemplaire;
/
show errors
-- ############# Tests unitaires
SELECT * FROM exemplaire;
--EXECUTE p_new_exemplaire('978-2264024848', SYSDATE);
--EXECUTE p_new_exemplaire('978-2253057840', SYSDATE);
EXECUTE p_new_exemplaire('1520068789', SYSDATE);
EXECUTE p_new_exemplaire('1520068789', SYSDATE);

SELECT * FROM exemplaire;
ROLLBACK;