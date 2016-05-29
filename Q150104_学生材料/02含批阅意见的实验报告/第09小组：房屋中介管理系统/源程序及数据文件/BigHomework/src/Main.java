import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;
import javax.swing.*;


	

public class Main extends JFrame implements ActionListener{
	//动态数组，创建一个向量comList，用于事先不知道数组大小
	Vector<Community> comList=new Vector<Community>();
	Vector<House> houseList=new Vector<House>();
	Vector<Price> priceList=new Vector<Price>();
	JMenu Menu0=new JMenu("文件");
	JMenuItem menuOpen=new JMenuItem("打开数据文件");
	JMenuItem menuSave=new JMenuItem("保存数据文件");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem comMenu=new JMenuItem("楼盘信息维护");
	JMenuItem houseMenu=new JMenuItem("房屋信息维护");
	JMenuItem priceMenu=new JMenuItem("房价信息维护");
	
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
		
		this.setTitle("房屋中介管理系统");											//题目
		this.setResizable(false);													//边框不可调整
		this.setSize(700,320);														//边框大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						//控制开关			
		this.setLocationRelativeTo(this.getOwner());								//居中
		
	}
	
	void readComFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Community.txt");
			InputStreamReader dis=new InputStreamReader(fis);						//将字节流转化字符流
			BufferedReader reader=new BufferedReader(dis);							//从字符输入流中读取文本，缓冲
			String s;
			while((s=reader.readLine())!=null)										//当读出的行数不是没有时，进行下面循环
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();													//在命令行打印异常信息在程序中出错的位置及原因
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
		this.add(comPanel,BorderLayout.CENTER);										//布局管理器
		comPanel.showCommunity(0);													//offset==0代表从第一个数据开始偏移
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
		// TODO 自动生成的方法存根
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
			JOptionPane.showMessageDialog(null, "您已经成功打开数据：\n楼盘信息"+comList.size()+"条\n房屋信息"+houseList.size()+"条\n房价信息"+priceList.size()+"条", "打开", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writeComFile();
			this.writeHouseFile();
			this.writePriceFile();
			menuSave.setEnabled(true);
			priceMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功保存数据：\n学生信息"+comList.size()+"条\n课程信息"+houseList.size()+"条\n成绩信息"+priceList.size()+"条", "保存", JOptionPane.INFORMATION_MESSAGE);
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
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	int count=0,current=0,inserting=0;
	
	ComPanel(){
		comID.setFont(new Font(null,Font.BOLD,15));
		comName.setFont(new Font(null,Font.BOLD,15));
		comAddress.setFont(new Font(null,Font.BOLD,15));
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("楼盘编号：");
		lb1.setFont(new Font(null,Font.BOLD,15));
		lb1.setBounds(140, 10, 100, 30);
		this.add(lb1);
		comID.setBounds(250,10, 300, 30);
		this.add(comID);
		//显示姓名
		JLabel lb2=new JLabel("楼盘名称：");
		lb2.setFont(new Font(null,Font.BOLD,15));
		lb2.setBounds(140, 60, 100, 30);
		this.add(lb2);
		comName.setBounds(250,60, 300, 30);
		this.add(comName);
		//显示性别
		JLabel lb3=new JLabel("楼盘地址：");
		lb3.setFont(new Font(null,Font.BOLD,15));
		lb3.setBounds(140, 110, 100, 30);
		this.add(lb3);
		comAddress.setBounds(250,110, 300, 30);
		this.add(comAddress);
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
			btn[i].addActionListener(this);
		}
	}

	
	void showCommunity(int offset){													//起始偏移量
		Community com=(Community) comList.get(offset);								//
		this.comID.setText(com.getComID());
		this.comName.setText(com.getComName());
		this.comAddress.setText(com.getComAddress());		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
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
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Community com=new Community();
				com.setComID(this.comID.getText().trim());
				com.setComName(this.comName.getText().trim());
				com.setComAddress(this.comAddress.getText().trim());
				comList.add(com);
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
				Community com=(Community)comList.get(current);
				com.setComID(this.comID.getText().trim());
				com.setComName(this.comName.getText().trim());
				com.setComAddress(this.comAddress.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
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
	private JTextField houseID=new JTextField();							//学号
	private JTextField houseArea=new JTextField();						//课程代号
	private JTextField houseStyle=new JTextField();	//成绩
	private JTextField houseStua=new JTextField();
	Vector<House> houseList=new Vector<House>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	HousePanel(){
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("房屋编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		houseID.setBounds(100,10, 100, 20);
		this.add(houseID);
		//显示课程代号
		JLabel lb2=new JLabel("房屋面积：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		houseArea.setBounds(100,60, 100, 20);
		this.add(houseArea);
		//显示成绩
		JLabel lb3=new JLabel("房屋类型：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		houseStyle.setBounds(100,110, 100, 20);
		this.add(houseStyle);
		JLabel lb4=new JLabel("房屋格局：");
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
				btn[4].setText("保存");
				btn[5].setText("取消");
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
				House house=(House)houseList.get(current);
				house.setHouseID(this.houseID.getText().trim());
				house.setHouseArea(this.houseArea.getText().trim());
				house.setHouseStyle(this.houseStyle.getText().trim());
				house.setHouseStua(this.houseStua.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
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
	private JTextField comID=new JTextField();											//学号
	private JTextField houseID=new JTextField();										//课程名称
	private JTextField site=new JTextField();										//学分
	private JTextField hPrice=new JTextField();												//任课教师
	Vector<Price> priceList=new Vector<Price>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	PricePanel(){
		this.setLayout(null);
		//显示课程代号
		JLabel lb1=new JLabel("楼盘编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		comID.setBounds(100,10, 100, 20);
		this.add(comID);
		//显示课程名
		JLabel lb2=new JLabel("房屋编号：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		houseID.setBounds(100,60, 100, 20);
		this.add(houseID);
		//显示学分
		JLabel lb3=new JLabel("单元位置：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		site.setBounds(100,110, 100, 20);
		this.add(site);
		//显示任课教师
		JLabel lb4=new JLabel("房屋市价：");
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
				btn[4].setText("保存");
				btn[5].setText("取消");
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
				Price price=(Price)priceList.get(current);
				price.setComID(this.comID.getText().trim());
				price.setHouseID(this.houseID.getText().trim());
				price.setSite(this.site.getText().trim());
				price.sethPrice(Double.parseDouble(this.hPrice.getText().trim()));
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
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
	private String comID;											//学号
	private String houseID;										//课程代号
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
	private String houseID;										//课程编号
	private String houseArea;									//课程名称
	private String houseStyle;								//课程学分
	private String houseStua;											//任课教师
	
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

