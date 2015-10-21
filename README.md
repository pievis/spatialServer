Ho messo su 2 progetti java, giusto per aprire il git.
Ovviamente ci conviene lavorare da subito in java 7, per essere più compatibili con Android.

Come dicevamo oggi direi di organizzarci così:

1 facciamo funzionare Server e Client con i relativi mock

2 facciamo funzionare Server e Client(POJO,PC) tra loro

3 portiamo il client su android

4 Applicazione Android che si appggi al client e relativa logica

5 Integrazione di Protelis 

6 Modifica applicazione per lavorare con Proelis

7 Testing 

8 Esetensioni 

9 Testing

NOTE
Il punto 5 e 6 se sono stati implementati bene prima (interfaces) non dovrebbero essere troppo complicati, il porting di proelis su Android è già stato fatto.
Nel caso si potrebbe valutare di integrare proelis già nella versione PC e fare il porting dopo. Vedremo come si delineano le cose.
Il punto 8 potrebbe essere come dicevo oggi, la possibilità di programmare direttamente i devices mettendo il codice sul server e facendoli collegare all'avvio.



# README #

This README would normally document whatever steps are necessary to get your application up and running.

### What is this repository for? ###

* Quick summary
* Version
* [Learn Markdown](https://bitbucket.org/tutorials/markdowndemo)

### How do I get set up? ###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repo owner or admin
* Other community or team contact