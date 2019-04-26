package org.pharmacy.locator.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.pharmacy.locator.helper.ConnectionHelper;
import org.pharmacy.locator.model.Pharmacy;
import org.pharmacy.locator.model.PharmacyDetails;

public class PharmacyService {

	public List<Pharmacy> getPharmacies() throws Exception {
		List<Pharmacy> pharmacies = new ArrayList<>();
        try {
        	Connection connection = ConnectionHelper.getConnection();
        	Pharmacy pharmacy;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from pharmacydetails");
            while(rs.next())
            {
            	pharmacy = new Pharmacy();
            	pharmacy.setId(rs.getInt("id"));
            	pharmacy.setName(rs.getString("name"));
            	pharmacy.setState(rs.getString("state"));
            	pharmacy.setAddress(rs.getString("address"));
            	pharmacy.setCity(rs.getString("city"));
            	pharmacy.setZip(rs.getString("zip"));
            	pharmacy.setLatitude(rs.getDouble("latitude"));
            	pharmacy.setLongitude(rs.getDouble("longitude"));
            	
            	pharmacies.add(pharmacy);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return pharmacies;
	}
	
	public double distance(double lat1, double lon1, double lat2, double lon2) {
		if ((lat1 == lat2) && (lon1 == lon2)) {
			return 0;
		}
		else {
			double theta = lon1 - lon2;
			double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
			dist = Math.acos(dist);
			dist = Math.toDegrees(dist);
			dist = dist * 60 * 1.1515;
			return (dist);
		}
	}
	
	public PharmacyDetails getDetails(Pharmacy pharm, double dist) {
		PharmacyDetails pharmDetails = new PharmacyDetails();
		pharmDetails.setName(pharm.getName());
		pharmDetails.setAddress(pharm.getAddress() + ", " + pharm.getCity() + ", " + pharm.getState() + " " + pharm.getZip());
		pharmDetails.setDistance(String.format("%.2f", dist) + " " + "Miles" );
		
		return pharmDetails;
	}

}
