package com.cg.tms.packagems.service;

import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.tms.packagems.exceptions.PackageNotFoundException;
import com.cg.tms.packagems.dao.*;
import com.cg.tms.packagems.entities.Package;

@Service
public class PackageServiceImpl implements IPackageService {

	@Autowired
	private IPackageRepository packageRepository;

	@Transactional
	@Override
	public Package addPackage(Package pack) {

		Package addPackage = new Package();
		addPackage.setPackageId(pack.getPackageId());
		addPackage.setPackageName(pack.getPackageName());
		addPackage.setPackageDescription(pack.getPackageDescription());
		addPackage.setPackageType(pack.getPackageType());
		addPackage.setPackageCost(pack.getPackageCost());
		packageRepository.save(addPackage);
		return addPackage;

	}

	@Transactional
	@Override
	public Package deletePackage(int packageId) throws PackageNotFoundException {

		Optional<Package> optional = packageRepository.findById(packageId);
		if (!optional.isPresent()) {

			throw new PackageNotFoundException("package not found for packageId=" + packageId);
		}

		return packageRepository.deletePackage(packageId);

	}

	@Override
	public Package searchPackage(int packageId) throws PackageNotFoundException {

		Optional<Package> optional = packageRepository.findById(packageId);
		if (!optional.isPresent()) {

			throw new PackageNotFoundException("package not found for packageId=" + packageId);
		}
		return optional.get();

	}
	

	@Override
	public List<Package> viewAllPackages() {

		List<Package> viewAllPackages = packageRepository.viewAllPackages();

		return viewAllPackages;

	}

}
