Rapport d'Analyse SonarQube
Description
Ce projet a été analysé à l’aide de SonarQube pour identifier et résoudre les problèmes de qualité de code, les bugs, les vulnérabilités, et les mauvaises pratiques (code smells). Vous trouverez ci-dessous un résumé des résultats de l'analyse, les solutions adoptées, et leur impact sur la maintenabilité du projet.

📊 Tableau de Bord SonarQube
Avant la correction :

4 bugs (gravité : Blocker)
1 vulnérabilité critique
Plusieurs code smells identifiés
Après correction :

Résolution des principaux bugs et vulnérabilités
Suppression des duplications majeures
Aucun faux positif signalé par SonarQube
🔍 Résultats de l'Analyse
1. Bugs et Résolutions
Bug 1 : Flux non fermés correctement dans ReservationDao et UserDao.

Solution : Utilisation du bloc try-with-resources pour fermer automatiquement les connexions.
Bug 2 : Chaîne sensible (PASSWORD) codée en dur.

Solution : Stockage dans un fichier config.properties sécurisé et ignoré par Git.
Bug 3 : Concaténation de chaînes dans des requêtes SQL.

Solution : Utilisation de requêtes SQL paramétrées avec le mécanisme de binding.
Bug 4 : Utilisation de printStackTrace().

Solution : Remplacement par un logger standardisé.
2. Vulnérabilités Identifiées
Blocker : Chaîne sensible exposée dans le code source.

Solution : Stockage des données sensibles dans un fichier de configuration externe.
Critical : Risque d’injection SQL.

Solution : Utilisation de requêtes préparées.
Minor : Mauvaise gestion des exceptions.

Solution : Adoption de pratiques uniformisées avec des loggers.
3. Code Smells Résolus
Suppression des imports inutilisés.
Ajout d’un constructeur privé pour empêcher l'instanciation.
Remplissage ou commentaire des classes vides.
Ajout d’exceptions dans les blocs catch.
Remplacement de System.out et System.err par des loggers.
4. Problèmes Non Identifiés par SonarQube
Certains problèmes de code n'ont pas été détectés par SonarQube :

Fermeture incorrecte des connexions dans Home.
Vérification insuffisante de args.length, risquant une ArrayIndexOutOfBoundsException.
Gestion incorrecte de conn (fermeture ou utilisation sans vérification de nullité).
Initialisation manquante de certaines dépendances.
5. Impact des Duplications de Code
Première duplication : Blocs try-catch répétés pour la gestion des exceptions.

Impact : Faible, car limité à la gestion d'erreurs.
Deuxième duplication : Duplication dans la construction d’objets pour les tests.

Impact :
Maintenance complexe en cas de modification.
Faible réutilisabilité à cause de la duplication des configurations.
