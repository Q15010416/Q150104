import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;
import javax.swing.*;


	

public class Main extends JFrame implements ActionListener{
	//��̬���飬����һ������comList���������Ȳ�֪�������С
	Vector<Community> comList=new Vector<Community>();
	Vector<House> houseList=new Vector<House>();
	Vector<Price> priceList=new Vector<Price>();
	JMenu Menu0=new JMenu("�ļ�");
	JMenuItem menuOpen=new JMenuItem("�������ļ�");
	JMenuItem menuSave=new JMenuItem("���������ļ�");
	JMenuItem menuExit=new JMenuItem("�˳�ϵͳ");
	JMenu Menu1=new JMenu("��Ϣά��");
	JMenuItem comMenu=new JMenuItem("¥����Ϣά��");
	JMenuItem houseMenu=new JMenuItem("������Ϣά��");
	JMenuItem priceMenu=new JMenuItem("������Ϣά��");
	
	public static void main(String[] args) {
		Main f = new Main();
		f.setVisible(true);
	}

	
	Main(){
		super();
		
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		comMenu.addActionListener(this);
		comMenu.setEnabled(false);
		Menu1.add(comMenu);
		houseMenu.addActionListener(this);
		houseMenu.setEnabled(false);
		Menu1.add(houseMenu);
		priceMenu.addActionListener(this);
		priceMenu.setEnabled(false);
		Menu1.add(priceMenu);
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
		
		this.setTitle("�����н����ϵͳ");											//��Ŀ
		this.setResizable(false);													//�߿򲻿ɵ���
		this.setSize(700,320);														//�߿��С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						//���ƿ���			
		this.setLocationRelativeTo(this.getOwner());								//����
		
	}
	
	void readComFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Community.txt");
			InputStreamReader dis=new InputStreamReader(fis);						//���ֽ���ת���ַ���
			BufferedReader reader=new BufferedReader(dis);							//���ַ��������ж�ȡ�ı�������
			String s;
			while((s=reader.readLine())!=null)										//����������������û��ʱ����������ѭ��
			{
				Community com=new Community();
				String[] temp=s.split(" ");
				com.setComID(temp[0]);
				com.setComName(temp[1]);
				com.setComAddress(temp[2]);
				comList.add(com);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();													//�������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
		}
		
	}
	void writeComFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Community.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<comList.size();i++){
				Community com=(Community)comList.get(i);
				writer.write(com.getComID()+" "+com.getComName()+" "+com.getComAddress()+"\n");
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
	
	void readHouseFile(){
		try {
			FileInputStream fis = new FileInputStream("House.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				House house=new House();
				String[] temp=s.split(" ");
				house.setHouseID(temp[0]);
				house.setHouseArea(temp[1]);
				house.setHouseStyle(temp[2]);
				house.setHouseStua(temp[3]);
				houseList.add(house);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeHouseFile(){
		try {
			FileOutputStream fos = new FileOutputStream("House.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<houseList.size();i++){
				House house=(House)houseList.get(i);
				writer.write(house.getHouseID()+" "+house.getHouseArea()+" "+house.getHouseStyle()+" "+house.getHouseStua()+"\n");
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
	void readPriceFile(){
		try {
			FileInputStream fis = new FileInputStream("Price.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Price price=new Price();
				String[] temp=s.split(" ");
				price.setComID(temp[0]);
				price.setHouseID(temp[1]);
				price.setSite(temp[2]);
				price.sethPrice(Double.parseDouble(temp[3]));
				priceList.add(price);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writePriceFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Price.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<priceList.size();i++){
				Price price=(Price)priceList.get(i);
				writer.write(price.getComID()+" "+price.getHouseID()+" "+price.getSite()+" "+price.gethPrice()+"\n");
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
	
	
	void showComPanel(){
		ComPanel comPanel=new ComPanel();
		comPanel.comList=this.comList;
		this.add(comPanel,BorderLayout.CENTER);										//���ֹ�����
		comPanel.showCommunity(0);													//offset==0����ӵ�һ�����ݿ�ʼƫ��
		this.setVisible(true);
	}
	void showHousePanel(){
		HousePanel housePanel=new HousePanel();
		housePanel.houseList=this.houseList;
		this.add(housePanel,BorderLayout.CENTER);
		housePanel.showHouse(0);;
		this.setVisible(true);
	}
	
	void showPricePanel(){
		PricePanel pricePanel=new PricePanel();
		pricePanel.priceList=this.priceList;
		this.add(pricePanel,BorderLayout.CENTER);
		pricePanel.showPrice(0);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==comMenu){
			this.getContentPane().removeAll();
			this.showComPanel();
		}
		if(e.getSource()==houseMenu){
			this.getContentPane().removeAll();
			this.showHousePanel();
		}
		if(e.getSource()==priceMenu){
			this.getContentPane().removeAll();
			this.showPricePanel();
		}
		if(e.getSource()==menuOpen){
			this.comList.removeAllElements();
			this.houseList.removeAllElements();
			this.priceList.removeAllElements();
			this.readComFile();
			comMenu.setEnabled(true);
			this.readHouseFile();
			houseMenu.setEnabled(true);
			this.readPriceFile();
			priceMenu.setEnabled(true);
			menuSave.setEnabled(true);
			JOptionPane.showMessageDialog(null, "���Ѿ��ɹ������ݣ�\n¥����Ϣ"+comList.size()+"��\n������Ϣ"+houseList.size()+"��\n������Ϣ"+priceList.size()+"��", "��", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writeComFile();
			this.writeHouseFile();
			this.writePriceFile();
			menuSave.setEnabled(true);
			priceMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "���Ѿ��ɹ��������ݣ�\nѧ����Ϣ"+comList.size()+"��\n�γ���Ϣ"+houseList.size()+"��\n�ɼ���Ϣ"+priceList.size()+"��", "����", JOptionPane.INFORMATION_MESSAGE);
		}			
		if(e.getSource()==menuExit){
			System.exit(0);
		}
	}
}

class ComPanel extends JPanel implements ActionListener{
	private JTextField comID=new JTextField();
	private JTextField comName=new JTextField();
	private JTextField comAddress=new JTextField();
	Vector comList=new Vector();													
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	int count=0,current=0,inserting=0;
	
	ComPanel(){
		comID.setFont(new Font(null,Font.BOLD,15));
		comName.setFont(new Font(null,Font.BOLD,15));
		comAddress.setFont(new Font(null,Font.BOLD,15));
		this.setLayout(null);
		//��ʾѧ��
		JLabel lb1=new JLabel("¥�̱�ţ�");
		lb1.setFont(new Font(null,Font.BOLD,15));
		lb1.setBounds(140, 10, 100, 30);
		this.add(lb1);
		comID.setBounds(250,10, 300, 30);
		this.add(comID);
		//��ʾ����
		JLabel lb2=new JLabel("¥�����ƣ�");
		lb2.setFont(new Font(null,Font.BOLD,15));
		lb2.setBounds(140, 60, 100, 30);
		this.add(lb2);
		comName.setBounds(250,60, 300, 30);
		this.add(comName);
		//��ʾ�Ա�
		JLabel lb3=new JLabel("¥�̵�ַ��");
		lb3.setFont(new Font(null,Font.BOLD,15));
		lb3.setBounds(140, 110, 100, 30);
		this.add(lb3);
		comAddress.setBounds(250,110, 300, 30);
		this.add(comAddress);
		//��ʾ���ư�ť
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
			btn[i].addActionListener(this);
		}
	}

	
	void showCommunity(int offset){													//��ʼƫ����
		Community com=(Community) comList.get(offset);								//
		this.comID.setText(com.getComID());
		this.comName.setText(com.getComName());
		this.comAddress.setText(com.getComAddress());		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		count=this.comList.size();
		if(e.getSource()==this.btn[0]){
			this.showCommunity(0);
			current=0;
		}
		if(e.getSource()==this.btn[1] && current>0){
			this.showCommunity(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2] && current<count-1){
			this.showCommunity(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showCommunity(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.comID.setText("");
				this.comName.setText("");
				this.comAddress.setText("");
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting=1;
			}else{
				Community com=new Community();
				com.setComID(this.comID.getText().trim());
				com.setComName(this.comName.getText().trim());
				com.setComAddress(this.comAddress.getText().trim());
				comList.add(com);
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
				Community com=(Community)comList.get(current);
				com.setComID(this.comID.getText().trim());
				com.setComName(this.comName.getText().trim());
				com.setComAddress(this.comAddress.getText().trim());
			}else{
				btn[4].setText("���");
				btn[5].setText("�޸�");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showCommunity(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			comList.remove(current);
			count--;
			if(count==0){
				this.comID.setText("");
				this.comName.setText("");
				this.comAddress.setText("");
			}else{
				if(current>count-1){
					this.showCommunity(current-1);
					current=current-1;
				}
				else
					this.showCommunity(current);
			}
		}
		this.repaint();
	}
}
class HousePanel extends JPanel implements ActionListener{
	private JTextField houseID=new JTextField();							//ѧ��
	private JTextField houseArea=new JTextField();						//�γ̴���
	private JTextField houseStyle=new JTextField();	//�ɼ�
	private JTextField houseStua=new JTextField();
	Vector<House> houseList=new Vector<House>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	HousePanel(){
		this.setLayout(null);
		//��ʾѧ��
		JLabel lb1=new JLabel("���ݱ�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		houseID.setBounds(100,10, 100, 20);
		this.add(houseID);
		//��ʾ�γ̴���
		JLabel lb2=new JLabel("���������");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		houseArea.setBounds(100,60, 100, 20);
		this.add(houseArea);
		//��ʾ�ɼ�
		JLabel lb3=new JLabel("�������ͣ�");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		houseStyle.setBounds(100,110, 100, 20);
		this.add(houseStyle);
		JLabel lb4=new JLabel("���ݸ�֣�");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		houseStua.setBounds(100,160, 100, 20);
		this.add(houseStua);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
		
	}
	
	void showHouse(int offset){
		House house=(House)houseList.get(offset);
		this.houseID.setText(house.getHouseID());
		this.houseArea.setText(""+house.getHouseArea());
		this.houseStyle.setText(house.getHouseStyle());
		this.houseStua.setText(house.getHouseStua());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.houseList.size();
		if(e.getSource()==this.btn[0]){
			this.showHouse(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showHouse(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showHouse(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showHouse(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.houseID.setText("");
				this.houseArea.setText("");
				this.houseStyle.setText("");
				this.houseStua.setText("");
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting=1;
			}
			else{
				House house=new House();
				house.setHouseID(this.houseID.getText().trim());
				house.setHouseArea(this.houseArea.getText().trim());
				house.setHouseStyle(this.houseStyle.getText().trim());
				house.setHouseStua(this.houseStua.getText().trim());
				houseList.add(house);
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
				House house=(House)houseList.get(current);
				house.setHouseID(this.houseID.getText().trim());
				house.setHouseArea(this.houseArea.getText().trim());
				house.setHouseStyle(this.houseStyle.getText().trim());
				house.setHouseStua(this.houseStua.getText().trim());
			}else{
				btn[4].setText("���");
				btn[5].setText("�޸�");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showHouse(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			houseList.remove(current);
			count--;
			if(count==0){
				this.houseID.setText("");
				this.houseArea.setText("");
				this.houseStyle.setText("");
				this.houseStua.setText("");
			}else{
				if(current>count-1){
					this.showHouse(current-1);
					current=current-1;
				}
				else
					this.showHouse(current);
			}
		}
		this.repaint();
	}
}
class PricePanel extends JPanel implements ActionListener {
	private JTextField comID=new JTextField();											//ѧ��
	private JTextField houseID=new JTextField();										//�γ�����
	private JTextField site=new JTextField();										//ѧ��
	private JTextField hPrice=new JTextField();												//�ον�ʦ
	Vector<Price> priceList=new Vector<Price>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	PricePanel(){
		this.setLayout(null);
		//��ʾ�γ̴���
		JLabel lb1=new JLabel("¥�̱�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		comID.setBounds(100,10, 100, 20);
		this.add(comID);
		//��ʾ�γ���
		JLabel lb2=new JLabel("���ݱ�ţ�");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		houseID.setBounds(100,60, 100, 20);
		this.add(houseID);
		//��ʾѧ��
		JLabel lb3=new JLabel("��Ԫλ�ã�");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		site.setBounds(100,110, 100, 20);
		this.add(site);
		//��ʾ�ον�ʦ
		JLabel lb4=new JLabel("�����мۣ�");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		hPrice.setBounds(100,160, 100, 20);
		this.add(hPrice);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}
	
	void showPrice(int offset){
		Price price=(Price) priceList.get(offset);
		this.comID.setText(price.getComID());
		this.houseID.setText(price.getHouseID());
		this.site.setText(price.getSite());
		this.hPrice.setText(""+price.gethPrice());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.priceList.size();
		if(e.getSource()==this.btn[0]){
			this.showPrice(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showPrice(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showPrice(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showPrice(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.comID.setText("");
				this.houseID.setText("");
				this.site.setText("");
				this.hPrice.setText("");
				btn[4].setText("����");
				btn[5].setText("ȡ��");
				this.inserting=1;
			}else{
				Price price=new Price();
				price.setComID(this.comID.getText().trim());
				price.setHouseID(this.houseID.getText().trim());
				price.setSite(this.site.getText().trim());
				price.sethPrice(Double.parseDouble(this.hPrice.getText().trim()));
				priceList.add(price);
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
				Price price=(Price)priceList.get(current);
				price.setComID(this.comID.getText().trim());
				price.setHouseID(this.houseID.getText().trim());
				price.setSite(this.site.getText().trim());
				price.sethPrice(Double.parseDouble(this.hPrice.getText().trim()));
			}else{
				btn[4].setText("���");
				btn[5].setText("�޸�");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showPrice(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			priceList.remove(current);
			count--;
			if(count==0){
				this.comID.setText("");
				this.houseID.setText("");
				this.site.setText("");
				this.hPrice.setText("");
			}else{
				if(current>count-1){
					this.showPrice(current-1);
					current=current-1;
				}
				else
					this.showPrice(current);
			}
		}
		this.repaint();
	}
}

class Price{
	private String comID;											//ѧ��
	private String houseID;										//�γ̴���
	private String site;
	private double hPrice;
	
	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getHouseID() {
		return houseID;
	}
	public void setHouseID(String houseID) {
		this.houseID = houseID;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public double gethPrice() {
		return hPrice;
	}
	public void sethPrice(double hPrice) {
		this.hPrice = hPrice;
	}
	
}
class House{
	private String houseID;										//�γ̱��
	private String houseArea;									//�γ�����
	private String houseStyle;								//�γ�ѧ��
	private String houseStua;											//�ον�ʦ
	
	public String getHouseID() {
		return houseID;
	}
	public void setHouseID(String houseID) {
		this.houseID = houseID;
	}
	public String getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
	}
	public String getHouseStyle() {
		return houseStyle;
	}
	public void setHouseStyle(String houseStyle) {
		this.houseStyle = houseStyle;
	}
	public String getHouseStua() {
		return houseStua;
	}
	public void setHouseStua(String houseStua) {
		this.houseStua = houseStua;
	}
	
}

class Community {
	private String comID;
	private String comName;
	private String comAddress;

	public String getComID() {
		return comID;
	}
	public void setComID(String comID) {
		this.comID = comID;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
}

