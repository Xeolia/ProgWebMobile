Reminder
=================

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/logoRemid.png" width="200">

Hey ! Vous avez envie d'organiser un évènement, une sortie avec vos amis, et déjà vous redoutez les multiples échanges de mails, sms, messages Facebook et autres pour décider et se mettre d'accord sur où, quand et à quelle heure on se retrouve...

Fini le cauchemar avec **Reminder**, l'app qui vise à améliorer l'organisation de vos meetings :) 
L'app vous permet d'inviter vos amis, de déterminer le type d'événement : réunion, repas, soirée, cinéma, sport, week-end ou autre, et de suggérer la date.
Pour vous accompagner partout le site est responsive vous pouvez donc l'utiliser depuis votre ordinateur et également l'avoir sur votre smartphone.

Table of contents
=================

<!--ts-->
- [Reminder](#reminder)
- [Table of contents](#table-of-contents)
  -   [Requirements](#requirements)
  -   [Installation](#installation)
  -   [Running the application locally](#running-the-application-locally)
- [Architecture](#architecture)
  - [full-stack Architecture](#full-stack-architecture)
  - [MCD Remider](#mcd-remider)
    * [API Authentification](#api-authentification)
    * [API connexion utilisateur](#api-connexion-utilisateur)
    * [API création sondage](#api-création-sondage)
    * [API vote sondage](#api-vote-sondage)
    + [Screenshots](#screenshots)
 - [Author](#author)
<!--te-->
    

Requirements
============

Pour construire et faire fonctionner l'application vous aurez besoin :

- [JDK 14](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Vue Js](https://fr.vuejs.org/v2/guide/installation.html)
- [Xampp](https://www.apachefriends.org/fr/index.html)


Installation
============

Pour pouvoir lancer correctement notre application il vous faudra une base de donnée en local avec les identifiants ci dessous :

```
TYPEBDD = MYSQL
HostBDD = db
PortBDD = 8085
DBName = reminder
LoginBDD = user
PasswordBDD= WEBEsiea0!
```


Running the application locally
============
Dans la partie back il faut regarder si le dossier ressource et bien configurer comme un ressource root. Pour exécuter l'application Back Spring Boot vous devez lancer la classe `application.properties` sur l'IDE en spécifiant  `application.properties` comme étant l'application Spring Boot à executer. 
Puis lancer Maven en utilisant la commande suivante:

```shell
mvn spring-boot:run
```
Pour exécuter l'application front en Vue Js il vous faut tout d'abord se déplacer dans le dossier front et effectuer les commandes ci-dessous:

```shell
npm install
```

```shell
npm run serve
```


Architecture
============


### full-stack Architecture

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/full-stack.PNG" width="900">



### MCD Remider

![image](https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/mcd.PNG)

### API Authentification

 API de création d'utilisateur

Méthode : POST  
Paramètres d'entrée :
  - PK: user_id
  - String : nom
  - String : email
  - String : login
  - String : mot de passe
  - String : pays
  - String : code_postal
  - String : ville
  
L'utilisateur doit fournir plusieurs champs à l'inscription, tel que son nom, son prénom, un mot de passe choisi, un pseudo, également avant la création de sondage il pourra fournir des éléments sur sa position géographique. Ces éléments sont ensuite intégrés à la base de données.

### API connexion utilisateur

Méthode : POST  
Paramètres d'entrée :
- String : login
- String : mot de passe

Sortie :
- Token : token d'identification utilisateur

L'utilisateur devra saisir les informations prélablement saisies lors de la création de son compte.
En se connectant à l'application, il générera un token d'authentification qui sera utilisé lors de l'envoi d'une proposition de rendez-vous. Ainsi il sera également utilisé  lors du vote pour participer à un sondage.

## API création sondage
Méthode : POST  
Paramètres d'entrée :
- PK: sondage_id
- FK1: user_id
- String : date
- String : description
- String : lieu
- String : ville
- String : name

Sortie :
- String : message de confirmation

## API vote sondage
Méthode : POST  
Paramètres d'entrée :
- FK1: sondage_id
- FK2: user_id
- PK: vote_id
- String : status


### Screenshots

**Création User**

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/createCompte.png" width="900">

**Validation de la création du compte**

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/validationCreateCompte.png" width="900">


**Login**

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/login.png" width="900">


**Création d'un sondage**

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/creerSondage.png" width="900">


**Ajout du lieu au sondage**

Possibilité d'ajouter un ou plusieurs lieux au sondage :

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/addLieu.png" width="900">


**Ajouter une date au sondage**

Possibilité d'ajouter une ou plusieurs dates au sondage :

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/addDate.png" width="900">


**Ajouter une personne au sondage**

Ajout de participant seulement qui sont inscrits sur le site et donc présents dans la liste dropdown

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/addUser.png" width="900">

**Voter sur le sondage**

Nous avons la possibilité de voter

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/vote.png" width="900">


**Validation du vote sur le sondage**

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/validationVote.png" width="900">


**On ne peut voter qu'une fois pour le lieu et qu'une fois pour la date**

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/validation1Time.png" width="900">


**Accès aux votes**

Chaque user a accès à ses sondages et uniquement les siens

Ici nous sommes connecté sur le compte Test

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/otherUserValidationVote.png" width="900">

Nous sommes connecté sur le Compte Tanguy

<img src="https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/resources/images/validationCompteTanguy.png" width="900">



Author
============

* [Tanguy Benard](https://github.com/tbenard78)
* [Alexia Serrier](https://github.com/Xeolia)

