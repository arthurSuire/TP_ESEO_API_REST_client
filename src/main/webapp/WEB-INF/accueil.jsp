<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Accueil</title>
        <link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.9.3/css/bulma.min.css">
		<link rel="stylesheet"
			href="https://cdn.datatables.net/1.11.5/css/dataTables.bulma.min.css">
		<link rel="stylesheet"
			href="https://cdn.datatables.net/responsive/2.2.9/css/responsive.bulma.min.css">
    </head>
    <%@include file="navbar.jsp"%>
    <body>
		<section class="section has-text-centered">
			<div class="container">
				<p class="Title" style="font-size: 18px">
					<strong>Liste des villes enregistrées dans la base de données :</strong>
				</p>
			</div>
		</section>
		
		<div style="margin: 0 20px 20px 20px; font-size: 18px">
			<table id="table_villes"
				class="table is-bordered is-striped is-narrow is-hoverable"
				style="width: 100%">
				<thead>
					<tr>
						<th>Nom</th>
						<th>Code commune</th>
						<th>Code postal</th>
						<th>Libelle acheminement</th>
						<th>Ligne 5</th>
						<th>Latitude</th>
						<th>Longitude</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ villes }" var="villes">
						<tr>
							<td><c:out value="${ villes.getNomCommune() }"/></td>
							<td><c:out value="${ villes.getCodeCommune() }"/></td>
							<td><c:out value="${ villes.getCodePostal() }"/></td>
							<td><c:out value="${ villes.getLibelleAcheminement() }"/></td>
							<td><c:out value="${ villes.getLigne5() }"/></td>
							<td><c:out value="${ villes.getCoordonnee().getLatitude() }"/></td>
							<td><c:out value="${ villes.getCoordonnee().getLongitude() }"/></td>
							<td><a href="<%=application.getContextPath()%>/modifierVille?codeCommune=${ villes.getCodeCommune() }" onclick="ModifierVille()">Modifier ville</a></td>
							<td><a href="<%=application.getContextPath()%>/supprimerVille?codeCommune=${ villes.getCodeCommune()}" onclick="SupprimerVille()">Supprimer ville</a></td> 
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
    </body>
	<%@include file="footer.jsp"%>
</html>