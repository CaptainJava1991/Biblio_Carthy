create or replace procedure p_new_archive
    (p_idexemplaire number, p_idutilisateur number, p_dateemprunt date) as
begin
    insert into empruntarchive values (seq_archive.nextval,
                                       sysdate,
                                       p_idexemplaire,
                                       p_idutilisateur,
                                       p_dateemprunt);
    
end;
/
show errors

--to_date(CURRENT_DATE,'dd/mm/yyyy'),
--update exemplaire set status = 'DISPONIBLE' where idexemplaire = p_idexemplaire;


--vos tests ici
exec   p_new_archive( 1, 1, '10/11/2010');
select * from empruntarchive where idexemplaire=1;

rollback;

