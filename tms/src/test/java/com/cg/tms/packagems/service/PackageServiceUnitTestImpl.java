package com.cg.tms.packagems.service;

import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.cg.tms.packagems.repository.*;
import org.junit.jupiter.api.Assertions;
import com.cg.tms.packagems.entities.Package;
import org.junit.jupiter.api.function.Executable;
import com.cg.tms.packagems.exceptions.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PackageServiceUnitTestImpl {

	@Mock
	IPackageRepository packageRepository;

	@Spy
	@InjectMocks
	PackageServiceImpl packageService;

	/**
	 * Scenario 1a: Add Package Successfully
	 */
	@Test
	public void testAdd_Package1() {

		Package pack = new Package(1, "Local", "diverse and cultural", "Normal", 8500.0);
		Mockito.when(packageRepository.save(pack)).thenReturn(pack);
		Package testPackage = packageService.addPackage(pack);
		Assertions.assertNotNull(testPackage);
		Assertions.assertEquals(1, testPackage.getPackageId());
		Assertions.assertEquals("Local", testPackage.getPackageName());
		Assertions.assertEquals("diverse and cultural", testPackage.getPackageDescription());
		Assertions.assertEquals("Normal", testPackage.getPackageType());
		Assertions.assertEquals(8500.0, testPackage.getPackageCost());

	}

	/**
	 * Scenario 1b : Add Package Failure
	 */
	@Test
	public void testAdd_Package2() {

		String packageName = "";
		Package pack = new Package(1, packageName, "diverse and cultural", "Normal", 8500.0);
		doThrow(InvalidPackageNameException.class).when(packageService).validatePackageName(packageName);
		Executable executable = () -> packageService.addPackage(pack);
		Assertions.assertThrows(InvalidPackageNameException.class, executable);

	}

	/**
	 * Scenario 2a : PackageId Validation - Success Scenario
	 * 
	 */
	@Test
	public void testValidatePackageId_1() {

		int packageId = 2;
		packageService.validatePackageId(packageId);

	}

	/**
	 * Scenario 2b : PackageId Validation - Negative PackageId
	 * 
	 */
	@Test
	public void testValidatePackageId_2() {
		int packageId = -20;
		Executable executable = () -> packageService.validatePackageId(packageId);
		Assertions.assertThrows(InvalidPackageIdException.class, executable);

	}

	/**
	 * Scenario 3a : PackageName Validation - Empty Input
	 * 
	 */
	@Test
	public void testValidatePackageName_1() {

		String packageName = "";
		Executable executable = () -> packageService.validatePackageName(packageName);
		Assertions.assertThrows(InvalidPackageNameException.class, executable);

	}

	/**
	 * Scenario 3b : PackageName Validation - Null Input
	 * 
	 */

	@Test
	public void testValidatePackageName_2() {

		String packageName = null;
		Executable executable = () -> packageService.validatePackageName(packageName);
		Assertions.assertThrows(InvalidPackageNameException.class, executable);

	}

	/**
	 * Scenario 3c : PackageName Validation - Name Not Empty
	 * 
	 */
	@Test
	public void testValidatePackageName_3() {

		String packageName = "Holiday";
		packageService.validatePackageName(packageName);
	}

	/**
	 * Scenario 4a : PackageDescription Validation - Empty Input
	 * 
	 */
	@Test
	public void testValidatePackageDescription_1() {

		String packageDescription = "";
		Executable executable = () -> packageService.validatePackageDescription(packageDescription);
		Assertions.assertThrows(InvalidPackageDescriptionException.class, executable);

	}

	/**
	 * Scenario 4b : PackageDescription Validation - Null Input
	 * 
	 */
	@Test
	public void testValidatePackageDescription_2() {

		String packageDescription = null;
		Executable executable = () -> packageService.validatePackageDescription(packageDescription);
		Assertions.assertThrows(InvalidPackageDescriptionException.class, executable);

	}

	/**
	 * Scenario 4c : PackageDescription Validation - Length Criteria Not
	 * Satisfied(should be greater than 10)
	 * 
	 */
	@Test
	public void testValidatePackageDescription_3() {

		String packageDescription = "worst";
		Executable executable = () -> packageService.validatePackageDescription(packageDescription);
		Assertions.assertThrows(InvalidPackageDescriptionException.class, executable);

	}

	/**
	 * Scenario 4d : PackageDescription Validation - Successful
	 * 
	 */
	@Test
	public void testValidatePackageDescription_4() {

		String packageDescription = "diverse and cultural";
		packageService.validatePackageDescription(packageDescription);

	}

	/**
	 * Scenario 5a : PackageType Validation - Empty Input
	 * 
	 */
	@Test
	public void testValidatePackageType_1() {

		String packageType = "";
		Executable executable = () -> packageService.validatePackageType(packageType);
		Assertions.assertThrows(InvalidPackageTypeException.class, executable);

	}

	/**
	 * Scenario 5b : PackageType Validation - Null Input
	 * 
	 */
	@Test
	public void testValidatePackageType_2() {

		String packageType = null;
		Executable executable = () -> packageService.validatePackageType(packageType);
		Assertions.assertThrows(InvalidPackageTypeException.class, executable);

	}

	/**
	 * Scenario 5c : PackageType Validation - Length Criteria Not Satisfied(should
	 * be less than 10)
	 * 
	 */
	@Test
	public void testValidatePackageType_3() {

		String packageType = "amazing and astounding";
		Executable executable = () -> packageService.validatePackageType(packageType);
		Assertions.assertThrows(InvalidPackageTypeException.class, executable);

	}

	/**
	 * Scenario 5d : PackageType Validation - Successful
	 * 
	 */
	@Test
	public void testValidatePackageType_4() {

		String packageType = "Delux";
		packageService.validatePackageType(packageType);

	}

	/**
	 * Scenario 6a : Package foundById
	 */
	@Test
	public void testSearchPackageById_1() {

		int packageId = 2;
		Package pack = mock(Package.class);
		Optional<Package> optional = Optional.of(pack);
		when(packageRepository.findById(packageId)).thenReturn(optional);
		Package result = packageService.searchPackage(packageId);
		Assertions.assertEquals(pack, result);
		verify(packageRepository).findById(packageId);
	}

	/**
	 * Scenario 6b :Package not foundById
	 */
	@Test
	public void testSearchPackageById_2() {

		int packageId = 50;
		Optional<Package> optional = Optional.empty();
		when(packageRepository.findById(packageId)).thenReturn(optional);
		Executable executable = () -> packageService.searchPackage(packageId);
		Assertions.assertThrows(PackageNotFoundException.class, executable);
	}

}
