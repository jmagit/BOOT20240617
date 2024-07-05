<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ include file="../parts/header.jsp" %>
<p>
C�digo: ${elemento.cityId}<br>
Nombre: ${elemento.city}<br>
Pais: ${elemento.country.country}
</p>
<p>
	<a href="/ciudades" class="btn btn-primary" >Volver</a>
</p>
<%@ include file="../parts/footer.jsp" %>
