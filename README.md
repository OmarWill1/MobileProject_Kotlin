# Projet E-commerce Mobile avec Kotlin et Jetpack Compose

Ce dépôt contient le code source d'une application e-commerce Android moderne, développée en Kotlin. L'application a été conçue en suivant les meilleures pratiques de développement Android, en utilisant les composants Jetpack et une architecture MVVM robuste.

##  Captures d'écran

![Uploading kotlin project .png…]()





##  Fonctionnalités

-   **Catalogue de produits dynamique** : Les produits sont chargés depuis une API externe (`FakeStoreAPI`).
-   **Authentification utilisateur** : Inscription et connexion avec persistance locale des données du compte.
-   **Gestion du Panier** : Ajout, suppression et mise à jour de la quantité des produits dans un panier persistant.
-   **Liste de favoris** : Possibilité pour l'utilisateur de sauvegarder ses produits préférés.
-   **Recherche de produits** : Filtrage en temps réel du catalogue sur la page boutique.
-   **Page de Profil** : Affichage des informations de l'utilisateur connecté avec option de déconnexion.
-   **Notifications Android** :
    -   Notification pour confirmer l'ajout d'un produit au panier.
    -   Notification pour confirmer une commande après le "Checkout".
-   **Service en arrière-plan** : Un service de premier plan surveille l'inactivité de l'utilisateur et le déconnecte automatiquement après 5 minutes pour des raisons de sécurité.

## Architecture et Contraintes Techniques

Ce projet a été développé en respectant des standards de qualité et une architecture moderne pour garantir sa maintenabilité et sa robustesse.

### 1. **Architecture MVVM + Repository**L'application suit une architecture MVVM (Model-View-ViewModel) stricte :
-   **View (UI)** : Entièrement construite avec **Jetpack Compose**, de manière déclarative et réactive.
-   **ViewModel** : `StoreViewModel` (pour les données locales) et `ProductViewModel` (pour les données distantes) servent de pont entre l'UI et les données.
-   **Model (Repository)** : Le pattern Repository (`StoreRepository`, `ProductRepository`) centralise l'accès aux données et masque leur provenance (base de données ou réseau).

### 2. **Sources de Données**
-   **Room** : Utilisé pour la persistance des données locales (comptes utilisateurs, panier, favoris). Les requêtes de lecture utilisent `Flow` pour une mise à jour automatique de l'UI.
-   **Retrofit** : Utilisé pour la communication avec la `FakeStoreAPI` afin de récupérer le catalogue de produits.

### 3. **Gestion des Chaînes de Caractères**
-   Toutes les chaînes de caractères visibles par l'utilisateur sont externalisées dans le fichier `res/values/strings.xml`, ce qui facilite la maintenance et prépare l'application pour une éventuelle traduction.

### 4. **Qualité du Code**
-   Le code est organisé en packages logiques (`service`, `notification`, `dao`, `schema`, etc.) pour une meilleure lisibilité.
-   Les principes de "code propre" (clean code) ont été appliqués, avec des noms de variables et de fonctions explicites et des commentaires pour expliquer les logiques complexes.


