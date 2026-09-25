/* =========================================================================
   MAILLON 2 — JAVA : le moteur de calcul
   Complétez les quatre méthodes. Les classes Ligne, Resultat et Chargeur
   sont fournies : ne les modifiez pas.
       javac -encoding UTF-8 -d out src/*.java
       java -Dstdout.encoding=UTF-8 -cp out Tests     (les tests)
       java -Dstdout.encoding=UTF-8 -cp out Main      (la production)
   ========================================================================= */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Classement {

    /** Barème officiel des dix premiers. FOURNI — NE PAS MODIFIER. */
    public static final int[] BAREME = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    // 1. pointsPourPosition(position) : points marqués pour cette position.
    //    1 -> 25, 2 -> 18, ..., 10 -> 1. Au-delà de la 10e place : 0.
    //    Un abandon vaut la position 0, donc 0 point.
    public static int pointsPourPosition(int position) {
        if (position >= 1 && position <= 10) {
            return BAREME[position - 1];
        }
        return 0;
    }

    // 2. classementPilotes(lignes) : un Resultat par pilote, avec ses points,
    //    ses victoires (position 1) et ses 2e places, trié par :
    //    points décroissants, puis victoires, puis 2e places, puis nom (A→Z).
    public static List<Resultat> classementPilotes(List<Ligne> lignes) {
        Map<String, Resultat> resultatsMap = new HashMap<>();

        for (Ligne ligne : lignes) {
            String nom = ligne.pilote();
            String ecurie = ligne.ecurie();

            Resultat r = resultatsMap.computeIfAbsent(nom, k -> new Resultat(nom, ecurie));
            r.points += pointsPourPosition(ligne.position());
            if (ligne.position() == 1) {
                r.victoires++;
            } else if (ligne.position() == 2) {
                r.deuxiemes++;
            }
        }

        List<Resultat> resultats = new ArrayList<>(resultatsMap.values());
        resultats.sort((a, b) -> {
            if (b.points != a.points) return Integer.compare(b.points, a.points);
            if (b.victoires != a.victoires) return Integer.compare(b.victoires, a.victoires);
            if (b.deuxiemes != a.deuxiemes) return Integer.compare(b.deuxiemes, a.deuxiemes);
            return a.nom.compareTo(b.nom);
        });

        return resultats;
    }

    // 3. classementEcuries(pilotes) : additionne les points, victoires et
    //    2e places des pilotes de chaque écurie. Même ordre de tri.
    public static List<Resultat> classementEcuries(List<Resultat> pilotes) {
        Map<String, Resultat> ecuriesMap = new HashMap<>();
        for (Resultat pilote : pilotes) {
            String ecurie = pilote.ecurie;
            Resultat r = ecuriesMap.computeIfAbsent(ecurie, k -> new Resultat(ecurie, ecurie));
            r.points += pilote.points;
            r.victoires += pilote.victoires;
            r.deuxiemes += pilote.deuxiemes;
        }
        List<Resultat> resultats= new ArrayList<>(ecuriesMap.values());
        resultats.sort((a, b) -> {
            if (b.points != a.points) return Integer.compare(b.points, a.points);
            if (b.victoires != a.victoires) return Integer.compare(b.victoires, a.victoires);
            if (b.deuxiemes != a.deuxiemes) return Integer.compare(b.deuxiemes, a.deuxiemes);
            return a.nom.compareTo(b.nom);
        });
        return resultats;
    }

    // 4. positionMoyenne(lignes, pilote) : moyenne des positions de ce pilote,
    //    ABANDONS EXCLUS, arrondie à 2 décimales. 0 s'il n'a jamais terminé.
    //    Ex. positions 1, 2 et un abandon -> 1.5
    public static double positionMoyenne(List<Ligne> lignes, String pilote) {
      int some = 0;
      int count = 0;
      for (Ligne ligne : lignes) {
          if (ligne.pilote().equals(pilote) && ligne.position() > 0) {
              some += ligne.position();
              count++;
          }
      }
      if (count == 0) {
          return 0.0;
      }
      double moyenne = (double) some / count;
      return Math.round(moyenne * 100.0) / 100.0;
    }           
}      

