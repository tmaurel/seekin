<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
  <head>
    <style type="text/css">
      html{
          font-size:1em;
      }
      @page{
          size:A4 portrait;
          margin:0.3in;
      }
      h1{
          float:left;
          display:block;
          width:250px;
          height:150px;
          margin-top:-5px;
      }
      h2{
          display:block;
          width:80%;
          margin-left:auto;
          margin-right:auto;
          margin-bottom:-20px;
          font-weight:bold;
          text-align:center;
          font-size:1em;
      }
      a{
          color:#00E;
          cursor:auto;
          display:inline;
          text-decoration:underline;
      }
      table{
          border-spacing:0px;
          border-collapse:collapse;
          width:100%;
          font-size:0.8em;
      }
      thead{
          font-weight:bold;
      }
      td{
          border:1px solid #000;
          padding:2px;
      }
      #infos{
          display:block;
          text-align:right;
          line-height:1.3em;
          width:450px;
          float:right;
      }
      #infos strong{
          font-size:1.2em;
          font-weight:bold;
      }
      #criteria{
          width:20%;
      }
      #desc{
          width:65%;
      }
      #footer{
          font-size:0.8em;
      }
      .note{
          width:3%;
      }
      .content{
          clear:both;
          width:100%;
          padding-top:4em;
          page-break-after:always;
      }
      .body{
          padding-top:3em;
      }
      .quote{
          padding:2em;
          padding-left:8em;
          font-weight:bold;
          line-height:1.6em;
      }
      .sign{
          text-align:right;
          padding-top:5em;
          padding-bottom:4em;
      }
      .tab{
          display:block;
          padding-left:2em;
      }
      .xtab {
          display:block;
          padding-left:9em;
      }
      .block{
          padding-top:0.4em;
          padding-bottom:3em;
      }
    </style>
  </head>
  <body>
    <h1><img src="convocation/logo.jpg" alt="Logo" /></h1>
    <span id="infos">
        <strong>UFR SCIENCES EXACTES ET NATURELLES</strong><br/>
        DEPARTEMENT DE MATHEMATIQUES<br/>
        ET D'INFORMATIQUE<br/><br/>
        Secrétariat<br/>
        Tel : 04.73.40.70.70 - FAX : 04.73.40.79.72<br/>
        Email : <a href="mailto:Monique.Gevaudant&#64;math.univ-bpclermont.fr">Monique.Gevaudant&#64;math.univ-bpclermont.fr</a>
    </span>

    <div class="content">
      <div class="header">
          <strong>OBJET : STAGE ${promotion?.formation?.label}</strong><br/>
          <strong>${manager?.lastName} ${manager?.firstName}</strong><br/>
          <strong>Email : </strong><a href="${manager?.email}">${manager?.email}</a><br/>
          <br/><br/>
          <strong>A l'attention de : </strong>${companyTutor?.lastName} ${companyTutor?.firstName}<br/>
      </div>
      <div class="body">
          Nous vous confirmons la date et l'heure de la soutenance du stagiaire étudiant(e) en ${promotion?.formation?.label} dont vous avez bien voulu être tuteur.
          <div class="quote">
            ETUDIANT(E) : ${student?.lastName?.toUpperCase()} ${student?.firstName?.toUpperCase()}<br />
            Date de soutenance : ${date}<br />
            <span class="xtab">à ${time}</span>
            Bâtiment : ${convocation?.building}, ${convocation?.room}.<br />
          </div>
          Afin de faciliter l'évaluation de l'étudiant nous vous prions de bien vouloir remplir la fiche d'évaluation ci-jointe et de vous en munir pour cette soutenance. Si vous êtes dans l'impossibilité de vous déplacer, veuillez tout de même nous la retourner au secrétariat du département.
          <br/><br/>
          <strong>(Ci-joint : plan d'accès et plan de situation du campus)</strong>
          <br/><br/>
          Nous vous remercions de votre participation et de l'intérêt que vous témoignez à notre formation.
      </div>
      <div class="sign">
          ${manager?.lastName} ${manager?.firstName}<br/>
          Responsable des Stages
      </div>

    </div>


    <h1><img src="convocation/logo.jpg" alt="Logo" /></h1>
    <span id="infos">
        <strong>UFR SCIENCES EXACTES ET NATURELLES</strong><br/>
        DEPARTEMENT DE MATHEMATIQUES<br/>
        ET D'INFORMATIQUE<br/><br />
        Secrétariat<br/>
        Tel : 04.73.40.70.70 - FAX : 04.73.40.79.72<br/>
        Email : <a href="mailto:Monique.Gevaudant&#64;math.univ-bpclermont.fr">Monique.Gevaudant&#64;math.univ-bpclermont.fr</a>
    </span>

    <div class="content">
      <div class="header">
          <strong>OBJET : STAGE ${promotion?.formation?.label}</strong><br/>
          <strong>${manager?.lastName} ${manager?.firstName}</strong><br/>
          <strong>Email : </strong><a href="${manager?.email}">${manager?.email}</a><br/>
          <br/><br/>
          <strong>A l'attention de : </strong>${student?.lastName} ${student?.firstName}<br/>
      </div>
      <div class="body">
          Nous vous confirmons la date et l'heure de la soutenance de votre stage :
          <div class="quote">
            Le ${date}<br />
            <span class="tab">à ${time}</span>
            Bâtiment : ${convocation?.building}, ${convocation?.room}.<br />
            Sous la responsabilité de ${academicTutor?.lastName} ${academicTutor?.firstName}<br />
          </div>
          <strong>Le rapport de stage est à renvoyer ou à remettre au secrétariat du Département de Mathématiques et d'Informatique, en double exemplaires, quatre jours ouvrables avant la date de soutenance.</strong>
          <br/><br/>
          Vous disposerez d'un PC portable pour un exposé de 20 minutes.
      </div>
      <div class="sign">
          ${manager?.lastName} ${manager?.firstName}<br/>
          Responsable des Stages
      </div>
    </div>
    <h2>APPRECIATION DU STAGIAIRE<br/> PAR LE RESPONSABLE DE L'ETABLISSEMENT D'ACCUEIL</h2>
    <div class="body">
      <p>Etablissement d'accueil : ${convocation?.internship?.company?.name?.toUpperCase()}</p>
      <p>Nom du responsable de l'établissement d'accueil : ${companyTutor?.lastName} ${companyTutor?.firstName}</p>
      <p>Adresse de l'établissement d'accueil :
        <span class="tab">${convocation?.internship?.company?.address?.street}, ${convocation?.internship?.company?.address?.zipCode} ${convocation?.internship?.company?.address?.town}</span>
      </p>
      <p>Nom du stagiaire : ${student?.lastName} ${student?.firstName} </p>
      <p>Sujet : ${convocation?.internship?.subject?.toUpperCase()}</p>
      <table>
        <thead>
          <td id="criteria">Critère</td>
          <td id="desc">Description</td>
          <td class="note">A</td>
          <td class="note">B</td>
          <td class="note">C</td>
          <td class="note">D</td>
          <td class="note">E</td>
        </thead>
        <tr>
          <td>Assiduité</td>
          <td>Aptitude à la concentration</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Ponctualité</td>
          <td>Aptitude à être à l'heure</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Adaptation</td>
          <td>Degré d'aptabilité à un problème nouveau</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Motivation</td>
          <td>Degré d'intérêt et d'enthousiasme pour le travail proposé</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Communication</td>
          <td>Aptitude à échanger verbalement ou par écrit des idées avec d'autres personnes</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Relations humaines</td>
          <td>Aptitude à échanger verbalement ou par écrit des idées avec d'autres personnes</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Jugement</td>
          <td>Habilité à évaluer une situation et à proposer la bonne solution ou décision</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Initiative</td>
          <td>Capacité d'agir efficacement avec un minimum de consignes et d'encadrement</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Méthodologie</td>
          <td>Aptitude à aborder et à mettre au point une étude</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Esprit d'innovation</td>
          <td>Aptitude à imaginer de nouvelles méthodes pour l'analyse des problèmes ou l'exécution des travaux</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Esprit critique d'analyse</td>
          <td>Aptitude à faire une critique constructive des situations rencontrées</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Rapidité d'exécution</td>
          <td>Aptitude à exécuter un travail dans les délais prévus</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>Qualité du travail</td>
          <td>Degré de perfection du travail exécuté et précision des résultats obtenus</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </div>
    <div class="block">
        Avis général : 
    </div>
    <div class="block">
        Points forts : 
    </div>
    <div class="block">
        Points faibles :
    </div>
    <div class="sign">
        Signature du responsable
    </div>
    <div id="footer">
      Codes des appréciations :
      <span class="tab">A : non seulement dépasse les attentes, mais mérite une mention particulière (exceptionnel)</span>
      <span class="tab">B : attentes bien satisfaites</span>
      <span class="tab">C : nécessite une amélioration</span>
      <span class="tab">D : correspond à peine aux attentes</span>
      <span class="tab">E : ne répond pas aux attentes</span>
    </div>
  </body>
</html>
