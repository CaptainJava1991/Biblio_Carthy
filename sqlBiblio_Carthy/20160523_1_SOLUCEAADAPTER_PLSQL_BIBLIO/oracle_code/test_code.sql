SET serveroutput ON
DROP TABLE resultat;
CREATE TABLE resultat (code VARCHAR2(15), lb_resultat VARCHAR2(55), vl_resultat INTEGER);

@p_multiple_loop_test
@p_multiple_while_test
@p_multiple_for_test