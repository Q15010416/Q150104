import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Vector<Stadium> staList=new Vector<Stadium>();
	Vector<Performer> perList=new Vector<Performer>();
	Vector<Concert> conList=new Vector<Concert>();
	JMenu Menu0=new JMenu("�ļ�");							//�˵���
	JMenuItem menuOpen=new JMenuItem("�������ļ�");
	JMenuItem menuSave=new JMenuItem("���������ļ�");
	JMenuItem menuExit=new JMenuItem("�˳�ϵͳ");
	JMenu Menu1=new JMenu("��Ϣά��");
	JMenuItem staMenu=new JMenuItem("������Ϣά��");
	JMenuItem PerMenu=new JMenuItem("������Ϣά��");
	JMenuItem ConMenu=new JMenuItem("�ݳ�����Ϣά��");
	
	public static void main(String[] args) {
		Main f=new Main();
		f.setVisible(true);
	}
	
	Main(){
		super();
		JMenuBar menuBar=new JMenuBar();			//�˵���
		this.setJMenuBar(menuBar);
		staMenu.addActionListener(this);
		staMenu.setEnabled(false);
		Menu1.add(staMenu);
		PerMenu.addActionListener(this);
		PerMenu.setEnabled(false);
		Menu1.add(PerMenu);
		ConMenu.addActionListener(this);
		ConMenu.setEnabled(false);
		Menu1.add(ConMenu);
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
		
		this.setTitle("�ݳ�����Ʊ����ϵͳ");
		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void readStaFile(){
		try {
			FileInputStream fis = new FileInputStream("Stadium.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Stadium sta=new Stadium();
				String[] temp=s.split(" ");
				sta.setStaNo(temp[0]);
				sta.setStaCity(temp[1]);
				sta.setStaName(temp[2]);
				staList.add(sta);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeStaFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Stadium.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<staList.size();i++){
				Stadium sta=(Stadium)staList.get(i);
				writer.write(sta.getStaNo()+" "+sta.getStaName()+"\n");
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
	
	void readPerformerFile(){
		try {
			FileInputStream fis = new FileInputStream("Performer.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Performer Performer=new Performer();
				String[] temp=s.split(" ");
				Performer.setPerformerNo(temp[0]);
				Performer.setPerformerName(temp[1]);
				perList.add(Performer);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writePerformerFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Performer.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<perList.size();i++){
				Performer Performer=(Performer)perList.get(i);
				writer.write(Performer.getPerformerNo()+" "+Performer.getPerformerName()+"\n");
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
	
	void readConcertFile(){
		try{
			FileInputStream fis;
			fis = new FileInputStream("Concert.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Concert Concert=new Concert();
				String[] temp=s.split(" ");
				Concert.setStaNo(temp[0]);
				Concert.setPerformerNo(temp[1]);
				Concert.setConTime(temp[2]);
				Concert.setConName(temp[3]);
				Concert.setConPrice(temp[4]);
				conList.add(Concert);
				
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeConcertFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Concert.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<conList.size();i++){
				Concert Concert=(Concert)conList.get(i);
				writer.write(Concert.getStaNo()+" "+Concert.getPerformerNo()+" "+Concert.getConTime()+" "+Concert.getConName()+" "+Concert.getConPrice()+"\n");
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
	
	void showStaPanel(){
		StaPanel staPanel=new StaPanel();
		staPanel.staList=this.staList;
		this.add(staPanel,BorderLayout.CENTER);
		staPanel.showStadium(0);
		this.setVisible(true);
	}
	
	void showPerformerPanel(){
		PerformerPanel PerformerPanel=new PerformerPanel();
		PerformerPanel.perList=this.perList;
		this.add(PerformerPanel,BorderLayout.CENTER);
		PerformerPanel.showPerformer(0);
		this.setVisible(true);
	}
	
	void showConcertPanel(){
		ConcertPanel ConcertPanel=new ConcertPanel();
		ConcertPanel.conList=this.conList;
		this.add(ConcertPanel,BorderLayout.CENTER);
		ConcertPanel.showConcert(0);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==staMenu){
			this.getContentPane().removeAll();
			this.showStaPanel();
		}
		if(e.getSource()==PerMenu){
			this.getContentPane().removeAll();
			this.showPerformerPanel();
		}
		if(e.getSource()==ConMenu){
			this.getContentPane().removeAll();
			this.showConcertPanel();
		}
		if(e.getSource()==menuOpen){
			this.staList.removeAllElements();
			this.conList.removeAllElements();
			this.perList.removeAllElements();
			this.readStaFile();
			staMenu.setEnabled(true);
			this.readPerformerFile();
			PerMenu.setEnabled(true);
			this.readConcertFile();
			ConMenu.setEnabled(true);
			menuSave.setEnabled(true);
			JOptionPane.showMessageDialog(null, "���Ѿ��ɹ������ݣ�\nѧ����Ϣ"+staList.size()+"��\n�γ���Ϣ"+perList.size()+"��\n�ɼ���Ϣ"+conList.size()+"��", "��", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writeStaFile();
			this.writePerformerFile();
			this.writeConcertFile();
			menuSave.setEnabled(true);
			ConMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "���Ѿ��ɹ��������ݣ�\nѧ����Ϣ"+staList.size()+"��\n�γ���Ϣ"+perList.size()+"��\n�ɼ���Ϣ"+conList.size()+"��", "����", JOptionPane.INFORMATION_MESSAGE);
		}			
		if(e.getSource()==menuExit){
			System.exit(0);
		}
	}
}

@SuppressWarnings("serial")
class ConcertPanel extends JPanel implements ActionListener{
	private JTextField staNo=new JTextField();							//ѧ��
	private JTextField PerformerNo=new JTextField();						//�γ̴���
	private JTextField ConTime=new JTextField();							//�ɼ�
	private JTextField ConName=new JTextField();							//�ɼ�
	private JTextField ConPrice=new JTextField();							//�ɼ�
	
	Vector<Concert> conList=new Vector<Concert>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	ConcertPanel(){
		this.setLayout(null);
		//��ʾ���ݱ��
		JLabel lb1=new JLabel("���ݱ�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(110,10, 100, 20);
		this.add(staNo);
		//��ʾ���˱��
		JLabel lb2=new JLabel("���˱�ţ�");
		lb2.setBounds(30, 50, 100, 20);
		this.add(lb2);
		PerformerNo.setBounds(110,50, 100, 20);
		this.add(PerformerNo);
		//��ʾ�ݳ���ʱ��
		JLabel lb3=new JLabel("�ݳ���ʱ�䣺");
		lb3.setBounds(30, 90, 200, 20);
		this.add(lb3);
		ConTime.setBounds(110,90, 200, 20);
		this.add(ConTime);
		//��ʾ�ݳ�������
		JLabel lb4=new JLabel("�ݳ������ƣ�");
		lb4.setBounds(30, 130, 300, 20);
		this.add(lb4);
		ConName.setBounds(110,130, 300, 20);
		this.add(ConName);
		//��ʾ�ݳ���Ʊ��
		JLabel lb5=new JLabel("�ݳ���Ʊ�ۣ�");
		lb5.setBounds(30, 170, 300, 20);
		this.add(lb5);
		ConPrice.setBounds(110,170, 300, 20);
		this.add(ConPrice);
				
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
		
	}
	
	void showConcert(int offset){
		Concert Concert=(Concert)conList.get(offset);
		this.staNo.setText(Concert.getStaNo());
		this.PerformerNo.setText(""+Concert.getPerformerNo());
		this.ConTime.setText(""+Concert.getConTime());
		this.ConName.setText(""+Concert.getConName());
		this.ConPrice.setText(""+Concert.getConPrice());
		
		
	}

	public void actionPerformed(ActionEvent e) {
		count=this.conList.size();
		if(e.getSource()==this.btn[0]){
			this.showConcert(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showConcert(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showConcert(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showConcert(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.staNo.setText("");
				this.PerformerNo.setText("");
				this.ConTime.setText("");
				this.ConName.setText("");
				this.ConPrice.setText("");					
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting=1;
			}
			else{
				Concert Concert=new Concert();
				Concert.setStaNo(this.staNo.getText().trim());
				Concert.setPerformerNo(this.PerformerNo.getText().trim());
				Concert.setConTime(this.ConTime.getText().trim());
				Concert.setConName(this.ConName.getText().trim());
				Concert.setConPrice(this.ConPrice.getText().trim());
				
				conList.add(Concert);
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
				Concert Concert=(Concert)conList.get(current);
				Concert.setStaNo(this.staNo.getText().trim());
				Concert.setPerformerNo(this.PerformerNo.getText().trim());
				Concert.setConTime(this.ConTime.getText().trim());
				Concert.setConName(this.ConName.getText().trim());
				Concert.setConPrice(this.ConPrice.getText().trim());
				
			}else{
				btn[4].setText("���");
				btn[5].setText("�޸�");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showConcert(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			conList.remove(current);
			count--;
			if(count==0){
				this.staNo.setText("");
				this.PerformerNo.setText("");
				this.ConTime.setText("");
				this.ConName.setText("");
				this.ConPrice.setText("");
				
			}else{
				if(current>count-1){
					this.showConcert(current-1);
					current=current-1;
				}
				else
					this.showConcert(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class StaPanel extends JPanel implements ActionListener {
	private JTextField staNo=new JTextField();											//ѧ��
	private JTextField staCity=new JTextField();											//�Ա�
	private JTextField staName=new JTextField();										//����
	Vector<Stadium> staList=new Vector<Stadium>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	StaPanel(){
		this.setLayout(null);
		//��ʾ���
		JLabel lb1=new JLabel("���ݱ�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(100,10, 100, 20);
		this.add(staNo);
		//��ʾ����
		JLabel lb2=new JLabel("���У�");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		staCity.setBounds(100,60, 100, 20);
		this.add(staCity);
		//��ʾ����
		JLabel lb3=new JLabel("�������ƣ�");
		lb3.setBounds(30, 110, 300, 20);
		this.add(lb3);
		staName.setBounds(100,110, 300, 20);
		this.add(staName);
				
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}
	
	void showStadium(int offset){
		Stadium sta=(Stadium) staList.get(offset);
		this.staNo.setText(sta.getStaNo());
		this.staCity.setText(sta.getStaCity());
		this.staName.setText(sta.getStaName());
		
	}

	public void actionPerformed(ActionEvent e) {
		count=this.staList.size();
		if(e.getSource()==this.btn[0]){
			this.showStadium(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showStadium(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showStadium(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showStadium(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.staNo.setText("");
				this.staCity.setText("");
				this.staName.setText("");
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting=1;
			}else{
				Stadium sta=new Stadium();
				sta.setStaNo(this.staNo.getText().trim());
				sta.setStaCity(this.staCity.getText().trim());
				sta.setStaName(this.staName.getText().trim());
				staList.add(sta);
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
				Stadium sta=(Stadium)staList.get(current);
				sta.setStaNo(this.staNo.getText().trim());
				sta.setStaCity(this.staCity.getText().trim());
				sta.setStaName(this.staName.getText().trim());

			}else{
				btn[4].setText("���");
				btn[5].setText("�޸�");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showStadium(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			staList.remove(current);
			count--;
			if(count==0){
				this.staNo.setText("");
				this.staCity.setText("");
				this.staName.setText("");
			}else{
				if(current>count-1){
					this.showStadium(current-1);
					current=current-1;
				}
				else
					this.showStadium(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class PerformerPanel extends JPanel implements ActionListener {
	private JTextField PerformerNo=new JTextField();											//ѧ��
	private JTextField PerformerName=new JTextField();										//�γ�����
	Vector<Performer> perList=new Vector<Performer>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	PerformerPanel(){
		this.setLayout(null);
		//��ʾ���˱��
		JLabel lb1=new JLabel("���˱�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		PerformerNo.setBounds(100,10, 100, 20);
		this.add(PerformerNo);
		//��ʾ��������
		JLabel lb2=new JLabel("����������");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		PerformerName.setBounds(100,60, 100, 20);
		this.add(PerformerName);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}
	
	void showPerformer(int offset){
		Performer Performer=(Performer) perList.get(offset);
		this.PerformerNo.setText(Performer.getPerformerNo());
		this.PerformerName.setText(Performer.getPerformerName());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.perList.size();
		if(e.getSource()==this.btn[0]){
			this.showPerformer(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showPerformer(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showPerformer(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showPerformer(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.PerformerNo.setText("");
				this.PerformerName.setText("");
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting=1;
			}else{
				Performer Performer=new Performer();
				Performer.setPerformerNo(this.PerformerNo.getText().trim());
				Performer.setPerformerName(this.PerformerName.getText().trim());
				perList.add(Performer);
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
				Performer Performer=(Performer)perList.get(current);
				Performer.setPerformerNo(this.PerformerNo.getText().trim());
				Performer.setPerformerName(this.PerformerName.getText().trim());
			}else{
				btn[4].setText("���");
				btn[5].setText("�޸�");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showPerformer(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			perList.remove(current);
			count--;
			if(count==0){
				this.PerformerNo.setText("");
				this.PerformerName.setText("");
			}else{
				if(current>count-1){
					this.showPerformer(current-1);
					current=current-1;
				}
				else
					this.showPerformer(current);
			}
		}
		this.repaint();
	}
}

class Stadium {
	private String staNo;	//���
	private String staCity;	//����
	private String staName;	//��������									//����
								
	Concert Concert;															//�ɼ�

	public String getStaNo() {
		return staNo;
	}

	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}

	public String getStaCity() {
		return staCity;
	}

	public void setStaCity(String staCity) {
		this.staCity = staCity;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}
	

	
}

class Performer{
	private String PerformerNo;										//�γ̱��
	private String PerformerName;									//�γ�����
	
	public String getPerformerNo() {
		return PerformerNo;
	}
	public void setPerformerNo(String PerformerNo) {
		this.PerformerNo = PerformerNo;
	}
	public String getPerformerName() {
		return PerformerName;
	}
	public void setPerformerName(String PerformerName) {
		this.PerformerName = PerformerName;
	}
}

class Concert{
	private String staNo;											//ѧ��
	private String PerformerNo;										//�γ̴���
	private String conTime;
	private String conName;
	private String conPrice;
	
	public String getStaNo() {
		return staNo;
	}
	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}
	public String getPerformerNo() {
		return PerformerNo;
	}
	public void setPerformerNo(String performerNo) {
		PerformerNo = performerNo;
	}
	public String getConTime() {
		return conTime;
	}
	public void setConTime(String conTime) {
		this.conTime = conTime;
	}
	public String getConName() {
		return conName;
	}
	public void setConName(String conName) {
		this.conName = conName;
	}
	public String getConPrice() {
		return conPrice;
	}
	public void setConPrice(String conPrice) {
		this.conPrice = conPrice;
	}
	
	
	}