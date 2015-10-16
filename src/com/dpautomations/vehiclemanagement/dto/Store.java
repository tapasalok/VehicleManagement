package com.dpautomations.vehiclemanagement.dto;

public class Store {

	// <----------------- store_registration DTO ------------------>
	private String storeRegistration_rowid;
	private String quantity_of_material;

	public String getStoreRegistration_rowid() {
		return storeRegistration_rowid;
	}

	public void setStoreRegistration_rowid(String materialRegistration_rowid) {
		this.storeRegistration_rowid = materialRegistration_rowid;
	}

	public String getQuantity_of_material() {
		return quantity_of_material;
	}

	public void setQuantity_of_material(String quantity_of_material) {
		this.quantity_of_material = quantity_of_material;
	}

	// <----------------- store_management DTO ------------------>
	private String storeManagement_rowid;
	private String date_for_entered_material_detail;
	private String type_of_material;
	private String supplierName;
	private String material_vehicle_no;
	private String load_Weight;
	private String empty_Weight;
	private String net_Weight;
	private String Hold;

	public String getStoreManagement_rowid() {
		return storeManagement_rowid;
	}

	public void setStoreManagement_rowid(String storeManagement_rowid) {
		this.storeManagement_rowid = storeManagement_rowid;
	}

	public String getDate_for_entered_material_detail() {
		return date_for_entered_material_detail;
	}

	public void setDate_for_entered_material_detail(String date_for_entered_material_detail) {
		this.date_for_entered_material_detail = date_for_entered_material_detail;
	}

	public String getType_of_material() {
		return type_of_material;
	}

	public void setType_of_material(String type_of_material) {
		this.type_of_material = type_of_material;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMaterial_vehicle_no() {
		return material_vehicle_no;
	}

	public void setMaterial_vehicle_no(String material_vehicle_no) {
		this.material_vehicle_no = material_vehicle_no;
	}

	public String getLoad_Weight() {
		return load_Weight;
	}

	public void setLoad_Weight(String load_Weight) {
		this.load_Weight = load_Weight;
	}

	public String getEmpty_Weight() {
		return empty_Weight;
	}

	public void setEmpty_Weight(String empty_Weight) {
		this.empty_Weight = empty_Weight;
	}

	public String getNet_Weight() {
		return net_Weight;
	}

	public void setNet_Weight(String net_Weight) {
		this.net_Weight = net_Weight;
	}

	public String getHold() {
		return Hold;
	}

	public void setHold(String Hold) {
		this.Hold = Hold;
	}

}
