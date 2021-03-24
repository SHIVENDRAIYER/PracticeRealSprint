package com.cg.tms.packagems.service;

import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.cg.tms.packagems.util.*;
import com.cg.tms.packagems.repository.*;
import org.junit.jupiter.api.Assertions;
import com.cg.tms.packagems.entities.Package;
import org.junit.jupiter.api.function.Executable;
import com.cg.tms.packagems.exceptions.*;

@ExtendWith(MockitoExtension.class)
public class PackageServiceUnitTestImpl {

	@Mock
	IPackageRepository packageRepository;
	@Mock
	PackageUtil packageUtil;

	@Spy
	@InjectMocks
	PackageServiceImpl packageService;

	/**
	 * success scenario : Add package successfully
	 */
	@Test
	public void testAdd_Package1() {

		/*
		 * String packageName = "Holiday"; String packageDescription =
		 * "peace and relaxation";
		 * doNothing().when(packageService).validatePackageName(packageName);
		 * doNothing().when(packageService).validatePackageDescription(
		 * packageDescription); Package reserved = Mockito.mock(Package.class); Package
		 * pack = Mockito.mock(Package.class);
		 * when(packageUtil.newPackage()).thenReturn(pack);
		 * when(packageRepository.save(pack)).thenReturn(reserved); Package result =
		 * packageService.addPackage(pack); Assertions.assertNotNull(result);
		 * Assertions.assertEquals(reserved, result);
		 * verify(packageRepository).save(pack);
		 * verify(packageService).validatePackageDescription(packageDescription);
		 * verify(packageService).validatePackageName(packageDescription);
		 */

		int packageId = 2;
		String packageName = "International";
		String packageDescription = "challenging adventure";
		String packageType = "Ultra";
		double packageCost = 10500.0;
		Package pack = new Package(packageId, packageName, packageDescription, packageType, packageCost);
		Package result = packageService.addPackage(pack);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(packageId, result.getPackageId());
		Assertions.assertEquals(packageName, result.getPackageName());
		Assertions.assertEquals(packageDescription, result.getPackageDescription());
		Assertions.assertEquals(packageType, result.getPackageType());
		Assertions.assertEquals(packageCost, result.getPackageCost());

	}

	/**
	 * Scenario: PackageName Validation - Blank input
	 * 
	 */
	@Test
	public void testAdd_Package2() {

		/*
		 * String packageName = "";
		 * doThrow(InvalidPackageNameException.class).when(packageService).
		 * validatePackageName(packageName); Executable executable = () ->
		 * packageService.addPackage();
		 * Assertions.assertThrows(InvalidPackageNameException.class, executable);
		 */

		String packageName = "";
		Package pack = new Package(1, packageName, "diverse and cultural", "Normal", 8500.0);
		Executable executable = () -> packageService.addPackage(pack);
		Assertions.assertThrows(InvalidPackageNameException.class, executable);

	}

}
