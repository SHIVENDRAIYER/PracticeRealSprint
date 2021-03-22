package com.cg.tms.packagems.ui;

import org.springframework.stereotype.Component;
import com.cg.tms.packagems.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.cg.tms.packagems.exceptions.*;
import java.util.*;

import com.cg.tms.packagems.entities.Package;

@Component
public class PackageUi {

	@Autowired
	private IPackageService packageService;

	public void start() {

		try {

			Package pack1 = new Package("Local","diverse and cultural","Normal",8500.0);
			packageService.addPackage(pack1);
			displayPackage(pack1);

			Package pack2 = new Package("International","challenging adventure","Ultra",10500.0);
			packageService.addPackage(pack2);
			displayPackage(pack2);

			Package pack3 = new Package("Holiday","peace and relaxtion","Deluxe",12500.0);
			packageService.addPackage(pack3);
			displayPackage(pack3);

			Package deletePack = packageService.deletePackage(3);
			displayPackage(deletePack);

			Package searchPack = packageService.searchPackage(2);
			displayPackage(searchPack);

			List<Package> allPackages = packageService.viewAllPackages();
			displayAllPackages(allPackages);

		} catch (PackageNotFoundException e) {

			System.out.println("Package not found");
			e.getMessage();

		} catch (Exception e) {

			System.out.println("Parent Exception");
			e.getMessage();

		}

	}

	public void displayPackage(Package pack) {

		System.out.println("packageId: " + pack.getPackageId() + " packageName: " + pack.getPackageName()
				+ " packageDescription: " + pack.getPackageDescription() + " packageType: " + pack.getPackageType()
				+ " packageCost: " + pack.getPackageCost());

	}

	public void displayAllPackages(Collection<Package> packs) {

		for (Package pack : packs) {

			displayPackage(pack);
		}

	}

}
