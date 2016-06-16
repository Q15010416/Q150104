
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
import java.text.DecimalFormat;

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
	JMenuItem menuChangePwd=new JMenuItem("更改管理密码");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem patMenu=new JMenuItem("病人信息维护");
	JMenuItem caseMenu=new JMenuItem("病历信息维护");
	JMenuItem doctorMenu=new JMenuItem("医生信息维护");
	JMenu Menu2=new JMenu("数据查询");
	JMenuItem patQueryMenu=new JMenuItem("病人信息查询");
	JMenuItem casQueryMenu=new JMenuItem("费用信息查询");


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
		Menu0.addSeparator();
		Menu0.add(menuChangePwd);
		menuChangePwd.addActionListener(this);
		Menu0.addSeparator();
		patQueryMenu.addActionListener(this);
		patQueryMenu.setEnabled(false);
		Menu2.add(patQueryMenu);
		casQueryMenu.addActionListener(this);
		casQueryMenu.setEnabled(false);
		Menu2.add(casQueryMenu);

		menuExit.addActionListener(this);
		Menu0.add(menuExit);
		menuBar.add(Menu0);
		menuBar.add(Menu1);
		menuBar.add(Menu2);


		this.setTitle("病人病历管理系统");
		this.setSize(700,360);
		this.setResizable(false);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
				cas.setPatientNo(temp[0]);
				cas.setDoctorNo(temp[1]);
				cas.setCaseName(temp[2]);
				cas.setDrugs(temp[3]);
				cas.setMoney(Double.parseDouble(temp[4]));
				cas.setDays(Double.parseDouble (temp[5]));
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
				writer.write(cas.getPatientNo()+" "+cas.getDoctorNo()+" "+cas.getCaseName()+" "+cas.getDrugs()+" "+cas.getMoney()+" "+cas.getDays()+"\n");
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
				doc.setDoctorNo(temp[0]);
				doc.setDoctorName(temp[1]);
				doc.setDoctorSection(temp[2]);
				doc.setDoctorOffice(temp[3]);
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
				writer.write(doc.getDoctorNo()+" "+doc.getDoctorName()+" "+doc.getDoctorSection()+" "+doc.getDoctorOffice()+"\n");
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


	void showPatQueryPanel(){
		PatQueryPanel patQPanel=new PatQueryPanel();
		patQPanel.patientList=this.patientList;
		patQPanel.caseList=this.caseList;
		patQPanel.doctorList=this.doctorList;
		this.add(patQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	void showCasQueryPanel(){
		CasQueryPanel caseQPanel=new CasQueryPanel();
		caseQPanel.patientList=this.patientList;
		caseQPanel.caseList=this.caseList;
		caseQPanel.doctorList=this.doctorList;
		this.add(caseQPanel, BorderLayout.CENTER);
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
		if(e.getSource()==patQueryMenu){
			this.getContentPane().removeAll();
			this.showPatQueryPanel();
		}
		if(e.getSource()==casQueryMenu){
			this.getContentPane().removeAll();
			this.showCasQueryPanel();
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
			patQueryMenu.setEnabled(true);
			casQueryMenu.setEnabled(true);
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
			if(e.getSource()==menuChangePwd){
				String s=JOptionPane.showInputDialog(null, "请输入新密码", "管理员密码修改", JOptionPane.PLAIN_MESSAGE);
				if(s==null)return;
				s=s.trim();
				if(s.length()==0){
					JOptionPane.showMessageDialog(null, "密码不能为空！", "学生信息管理系统", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String clearText = 	"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
				String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
				String resultText=		"";
				for(int i=0;i<s.length();i++){
					char c=s.charAt(i);
					if(clearText.indexOf(""+c)==-1){
						JOptionPane.showMessageDialog(null, "密码中包含非法字符", "学生信息管理系统", JOptionPane.ERROR_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "密码修改成功！", "学生信息管理系统", JOptionPane.INFORMATION_MESSAGE);
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

	

}

@SuppressWarnings("serial")
class CasePanel extends JPanel implements ActionListener {
	private JTextField patientNo=new JTextField();
	private JTextField doctorNo=new JTextField();
	private JTextField caseName=new JTextField();											
	private JTextField drugs=new JTextField();										
	private JTextField money=new JTextField();										
	private JTextField days=new JTextField();
	Vector<Case> caseList=new Vector<Case>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	CasePanel(){
		this.setLayout(null);
		JLabel lb1=new JLabel("病人编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		patientNo.setBounds(100,10, 100, 20);
		this.add(patientNo);

		JLabel lb2=new JLabel("医师编号：");
		lb2.setBounds(30, 45, 100, 20);
		this.add(lb2);
		doctorNo.setBounds(100 ,45, 100, 20);
		this.add(doctorNo);

		JLabel lb3=new JLabel("疾病名称：");
		lb3.setBounds(30, 90, 100, 20);
		this.add(lb3);
		caseName.setBounds(100,90, 100, 20);
		this.add(caseName);

		JLabel lb4=new JLabel("药品：");
		lb4.setBounds(30, 135, 100, 20);
		this.add(lb4);
		drugs.setBounds(100,135, 100, 20);
		this.add(drugs);

		JLabel lb5=new JLabel("医药费用：");
		lb5.setBounds(30, 180, 100, 20);
		this.add(lb5);
		money.setBounds(100,180, 100, 20);
		this.add(money);

		JLabel lb6=new JLabel("住院天数：");
		lb6.setBounds(30, 225, 100, 20);
		this.add(lb6);
		days.setBounds(100,225, 100, 20);
		this.add(days);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 260, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showCase(int offset){
		Case cas=(Case) caseList.get(offset);
		this.patientNo.setText(cas.getPatientNo());
		this.doctorNo.setText(cas.getDoctorNo());
		this.caseName.setText(cas.getCaseName());
		this.drugs.setText(cas.getDrugs());
		this.money.setText(""+cas.getMoney());
		this.days.setText(""+cas.getDays());
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
				this.patientNo.setText("");
				this.doctorNo.setText("");
				this.caseName.setText("");
				this.drugs.setText("");
				this.money.setText("");
				this.days.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Case cas=new Case();
				cas.setPatientNo(this.patientNo.getText().trim());
				cas.setDoctorNo(this.doctorNo.getText().trim());
				cas.setCaseName(this.caseName.getText().trim());
				cas.setDrugs(this.drugs.getText().trim());
				cas.setMoney(Double.parseDouble(this.money.getText().trim()));
				cas.setDays(Double.parseDouble(this.days.getText().trim()));

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
				cas.setPatientNo(this.patientNo.getText().trim());
				cas.setDoctorNo(this.doctorNo.getText().trim());
				cas.setCaseName(this.caseName.getText().trim());
				cas.setDrugs(this.drugs.getText().trim());
				cas.setMoney(Double.parseDouble(this.money.getText().trim()));
				cas.setDays(Double.parseDouble(this.days.getText().trim()));
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
				this.patientNo.setText("");
				this.doctorNo.setText("");
				this.caseName.setText("");
				this.drugs.setText("");
				this.money.setText("");
				this.days.setText("");

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
	private JTextField doctorNo=new JTextField();
	private JTextField doctorName=new JTextField();											
	private JTextField doctorSection=new JTextField();										
	private JTextField doctorOffice=new JTextField();										

	Vector<Doctor> doctorList=new Vector<Doctor>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	DoctorPanel(){
		this.setLayout(null);

		JLabel lb1=new JLabel("医师编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		doctorNo.setBounds(100,10, 100, 20);
		this.add(doctorNo);

		JLabel lb2=new JLabel("医师姓名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		doctorName.setBounds(100,60, 100, 20);
		this.add(doctorName);


		JLabel lb3=new JLabel("科室：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		doctorSection.setBounds(100,110, 100, 20);
		this.add(doctorSection);

		JLabel lb4=new JLabel("办公室：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		doctorOffice.setBounds(100,160, 100, 20);
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
		this.doctorNo.setText(doc.getDoctorNo());
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
				this.doctorNo.setText("");
				this.doctorName.setText("");
				this.doctorSection.setText("");
				this.doctorOffice.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Doctor doc=new Doctor();
				doc.setDoctorNo(this.doctorNo.getText().trim());
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
				doc.setDoctorNo(this.doctorNo.getText().trim());
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
@SuppressWarnings("serial")
class PatQueryPanel extends JPanel implements ActionListener{
	@SuppressWarnings("rawtypes")
	JComboBox searchBy=new JComboBox();
	JTextField keyword=new JTextField(10);
	JTextArea result=new JTextArea();
	Vector<Patient> patientList=new Vector<Patient>();
	Vector<Case> caseList=new Vector<Case>();
	Vector<Doctor> doctorList=new Vector<Doctor>();
	@SuppressWarnings("unchecked")
	PatQueryPanel(){
		this.setLayout(new BorderLayout());
		JPanel subPanel=new JPanel();
		subPanel.setLayout(new FlowLayout());
		searchBy.addItem("按病人编号查询");
		searchBy.addItem("按病人姓名查询");		
		subPanel.add(searchBy);
		subPanel.add(keyword);
		JButton btn=new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		String str="编号\t姓名\t性别\t年龄\t医师编号\t疾病\n";
		for(int i=0;i<patientList.size();i++){
			Patient pat=(Patient)this.patientList.get(i);
			if(this.searchBy.getSelectedIndex()==0){
				if(pat.getPatientNo().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+pat.getPatientNo()+"\t"+pat.getPatientName()+"\t"+pat.getPatientSex()+"\t"+pat.getPatientAge()+"\t";
				}else
					continue;
			}if(this.searchBy.getSelectedIndex()==1){
				if(pat.getPatientName().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+pat.getPatientNo()+"\t"+pat.getPatientName()+"\t"+pat.getPatientSex()+"\t"+pat.getPatientAge()+"\t";
				}else
					continue;
			}
			for(int j=0;j<caseList.size();j++){
				Case cas=(Case)caseList.get(j);
				if(cas.getPatientNo().equals(pat.getPatientNo())){
					str=str+cas.getDoctorNo()+"\t"+cas.getCaseName()+"  ";
				}
			}
			str+="\n";
		}
		this.result.setText(str);
	}

	String getDoctorName(String courseNo){
		for(int i=0;i<doctorList.size();i++){
			Doctor doctor=(Doctor)this.doctorList.get(i);
			if(doctor.equals(doctor.getDoctorNo())){
				return doctor.getDoctorName();
			}
		}
		return null;
	}
}

@SuppressWarnings("serial")
class CasQueryPanel extends JPanel implements ActionListener{
	JTextField keyword=new JTextField(10);
	JTextArea result=new JTextArea();
	Vector<Patient> patientList=new Vector<Patient>();
	Vector<Case> caseList=new Vector<Case>();
	Vector<Doctor> doctorList=new Vector<Doctor>();
	CasQueryPanel(){
		this.setLayout(new BorderLayout());
		JPanel subPanel=new JPanel();
		subPanel.setLayout(new FlowLayout());
		subPanel.add(new JLabel("日期编号："));
		subPanel.add(keyword);
		JButton btn=new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(keyword.getText().length()!=6){
			JOptionPane.showMessageDialog(null, "请输入正确的日期编号", "病人病历管理系统", JOptionPane.ERROR_MESSAGE);
			return;
		}
		double max,min,sum,average;
		int count;
		String str="最贵\t最便宜\t平均费用\n";
		Case cas=null;
		
		for(int i=0;i<caseList.size();i++){
			cas=caseList.get(i);
			max=0;min=10000;sum=0;average=0;count=0;
			for(int j=0;j<caseList.size();j++){
				cas=caseList.get(j);
				if(cas.getPatientNo().startsWith(this.keyword.getText().trim())){
					if(cas.getMoney()>max)
						max=cas.getMoney();
					if(cas.getMoney()<min)
						min=cas.getMoney();
					sum+=cas.getMoney();
					count++;
				}
			}
			if(count!=0){
				DecimalFormat df=new DecimalFormat("#.0");
				average=sum/count;
				str="最贵\t最便宜\t平均费用\n"+df.format(max)+"\t"+df.format(min)+"\t"+df.format(average)+"\n";
			}
		}
		
		this.result.setText(str);
	}
}

@SuppressWarnings("serial")
class LoginFrame extends JFrame implements ActionListener{
	JTextField username=new JTextField(10);
	JPasswordField pwd=new JPasswordField(10);
	LoginFrame(){
		this.setTitle("系统登录");
		this.setResizable(false);
		this.setSize(250,130);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lb1=new JLabel("用户名：");
		JLabel lb2=new JLabel("密　码：");
		JButton btn=new JButton("登录");
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
						JOptionPane.showMessageDialog(null, "您填写的密码不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "您填写的用户名不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
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
