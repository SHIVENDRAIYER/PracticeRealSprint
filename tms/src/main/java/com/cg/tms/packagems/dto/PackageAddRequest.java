package com.cg.tms.packagems.dto;

public class PackageAddRequest {

	private String packageName;
	private String packageDescription;

	public PackageAddRequest() {

	}

	public String getPackageName() {

		return packageName;
	}

	public void setPackageName(String packageName) {

		this.packageName = packageName;
	}

	public String getPackageDescription() {

		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {

		this.packageDescription = packageDescription;
	}

}
