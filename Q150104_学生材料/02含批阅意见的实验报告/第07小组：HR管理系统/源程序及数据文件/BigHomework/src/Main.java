public class Main {

	public static void main(String[] args) {
		//建立一个窗体对象
		MyFrame f = new MyFrame();
	}

}

class Employee{
	private String empNo;				//工号
	private String empName;				//姓名
	private String empSex;				//性别
	private String empBirthday;			//出生日期
	private String empTelephone;		//联系方式
	private String depNo;				//部门编号
	
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