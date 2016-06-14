


public class Main {

	public static void main(String[] args) {
		
		MyFrame f = new MyFrame();
		f.setVisible(true);
	}

}
	


class Patient{
	private String patientNo;				//编号
	private String patientName;				//姓名
	private String patientSex;				//性别
	private String patientCard;			//身份证号
	private String patientAge;				//年龄
	
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientCard() {
		return patientCard;
	}
	public void setPatientCard(String patientCard) {
		this.patientCard = patientCard;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientAge() {
		return patientAge;
	}
}

class Case{
	private String caseName;																							//疾病名称
	private String drugs;																								//药品
	private double money;																								//药品费用
	private double days;																								//住院天数
	private String operation;																							//病人手术安排
	
	public String getCaseName() {
		return caseName;
	}
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public double getDays() {
		return days;
	}
	public void setDays(double days) {
		this.days = days;
	}
    public String getOperation() {
	    return operation;
    }
    public void setOperation(String operation) {
	    this.operation = operation;
    }
}
class Doctor{ 
	private String doctorName;																							//医师姓名
	private String doctorSection;																						//所属科室
	private String doctorOffice;																						//办公室
	
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorSection() {
		return doctorSection;
	}
	public void setDoctorSection(String doctorSection) {
		this.doctorSection = doctorSection;
	}
	public String getDoctorOffice() {
		return doctorOffice;
	}
	public void setDoctorOffice(String doctorOffice) {
		this.doctorOffice = doctorOffice;
	}
}

	
	





