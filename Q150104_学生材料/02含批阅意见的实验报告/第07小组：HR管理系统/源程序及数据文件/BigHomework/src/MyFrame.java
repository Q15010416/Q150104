import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	Vector<Employee> empList=new Vector<Employee>();
	Vector<Attendance> attList=new Vector<Attendance>();
	Vector< Department> depList=new Vector< Department>();
	JMenu Menu0=new JMenu("文件");
	JMenuItem menuOpen=new JMenuItem("打开数据文件");
	JMenuItem menuSave=new JMenuItem("保存数据文件");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem empMenu=new JMenuItem("员工信息维护");
	JMenuItem attendanceMenu=new JMenuItem("考勤信息维护");
	JMenuItem departmentMenu=new JMenuItem("部门信息维护");
	
	public static void main(String[] args) {
		MyFrame f=new MyFrame();
		f.setVisible(true);
	}
	MyFrame(){
		super();
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		empMenu.addActionListener(this);
		empMenu.setEnabled(false);
		Menu1.add(empMenu);
		attendanceMenu.addActionListener(this);
		attendanceMenu.setEnabled(false);
		Menu1.add(attendanceMenu);
		departmentMenu.addActionListener(this);
		departmentMenu.setEnabled(false);
		Menu1.add(departmentMenu);
		menuOpen.addActionListener(this);
		Menu0.add(menuOpen);
		menuSave.addActionListener(this);
		menuSave.setEnabled(false);
		Menu0.add(menuSave);
		Menu0.addSeparator();
		menuExit.addActionListener(this);
		Menu0.add(menuExit);
		menuBar.add(Menu0);
		menuBar.add(Menu1);
		
		
	    
		this.setTitle("HR管理系统");
		this.setResizable(false);
		this.setSize(700,380);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		
	}
	void readEmpFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Employee.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Employee emp=new Employee();
				String[] temp=s.split(" ");
				emp.setEmpNo(temp[0]);
				emp.setEmpName(temp[1]);
				emp.setEmpSex(temp[2]);
				emp.setEmpBirthday(temp[3]);
				emp.setDepNo(temp[4]);
				emp.setEmpTelephone(temp[5]);
				empList.add(emp);
				
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeEmpFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Employee.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<empList.size();i++){
				Employee emp=(Employee)empList.get(i);
				writer.write(emp.getEmpNo()+" "+emp.getEmpName()+" "+emp.getEmpSex()+" "+emp.getEmpBirthday()+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void readAttendanceFile(){
		try {
			FileInputStream fis = new FileInputStream("Attendance.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Attendance attendance=new Attendance();
				String[] temp=s.split(" ");
				attendance.setEmpNo(temp[0]);
				attendance.setDepNo(temp[1]);
				attendance.setPerformance(Double.parseDouble(temp[2]));
			    attendance.setTime(Double.parseDouble(temp[3]));
				attList.add(attendance);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeAttendanceFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Attendance.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<attList.size();i++){
				Attendance attendance=(Attendance)attList.get(i);
				writer.write(attendance.getEmpNo()+" "+attendance.getDepNo()+" "+attendance.getTime()+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void readDepartmentFile(){
		try{
			FileInputStream fis;
			fis = new FileInputStream("Department.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Department department=new Department();
				String[] temp=s.split(" ");
				department.setDepNo(temp[0]);
				department.setDepName(temp[1]);
				department.setLocation(temp[2]);
				department.setManager(temp[3]);
				depList.add(department);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeDepartmentFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Department.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<depList.size();i++){
			Department department=(Department)depList.get(i);
			writer.write(department.getDepNo()+" "+department.getDepName()+" "+String.format("%.1f", department.getLocation())+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void showEmpPanel(){
		EmpPanel empPanel=new EmpPanel();
		empPanel.empList=this.empList;
		this.add(empPanel,BorderLayout.CENTER);
		empPanel.showEmployee(0);
		this.setVisible(true);
	}
	
	void showAttendancePanel(){
		AttendancePanel attendancePanel=new AttendancePanel();
		attendancePanel.attList=this.attList;
		this.add(attendancePanel,BorderLayout.CENTER);
		attendancePanel.showAttendance(0);
		this.setVisible(true);
	}
	
	void showDepartmentPanel(){
		DepartmentPanel departmentPanel=new DepartmentPanel();
		departmentPanel.depList=this.depList;
		this.add(departmentPanel,BorderLayout.CENTER);
		departmentPanel.showDepartment(0);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==empMenu){
			this.getContentPane().removeAll();
			this.showEmpPanel();
		}
		if(e.getSource()==attendanceMenu){
			this.getContentPane().removeAll();
			this.showAttendancePanel();
		}
		if(e.getSource()==departmentMenu){
			this.getContentPane().removeAll();
			this.showDepartmentPanel();
		}
		if(e.getSource()==menuOpen){
			this.empList.removeAllElements();
			this.depList.removeAllElements();
			this.attList.removeAllElements();
			this.readEmpFile();
			empMenu.setEnabled(true);
			this.readAttendanceFile();
			attendanceMenu.setEnabled(true);
			this.readDepartmentFile();
			departmentMenu.setEnabled(true);
			menuSave.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功打开数据：\n学生信息"+empList.size()+"条\n课程信息"+attList.size()+"条\n成绩信息"+depList.size()+"条", "打开", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writeEmpFile();
			this.writeAttendanceFile();
			this.writeDepartmentFile();
			menuSave.setEnabled(true);
			departmentMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功保存数据：\n员工信息"+empList.size()+"条\n考勤信息"+attList.size()+"条\n部门信息"+depList.size()+"条", "保存", JOptionPane.INFORMATION_MESSAGE);
		}			
		if(e.getSource()==menuExit){
			System.exit(0);
		}
	}
}

@SuppressWarnings("serial")
class DepartmentPanel extends JPanel implements ActionListener{
	public Vector<Department> depList;
	private JTextField depNo=new JTextField();						  		//部门编号
	private JTextField depName=new JTextField();							//部门名称
	private JTextField location=new JTextField();							//办公室
	private JTextField manager=new JTextField();							//部门主管
	
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	DepartmentPanel(){
		this.setLayout(null);
		//显示工号
		JLabel lb1=new JLabel("工号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		depNo.setBounds(100,10, 100, 20);
		this.add(depNo);
		//显示部门名称
		JLabel lb2=new JLabel("部门名称：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		depName.setBounds(100,60, 100, 20);
		this.add(depName);
		//显示办公室
		JLabel lb3=new JLabel("办公室：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		location.setBounds(100,110, 100, 20);
		this.add(location);
		//
		JLabel lb4=new JLabel("部门负责人：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		manager.setBounds(100,160, 100, 20);
		this.add(manager);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
		
	}
	
	void showDepartment(int offset){
		Department department=(Department)depList.get(offset);
		this.depNo.setText(department.getDepNo());
		this.depName.setText(""+department.getDepName());
		this.location.setText(""+department.getLocation());
		this.manager.setText(""+department.getManager());
		
	}

	public void actionPerformed(ActionEvent e) {
		count=this.depList.size();
		if(e.getSource()==this.btn[0]){
			this.showDepartment(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showDepartment(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showDepartment(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showDepartment(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.depNo.setText("");
				this.depName.setText("");
				this.location.setText("");
				this.manager.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Department department=new Department();
				department.setDepNo(this.depNo.getText().trim());
				department.setDepName(this.depName.getText().trim());
				department.setLocation(this.location.getText().trim());
				department.setManager(this.manager.getText().trim());
				depList.add(department);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}			
		}
		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Department department=(Department)depList.get(current);
				department.setDepNo(this.depNo.getText().trim());
				department.setDepName(this.depName.getText().trim());
				department.setLocation(this.location.getText().trim());
				department.setManager(this.manager.getText().trim());

			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showDepartment(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			depList.remove(current);
			count--;
			if(count==0){
				this.depNo.setText("");
				this.depName.setText("");
				this.location.setText("");
				this.manager.setText("");
			}else{
				if(current>count-1){
					this.showDepartment(current-1);
					current=current-1;
				}
				else
					this.showDepartment(current);
			}
		}
		this.repaint();
	}
}
@SuppressWarnings("serial")
class AttendancePanel extends JPanel implements ActionListener {
	private JTextField empNo=new JTextField();											//学号
	private JTextField depNo=new JTextField();										//课程名称
	private JTextField performance=new JTextField();										//学分
	private JTextField time=new JTextField();												//任课教师
	Vector<Attendance> attList=new Vector<Attendance>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	AttendancePanel(){
		this.setLayout(null);
		//显示工号
		JLabel lb1=new JLabel("工号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		empNo.setBounds(100,10, 100, 20);
		this.add(empNo);
		//显示部门编号
		JLabel lb2=new JLabel("部门编号：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		depNo.setBounds(100,60, 100, 20);
		this.add(depNo);
		//显示业绩
		JLabel lb3=new JLabel("业绩：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		performance.setBounds(100,110, 100, 20);
		this.add(performance);
		//显示出勤
		JLabel lb4=new JLabel("出勤：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		time.setBounds(100,160, 100, 20);
		this.add(time);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}
	
	void showAttendance(int offset){
		Attendance attendance=(Attendance) attList.get(offset);
		this.empNo.setText(attendance.getEmpNo());
		this.depNo.setText(attendance.getDepNo());
		this.performance.setText(""+attendance.getPerformance());
		this.time.setText(""+attendance.getTime());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.attList.size();
		if(e.getSource()==this.btn[0]){
			this.showAttendance(0);
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showAttendance(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showAttendance(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showAttendance(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.empNo.setText("");
				this.depNo.setText("");
				this.performance.setText("");
				this.time.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Attendance attendance=new Attendance();
				attendance.setEmpNo(this.empNo.getText().trim());
				attendance.setDepNo(this.depNo.getText().trim());
				attendance.setPerformance(Double.parseDouble(this.performance.getText().trim()));
				attendance.setTime(Double.parseDouble(this.time.getText().trim()));
				attList.add(attendance);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}	
		}

		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Attendance attendance=(Attendance)attList.get(current);
				attendance.setEmpNo(this.empNo.getText().trim());
				attendance.setDepNo(this.depNo.getText().trim());
				attendance.setPerformance(Double.parseDouble(this.performance.getText().trim()));
				attendance.setTime(Double.parseDouble(this.time.getText().trim()));
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showAttendance(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			attList.remove(current);
			count--;
			if(count==0){
				this.empNo.setText("");
				this.depNo.setText("");
				this.performance.setText("");
				this.time.setText("");
			}else{
				if(current>count-1){
					this.showAttendance(current-1);
					current=current-1;
				}
				else
					this.showAttendance(current);
			}
		}
		this.repaint();
	}
}





	class EmpPanel extends JPanel implements ActionListener {
 

	Vector empList=new Vector();
	
	private JTextField empNo=new JTextField();											//学号
	private JTextField empName=new JTextField();										//姓名
	private JTextField empSex=new JTextField();											//性别
	private JTextField empBirthday=new JTextField();
	private JTextField empTelephone=new JTextField();
	private JTextField depNo=new JTextField();
	 
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn =new JButton[btnStr.length];
	int count=0,current=0,inserting=0;

	
	EmpPanel(){
		this.setLayout(null);
		//显示工号
		JLabel lb1=new JLabel("工号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		empNo.setBounds(100,10, 100, 20);
		this.add(empNo);
		//显示姓名
		JLabel lb2=new JLabel("姓名：");
		lb2.setBounds(30, 50, 100, 20);
		this.add(lb2);
		empName.setBounds(100,50, 100, 20);
		this.add(empName);
		//显示性别
		JLabel lb3=new JLabel("性别：");
		lb3.setBounds(30, 90, 100, 20);
		this.add(lb3);
		empSex.setBounds(100,90, 100, 20);
		this.add(empSex);
		//显示出生日期
		JLabel lb4=new JLabel("生日：");
		lb4.setBounds(30, 130, 100, 20);
		this.add(lb4);
		empBirthday.setBounds(100,130, 100, 20);
		this.add(empBirthday);
		//显示所属部门
		JLabel lb5=new JLabel("部门：");
		lb5.setBounds(30, 170, 100, 20);
		this.add(lb5);
		depNo.setBounds(100,170, 100, 20);
		this.add(depNo);
		//显示联系方式
        JLabel lb6=new JLabel("联系方式：");
		lb6.setBounds(30, 210, 100, 20);
		this.add(lb6);
		empTelephone.setBounds(100,210, 100, 20);
		this.add(empTelephone);
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(50+i*90, 250, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
			
		}
		
	}
	
	void showEmployee(int offset){
		Employee emp=(Employee) empList.get(offset);
		this.empNo.setText(emp.getEmpNo());
		this.empName.setText(emp.getEmpName());
		this.empSex.setText(emp.getEmpSex());
		this.empBirthday.setText(emp.getEmpBirthday());
		this.depNo.setText(emp.getDepNo());
		this.empTelephone.setText(emp.getEmpTelephone());
		
	}


   public void actionPerformed(ActionEvent e) {
	count=this.empList.size();
	if(e.getSource()==this.btn[0]){
		this.showEmployee(0);
		current=0;
	}
	if(e.getSource()==this.btn[1] && current>0){
		this.showEmployee(current-1);
		current=current-1;
	}
	if(e.getSource()==this.btn[2] && current<count-1){
		this.showEmployee(current+1);
		current=current+1;
	}
	if(e.getSource()==this.btn[3]){
		this.showEmployee(count-1);
		current=count-1;
	}
	if(e.getSource()==this.btn[4]){
		if(this.inserting==0){
			this.empNo.setText("");
			this.empName.setText("");
			this.empSex.setText("");
			this.empBirthday.setText("");
			this.depNo.setText("");
			this.empTelephone.setText("");
			btn[4].setText("保存");
			btn[5].setText("取消");
			this.inserting=1;
		}else{
			Employee emp=new Employee();
			emp.setEmpNo(this.empNo.getText().trim());
			emp.setEmpName(this.empName.getText().trim());
			emp.setEmpSex(this.empSex.getText().trim());
			emp.setEmpBirthday(this.empBirthday.getText().trim());
			emp.setDepNo(this.depNo.getText().trim());
			emp.setEmpTelephone(this.empTelephone.getText().trim());
			empList.add(emp);
			count++;
			current=count-1;
			btn[4].setText("添加");
			btn[5].setText("修改");
			this.inserting=0;
		}
		for(int i=0;i<btn.length;i++){
			if(i==4||i==5) continue;
			btn[i].setEnabled(!btn[i].isEnabled());
		}
	}

	if(e.getSource()==this.btn[5]){
		if(this.inserting==0){
          Employee emp= (Employee)empList.get(current);
			emp.setEmpNo(this.empNo.getText().trim());
			emp.setEmpName(this.empName.getText().trim());
			emp.setEmpSex(this.empSex.getText().trim());
			emp.setEmpBirthday(this.empBirthday.getText().trim());
			emp.setDepNo(this.depNo.getText().trim());
			emp.setEmpTelephone(this.empTelephone.getText().trim());
			
		}else{
			btn[4].setText("添加");
			btn[5].setText("修改");
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}
			this.inserting=0;
			this.showEmployee(current);
		}
	}
	if(e.getSource()==this.btn[6]){
		if(count==0)
			return;
		empList.remove(current);
		count--;
		if(count==0){
			this.empNo.setText("");
			this.empName.setText("");
			this.empSex.setText("");
			this.empBirthday.setText("");
			this.depNo.setText("");
			this.empTelephone.setText("");
		}else{
			if(current>count-1){
				this.showEmployee(current-1);
				current=current-1;
			}
			else
				this.showEmployee(current);
		}
	}
	this.repaint();
}
}



