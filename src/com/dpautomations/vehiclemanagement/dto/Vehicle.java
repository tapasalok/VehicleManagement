package com.dpautomations.vehiclemanagement.dto;

public class Vehicle {

	//<----------------- vehicle_registration DTO ------------------>
	private String vehicle_no;
	private String plant;
	private String transporter_name;
	private String vehicleRegistration_rowId;
	private String vehicleState;
	
	public String getVehicleState() {
		return vehicleState;
	}
	public void setVehicleState(String vehicleState) {
		this.vehicleState = vehicleState;
	}
	public String getVehicleRegistration_rowId() {
		return vehicleRegistration_rowId;
	}
	public void setVehicleRegistration_rowId(String vehicleRegistration_rowId) {
		this.vehicleRegistration_rowId = vehicleRegistration_rowId;
	}
	public String getVehicle_no() {
		return vehicle_no;
	}
	public void setVehicle_no(String vehicle_no) {
		this.vehicle_no = vehicle_no;
	}
	public String getPlant() {
		return plant;
	}
	public void setPlant(String plant) {
		this.plant = plant;
	}
	public String getTransporter_name() {
		return transporter_name;
	}
	public void setTransporter_name(String transporter_name) {
		this.transporter_name = transporter_name;
	}
	
	//<----------------- vehicle_management DTO ------------------>
	private String date_for_entered_data;
	private String dc_no;
	private String diesel_issued;
	private String open_kms;
	private String out_date_time;
	private String closing_kms;
	private String in_date_time;
	private String start_engine_hrs;
	private String closing_engine_hrs;
	private String fow_hrs;
	private String rev_hrs;
	private String quantity;
	private String man_pump;

	public String getDate_for_entered_data() {
		return date_for_entered_data;
	}
	public void setDate_for_entered_data(String date_for_entered_data) {
		this.date_for_entered_data = date_for_entered_data;
	}
	public String getDc_no() {
		return dc_no;
	}
	public void setDc_no(String dc_no) {
		this.dc_no = dc_no;
	}
	public String getDiesel_issued() {
		return diesel_issued;
	}
	public void setDiesel_issued(String diesel_issued) {
		this.diesel_issued = diesel_issued;
	}
	public String getOpen_kms() {
		return open_kms;
	}
	public void setOpen_kms(String open_kms) {
		this.open_kms = open_kms;
	}
	public String getOut_date_time() {
		return out_date_time;
	}
	public void setOut_date_time(String out_date_time) {
		this.out_date_time = out_date_time;
	}
	public String getClosing_kms() {
		return closing_kms;
	}
	public void setClosing_kms(String closing_kms) {
		this.closing_kms = closing_kms;
	}
	public String getIn_date_time() {
		return in_date_time;
	}
	public void setIn_date_time(String in_date_time) {
		this.in_date_time = in_date_time;
	}
	public String getStart_engine_hrs() {
		return start_engine_hrs;
	}
	public void setStart_engine_hrs(String start_engine_hrs) {
		this.start_engine_hrs = start_engine_hrs;
	}
	public String getClosing_engine_hrs() {
		return closing_engine_hrs;
	}
	public void setClosing_engine_hrs(String closing_engine_hrs) {
		this.closing_engine_hrs = closing_engine_hrs;
	}
	public String getFow_hrs() {
		return fow_hrs;
	}
	public void setFow_hrs(String fow_hrs) {
		this.fow_hrs = fow_hrs;
	}
	public String getRev_hrs() {
		return rev_hrs;
	}
	public void setRev_hrs(String rev_hrs) {
		this.rev_hrs = rev_hrs;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getMan_pump() {
		return man_pump;
	}
	public void setMan_pump(String man_pump) {
		this.man_pump = man_pump;
	}
	
}
