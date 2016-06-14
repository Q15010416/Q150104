
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ActionListener {
	Vector<Patient> patientList=new Vector<Patient>();
	Vector<Case> caseList=new Vector<Case>();
	Vector<Doctor> doctorList=new Vector<Doctor>();
	JMenu Menu0=new JMenu("文件");
	JMenuItem menuOpen=new JMenuItem("打开数据文件");
	JMenuItem menuSave=new JMenuItem("保存数据文件");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem patMenu=new JMenuItem("病人信息维护");
	JMenuItem caseMenu=new JMenuItem("病历信息维护");
	JMenuItem doctorMenu=new JMenuItem("医生信息维护");

	MyFrame(){
		super("病人病历管理系统");
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		patMenu.addActionListener(this);
		patMenu.setEnabled(false);
		Menu1.add(patMenu);
		caseMenu.addActionListener(this);
		caseMenu.setEnabled(false);
		Menu1.add(caseMenu);
		doctorMenu.addActionListener(this);
		doctorMenu.setEnabled(false);
		Menu1.add(doctorMenu);
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

		this.setTitle("病人病历管理系统");
		this.setSize(700,360);
		this.setResizable(false);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.readPatFile();
		this.showPatientPanel();
		this.readCaseFile();
		this.showCasePanel();
		this.readDoctorFile();
		this.showDoctorPanel();
		this.setVisible(true);
	}
	void readPatFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Patient.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Patient pat=new Patient();
				String[] temp=s.split(" ");
				pat.setPatientNo(temp[0]);
				pat.setPatientName(temp[1]);
				pat.setPatientSex(temp[2]);
				pat.setPatientCard(temp[3]);
				pat.setPatientAge(temp[4]);
				patientList.add(pat);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	void writePatFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Patient.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<patientList.size();i++){
				Patient pat=(Patient)patientList.get(i);
				writer.write(pat.getPatientNo()+" "+pat.getPatientName()+" "+pat.getPatientSex()+" "+pat.getPatientCard()+" "+pat.getPatientAge()+"\n");
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

	void readCaseFile(){
		try {
			FileInputStream fis = new FileInputStream("Case.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Case cas=new Case();
				String[] temp=s.split(" ");
				cas.setCaseName(temp[0]);
				cas.setDrugs(temp[1]);
				cas.setMoney(Double.parseDouble(temp[2]));
				cas.setDays(Double.parseDouble (temp[3]));
				cas.setOperation(temp[4]);
				caseList.add(cas);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeCaseFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Case.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<caseList.size();i++){
				Case cas=(Case)caseList.get(i);
				writer.write(cas.getCaseName()+" "+cas.getDrugs()+" "+cas.getMoney()+" "+cas.getDays()+" "+cas.getOperation()+"\n");
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
	void readDoctorFile(){
		try{
			FileInputStream fis;
			fis = new FileInputStream("Doctor.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Doctor doc=new Doctor();
				String[] temp=s.split(" ");
				doc.setDoctorName(temp[0]);
				doc.setDoctorSection(temp[1]);
				doc.setDoctorOffice(temp[2]);
				doctorList.add(doc);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeDoctorFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Doctor.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<doctorList.size();i++){
				Doctor doc=(Doctor)doctorList.get(i);
				writer.write(doc.getDoctorName()+" "+doc.getDoctorSection()+" "+doc.getDoctorOffice()+"\n");
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
	void showPatientPanel(){
		PatientPanel patientPanel=new PatientPanel();
		patientPanel.patientList=this.patientList;
		this.add(patientPanel,BorderLayout.CENTER);
		patientPanel.showPatient(0);
		this.setVisible(true);
	}

	void showCasePanel(){
		CasePanel casePanel=new CasePanel();
		casePanel.caseList=this.caseList;
		this.add(casePanel,BorderLayout.CENTER);
		casePanel.showCase(0);
		this.setVisible(true);
	}
	void showDoctorPanel(){
		DoctorPanel doctorPanel=new DoctorPanel();
		doctorPanel.doctorList=this.doctorList;
		this.add(doctorPanel,BorderLayout.CENTER);
		doctorPanel.showDoctor(0);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==patMenu){
			this.getContentPane().removeAll();
			this.showPatientPanel();
		}
		if(e.getSource()==caseMenu){
			this.getContentPane().removeAll();
			this.showCasePanel();
		}
		if(e.getSource()==doctorMenu){
			this.getContentPane().removeAll();
			this.showDoctorPanel();
		}
		if(e.getSource()==menuOpen){
			this.patientList.removeAllElements();
			this.caseList.removeAllElements();
			this.doctorList.removeAllElements();
			this.readPatFile();
			patMenu.setEnabled(true);
			this.readCaseFile();
			caseMenu.setEnabled(true);
			this.readDoctorFile();
			doctorMenu.setEnabled(true);
			menuSave.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功打开数据：\n病人信息"+patientList.size()+"条\n病历信息"+caseList.size()+"条\n医生信息"+doctorList.size()+"条", "打开", JOptionPane.INFORMATION_MESSAGE);
		}


		if(e.getSource()==menuSave){
			this.writePatFile();
			this.writeCaseFile();
			this.writeDoctorFile();
			menuSave.setEnabled(true);
			doctorMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功保存数据：\n病人信息"+patientList.size()+"条\n病历信息"+caseList.size()+"条\n医生信息"+doctorList.size()+"条", "保存", JOptionPane.INFORMATION_MESSAGE);
			if(e.getSource()==menuExit){
				System.exit(0);
			}
		}
	}
}

@SuppressWarnings("serial")
class PatientPanel extends JPanel implements ActionListener {
	private JTextField patientNo=new JTextField();											//编号
	private JTextField patientName=new JTextField();										//姓名
	private JTextField patientSex=new JTextField();											//性别
	private JTextField patientAge=new JTextField();											//年龄
	private JTextField patientCard=new JTextField();										//身份证号
	Vector<Patient> patientList=new Vector<Patient>();
	int count=0,current=0,inserting=0;
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];


	PatientPanel(){
		this.setLayout(null);
		//显示编号
		JLabel lb1=new JLabel("编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		patientNo.setBounds(100,10, 140, 20);
		this.add(patientNo);
		//显示姓名
		JLabel lb2=new JLabel("姓名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		patientName.setBounds(100,60, 140, 20);
		this.add(patientName);
		//显示性别
		JLabel lb3=new JLabel("性别：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		patientSex.setBounds(100,110, 140, 20);
		this.add(patientSex);
		//显示年龄
		JLabel lb4=new JLabel("年龄：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		patientAge.setBounds(100,160, 140, 20);
		this.add(patientAge);
		//显示身份证号
		JLabel lb5=new JLabel("身份证号：");
		lb5.setBounds(30,210, 100, 20);
		this.add(lb5);
		patientCard.setBounds(100,210, 140, 20);
		this.add(patientCard);
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 260, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showPatient(int offset){
		Patient pat=(Patient) patientList.get(offset);
		this.patientNo.setText(pat.getPatientNo());
		this.patientName.setText(pat.getPatientName());
		this.patientSex.setText(pat.getPatientSex());
		this.patientAge.setText(pat.getPatientAge());
		this.patientCard.setText(pat.getPatientCard());
	}
	public void actionPerformed(ActionEvent e) {
		count=this.patientList.size();
		if(e.getSource()==this.btn[0]){
			this.showPatient(0);
			current=0;
		}
		if(e.getSource()==this.btn[1] && current>0){
			this.showPatient(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2] && current<count-1){
			this.showPatient(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showPatient(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.patientNo.setText("");
				this.patientName.setText("");
				this.patientSex.setText("");
				this.patientAge.setText("");
				this.patientCard.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Patient pat=new Patient();
				pat.setPatientNo(this.patientNo.getText().trim());
				pat.setPatientName(this.patientName.getText().trim());
				pat.setPatientSex(this.patientSex.getText().trim());
				pat.setPatientAge(this.patientAge.getText().trim());
				pat.setPatientCard(this.patientCard.getText().trim());
				patientList.add(pat);
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
				Patient pat=(Patient)patientList.get(current);
				pat.setPatientNo(this.patientNo.getText().trim());
				pat.setPatientName(this.patientName.getText().trim());
				pat.setPatientSex(this.patientSex.getText().trim());
				pat.setPatientAge(this.patientAge.getText().trim());
				pat.setPatientAge(this.patientCard.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showPatient(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			patientList.remove(current);
			count--;
			if(count==0){
				this.patientNo.setText("");
				this.patientName.setText("");
				this.patientSex.setText("");
				this.patientAge.setText("");
				this.patientCard.setText("");
			}else{
				if(current>count-1){
					this.showPatient(current-1);
					current=current-1;
				}
				else
					this.showPatient(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class CasePanel extends JPanel implements ActionListener {
	private JTextField caseName=new JTextField();											
	private JTextField drugs=new JTextField();										
	private JTextField money=new JTextField();										
	private JTextField days=new JTextField();												
	private JTextField operation=new JTextField();
	Vector<Case> caseList=new Vector<Case>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	CasePanel(){
		this.setLayout(null);

		JLabel lb1=new JLabel("疾病名称：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		caseName.setBounds(100,10, 100, 20);
		this.add(caseName);

		JLabel lb2=new JLabel("药品：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		drugs.setBounds(100,60, 100, 20);
		this.add(drugs);

		JLabel lb3=new JLabel("医药费用：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		money.setBounds(100,110, 100, 20);
		this.add(money);

		JLabel lb4=new JLabel("住院天数：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		days.setBounds(100,160, 100, 20);
		this.add(days);

		JLabel lb5=new JLabel("手术安排：");
		lb5.setBounds(30, 210, 100, 20);
		this.add(lb4);
		operation.setBounds(100,210, 100, 20);
		this.add(operation);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showCase(int offset){
		Case cas=(Case) caseList.get(offset);
		this.caseName.setText(cas.getCaseName());
		this.drugs.setText(cas.getDrugs());
		this.money.setText(""+cas.getMoney());
		this.days.setText(""+cas.getDays());
		this.operation.setText(cas.getOperation());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.caseList.size();
		if(e.getSource()==this.btn[0]){
			this.showCase(0);
			current=0;                                   
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showCase(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showCase(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showCase(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.caseName.setText("");
				this.drugs.setText("");
				this.money.setText("");
				this.days.setText("");
				this.operation.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Case cas=new Case();
				cas.setCaseName(this.caseName.getText().trim());
				cas.setDrugs(this.drugs.getText().trim());
				cas.setMoney(Double.parseDouble(this.money.getText().trim()));
				cas.setDays(Double.parseDouble(this.days.getText().trim()));
				cas.setOperation(this.operation.getText().trim());

				caseList.add(cas);
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
				Case cas=(Case)caseList.get(current);
				cas.setCaseName(this.caseName.getText().trim());
				cas.setDrugs(this.drugs.getText().trim());
				cas.setMoney(Double.parseDouble(this.money.getText().trim()));
				cas.setDays(Double.parseDouble(this.days.getText().trim()));
				cas.setOperation(this.operation.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showCase(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			caseList.remove(current);
			count--;
			if(count==0){
				this.caseName.setText("");
				this.drugs.setText("");
				this.money.setText("");
				this.days.setText("");
				this.operation.setText("");
			}else{
				if(current>count-1){
					this.showCase(current-1);
					current=current-1;
				}
				else
					this.showCase(current);
			}
		}
		this.repaint();
	}
}
	
@SuppressWarnings("serial")
class DoctorPanel extends JPanel implements ActionListener{	
	private JTextField doctorName=new JTextField();											
	private JTextField doctorSection=new JTextField();										
	private JTextField doctorOffice=new JTextField();										
	
	Vector<Doctor> doctorList=new Vector<Doctor>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	DoctorPanel(){
		this.setLayout(null);

		JLabel lb1=new JLabel("医生姓名：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		doctorName.setBounds(100,10, 100, 20);
		this.add(doctorName);

		JLabel lb2=new JLabel("科室：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		doctorSection.setBounds(100,60, 100, 20);
		this.add(doctorSection);

		JLabel lb3=new JLabel("办公室：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		doctorOffice.setBounds(100,110, 100, 20);
		this.add(doctorOffice);

		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showDoctor(int offset){
		Doctor doc=(Doctor)doctorList.get(offset);
		this.doctorName.setText(doc.getDoctorName());
		this.doctorSection.setText(""+doc.getDoctorSection());
		this.doctorOffice.setText(""+doc.getDoctorOffice());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.doctorList.size();
		if(e.getSource()==this.btn[0]){
			this.showDoctor(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showDoctor(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showDoctor(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showDoctor(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.doctorName.setText("");
				this.doctorSection.setText("");
				this.doctorOffice.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Doctor doc=new Doctor();
				doc.setDoctorName(this.doctorName.getText().trim());
				doc.setDoctorSection(this.doctorSection.getText().trim());
				doc.setDoctorOffice(this.doctorOffice.getText().trim());
				doctorList.add(doc);
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
				Doctor doc=(Doctor)doctorList.get(current);
				doc.setDoctorName(this.doctorName.getText().trim());
				doc.setDoctorSection(this.doctorSection.getText().trim());
				doc.setDoctorOffice(this.doctorOffice.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showDoctor(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			doctorList.remove(current);
			count--;
			if(count==0){
				this.doctorName.setText("");
				this.doctorSection.setText("");
				this.doctorOffice.setText("");
			}else{
				if(current>count-1){
					this.showDoctor(current-1);
					current=current-1;
				}
				else
					this.showDoctor(current);
			}
		}
		this.repaint();
	}
}


