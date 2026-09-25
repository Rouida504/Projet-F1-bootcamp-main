# Projet transversal F1 — Python → Java → JavaScript

Pack complet : énoncé, données, squelettes, tests, extensions et corrigés.

```
ENONCE.md                    l'énoncé étudiant (à lire en premier)
donnees/resultats.csv        l'export brut du championnat
01-python/ingestion.ipynb    maillon 1 — à compléter
# dans la partie temps :j'ai fait une fonction temps_en_secondes pour passer les chronos de type 1:33.996 en secondes (93.996). S'il n'y a pas de temps, elle renvoie None 
# dans la partie lectures:  dans lire_resultats, je lis le CSV ligne par ligne en séparant par des ;. Si le pilote a abandonné, je passe sa position à 0
# dans la partie l'écriture : avec ecrire_courses_propres, je crée le nouveau CSV propre pour Java avec le bon en-tête, les temps arrondis à 3 décimales et les cases vides pour les abandons

02-java/src/                 maillon 2 — Classement.java à compléter
# dans pointsPourPosition : j'ai implémenté le barème officiel des 10 premiers (25, 18, 15... points). Renvoie 0 si hors top 10
# dans classementPilotes  : agrégation des points, victoires et 2es places par pilote via une Map. Tri avec gestion des égalités
# dans classementEcuries  : cumul des statistiques des pilotes par écurie avec le même système de tri
# dans positionMoyenne    : calcul de la moyenne des positions en ignorant les abandons (position <= 0), arrondie à 1 décimale


03-js/                       maillon 3 — app.js à compléter, index.html à ouvrir
secours/                     résultats de référence, en cas de blocage
extensions/E1 à E4           les extensions et leurs tests
formateur/                   corrigés, grille, générateur — À RETIRER avant distribution
```

Prérequis : Python 3 avec Jupyter, un JDK (`javac -version`), un navigateur.
