# Chatbot Telegram avec Spring Boot et OpenAI

## Description

Ce projet est un chatbot Telegram développé avec Spring Boot. Il utilise l'API OpenAI pour répondre aux questions des utilisateurs en fonction du contexte fourni.

Le bot affiche une action de saisie (typing) avant d’envoyer la réponse générée par l’IA.

# Fonctionnalités

Réception et envoi de messages via Telegram Bot API.

Interaction avec OpenAI Chat API pour générer des réponses.

Gestion des erreurs : si OpenAI renvoie une erreur (ex. quota épuisé), le bot répond par "JE NE SAIS PAS".

# Prérequis

Java 17 ou supérieur

Maven 3.8+

Compte Telegram et Token Bot

Compte OpenAI et clé API

# Gestion des erreurs

HTTP 429 / Insufficient Quota :
Si le crédit OpenAI est épuisé, toutes les requêtes échouent et le bot renvoie "JE NE SAIS PAS".


# Screen

![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)

![img_3.png](img_3.png)

![img_5.png](img_5.png)

![img_6.png](img_6.png)