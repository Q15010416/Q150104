import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.Vector;

import javax.swing.*;


@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Vector<Guest> guestList=new Vector<Guest>();
	Vector<Guestmen> guestmenList=new Vector<Guestmen>();
	Vector<Pair> pairList=new Vector<Pair>();
	JMenu Menu0=new JMenu("文件");
	JMenuItem menuOpen=new JMenuItem("打开数据文件");
	JMenuItem menuSave=new JMenuItem("保存数据文件");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem guestMenu=new JMenuItem("女嘉宾信息维护");
	JMenuItem guestmenMenu=new JMenuItem("男嘉宾信息维护");
	JMenuItem pairMenu=new JMenuItem("配对信息维护");
	
	public static void main(String[] args) {
		Main f=new Main();
		f.setVisible(true);
	}
	
	Main(){
		super();
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		guestMenu.addActionListener(this);
		guestMenu.setEnabled(false);
		Menu1.add(guestMenu);
		guestmenMenu.addActionListener(this);
		guestmenMenu.setEnabled(false);
		Menu1.add(guestmenMenu);
		pairMenu.addActionListener(this);
		pairMenu.setEnabled(false);
		Menu1.add(pairMenu);
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
		
		this.setTitle("非诚勿扰男女嘉宾信息管理系统");
		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	void readGuestFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("women.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Guest guest=new Guest();
				String[] temp=s.split(" ");
				guest.setGuestNo(temp[0]);
				guest.setGuestName(temp[1]);
				guest.setGuestAge(temp[2]);
				guest.setGuestWork(temp[3]);
				guest.setGuestHobby(temp[4]);
				guestList.add(guest);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	



void writeGuestFile(){
	try {
		FileOutputStream fos = new FileOutputStream("women.txt");
		OutputStreamWriter dos=new OutputStreamWriter(fos);
		BufferedWriter writer=new BufferedWriter(dos);
		for(int i=0;i<guestList.size();i++){
			Guest guest=(Guest)guestList.get(i);
			writer.write(guest.getGuestNo()+" "+guest.getGuestName()+" "+guest.getGuestAge()+" "+guest.getGuestWork()+guest.getGuestHobby()+" "+"\n");
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

void readGuestmenFile(){
	try {
		FileInputStream fis = new FileInputStream("men.txt");
		InputStreamReader dis=new InputStreamReader(fis);
		BufferedReader reader=new BufferedReader(dis);
		String s;
		while((s=reader.readLine())!=null)
		{
			Guestmen guestmen=new Guestmen();
			String[] temp=s.split(" ");
			guestmen.setGuestmenNo(temp[0]);
			guestmen.setGuestmenName(temp[1]);
			guestmen.setGuestmenAge(temp[2]);
			guestmen.setGuestmenWork(temp[3]);
			guestmenList.add(guestmen);
		}
		reader.close();
		dis.close();
		fis.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

void writeGuestmenFile(){
	try {
		FileOutputStream fos = new FileOutputStream("men.txt");
		OutputStreamWriter dos=new OutputStreamWriter(fos);
		BufferedWriter writer=new BufferedWriter(dos);
		for(int i=0;i<guestmenList.size();i++){
			Guestmen guestmen=(Guestmen)guestmenList.get(i);
			writer.write(guestmen.getGuestmenNo()+" "+guestmen.getGuestmenName()+" "+guestmen.getGuestmenAge()+" "+guestmen.getGuestmenWork()+"\n");
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

void readPairFile(){
	try{
		FileInputStream fis;
		fis = new FileInputStream("pair.txt");
		InputStreamReader dis=new InputStreamReader(fis);
		BufferedReader reader=new BufferedReader(dis);
		String s;
		while((s=reader.readLine())!=null)
		{
			Pair pair=new Pair();
			String[] temp=s.split(" ");
			pair.setPairNo(temp[0]);
			pair.setGuestwName(temp[1]);
			pair.setGuestmName(temp[2]);
			pair.setPairInfo(temp[3]);
			pairList.add(pair);
		}
		reader.close();
		dis.close();
		fis.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
}

void writePairFile(){
	try {
		FileOutputStream fos = new FileOutputStream("pair.txt");
		OutputStreamWriter dos=new OutputStreamWriter(fos);
		BufferedWriter writer=new BufferedWriter(dos);
		for(int i=0;i<pairList.size();i++){
			Pair pair=(Pair)pairList.get(i);
			writer.write(pair.getPairNo()+" "+pair.getGuestwName()+" "+pair.getGuestmName()+" "+pair.getPairInfo()+"\n");
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

void showGuestPanel(){
	GuestPanel guestPanel=new GuestPanel();
	guestPanel.guestList=this.guestList;
	this.add(guestPanel,BorderLayout.CENTER);
	guestPanel.showGuest(0);
	this.setVisible(true);
}

void showGuestmenPanel(){
	GuestmenPanel guestmenPanel=new GuestmenPanel();
	guestmenPanel.guestmenList=this.guestmenList;
	this.add(guestmenPanel,BorderLayout.CENTER);
	guestmenPanel.showGuestmen(0);
	this.setVisible(true);
}

void showPairPanel(){
	PairPanel pairPanel=new PairPanel();
	pairPanel.pairList=this.pairList;
	this.add(pairPanel,BorderLayout.CENTER);
	pairPanel.showPair(0);
	this.setVisible(true);
}

public void actionPerformed(ActionEvent e) {
	if(e.getSource()==guestMenu){
		this.getContentPane().removeAll();
		this.showGuestPanel();
	}
	if(e.getSource()==guestmenMenu){
		this.getContentPane().removeAll();
		this.showGuestmenPanel();
	}
	if(e.getSource()==pairMenu){
		this.getContentPane().removeAll();
		this.showPairPanel();
	}
	if(e.getSource()==menuOpen){
		this.guestList.removeAllElements();
		this.guestmenList.removeAllElements();
		this.pairList.removeAllElements();
		this.readGuestFile();
		guestMenu.setEnabled(true);
		this.readGuestmenFile();
		guestmenMenu.setEnabled(true);
		this.readPairFile();
		pairMenu.setEnabled(true);
		menuSave.setEnabled(true);
		JOptionPane.showMessageDialog(null, "您已经成功打开数据：\n女嘉宾信息"+guestList.size()+"条\n男嘉宾信息"+guestmenList.size()+"条\n配对信息"+pairList.size()+"条", "打开", JOptionPane.INFORMATION_MESSAGE);
	}
	if(e.getSource()==menuSave){
		this.writeGuestFile();
		this.writeGuestmenFile();
		this.writePairFile();
		menuSave.setEnabled(true);
		pairMenu.setEnabled(true);
		JOptionPane.showMessageDialog(null, "您已经成功保存数据：\n女嘉宾信息"+guestList.size()+"条\n男嘉宾信息"+guestmenList.size()+"条\n配对信息"+pairList.size()+"条", "保存", JOptionPane.INFORMATION_MESSAGE);
	}			
	if(e.getSource()==menuExit){
		System.exit(0);
	}
}




class GuestPanel extends JPanel implements ActionListener {
	private JTextField guestNo=new JTextField();											//学号
	private JTextField guestName=new JTextField();										//姓名
	private JTextField guestAge=new JTextField();											//性别
	private JTextField guestWork=new JTextField();		
	private JTextField guestHobby=new JTextField();
	Vector guestList=new Vector();
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	int count=0,current=0,inserting=0;
	
	GuestPanel(){
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		guestNo.setBounds(100,10, 100, 20);
		this.add(guestNo);
		//显示姓名
		JLabel lb2=new JLabel("姓名：");
		lb2.setBounds(30, 50, 100, 20);
		this.add(lb2);
		guestName.setBounds(100,50, 100, 20);
		this.add(guestName);
		//显示年龄
		JLabel lb3=new JLabel("年龄：");
		lb3.setBounds(30, 90, 100, 20);
		this.add(lb3);
		guestAge.setBounds(100,90, 100, 20);
		this.add(guestAge);
		//显示工作
		JLabel lb4=new JLabel("工作：");
		lb4.setBounds(30, 130, 100, 20);
		this.add(lb4);
		guestWork.setBounds(100,130, 100, 20);
		this.add(guestWork);
		//显示兴趣爱好
		JLabel lb5=new JLabel("兴趣爱好：");
		lb5.setBounds(30, 170, 100, 20);
		this.add(lb5);
		guestHobby.setBounds(100,170, 100, 20);
		this.add(guestHobby);
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}

	}
	
	void showGuest(int offset){
		Guest stu=(Guest) guestList.get(offset);
		this.guestNo.setText(stu.getGuestNo());
		this.guestName.setText(stu.getGuestName());
		this.guestAge.setText(stu.getGuestAge());
		this.guestWork.setText(stu.getGuestWork());
		this.guestHobby.setText(stu.getGuestHobby());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.guestList.size();
		if(e.getSource()==this.btn[0]){
			this.showGuest(0);
			current=0;
		}
		if(e.getSource()==this.btn[1] && current>0){
			this.showGuest(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2] && current<count-1){
			this.showGuest(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showGuest(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.guestNo.setText("");
				this.guestName.setText("");
				this.guestAge.setText("");
				this.guestWork.setText("");
				this.guestHobby.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Guest guest=new Guest();
				guest.setGuestNo(this.guestNo.getText().trim());
				guest.setGuestName(this.guestName.getText().trim());
				guest.setGuestAge(this.guestAge.getText().trim());
				guest.setGuestWork(this.guestWork.getText().trim());
				guest.setGuestHobby(this.guestHobby.getText().trim());
				guestList.add(guest);
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
				Guest guest=(Guest)guestList.get(current);
				guest.setGuestNo(this.guestNo.getText().trim());
				guest.setGuestName(this.guestName.getText().trim());
				guest.setGuestAge(this.guestAge.getText().trim());
				guest.setGuestWork(this.guestWork.getText().trim());
				guest.setGuestHobby(this.guestHobby.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showGuest(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			guestList.remove(current);
			count--;
			if(count==0){
				this.guestNo.setText("");
				this.guestName.setText("");
				this.guestAge.setText("");
				this.guestWork.setText("");
				this.guestHobby.setText("");
			}else{
				if(current>count-1){
					this.showGuest(current-1);
					current=current-1;
				}
				else
					this.showGuest(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class GuestmenPanel extends JPanel implements ActionListener {
	private JTextField guestmenNo=new JTextField();											//男嘉宾编号
	private JTextField guestmenName=new JTextField();										//姓名
	private JTextField guestmenAge=new JTextField();										//年龄
	private JTextField guestmenWork=new JTextField();										//工作
	Vector<Guestmen> guestmenList=new Vector<Guestmen>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	GuestmenPanel(){
		this.setLayout(null);
		//显示男嘉宾编号
		JLabel lb1=new JLabel("编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		guestmenNo.setBounds(100,10, 100, 20);
		this.add(guestmenNo);
		//显示姓名
		JLabel lb2=new JLabel("姓名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		guestmenName.setBounds(100,60, 100, 20);
		this.add(guestmenName);
		//显示年龄
		JLabel lb3=new JLabel("年龄：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		guestmenAge.setBounds(100,110, 100, 20);
		this.add(guestmenAge);
		//显示工作
		JLabel lb4=new JLabel("工作：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		guestmenWork.setBounds(100,160, 100, 20);
		this.add(guestmenWork);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}
	
	void showGuestmen(int offset){
		Guestmen course=(Guestmen) guestmenList.get(offset);
		this.guestmenNo.setText(course.getGuestmenNo());
		this.guestmenName.setText(course.getGuestmenName());
		this.guestmenAge.setText(course.getGuestmenAge());
		this.guestmenWork.setText(course.getGuestmenWork());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.guestmenList.size();
		if(e.getSource()==this.btn[0]){
			this.showGuestmen(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showGuestmen(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showGuestmen(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showGuestmen(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.guestmenNo.setText("");
				this.guestmenName.setText("");
				this.guestmenAge.setText("");
				this.guestmenWork.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Guestmen guestmen=new Guestmen();
				guestmen.setGuestmenNo(this.guestmenNo.getText().trim());
				guestmen.setGuestmenName(this.guestmenName.getText().trim());
				guestmen.setGuestmenAge(this.guestmenAge.getText().trim());
				guestmen.setGuestmenWork(this.guestmenWork.getText().trim());
				guestmenList.add(guestmen);
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
				Guestmen course=(Guestmen)guestmenList.get(current);
				course.setGuestmenNo(this.guestmenNo.getText().trim());
				course.setGuestmenName(this.guestmenName.getText().trim());
				course.setGuestmenAge(this.guestmenAge.getText().trim());
				course.setGuestmenWork(this.guestmenWork.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showGuestmen(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			guestmenList.remove(current);
			count--;
			if(count==0){
				this.guestmenNo.setText("");
				this.guestmenName.setText("");
				this.guestmenAge.setText("");
				this.guestmenWork.setText("");
			}else{
				if(current>count-1){
					this.showGuestmen(current-1);
					current=current-1;
				}
				else
					this.showGuestmen(current);
			}
		}    
		this.repaint();
	}
}

@SuppressWarnings("serial")
class PairPanel extends JPanel implements ActionListener{
	private JTextField pairNo=new JTextField();							//编号
	private JTextField guestwName=new JTextField();						//女嘉宾姓名
	private JTextField guestmName=new JTextField();						//男嘉宾姓名
	private JTextField pairInfo=new JTextField();						//配对信息
	Vector<Pair> pairList=new Vector<Pair>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	PairPanel(){
		this.setLayout(null);
		//显示编号号
		JLabel lb1=new JLabel("编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		pairNo.setBounds(110,10, 100, 20);
		this.add(pairNo);
		//显示女嘉宾姓名
		JLabel lb2=new JLabel("女嘉宾姓名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		guestwName.setBounds(110,60, 100, 20);
		this.add(guestwName);
		//显示男嘉宾姓名
		JLabel lb3=new JLabel("男嘉宾姓名：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		guestmName.setBounds(110,110, 100, 20);
		this.add(guestmName);
		//显示配对信息
		JLabel lb4=new JLabel("配对信息：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		pairInfo.setBounds(110,160, 100, 20);
		this.add(pairInfo);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
		
	}
	
	void showPair(int offset){
		Pair pair=(Pair)pairList.get(offset);
		this.pairNo.setText(pair.getPairNo());
		this.guestwName.setText(pair.getGuestwName());
		this.guestmName.setText(pair.getGuestmName());
		this.pairInfo.setText(pair.getPairInfo());

		
	}

	public void actionPerformed(ActionEvent e) {
		count=this.pairList.size();
		if(e.getSource()==this.btn[0]){
			this.showPair(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showPair(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showPair(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showPair(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.pairNo.setText("");
				this.guestwName.setText("");
				this.guestmName.setText("");
				this.pairInfo.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Pair pair=new Pair();
				pair.setPairNo(this.pairNo.getText().trim());
				pair.setGuestwName(this.guestwName.getText().trim());
				pair.setGuestmName(this.guestmName.getText().trim());
				pair.setPairInfo(this.pairInfo.getText().trim());
				pairList.add(pair);
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
				Pair pair=(Pair)pairList.get(current);
				pair.setPairNo(this.pairNo.getText().trim());
				pair.setGuestwName(this.guestwName.getText().trim());
				pair.setGuestmName(this.guestmName.getText().trim());
				pair.setPairInfo(this.pairInfo.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showPair(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			pairList.remove(current);
			count--;
			if(count==0){
				this.pairNo.setText("");
				this.guestwName.setText("");
				this.guestmName.setText("");
				this.pairInfo.setText("");
			}else{
				if(current>count-1){
					this.showPair(current-1);
					current=current-1;
				}
				else
					this.showPair(current);
			}
		}
		this.repaint();
	}
}



class Guest {
	private String guestNo;											//编号
	private String guestName;										//姓名
	private String guestAge;									    //年龄
	private String guestWork;									    //工作
	private String guestHobby;							   		    //兴趣爱好
	
	public String getGuestNo() {
		return guestNo;
	}
	public void setGuestNo(String guestNo) {
		this.guestNo = guestNo;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getGuestAge() {
		return guestAge;
	}
	public void setGuestAge(String guestAge) {
		this.guestAge = guestAge;
	}
	public String getGuestWork() {
		return guestWork;
	}
	public void setGuestWork(String guestWork) {
		this.guestWork = guestWork;
	}
	public String getGuestHobby() {
		return guestHobby;
	}
	public void setGuestHobby(String guestHobby) {
		this.guestHobby = guestHobby;
	}
}

class Guestmen{
	private String guestmenNo;										//编号
	private String guestmenName;									//姓名
	private String guestmenAge;								        //年龄
	private String guestmenWork;					     			//职业
	
	public String getGuestmenNo() {
		return guestmenNo;
	}
	public void setGuestmenNo(String guestmenNo) {
		this.guestmenNo = guestmenNo;
	}
	public String getGuestmenName() {
		return guestmenName;
	}
	public void setGuestmenName(String guestmenName) {
		this.guestmenName = guestmenName;
	}
	public String getGuestmenAge() {
		return guestmenAge;
	}
	public void setGuestmenAge(String guestmenAge) {
		this.guestmenAge = guestmenAge;
	}
	public String getGuestmenWork() {
		return guestmenWork;
	}
	public void setGuestmenWork(String guestmenWork) {
		this.guestmenWork = guestmenWork;
	}
}

class Pair{
	private String pairNo;											//学号
	private String guestwName;										//女嘉宾姓名
	private String guestmName;										//男嘉宾姓名
	private String pairInfo;										//配对信息

	
	public String getPairNo() {
		return pairNo;
	}
	public void setPairNo(String pairNo) {
		this.pairNo = pairNo;
	}
	public String getGuestwName() {
		return guestwName;
	}
	public void setGuestwName(String guestwName) {
		this.guestwName = guestwName;
	}
	public String getGuestmName() {
		return guestmName;
	}
	public void setGuestmName(String guestmName) {
		this.guestmName = guestmName;
	}
	public String getPairInfo() {
		return pairInfo;
	}
	public void setPairInfo(String pairInfo) {
		this.pairInfo = pairInfo;
	}
 }
}