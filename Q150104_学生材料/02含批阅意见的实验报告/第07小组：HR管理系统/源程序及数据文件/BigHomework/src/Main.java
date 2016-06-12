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
   class Attendance{
	private String empNo;									//工号
	private String depNo;									//部门代号
	private double performance;								//业绩
	private double time;									//出勤

	
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
	private String depNo;											//部门编号
	private String depName;										   //部门名称
	private String location;										//办公室位置
	private String manager;											//部门负责人
	
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
