
---

# BDCC AI Agent – Agent IA avec RAG

## Description

BDCC AI Agent est une application d’agent intelligent développée avec **Spring Boot** et **Angular**, intégrant **Spring AI** pour le traitement du langage naturel.
L’agent utilise la méthode **RAG (Retrieval-Augmented Generation)** pour fournir des réponses enrichies en s’appuyant sur des documents indexés.

---

## Architecture

### Backend (Spring Boot)

* **Framework :** Spring Boot 3.5.4
* **IA :** Spring AI avec OpenAI (GPT-4.1)
* **Port :** 8081
* **Fonctionnalités :**

    * Agent IA avec mémoire de conversation
    * RAG pour recherche dans des documents PDF
    * Outils personnalisés pour l’agent
    * API REST avec streaming de réponses

### Frontend (Angular)

* **Framework :** Angular 21
* **UI :** Bootstrap 5.3.8
* **Port :** 4200 (mode développement)
* **Fonctionnalités :**

    * Interface de chat interactive
    * Affichage des réponses en temps réel (streaming)
    * Support du Markdown pour formater les réponses

---

## Installation et Configuration

### Prérequis

* Java 21 ou supérieur
* Maven 3.6+
* Node.js 18+ et npm
* Clé API OpenAI

### Configuration Backend

1. Éditez `src/main/resources/application.properties` et configurez votre clé API OpenAI :

```properties
spring.ai.openai.api-key=VOTRE_CLE_API_OPENAI
spring.ai.openai.chat.options.model=gpt-4.1
server.port=8081
```

2. Préparez les documents PDF :
   Placez vos fichiers dans `src/main/resources/pdfs/` (par défaut `cv.pdf` est indexé).

3. Compiler et lancer le backend :

```bash
mvn clean install
mvn spring-boot:run
```

Le backend sera accessible sur `http://localhost:8081`.

### Configuration Frontend

1. Installer les dépendances :

```bash
cd agent-ui
npm install
```

2. Lancer l’application Angular :

```bash
npm start
```

Le frontend sera accessible sur `http://localhost:4200`.

---

## Structure du Projet

```
bdcc-ai-agent/
├── src/main/java/org/example/bdccaiagent/
│   ├── agents/         -> AIAgent.java (agent principal)
│   ├── controllers/    -> AgentController.java (REST API)
│   ├── rag/            -> RagDocumentIndexor.java (indexation PDF)
│   ├── tools/          -> AgentTools.java (outils personnalisés)
│   └── BdccAiAgentApplication.java
├── src/main/resources/
│   ├── application.properties
│   ├── pdfs/           -> Documents PDF à indexer
│   └── store/          -> Vector store (store.json)
├── agent-ui/           -> Frontend Angular
│   ├── src/app/
│   │   ├── app.ts
│   │   └── chat/
│   │       ├── chat.ts
│   │       └── chat.html
│   └── main.ts
└── pom.xml             -> Configuration Maven
```

---

## Fonctionnalités

### Agent IA

* Mémoire de conversation pour conserver le contexte
* RAG (QuestionAnswerAdvisor) pour enrichir les réponses via les documents PDF
* Journalisation des interactions pour le débogage

### Indexation de Documents (RAG)

* **Format :** PDF
* **Dossier :** `src/main/resources/pdfs/`
* **Stockage :** Vector Store (`src/main/resources/store/store.json`)
* **Premier lancement :** Création automatique de l’index
* **Lancements suivants :** Chargement du vector store existant

### API REST

* **Endpoint :** `/askAgent`
* **Méthode :** GET
* **Paramètre :** `query` (question de l’utilisateur)
* **Réponse :** Flux de texte en streaming (`text/plain`)
* **CORS :** Activé pour toutes les origines

---

## Utilisation

1. Démarrer le backend :

```bash
mvn spring-boot:run
```

2. Démarrer le frontend (dans un autre terminal) :

```bash
cd agent-ui
npm start
```

3. Ouvrir le navigateur et accéder à : `http://localhost:4200`
4. Saisir votre question dans le champ de chat et cliquer sur **Send**
5. La réponse s’affichera en temps réel.

---


