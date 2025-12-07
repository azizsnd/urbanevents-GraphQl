# 🌇 City Services Platform - Plateforme de Services Urbains Intelligents

**Projet d'Ingénierie 3ème année GINF** - *Service Oriented Computing*

## 📋 Description du Projet

Développement d'une plateforme de services urbains interopérables pour une ville intelligente, intégrant différents protocoles de communication (SOAP, REST, GraphQL, gRPC).

## 🎯 Objectifs

- Implémenter 4 services distincts avec des protocoles différents
- Développer une architecture microservices
- Assurer l'interopérabilité entre services hétérogènes
- Fournir une interface client web unifiée


## 🛠️ Technologies Utilisées

- **Backend** : Spring Boot 3.x, Java 17
- **Base de données** : MongoDB
- **Conteneurisation** : Docker, Docker Compose
- **API** : REST, GraphQL, SOAP, gRPC
- **Outils** : Maven, IntelliJ IDEA, Postman

## 📁 Structure du Projet
city-services-platform/

├── urbanevents-graphql/ # Service GraphQL - Événements urbains

├── mobility-rest/ # Service REST - Mobilité intelligente

├── airquality-soap/ # Service SOAP - Qualité de l'air

├── emergency-grpc/ # Service gRPC - Urgences

├── api-gateway/ # API Gateway (Spring Cloud Gateway)

└── web-client/ # Client web (React/Angular)


## 🚀 Installation et Démarrage

### Prérequis

- Java 17 ou supérieur
- Maven 3.8+
- Docker et Docker Compose
- MongoDB (optionnel - Docker fourni)

### 1. Cloner le projet

```bash
git clone https://github.com/azizsnd/urbanevents-GraphQl.git
cd city-services-platform
```
