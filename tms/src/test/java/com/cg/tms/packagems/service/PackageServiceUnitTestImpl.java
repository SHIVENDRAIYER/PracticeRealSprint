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

@ExtendWith(MockitoExtension.class)
public class PackageServiceUnitTestImpl {

	@Mock
	IPackageRepository packageRepository;
	@Mock
	PackageUtil packageUtil;

	@Spy
	@InjectMocks
	PackageServiceImpl packageService;

	@Test
	public void testAdd_Package1() {

		String packageName = "Holiday";
		String packageDescription = "peace and relaxation";
		doNothing().when(packageService).validatePackageName(packageName);
		doNothing().when(packageService).validatePackageDescription(packageDescription);
		Package reserved = Mockito.mock(Package.class);
		Package pack = Mockito.mock(Package.class);
		when(packageUtil.newPackage()).thenReturn(pack);
		when(packageRepository.save(pack)).thenReturn(reserved);
		Package result = packageService.addPackage(pack);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(reserved, result);
		verify(packageRepository).save(pack);
		verify(packageService).validatePackageDescription(packageDescription);
		verify(packageService).validatePackageName(packageDescription);

	}

}
