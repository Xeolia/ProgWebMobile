# Remider

Hey ! Vous avez envie d'organiser un évènement, une sortie avec vos amis, et déjà vous redoutez les multiples échanges de mails, sms, messages Facebook et autres pour décider et se mettre d'accord sur où, quand et à quelle heure on se retrouve...

Fini le cauchemar avec **Remider**, l'app qui vise à améliorer l'organisation de vos meetings :) 
L'app vous permet d'inviter vos amis, de déterminer le type d'événement : réunion, repas, soirée, cinéma, sport, week-end ou autre, et de suggérer la date.

## Requirements

Pour construire et faire fonctionner l'application vous aurez besoin :

- [JDK 14](https://www.oracle.com/java/technologies/javase/jdk14-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
- [Vue Js](https://fr.vuejs.org/v2/guide/installation.html)
- [Xampp](https://www.apachefriends.org/fr/index.html)


## Installation

Pour pouvoir lancer correctement notre application il vous faudra une base de donnée en local avec les identifiants ci dessous :

```
TYPEBDD = MYSQL
HostBDD = db
PortBDD = 8085
DBName = reminder
LoginBDD = user
PasswordBDD= WEBEsiea0!
```


## Running the application locally

Pour exécuter l'application Back Spring Boot vous devez lancer la classe `application.properties` sur l'IDE, en utilisant la commande suivante:

```shell
mvn spring-boot:run
```
Pour exécuter l'application front en Vue Js il vous faut tout d'abord les packages:
```shell
npm install -g @vue/cli
```
### Content


## Authentification

L'utilisateur doit fournir plusieurs champs à l'inscription, tel que son nom, son prénom, un mot de passe choisi, un pseudo, également il devra fournir des éléments sur sa position géographique

## Connection
### Screenshots


full-stack Architecture:
![image](https://github.com/Xeolia/ProgWebMobile/blob/develop/projet-web-mobile/back/src/main/ressources/images/full-stack.PNG)
