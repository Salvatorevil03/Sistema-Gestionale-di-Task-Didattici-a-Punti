# Docenti

INSERT INTO docenti VALUES (1, 'Mario', 'Rossi', 'm.rossi1@unina.it', 'mrossi');
INSERT INTO docenti VALUES (2, 'Giovanna', 'Verdi', 'g.verdi1@unina.it', 'gverdi');

# Classe 1 e Task, Docente 1
INSERT INTO classi VALUES (1, 'Ingegneria del Software',2  , 1);

INSERT INTO task VALUES (1, ' ', ' ', '2025-05-21', 30, 1);
INSERT INTO task VALUES (2, ' ', ' ', '2025-05-25', 25, 1);

# Classe 2 No Task, Docente 1
INSERT INTO classi VALUES (2, 'Software Architecture Design',0  , 1);

# Classe 3 e Task, Docente 2
INSERT INTO classi VALUES (3, 'Basi di Dati',2  , 2);

INSERT INTO task VALUES (3, ' ', ' ', '2025-05-24', 20, 3);
INSERT INTO task VALUES (4, ' ', ' ', '2025-06-30', 30, 3);

# Mario Rossi No Classe 
INSERT INTO studenti VALUES (1, 'Mario', 'Rossi', 'm.rossi1@studenti.unina.it', 'mrossi', 0, 0, 0, NULL);

# Ciro Esposito Classe 1
INSERT INTO studenti VALUES (2, 'Ciro', 'Esposito', 'c.esposito1@studenti.unina.it', 'cesposito', 2, 1, 28, 1);

INSERT INTO consegne VALUES (28, ' ', 1, 2);
INSERT INTO consegne VALUES (NULL, ' ', 2, 2); # Non è stata valutata

# Maria Esposito Classe 1
INSERT INTO studenti VALUES (3, 'Maria', 'Esposito', 'm.esposito1@studenti.unina.it', 'mesposito', 2, 2, 45, 1);

INSERT INTO consegne VALUES (30, ' ', 1, 3);
INSERT INTO consegne VALUES (15, ' ', 2, 3);

# Chiara Rossi Classe 3

INSERT INTO studenti VALUES (4, 'Chiara', 'Rossi', 'c.rossi1@studenti.unina.it', 'crossi', 1, 1, 20, 3);

INSERT INTO consegne VALUES (20,'',3,4);
