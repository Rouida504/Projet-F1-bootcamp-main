/* =========================================================================
   MAILLON 3 — JAVASCRIPT : l'interface
   Les données arrivent du maillon Java, dans donnees.js :
     PILOTES = [{nom, ecurie, points, victoires}, ...]
     ECURIES = [{nom, points, victoires}, ...]
   Complétez les trois fonctions, puis ouvrez index.html dans le navigateur.
   ========================================================================= */

// 1. trierParPoints(liste) : renvoie une NOUVELLE liste triée par points
//    DÉCROISSANTS. La liste reçue ne doit pas être modifiée.
//    À points égaux, celui qui a le plus de victoires passe devant.
function trierParPoints(liste) {
 return liste.slice().sort((a, b) => {
    if (b.points !== a.points) {
      return b.points - a.points; 
    } else {
      return b.victoires - a.victoires; 
    }
  });
}

// 2. remplirTableau(idCorps, liste) : remplit le <tbody> dont l'id est fourni.
//    Une ligne <tr> par entrée, avec dans l'ordre les cellules <td> :
//      rang (1, 2, 3...) | nom | écurie (chaîne vide si absente) | points | victoires
//    Chaque <tr> porte l'attribut data-nom. Un nouvel appel REMPLACE le contenu.
function remplirTableau(idCorps, liste) {
  const tbody = document.getElementById(idCorps);
  if (!tbody) return;

  tbody.innerHTML = "";

  liste.forEach((element, index) => {
    const tr = document.createElement("tr");
    tr.dataset.nom = element.nom;

    const tdRang = document.createElement("td");
    tdRang.textContent = index + 1;

    const tdNom = document.createElement("td");
    tdNom.textContent = element.nom;

    const tdEcurie = document.createElement("td");
    tdEcurie.textContent = element.ecurie || "";

    const tdPoints = document.createElement("td");
    tdPoints.textContent = element.points;

    const tdVictoires = document.createElement("td");
    tdVictoires.textContent = element.victoires;

    tr.appendChild(tdRang);
    tr.appendChild(tdNom);
    tr.appendChild(tdEcurie);
    tr.appendChild(tdPoints);
    tr.appendChild(tdVictoires);

    tbody.appendChild(tr);
  });
}

// 3. marquerPodium(idCorps) : ajoute la classe CSS "podium" aux TROIS PREMIÈRES
//    lignes du tableau, et la retire de toutes les autres.
function marquerPodium(idCorps) {
  const tbody = document.getElementById(idCorps);
  const lignes = tbody.querySelectorAll("tr");

  lignes.forEach((ligne, index) => {
    if (index < 3) {
      ligne.classList.add("podium");
    } else {
      ligne.classList.remove("podium");
    }
  });
}

/* --- FOURNI — NE PAS MODIFIER : affichage de la saison ------------------- */
function afficherSaison() {
  if (typeof PILOTES === "undefined") {
    return;
  }
  remplirTableau("corps-pilotes", trierParPoints(PILOTES));
  marquerPodium("corps-pilotes");
  remplirTableau("corps-ecuries", trierParPoints(ECURIES));
  marquerPodium("corps-ecuries");
}
