package com.cg.tms.packagems.service;

import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.tms.packagems.exceptions.PackageNotFoundException;
import com.cg.tms.packagems.repository.*;
import com.cg.tms.packagems.exceptions.*;
import com.cg.tms.packagems.entities.Package;

@Service
public class PackageServiceImpl implements IPackageService {

	@Autowired
	private IPackageRepository packageRepository;

	@Transactional
	@Override
	public Package addPackage(Package pack) {

		validatePackageId(pack.getPackageId());
		validatePackageName(pack.getPackageName());
		validatePackageDescription(pack.getPackageDescription());
		
		packageRepository.save(pack);
		return pack;

	}

	@Transactional
	@Override
	public Package deletePackage(int packageId) throws PackageNotFoundException {

		validatePackageId(packageId);
		Optional<Package> optional = packageRepository.findById(packageId);
		if (!optional.isPresent()) {

			throw new PackageNotFoundException("package not found for packageId=" + packageId);
		}

		packageRepository.deleteById(packageId);

		return optional.get();
	}

	@Override
	public Package searchPackage(int packageId) throws PackageNotFoundException {

		validatePackageId(packageId);
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

	public void validatePackageId(int packageId) {

		if (packageId < 0) {

			throw new InvalidPackageIdException("packageId should not be negative");
		}
	}

	public void validatePackageName(String packageName) {

		if (packageName == null || packageName.isEmpty() || packageName.trim().isEmpty()) {

			throw new InvalidPackageNameException("packageName can't be null or empty");
		}
	}

	public void validatePackageDescription(String packageDescription) {

		if (packageDescription == null || packageDescription.isEmpty() || packageDescription.trim().isEmpty()) {

			throw new InvalidPackageDescriptionException("packageDescription can't be null or empty");
		}
	}

}
