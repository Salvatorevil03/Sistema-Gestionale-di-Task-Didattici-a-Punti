-- Trigger per verificare che la mail dello studente non sia già presente nei docenti
DELIMITER //

CREATE TRIGGER before_insert_studente_check_mail
BEFORE INSERT ON taskdidattici.studenti
FOR EACH ROW
BEGIN
    DECLARE mail_exists INT DEFAULT 0;
    
    -- Verifica se la mail esiste già nella tabella docenti
    SELECT COUNT(*) INTO mail_exists 
    FROM taskdidattici.docenti
    WHERE docenti.mail = NEW.mail;
    
    -- Se la mail esiste già nei docenti, genera un errore
    IF mail_exists > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Errore: La mail è già utilizzata da un docente';
    END IF;
END//

DELIMITER ;

-- Trigger per verificare che la mail dello studente non sia già presente nei docenti
DELIMITER //

CREATE TRIGGER before_insert_docente_check_mail
BEFORE INSERT ON taskdidattici.docenti
FOR EACH ROW
BEGIN
    DECLARE mail_exists INT DEFAULT 0;
    
    -- Verifica se la mail esiste già nella tabella docenti
    SELECT COUNT(*) INTO mail_exists 
    FROM taskdidattici.studenti
    WHERE studenti.mail = NEW.mail;
    
    -- Se la mail esiste già nei docenti, genera un errore
    IF mail_exists > 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Errore: La mail è già utilizzata da un docente';
    END IF;
END//

DELIMITER ;