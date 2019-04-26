package org.pharmacy.locator.resource;

import javax.ws.rs.core.Response;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.pharmacy.locator.model.Pharmacy;
import org.pharmacy.locator.service.PharmacyService;


@Path("pharmacy")
public class PharmacyResource {
	
	@GET
	@Path("/{lattitude}/{longitude}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMessage(@PathParam("lattitude") Double lattitude, @PathParam("longitude") Double longitude) throws Exception {
		double pharmacyDistance = Double.MAX_VALUE;
		double tempDistance = 0.00;
		Pharmacy nearestPharmacy = null;
		PharmacyService pharmacyService = new PharmacyService();
		List<Pharmacy> pharmaciesList = pharmacyService.getPharmacies();
		for(Pharmacy phr : pharmaciesList) {
			tempDistance = pharmacyService.distance(lattitude, longitude, phr.getLatitude(), phr.getLongitude());
			if(tempDistance < pharmacyDistance) {
				pharmacyDistance = tempDistance;
				nearestPharmacy = phr;
			}
		}
		
		return Response.status(Response.Status.OK).entity(pharmacyService.getDetails(nearestPharmacy, pharmacyDistance)).build();
	}

}
