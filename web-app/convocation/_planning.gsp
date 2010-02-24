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
          margin-bottom:30px;
          font-weight:bold;
          text-align:center;
          font-size:1em;
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
      #body {
          clear:both;
          padding-top:5em;
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

    <div id="body">
      <h2>PLANNING DES SOUTENANCES</h2>

      <table>
        <thead>
          <td>Date</td>
          <td>Heure</td>
          <td>Etudiant</td>
          <td>Batiment</td>
          <td>Salle</td>
          <td>Tuteur</td>
        </thead>
        <% convocations.each { %>
          <tr>
            <td>${it.date}</td>
            <td>${it.time}</td>
            <td>${it.name}</td>
            <td>${it.building}</td>
            <td>${it.room}</td>
            <td>${it.academicTutor}</td>
          </tr>
        <% } %>
      </table>
    </div>
    
  </body>
</html>
