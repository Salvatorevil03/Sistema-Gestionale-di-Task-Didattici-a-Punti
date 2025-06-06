import mysql.connector
from faker import Faker
import random

# Connessione MySQL
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="3343351911Aa#",  # Cambia con la tua password
    database="taskdidattici"
)
cursor = conn.cursor()
fake = Faker()

# Configurazioni
NUM_DOCENTI = 3
NUM_CLASSI = 4
NUM_STUDENTI = 12
TASK_PER_CLASSE = 3
CONSEGNE_PER_STUDENTE = 2

# ID manuali
next_docente_id = 1
next_classe_codice = 1001
next_studente_id = 1
next_task_id = 1
next_consegna_id = 1

# Dati temporanei
docenti = []
classi = []
studenti = []
tasks = []
consegne = []

# 1. Genera docenti
print("\n-- DOCENTI --")
for _ in range(NUM_DOCENTI):
    d = {
        'id': next_docente_id,
        'nome': fake.first_name(),
        'cognome': fake.last_name(),
        'mail': fake.email(),
        'password': fake.password()
    }
    next_docente_id += 1
    docenti.append(d)
    print(d)

# 2. Genera classi
print("\n-- CLASSI --")
for _ in range(NUM_CLASSI):
    docente = random.choice(docenti)
    c = {
        'codice': next_classe_codice,
        'nome': fake.word().capitalize(),
        'numeroTask': 0,
        'docente_id': docente['id']
    }
    next_classe_codice += 1
    classi.append(c)
    print(c)

# 3. Genera studenti
print("\n-- STUDENTI --")
for _ in range(NUM_STUDENTI):
    classe = random.choice(classi)
    s = {
        'id': next_studente_id,
        'nome': fake.first_name(),
        'cognome': fake.last_name(),
        'mail': fake.email(),
        'password': fake.password(),
        'numTaskCompletati': 0,
        'numTaskValutati': 0,
        'punteggioTotale': 0,
        'classe_codice': classe['codice']
    }
    next_studente_id += 1
    studenti.append(s)
    print(s)

# 4. Genera task
print("\n-- TASK --")
for classe in classi:
    for _ in range(TASK_PER_CLASSE):
        t = {
            'id': next_task_id,
            'titolo': fake.sentence(nb_words=3),
            'descrizione': fake.text(max_nb_chars=100),
            'dataScadenza': fake.date_this_year().strftime('%Y-%m-%d'),
            'maxPunti': random.randint(5, 10),
            'classe_codice': classe['codice']
        }
        next_task_id += 1
        classe['numeroTask'] += 1
        tasks.append(t)
        print(t)

# 5. Genera consegne
print("\n-- CONSEGNE --")
for studente in studenti:
    stud_tasks = [t for t in tasks if t['classe_codice'] == studente['classe_codice']]
    sampled = random.sample(stud_tasks, min(len(stud_tasks), CONSEGNE_PER_STUDENTE))
    for task in sampled:
        punti = random.randint(0, task['maxPunti'])
        studente['numTaskCompletati'] += 1
        studente['numTaskValutati'] += 1
        studente['punteggioTotale'] += punti
        c = {
            'id': next_consegna_id,
            'punteggio': punti,
            'soluzione': fake.text(max_nb_chars=50),
            'task_id': task['id'],
            'studente_id': studente['id']
        }
        next_consegna_id += 1
        consegne.append(c)
        print(c)

# Conferma inserimento
proceed = input("\nVuoi inserire i dati nel database? (s/N): ")
if proceed.lower() == 's':
    cursor.execute("DELETE FROM consegne")
    cursor.execute("DELETE FROM task")
    cursor.execute("DELETE FROM studenti")
    cursor.execute("DELETE FROM classi")
    cursor.execute("DELETE FROM docenti")

    for d in docenti:
        cursor.execute("""
            INSERT INTO docenti (id, nome, cognome, mail, password) VALUES (%s, %s, %s, %s, %s)
        """, (d['id'], d['nome'], d['cognome'], d['mail'], d['password']))

    for c in classi:
        cursor.execute("""
            INSERT INTO classi (codice, nome, numeroTask, docente_id) VALUES (%s, %s, %s, %s)
        """, (c['codice'], c['nome'], c['numeroTask'], c['docente_id']))

    for s in studenti:
        cursor.execute("""
            INSERT INTO studenti (id, nome, cognome, mail, password, numTaskCompletati, numTaskValutati, punteggioTotaleOttenuto, classe_codice)
            VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)
        """, (s['id'], s['nome'], s['cognome'], s['mail'], s['password'],
              s['numTaskCompletati'], s['numTaskValutati'], s['punteggioTotale'], s['classe_codice']))

    for t in tasks:
        cursor.execute("""
            INSERT INTO task (id, titolo, descrizione, dataScadenza, maxPuntiAssegnabili, classe_codice)
            VALUES (%s, %s, %s, %s, %s, %s)
        """, (t['id'], t['titolo'], t['descrizione'], t['dataScadenza'], t['maxPunti'], t['classe_codice']))

    for c in consegne:
        cursor.execute("""
            INSERT INTO consegne (id, punteggio, soluzione, task_id, studente_id)
            VALUES (%s, %s, %s, %s, %s)
        """, (c['id'], c['punteggio'], c['soluzione'], c['task_id'], c['studente_id']))

    conn.commit()
    print("Dati inseriti con successo.")
else:
    print("Operazione annullata.")

conn.close()
