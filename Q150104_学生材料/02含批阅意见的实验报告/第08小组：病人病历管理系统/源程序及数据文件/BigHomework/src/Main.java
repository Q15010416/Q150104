

public class Main {

	public static void main(String[] args) {
		
		LoginFrame f = new LoginFrame();
		f.setVisible(true);
	}

}
	


class Patient{
	private String patientNo;								//���˱��
	private String patientName;								//��������
	private String patientSex;								//�Ա�
	private String patientCard;								//���֤��
	private String patientAge;								//����
	
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
	private String patientNo;								//���˱��
	private String doctorNo;								//ҽʦ���
	private String caseName;								//��������
	private String drugs;									//ҩƷ
	private double money;									//ҩƷ����
	private double days;									//סԺ����
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo= patientNo;
	}
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
    public String getDoctorNo() {
	    return doctorNo;
    }
    public void setDoctorNo(String doctorNo) {
	    this.doctorNo = doctorNo;
    }
}
class Doctor{
	private String doctorNo;								//ҽʦ���
	private String doctorName;								//ҽʦ����
	private String doctorSection;							//��������
	private String doctorOffice;							//�칫��
	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}
	public String getDoctorNo() {
		return doctorNo;
	}
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

	
	





