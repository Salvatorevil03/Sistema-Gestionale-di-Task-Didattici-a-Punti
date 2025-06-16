MODIFICHE:

Aggiunto package exceptions con eccezioni:

ClassCreationException.java
ClassEnrollmentException.java
DBException.java
DescriptionLengthException.java
FormattedDateException.java
InvalidDateException.java
MailAlreadyUsedException.java                                                                                                   
MailNotValidException.java
RoleNotValidException.java
ScoreEvaluationException.java
SistemaNotificheException.java
StudentException.java
SubmissionAlreadyEvaluatedException.java
SubmissionEvaluationException.java
SubmissionException.java
TaskCreationException.java                                                                                                      
TitleLengthException.java
UnassignableTaskScoreException.java
_________________________________________________________________________________________

I DAO (Non DBConnectionManager) sollevano l'eccezione DBException invece che ritornare -1
Il relativo livello Entity fa un catch di DBException e fa un 'throw new eccezioneSpecifica'
_________________________________________________________________________________________

In Controller il valore di ritorno è stato sostituito da int a void nei metodi:

registrazione
creaClasse
iscrizione
creaTask
valutaConsegna
consegnaSoluzione

L'unico metodo che ha mantenuto il valore di ritorno int è getClasseID per ovvi motivi
_________________________________________________________________________________________

In Entity il valore di ritorno è stato sostituito da int a void nei metodi:

EntityTask : 
	valutaConsegna, consegnaSoluzione

EntityDocente: 
	creaClasse, inserisciSuDB

EntityClasse: 
	iscrizione, creaTask

EntityStudente: 
	incrementaNumTaskCompletati, inserisciSuDB

EntityConsegna: 
	aggiornaStatisticheAndInviaMail, creaConsegna, 
	updateNumTaskCompletati, impostaPunteggio
_________________________________________________________________________________________

In Database il valore di ritorno è stato sostituito da int a void nei metodi:

DBClasse:
	creaTask, salvaNumTaskSuDB, iscrizioneSuDB

DBConsegna:
	salvaInDB, inserisciSuDB

DBDocente:
	inserisciSuDB

DBStudente:
	inserisciSuDB, salvaTaskCompletatiInDB, salvaInDB

DBTask:
	nessuna

DBUtente:
	nessuna

_________________________________________________________________________________________

In SistemaDiNotifiche il valore di ritorno è stato sostituito da int a void nei metodi:

inviaCreazioneTask
inviaValutazioneTask

Questi metodi invece che ritornare il valore -1 sollevano l'eccezione SistemaNotificheException

Inoltre è stato fatto un refactoring sui metodi inviaValutazioneTask e initMail creando il metodo getGmailSmtpProperties visto che entrambi eseguivano le stesse operazioni
_________________________________________________________________________________________

In Boundary queste sono le eccezioni sollevate da ogni GUI dopo la modifica:

GUIClasseDocente: 
	FormattedDateException, InvalidDateException, TitleLengthException,
	DescriptionLengthException, UnassignableTaskScoreException, 
	TaskCreationException exception

GUIClassificaPunteggio:
	Nessuna eccezione del nuovo package

GUIClassificaTask:
	Nessuna eccezione del nuovo package

GUIConsegna:
	SubmissionEvaluationException, ScoreEvaluationException,
	SubmissionAlreadyEvaluatedException, StudentException,
	SistemaNotificheException

GUIConsegnaStudente:
	SubmissionException, StudentException

GUIDocente:
	ClassCreationException

GUIIscrizioneClasse:
	ClassEnrollmentException

GUIListaStudentiClasse:
	Nessuna eccezione del nuovo package

GUIListaStudentiPiattaforma:
	ClassEnrollmentException

GUILogin:
	Nessuna eccezione del nuovo package

GUIRegistrazione:
	RoleNotValidException, MailAlreadyUsedException, MailNotValidException

GUIStudente:
	Nessuna eccezione del nuovo package




