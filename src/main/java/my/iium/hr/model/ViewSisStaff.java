package my.iium.hr.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown= true)
public class ViewSisStaff {
	
    private String staffNo;
    private String staffName;
    private String email;
    private String staffPosition;
    private String positionCode;
    private String kcdioCode;
    private String campusCode;
    private String staffTypeDesc;
    private String smStaffType;
    private String ssoId;
    private String nonStaff;
    private String staffStatus;
    private String staffStatusDesc;
    private String computedStatus;
    private String computedStatusDesc;
    private String jobCategory;
    private String joinDate;
    private String exitDate;
    private String exitType;
    private String exitTypeDesc;
    private String exitReason;
    private String exitReasonDesc;
    private String salaryGrade;
    private String smTitle;
    private String titleDesc;
    private String kcdName; //address 2
    private String kcdShortName;
    private String campusName; //address 1,
	private String dependentPath;
	private String qualificationPath;
	private String photoPath;

	
	
	public ViewSisStaff() {
		super();
	}
	
	public ViewSisStaff(String staffNo, String staffName, String email, String staffPosition, String positionCode,
			String kcdioCode, String campusCode, String staffTypeDesc, String smStaffType, String ssoId,
			String nonStaff, String staffStatus, String staffStatusDesc, String computedStatus,
			String computedStatusDesc, String jobCategory, String joinDate, String exitDate, String exitType,
			String exitTypeDesc, String exitReason, String exitReasonDesc, String salaryGrade, String smTitle,
			String titleDesc, String kcdName, String kcdShortName, String campusName, String dependentPath,
			String qualificationPath, String photoPath) {
		super();
		this.staffNo = staffNo;
		this.staffName = staffName;
		this.email = email;
		this.staffPosition = staffPosition;
		this.positionCode = positionCode;
		this.kcdioCode = kcdioCode;
		this.campusCode = campusCode;
		this.staffTypeDesc = staffTypeDesc;
		this.smStaffType = smStaffType;
		this.ssoId = ssoId;
		this.nonStaff = nonStaff;
		this.staffStatus = staffStatus;
		this.staffStatusDesc = staffStatusDesc;
		this.computedStatus = computedStatus;
		this.computedStatusDesc = computedStatusDesc;
		this.jobCategory = jobCategory;
		this.joinDate = joinDate;
		this.exitDate = exitDate;
		this.exitType = exitType;
		this.exitTypeDesc = exitTypeDesc;
		this.exitReason = exitReason;
		this.exitReasonDesc = exitReasonDesc;
		this.salaryGrade = salaryGrade;
		this.smTitle = smTitle;
		this.titleDesc = titleDesc;
		this.kcdName = kcdName;
		this.kcdShortName = kcdShortName;
		this.campusName = campusName;
		this.dependentPath = dependentPath;
		this.qualificationPath = qualificationPath;
		this.photoPath = photoPath;
	}


	public String getStaffNo() {
		return staffNo;
	}

	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStaffPosition() {
		return staffPosition;
	}

	public void setStaffPosition(String staffPosition) {
		this.staffPosition = staffPosition;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getKcdioCode() {
		return kcdioCode;
	}

	public void setKcdioCode(String kcdioCode) {
		this.kcdioCode = kcdioCode;
	}

	public String getCampusCode() {
		return campusCode;
	}

	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}

	public String getStaffTypeDesc() {
		return staffTypeDesc;
	}

	public void setStaffTypeDesc(String staffTypeDesc) {
		this.staffTypeDesc = staffTypeDesc;
	}

	public String getSmStaffType() {
		return smStaffType;
	}

	public void setSmStaffType(String smStaffType) {
		this.smStaffType = smStaffType;
	}

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public String getNonStaff() {
		return nonStaff;
	}

	public void setNonStaff(String nonStaff) {
		this.nonStaff = nonStaff;
	}

	public String getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(String staffStatus) {
		this.staffStatus = staffStatus;
	}

	public String getStaffStatusDesc() {
		return staffStatusDesc;
	}

	public void setStaffStatusDesc(String staffStatusDesc) {
		this.staffStatusDesc = staffStatusDesc;
	}

	public String getComputedStatus() {
		return computedStatus;
	}

	public void setComputedStatus(String computedStatus) {
		this.computedStatus = computedStatus;
	}

	public String getComputedStatusDesc() {
		return computedStatusDesc;
	}

	public void setComputedStatusDesc(String computedStatusDesc) {
		this.computedStatusDesc = computedStatusDesc;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getExitDate() {
		return exitDate;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	public String getExitType() {
		return exitType;
	}

	public void setExitType(String exitType) {
		this.exitType = exitType;
	}

	public String getExitTypeDesc() {
		return exitTypeDesc;
	}

	public void setExitTypeDesc(String exitTypeDesc) {
		this.exitTypeDesc = exitTypeDesc;
	}

	public String getExitReason() {
		return exitReason;
	}

	public void setExitReason(String exitReason) {
		this.exitReason = exitReason;
	}

	public String getExitReasonDesc() {
		return exitReasonDesc;
	}

	public void setExitReasonDesc(String exitReasonDesc) {
		this.exitReasonDesc = exitReasonDesc;
	}

	public String getSalaryGrade() {
		return salaryGrade;
	}

	public void setSalaryGrade(String salaryGrade) {
		this.salaryGrade = salaryGrade;
	}

	public String getSmTitle() {
		return smTitle;
	}

	public void setSmTitle(String smTitle) {
		this.smTitle = smTitle;
	}

	public String getTitleDesc() {
		return titleDesc;
	}

	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc;
	}

	public String getKcdName() {
		return kcdName;
	}

	public void setKcdName(String kcdName) {
		this.kcdName = kcdName;
	}

	public String getKcdShortName() {
		return kcdShortName;
	}

	public void setKcdShortName(String kcdShortName) {
		this.kcdShortName = kcdShortName;
	}

	public String getCampusName() {
		return campusName;
	}

	public void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	public String getDependentPath() {
		return dependentPath;
	}

	public void setDependentPath(String dependentPath) {
		this.dependentPath = dependentPath;
	}

	public String getQualificationPath() {
		return qualificationPath;
	}

	public void setQualificationPath(String qualificationPath) {
		this.qualificationPath = qualificationPath;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "ViewSisStaff [staffNo=" + staffNo + ", staffName=" + staffName + ", email=" + email + ", staffPosition="
				+ staffPosition + ", positionCode=" + positionCode + ", kcdioCode=" + kcdioCode + ", campusCode="
				+ campusCode + ", staffTypeDesc=" + staffTypeDesc + ", smStaffType=" + smStaffType + ", ssoId=" + ssoId
				+ ", nonStaff=" + nonStaff + ", staffStatus=" + staffStatus + ", staffStatusDesc=" + staffStatusDesc
				+ ", computedStatus=" + computedStatus + ", computedStatusDesc=" + computedStatusDesc + ", jobCategory="
				+ jobCategory + ", joinDate=" + joinDate + ", exitDate=" + exitDate + ", exitType=" + exitType
				+ ", exitTypeDesc=" + exitTypeDesc + ", exitReason=" + exitReason + ", exitReasonDesc=" + exitReasonDesc
				+ ", salaryGrade=" + salaryGrade + ", smTitle=" + smTitle + ", titleDesc=" + titleDesc + ", kcdName="
				+ kcdName + ", kcdShortName=" + kcdShortName + ", campusName=" + campusName + ", dependentPath="
				+ dependentPath + ", qualificationPath=" + qualificationPath + ", photoPath=" + photoPath + "]";
	}
 

    
}