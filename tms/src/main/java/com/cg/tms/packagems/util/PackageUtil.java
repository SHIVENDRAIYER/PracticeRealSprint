package com.cg.tms.packagems.util;

import com.cg.tms.packagems.dto.*;
import com.cg.tms.packagems.entities.Package;
import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class PackageUtil {

	
	 public Package newPackage(){
		 
	        return new Package();
	    }

	public PackageDetails toDetails(Package pack) {

		PackageDetails packageDetails = new PackageDetails();
		packageDetails.setPackageId(pack.getPackageId());
		packageDetails.setPackageName(pack.getPackageName());
		packageDetails.setPackageDescription(pack.getPackageDescription());
		packageDetails.setPackageType(pack.getPackageType());
		packageDetails.setPackageCost(pack.getPackageCost());

		return packageDetails;

	}

	public List<PackageDetails> toDetailsList(Collection<Package> packs) {

		List<PackageDetails> desired = new ArrayList<>();
		for (Package pack : packs) {

			PackageDetails packageDetails = toDetails(pack);
			desired.add(packageDetails);
		}
		return desired;

	}

}
