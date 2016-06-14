import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {
	Vector<Employee> empList=new Vector<Employee>();
	Vector<Attendance> attList=new Vector<Attendance>();
	Vector< Department> depList=new Vector< Department>();
	JMenu Menu0=new JMenu("�ļ�");
	JMenuItem menuOpen=new JMenuItem("�������ļ�");
	JMenuItem menuSave=new JMenuItem("���������ļ�");
	JMenuItem menuExit=new JMenuItem("�˳�ϵͳ");
	JMenu Menu1=new JMenu("��Ϣά��");
	JMenuItem empMenu=new JMenuItem("Ա����Ϣά��");
	JMenuItem attendanceMenu=new JMenuItem("������Ϣά��");
	JMenuItem departmentMenu=new JMenuItem("������Ϣά��");
	JMenuItem empQueryMenu=new JMenuItem("Ա����Ϣ��ѯ");
	JMenuItem attendanceQueryMenu=new JMenuItem("ҵ����Ϣ��ѯ");
	JMenuItem menuChangePwd=new JMenuItem("���Ĺ�������");
	JMenu Menu2=new JMenu("���ݲ�ѯ");


	
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
        empQueryMenu.addActionListener(this);
		empQueryMenu.setEnabled(false);
		Menu2.add(empQueryMenu);
		attendanceQueryMenu.addActionListener(this);
		attendanceQueryMenu.setEnabled(false);
		Menu2.add(attendanceQueryMenu);
		menuBar.add(Menu0);
		menuBar.add(Menu1);
		menuBar.add(Menu2);

		
		
	    
		this.setTitle("HR����ϵͳ");
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
	void showEmpQueryPanel(){
		EmpQueryPanel empQPanel=new EmpQueryPanel();
		empQPanel.empList=this.empList;
		empQPanel.attList=this.attList;
		empQPanel.depList=this.depList;
		this.add(empQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	void showAttendanceQueryPanel(){
		AttendanceQueryPanel attendanceQPanel=new AttendanceQueryPanel();
		attendanceQPanel.empList=this.empList;
		attendanceQPanel.attList=this.attList;
		attendanceQPanel.depList=this.depList;
		this.add(attendanceQPanel, BorderLayout.CENTER);
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
			this.showDepartmentPanel();}
	   if(e.getSource()==empQueryMenu){
				this.getContentPane().removeAll();
				this.showEmpQueryPanel();
			}
			if(e.getSource()==attendanceQueryMenu){
				this.getContentPane().removeAll();
				this.showAttendanceQueryPanel();
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
			empQueryMenu.setEnabled(true);
			attendanceQueryMenu.setEnabled(true);
            JOptionPane.showMessageDialog(null, "���Ѿ��ɹ������ݣ�\nѧ����Ϣ"+empList.size()+"��\n�γ���Ϣ"+attList.size()+"��\n�ɼ���Ϣ"+depList.size()+"��", "��", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writeEmpFile();
			this.writeAttendanceFile();
			this.writeDepartmentFile();
			menuSave.setEnabled(true);
			departmentMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "���Ѿ��ɹ��������ݣ�\nԱ����Ϣ"+empList.size()+"��\n������Ϣ"+attList.size()+"��\n������Ϣ"+depList.size()+"��", "����", JOptionPane.INFORMATION_MESSAGE);
		}			
		if(e.getSource()==menuExit){
			System.exit(0);
		}
		if(e.getSource()==menuChangePwd){
			String s=JOptionPane.showInputDialog(null, "������������", "����Ա�����޸�", JOptionPane.PLAIN_MESSAGE);
			if(s==null)return;
			s=s.trim();
			if(s.length()==0){
				JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�", "HR����ϵͳ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String clearText = 	"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			String resultText=		"";
			for(int i=0;i<s.length();i++){
				char c=s.charAt(i);
				if(clearText.indexOf(""+c)==-1){
					JOptionPane.showMessageDialog(null, "�����а����Ƿ��ַ�", "HR����ϵͳ", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					resultText+=""+cipherText.charAt(clearText.indexOf(""+c));
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream("Password.txt");
				OutputStreamWriter dos=new OutputStreamWriter(fos);
				BufferedWriter writer=new BufferedWriter(dos);
				writer.write(resultText);
				writer.close();dos.close();fos.close();
				JOptionPane.showMessageDialog(null, "�����޸ĳɹ���", "ѧ����Ϣ����ϵͳ", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}			
		if(e.getSource()==menuExit){
			System.exit(0);
		}
	}
}



@SuppressWarnings("serial")
class DepartmentPanel extends JPanel implements ActionListener{
	public Vector<Department> depList;
	private JTextField depNo=new JTextField();						  		//���ű��
	private JTextField depName=new JTextField();							//��������
	private JTextField location=new JTextField();							//�칫��
	private JTextField manager=new JTextField();							//��������
	
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	DepartmentPanel(){
		this.setLayout(null);
		//��ʾ����
		JLabel lb1=new JLabel("���ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		depNo.setBounds(100,10, 100, 20);
		this.add(depNo);
		//��ʾ��������
		JLabel lb2=new JLabel("�������ƣ�");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		depName.setBounds(100,60, 100, 20);
		this.add(depName);
		//��ʾ�칫��
		JLabel lb3=new JLabel("�칫�ң�");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		location.setBounds(100,110, 100, 20);
		this.add(location);
		//
		JLabel lb4=new JLabel("���Ÿ����ˣ�");
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
				btn[4].setText("����");
				btn[5].setText("ȡ��");
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
				btn[4].setText("���");
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
				btn[4].setText("���");
				btn[5].setText("�޸�");
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
	private JTextField empNo=new JTextField();											//ѧ��
	private JTextField depNo=new JTextField();										//�γ�����
	private JTextField performance=new JTextField();										//ѧ��
	private JTextField time=new JTextField();												//�ον�ʦ
	Vector<Attendance> attList=new Vector<Attendance>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	AttendancePanel(){
		this.setLayout(null);
		//��ʾ����
		JLabel lb1=new JLabel("���ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		empNo.setBounds(100,10, 100, 20);
		this.add(empNo);
		//��ʾ���ű��
		JLabel lb2=new JLabel("���ű�ţ�");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		depNo.setBounds(100,60, 100, 20);
		this.add(depNo);
		//��ʾҵ��
		JLabel lb3=new JLabel("ҵ����");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		performance.setBounds(100,110, 100, 20);
		this.add(performance);
		//��ʾ����
		JLabel lb4=new JLabel("���ڣ�");
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
				btn[4].setText("����");
				btn[5].setText("ȡ��");
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
				btn[4].setText("���");
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
				btn[4].setText("���");
				btn[5].setText("�޸�");
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
	
	private JTextField empNo=new JTextField();											//ѧ��
	private JTextField empName=new JTextField();										//����
	private JTextField empSex=new JTextField();											//�Ա�
	private JTextField empBirthday=new JTextField();
	private JTextField empTelephone=new JTextField();
	private JTextField depNo=new JTextField();
	 
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn =new JButton[btnStr.length];
	int count=0,current=0,inserting=0;

	
	EmpPanel(){
		this.setLayout(null);
		//��ʾ����
		JLabel lb1=new JLabel("���ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		empNo.setBounds(100,10, 100, 20);
		this.add(empNo);
		//��ʾ����
		JLabel lb2=new JLabel("������");
		lb2.setBounds(30, 50, 100, 20);
		this.add(lb2);
		empName.setBounds(100,50, 100, 20);
		this.add(empName);
		//��ʾ�Ա�
		JLabel lb3=new JLabel("�Ա�");
		lb3.setBounds(30, 90, 100, 20);
		this.add(lb3);
		empSex.setBounds(100,90, 100, 20);
		this.add(empSex);
		//��ʾ��������
		JLabel lb4=new JLabel("���գ�");
		lb4.setBounds(30, 130, 100, 20);
		this.add(lb4);
		empBirthday.setBounds(100,130, 100, 20);
		this.add(empBirthday);
		//��ʾ��������
		JLabel lb5=new JLabel("���ţ�");
		lb5.setBounds(30, 170, 100, 20);
		this.add(lb5);
		depNo.setBounds(100,170, 100, 20);
		this.add(depNo);
		//��ʾ��ϵ��ʽ
        JLabel lb6=new JLabel("��ϵ��ʽ��");
		lb6.setBounds(30, 210, 100, 20);
		this.add(lb6);
		empTelephone.setBounds(100,210, 100, 20);
		this.add(empTelephone);
		//��ʾ���ư�ť
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
			btn[4].setText("����");
			btn[5].setText("ȡ��");
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
			btn[4].setText("���");
			btn[5].setText("�޸�");
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
			btn[4].setText("���");
			btn[5].setText("�޸�");
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
	@SuppressWarnings("serial")
	class EmpQueryPanel extends JPanel implements ActionListener{
		JComboBox searchBy=new JComboBox();
		JTextField keyword=new JTextField(10);
		JTextArea result=new JTextArea();
		Vector<Employee> empList=new Vector<Employee>();
		Vector<Attendance> attList=new Vector<Attendance>();
		Vector<Department> depList=new Vector<Department>();
		EmpQueryPanel(){
			this.setLayout(new BorderLayout());
			JPanel empPanel=new JPanel();
			empPanel.setLayout(new FlowLayout());
			searchBy.addItem("�����Ų�ѯ");
			searchBy.addItem("��������ѯ");		
			empPanel.add(searchBy);
			empPanel.add(keyword);
			JButton btn=new JButton("��ѯ");
			btn.addActionListener(this);
			empPanel.add(btn);
			this.add(empPanel, BorderLayout.NORTH);
			JScrollPane scrollPane=new JScrollPane(result);
			this.add(scrollPane, BorderLayout.CENTER);
		}

		public void actionPerformed(ActionEvent arg0) {
			String str="����\t����\t�Ա�\t�������ţ�����\n";
			for(int i=0;i<empList.size();i++){
				Employee emp=(Employee)this.empList.get(i);
				if(this.searchBy.getSelectedIndex()==0){
					if(emp.getEmpNo().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
						str=str+emp.getEmpNo()+"\t"+emp.getEmpName()+"\t"+emp.getEmpSex()+"\t";
					}else
						continue;
				}if(this.searchBy.getSelectedIndex()==1){
					if(emp.getEmpName().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
						str=str+emp.getEmpNo()+"\t"+emp.getEmpName()+"\t"+emp.getEmpSex()+"\t";
					}else
						continue;
				}
				for(int j=0;j<attList.size();j++){
					Attendance attendance=(Attendance)attList.get(j);
					if(attendance.getEmpNo().equals(emp.getEmpNo())){
						str=str+attendance.getEmpNo()+"��"+attendance.getTime()+"  ";
					}
				}
				str+="\n";
			}
			this.result.setText(str);
		}
		
		String getDepartmentName(String depNo){
			for(int i=0;i<depList.size();i++){
				Department department=(Department)this.depList.get(i);
				if(depNo.equals(department.getDepNo())){
					return department.getDepName();
				}
			}
			return null;
		}
	}

	@SuppressWarnings("serial")
	class AttendanceQueryPanel extends JPanel implements ActionListener{
		JTextField keyword=new JTextField(10);
		JTextArea result=new JTextArea();
		Vector<Employee> empList=new Vector<Employee>();
		Vector<Attendance> attList=new Vector<Attendance>();
		Vector<Department> depList=new Vector<Department>();
		AttendanceQueryPanel(){
			this.setLayout(new BorderLayout());
			JPanel subPanel=new JPanel();
			subPanel.setLayout(new FlowLayout());
			subPanel.add(new JLabel("���ű�ţ�"));
			subPanel.add(keyword);
			JButton btn=new JButton("��ѯ");
			btn.addActionListener(this);
			subPanel.add(btn);
			this.add(subPanel, BorderLayout.NORTH);
			JScrollPane scrollPane=new JScrollPane(result);
			this.add(scrollPane, BorderLayout.CENTER);
		}

		public void actionPerformed(ActionEvent arg0) {
			if(keyword.getText().length()!=7){
				JOptionPane.showMessageDialog(null, "��������ȷ�Ĳ��ű��", "HR����ϵͳ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			double max,min,sum,average;
			int count;
			String str="���ű��\t��������\t���ҵ��\t���ҵ��\tƽ��ҵ��\n";
			Department dep=null;
			Attendance attendance=null;
			for(int i=0;i<depList.size();i++){
				dep=depList.get(i);
				max=0;min=100;sum=0;average=0;count=0;
				for(int j=0;j<attList.size();j++){
					attendance=attList.get(j);
					if(attendance.getEmpNo().startsWith(this.keyword.getText().trim())&&attendance.getDepNo().equals(dep.getDepNo())){
						if(attendance.getPerformance()>max)
							max=attendance.getPerformance();
						if(attendance.getPerformance()<min)
							min=attendance.getPerformance();
						sum+=attendance.getPerformance();
						count++;
					}
				}
				if(count!=0){
					DecimalFormat df=new DecimalFormat("#.0");
					average=sum/count;
					str+=dep.getDepNo()+"\t"+dep.getDepName()+"\t"+df.format(max)+"\t"+df.format(min)+"\t"+df.format(average)+"\n";
				}
			}
			
			this.result.setText(str);
		}
	}

	class LoginFrame extends JFrame implements ActionListener{
		JTextField username=new JTextField(10);
		JPasswordField pwd=new JPasswordField(10);
		LoginFrame(){
			this.setTitle("ϵͳ��¼");
			this.setResizable(false);
			this.setSize(250,130);
			this.setLocationRelativeTo(this.getOwner());
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JLabel lb1=new JLabel("�û�����");
			JLabel lb2=new JLabel("�ܡ��룺");
			JButton btn=new JButton("��¼");
			btn.addActionListener(this);
			Container c=this.getContentPane();
			c.setLayout(null);
			lb1.setBounds(30, 10, 100, 20);
			c.add(lb1);
			username.setBounds(120, 10, 100, 20);
			username.addActionListener(this);
			c.add(username);
			lb2.setBounds(30, 40, 100, 20);
			c.add(lb2);
			pwd.setBounds(120, 40, 100, 20);
			pwd.addActionListener(this);
			c.add(pwd);
			btn.setBounds(80, 70, 90, 20);
			c.add(btn);
			this.setVisible(true);
			this.username.requestFocus();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==this.username){
				this.pwd.requestFocus();
				return;
			}
			try {
				FileInputStream fis;
				fis = new FileInputStream("Password.txt");
				InputStreamReader dis=new InputStreamReader(fis);
				BufferedReader reader=new BufferedReader(dis);
				String s;
				String clearText = 	"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
				if((s=reader.readLine())!=null){
					if(username.getText().trim().equals("admin")){
						boolean isCorrect=true;
						char[] ch1=pwd.getPassword();
						char[] ch2=s.toCharArray();
						if(ch1.length==ch2.length){
							for(int i=0;i<ch1.length;i++){
								if(clearText.indexOf(ch1[i])!=cipherText.indexOf(ch2[i])){
									isCorrect=false;
									break;
								}
							}
						}else{
							isCorrect=false;
						}
						if(isCorrect){
							this.setVisible(false);
							this.dispose();
							MyFrame f=new MyFrame();
							f.setVisible(true);
						}else{
							JOptionPane.showMessageDialog(null, "����д�����벻��ȷ", "�û���¼", JOptionPane.WARNING_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "����д���û�������ȷ", "�û���¼", JOptionPane.WARNING_MESSAGE);
						this.username.requestFocus();
					}
				}
				reader.close();
				dis.close();
				fis.close();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


