/*package com.cg.tms.packagems.controllers;

import org.slf4j.*;
import java.util.*;
import com.cg.tms.packagems.util.*;
import com.cg.tms.packagems.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.cg.tms.packagems.dto.*;
import com.cg.tms.packagems.entities.Package;
import org.springframework.http.HttpStatus;

@RequestMapping("/packages")
@RestController
public class PackageRestController {

	private static final Logger LOG = LoggerFactory.getLogger(PackageRestController.class);

	@Autowired
	private IPackageService packageService;

	@Autowired
	private PackageUtil packageUtil;

	@GetMapping(value = "/byid/{packageId}")
	public PackageDetails fetchPackage(@PathVariable("packageId") Integer packageId) {

		LOG.debug("packageId in fetchPackage in PackageRestController " + packageId);
		Package pack = packageService.searchPackage(packageId);
		PackageDetails packageDetails = packageUtil.toDetails(pack);
		return packageDetails;
	}

	@GetMapping
	public List<PackageDetails> allPackages() {

		List<Package> packs = packageService.viewAllPackages();
		List<PackageDetails> desired = packageUtil.toDetailsList(packs);
		return desired;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/add")
	public Package addPackage(@RequestBody PackageAddRequest requestData) {

		Package added = packageService.addPackage(requestData.getPackage());
		return added;
	}

	@DeleteMapping("/delete")
	public String delete(@RequestBody PackageCancelRequest requestData) {
		
		packageService.deletePackage(requestData.getPackageId());
		return "package cancelled for packageId=" + requestData.getPackageId();
	}
	
	

}*/
