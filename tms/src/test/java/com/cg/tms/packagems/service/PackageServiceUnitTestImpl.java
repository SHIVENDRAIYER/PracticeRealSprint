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
	 * Scenario : Add package successfully
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
	 * Scenario : Add package failure
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
	 * Scenario: PackageId Validation - Success scenario
	 * 
	 */
	@Test
	public void testValidatePackageId_1() {

		int packageId = 2;
		packageService.validatePackageId(packageId);

	}

	/**
	 * Scenario: PackageId Validation - Negative PackageId
	 * 
	 */
	@Test
	public void testValidatePackageId_2() {
		int packageId = -20;
		Executable executable = () -> packageService.validatePackageId(packageId);
		Assertions.assertThrows(InvalidPackageIdException.class, executable);

	}

	/**
	 * Scenario: PackageName Validation - Blank input
	 * 
	 */
	@Test
	public void testValidatePackageName_1() {

		String packageName = "";
		Executable executable = () -> packageService.validatePackageName(packageName);
		Assertions.assertThrows(InvalidPackageNameException.class, executable);

	}

	/**
	 * Scenario: PackageName Validation - Null input
	 * 
	 */

	@Test
	public void testValidatePackageName_2() {

		String packageName = null;
		Executable executable = () -> packageService.validatePackageName(packageName);
		Assertions.assertThrows(InvalidPackageNameException.class, executable);

	}

	/**
	 * Scenario: PackageName Validation - Name not Empty
	 * 
	 */
	@Test
	public void testValidatePackageName_3() {

		String packageName = "Holiday";
		packageService.validatePackageName(packageName);
	}

	/**
	 * scenario: Package foundById
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
	 * scenario :Package not foundById
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
