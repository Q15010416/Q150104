public class Main {

	public static void main(String[] args) {
		//����һ���������
		MyFrame f = new MyFrame();
	}

}

class Employee{
	private String empNo;				//����
	private String empName;				//����
	private String empSex;				//�Ա�
	private String empBirthday;			//��������
	private String empTelephone;		//��ϵ��ʽ
	private String depNo;				//���ű��
	
	public String getEmpTelephone() {
		return empTelephone;
	}
	public void setEmpTelephone(String empTelephone) {
		this.empTelephone = empTelephone;
	}
	public String getDepNo() {
		return depNo;
	}
	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpSex() {
		return empSex;
	}
	public void setEmpSex(String empSex) {
		this.empSex = empSex;
	}
	public String getEmpBirthday() {
		return empBirthday;
	}
	public void setEmpBirthday(String empBirthday) {
		this.empBirthday = empBirthday;
	}
	
}
   class Attendance{
	private String empNo;									//����
	private String depNo;									//���Ŵ���
	private double performance;								//ҵ��
	private double time;									//����

	
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getDepNo() {
		return depNo;
	}
	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}
	public double getPerformance() {
		return performance;
	}
	public void setPerformance(double performance) {
		this.performance =performance;
	}
	public double getTime() {
		return time;
	}
	public void setTime(double time) {
		this.time =time;
	}
	
}

class Department{
	private String depNo;											//���ű��
	private String depName;										   //��������
	private String location;										//�칫��λ��
	private String manager;											//���Ÿ�����
	
	public String getDepNo() {
		return depNo;
	}
	public void setDepNo(String depNo) {
		this.depNo = depNo;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
}
