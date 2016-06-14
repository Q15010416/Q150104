import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Vector<Variety> varList=new Vector<Variety>();
	Vector<Information> infList=new Vector<Information>();
	Vector<Shop> shopList=new Vector<Shop>();
	Vector<SendAddress> addrList=new Vector<SendAddress>();
	JMenu Menu0=new JMenu("文件");
	JMenuItem menuOpen=new JMenuItem("打开数据文件");
	JMenuItem menuSave=new JMenuItem("保存数据文件");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem varMenu=new JMenuItem("生鲜种类维护");
	JMenuItem infMenu=new JMenuItem("生鲜信息维护");
	JMenuItem shopMenu=new JMenuItem("店铺信息维护");
	JMenuItem addrMenu=new JMenuItem("地址信息维护");

	public static void main(String[] args) {
		Main f=new Main();
		f.setVisible(true);
	}

	Main(){
		super();
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);

		varMenu.addActionListener(this);
		varMenu.setEnabled(false);
		Menu1.add(varMenu);
		infMenu.addActionListener(this);
		infMenu.setEnabled(false);
		Menu1.add(infMenu);
		shopMenu.addActionListener(this);
		shopMenu.setEnabled(false);
		Menu1.add(shopMenu);
		addrMenu.addActionListener(this);
		addrMenu.setEnabled(false);
		Menu1.add(addrMenu);

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

		this.setTitle("栖霞区生鲜配送管理系统");
		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void readVarFile(){
		try {
			FileInputStream fis = new FileInputStream("Variety.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Variety var=new Variety();
				String[] temp=s.split(" ");
				var.setVNo(temp[0]);
				var.setVName(temp[1]);
				var.setFreshName(temp[2]);
				varList.add(var);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeVarFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Variety.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<varList.size();i++){
				Variety var=(Variety)varList.get(i);
				writer.write(var.getVNo()+" "+var.getVName()+" "+var.getFreshName()+"\n");
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

	void readInfFile(){
		try {
			FileInputStream fis = new FileInputStream("Information.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Information inf=new Information();
				String[] temp=s.split(" ");
				inf.setFreshName(temp[0]);
				inf.setShopName(temp[1]);
				inf.setProducingTime(temp[2]);
				inf.setPrice(Double.parseDouble(temp[3]));
				inf.setSales(Double.parseDouble(temp[4]));
				inf.setGrade(Double.parseDouble(temp[5]));
				infList.add(inf);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeInfFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Information.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<infList.size();i++){
				Information inf=(Information)infList.get(i);
				writer.write(inf.getFreshName()+" "+inf.getShopName()+" "+inf.getProducingTime()+" "
						+inf.getPrice()+" "+inf.getSales()+" "+inf.getGrade()+"\n");
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

	void readShopFile(){
		try {
			FileInputStream fis = new FileInputStream("Shop.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Shop shop=new Shop();
				String[] temp=s.split(" ");
				shop.setShopName(temp[0]);
				shop.setAddress(temp[1]);
				shop.setFreight1(Double.parseDouble(temp[2]));
				shop.setFreight2(Double.parseDouble(temp[3]));
				shop.setMinPrice(Double.parseDouble(temp[4]));
				shop.setShop(Double.parseDouble(temp[5]));
				shop.setCompare(temp[6]);
				shop.setTele(temp[7]);
				shopList.add(shop);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeShopFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Shop.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<shopList.size();i++){
				Shop shop=(Shop)shopList.get(i);
				writer.write(shop.getShopName()+" "+shop.getAddress()+" "+shop.getFreight1()+" "+shop.getFreight2()+" "
						+shop.getMinPrice()+" "+shop.getShop()+" "+shop.getCompare()+" "+shop.getTele()+"\n");
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

	void readAddrFile(){
		try{
			FileInputStream fis;
			fis = new FileInputStream("SendAddress.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				SendAddress addr=new SendAddress();
				String[] temp=s.split(" ");
				addr.setStreetName(temp[0]);
				addr.setCellAddress(temp[1]);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeAddrFile(){
		try {
			FileOutputStream fos = new FileOutputStream("SendAddress.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<addrList.size();i++){
				SendAddress addr=(SendAddress)addrList.get(i);
				writer.write(addr.getStreetName()+" "+addr.getCellAddress()+"\n");
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



	void showVarPanel(){
		VarPanel varPanel=new VarPanel();
		varPanel.varList=this.varList;
		this.add(varPanel,BorderLayout.CENTER);
		varPanel.showVar(0);
		this.setVisible(true);
	}

	void showInfPanel(){
		InfPanel InfPanel=new InfPanel();
		InfPanel.infList=this.infList;
		this.add(InfPanel,BorderLayout.CENTER);
		InfPanel.showInf(0);
		this.setVisible(true);
	}

	void showShopPanel(){
		ShopPanel ShopPanel=new ShopPanel();
		ShopPanel.shopList=this.shopList;
		this.add(ShopPanel,BorderLayout.CENTER);
		ShopPanel.showShop(0);
		this.setVisible(true);
	}

	void showAddrPanel(){
		AddrPanel addrPanel=new AddrPanel();
		addrPanel.addrList=this.addrList;
		this.add(addrPanel,BorderLayout.CENTER);
		addrPanel.showAddr(0);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==varMenu){
			this.getContentPane().removeAll();
			this.showVarPanel();
		}
		if(e.getSource()==infMenu){
			this.getContentPane().removeAll();
			this.showInfPanel();
		}
		if(e.getSource()==shopMenu){
			this.getContentPane().removeAll();
			this.showShopPanel();
		}
		if(e.getSource()==addrMenu){
			this.getContentPane().removeAll();
			this.showAddrPanel();
		}

		if(e.getSource()==menuOpen){
			this.varList.removeAllElements();
			this.shopList.removeAllElements();
			this.infList.removeAllElements();
			this.addrList.removeAllElements();
			this.readVarFile();
			varMenu.setEnabled(true);
			this.readInfFile();
			infMenu.setEnabled(true);
			this.readShopFile();
			shopMenu.setEnabled(true);
			this.readAddrFile();
			addrMenu.setEnabled(true);
			menuSave.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功打开数据：\n生鲜种类信息"+varList.size()+
					"条\n生鲜具体信息"+infList.size()+"条\n店铺信息"+shopList.size()+"条\n地址信息"+addrList.size()+"条",
					"打开", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writeVarFile();
			this.writeInfFile();
			this.writeShopFile();
			this.writeAddrFile();
			menuSave.setEnabled(true);
			shopMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经保存打开数据：\n生鲜种类信息"+varList.size()+
					"条\n生鲜具体信息"+infList.size()+"条\n店铺信息"+shopList.size()+"条\n地址信息"+addrList.size()+"条",
					"保存", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuExit){
			System.exit(0);
		}
	}
}


@SuppressWarnings("serial")
class VarPanel extends JPanel implements ActionListener{
	private JTextField VNo=new JTextField();				//种类序号
	private JTextField VName=new JTextField();				//种类名称
	private JTextField freshName=new JTextField();			//生鲜名称
	Vector<Variety> varList=new Vector<Variety>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	VarPanel(){
		this.setLayout(null);
		//显示种类序号
		JLabel lb1=new JLabel("种类序号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		VNo.setBounds(100,10, 100, 20);
		this.add(VNo);
		//显示种类名称
		JLabel lb2=new JLabel("种类名称：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		VName.setBounds(100,60, 100, 20);
		this.add(VName);
		//显示生鲜名称
		JLabel lb3=new JLabel("生鲜名称：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		freshName.setBounds(100,110, 100, 20);
		this.add(freshName);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}

	}

	void showVar(int offset){
		Variety var=(Variety)varList.get(offset);
		this.VNo.setText(var.getVNo());
		this.VName.setText(""+var.getVName());
		this.freshName.setText(""+var.getFreshName());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.varList.size();
		if(e.getSource()==this.btn[0]){
			this.showVar(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showVar(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showVar(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showVar(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.VNo.setText("");
				this.VName.setText("");
				this.freshName.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Variety var=new Variety();
				var.setVNo(this.VNo.getText().trim());
				var.setVName(this.VName.getText().trim());
				var.setFreshName(this.freshName.getText().trim());
				varList.add(var);
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
				Variety var=(Variety)varList.get(current);
				var.setVNo(this.VNo.getText().trim());
				var.setVName(this.VName.getText().trim());
				var.setFreshName(this.freshName.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showVar(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			varList.remove(current);
			count--;
			if(count==0){
				this.VNo.setText("");
				this.VName.setText("");
				this.freshName.setText("");
			}else{
				if(current>count-1){
					this.showVar(current-1);
					current=current-1;
				}
				else
					this.showVar(current);
			}
		}
		this.repaint();
	}
}


@SuppressWarnings("serial")
class InfPanel extends JPanel implements ActionListener {
	private JTextField freshName=new JTextField();						//生鲜名称
	private JTextField shopName=new JTextField();						//店铺名称
	private JTextField producingTime=new JTextField();					//出厂日期
	private JTextField price=new JTextField();							//生鲜单价
	private JTextField sales=new JTextField();							//月均销量
	private JTextField grade=new JTextField();							//顾客评分
	Vector<Information> infList=new Vector<Information>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	InfPanel(){
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("生鲜名称：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		freshName.setBounds(100,10, 100, 20);
		this.add(freshName);
		//显示姓名
		JLabel lb2=new JLabel("店铺名称：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		shopName.setBounds(100,60, 100, 20);
		this.add(shopName);
		//显示性别
		JLabel lb3=new JLabel("出厂日期：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		producingTime.setBounds(100,110, 100, 20);
		this.add(producingTime);
		//显示出生日期
		JLabel lb4=new JLabel("生鲜单价：");
		lb4.setBounds(300, 10, 100, 20);
		this.add(lb4);
		price.setBounds(370, 10, 100, 20);
		this.add(price);
		JLabel lb5=new JLabel("月均销量：");
		lb5.setBounds(300, 60, 100, 20);
		this.add(lb5);
		sales.setBounds(370, 60, 100, 20);
		this.add(sales);
		JLabel lb6=new JLabel("顾客评分：");
		lb6.setBounds(300, 110, 100, 20);
		this.add(lb6);
		grade.setBounds(370,110, 100, 20);
		this.add(grade);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}

	void showInf(int offset){
		Information inf=(Information) infList.get(offset);
		this.freshName.setText(inf.getFreshName());
		this.shopName.setText(""+inf.getShopName());
		this.producingTime.setText(""+inf.getProducingTime());
		this.price.setText(""+inf.getPrice());
		this.sales.setText(""+inf.getSales());
		this.grade.setText(""+inf.getGrade());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.infList.size();
		if(e.getSource()==this.btn[0]){
			this.showInf(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showInf(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showInf(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showInf(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.freshName.setText("");
				this.shopName.setText("");
				this.producingTime.setText("");
				this.price.setText("");
				this.sales.setText("");
				this.grade.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Information Inf=new Information();
				Inf.setFreshName(this.freshName.getText().trim());
				Inf.setShopName(this.shopName.getText().trim());
				Inf.setProducingTime(this.producingTime.getText().trim());
				Inf.setPrice(Double.parseDouble(this.price.getText().trim()));
				Inf.setSales(Double.parseDouble(this.sales.getText().trim()));
				Inf.setGrade(Double.parseDouble(this.grade.getText().trim()));
				infList.add(Inf);
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
				Information Inf=(Information)infList.get(current);
				Inf.setFreshName(this.freshName.getText().trim());
				Inf.setShopName(this.shopName.getText().trim());
				Inf.setProducingTime(this.producingTime.getText().trim());
				Inf.setPrice(Double.parseDouble(this.price.getText().trim()));
				Inf.setSales(Double.parseDouble(this.sales.getText().trim()));
				Inf.setGrade(Double.parseDouble(this.grade.getText().trim()));
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showInf(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			infList.remove(current);
			count--;
			if(count==0){
				this.freshName.setText("");
				this.shopName.setText("");
				this.producingTime.setText("");
				this.price.setText("");
				this.sales.setText("");
				this.grade.setText("");
			}else{
				if(current>count-1){
					this.showInf(current-1);
					current=current-1;
				}
				else
					this.showInf(current);
			}
		}
		this.repaint();
	}
}



@SuppressWarnings("serial")
class ShopPanel extends JPanel implements ActionListener{
	private JTextField shopName=new JTextField();	
	private JTextField address=new JTextField();	
	private JTextField freight1=new JTextField();			
	private JTextField freight2=new JTextField();
	private JTextField minPrice=new JTextField();
	private JTextField Shop=new JTextField();
	private JTextField compare=new JTextField();
	private JTextField tele=new JTextField();


	Vector<Shop> shopList=new Vector<Shop>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	ShopPanel(){
		this.setLayout(null);
		//显示
		JLabel lb1=new JLabel("店铺名称：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		shopName.setBounds(130,10, 100, 20);
		this.add(shopName);
		//显示
		JLabel lb2=new JLabel("店铺地址：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		address.setBounds(130,60, 100, 20);
		this.add(address);
		//显示
		JLabel lb3=new JLabel("3km内配送费：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		freight1.setBounds(130,110, 100, 20);
		this.add(freight1);
		//
		JLabel lb4=new JLabel("超出部分配送费：");
		lb4.setBounds(30, 160, 120, 20);
		this.add(lb4);
		freight2.setBounds(130,160, 100, 20);
		this.add(freight2);
	    //
		JLabel lb5=new JLabel("起送价：");
		lb5.setBounds(300, 10, 100, 20);
		this.add(lb5);
		minPrice.setBounds(390, 10, 100, 20);
		this.add(minPrice);
		//
		JLabel lb6=new JLabel("店铺信用度：");
		lb6.setBounds(300, 60, 100, 20);
		this.add(lb6);
		Shop.setBounds(390,60, 100, 20);
		this.add(Shop);
		//
		JLabel lb7=new JLabel("信用度比较：");
		lb7.setBounds(300, 110, 100, 20);
		this.add(lb7);
		compare.setBounds(390,110, 100, 20);
		this.add(compare);
		//
		JLabel lb8=new JLabel("联系方式：");
		lb8.setBounds(300, 160, 100, 20);
		this.add(lb8);
		tele.setBounds(390,160, 100, 20);
		this.add(tele);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}

	}

	void showShop(int offset){
		Shop shop=(Shop)shopList.get(offset);
		this.shopName.setText(shop.getShopName());
		this.address.setText(""+shop.getAddress());
		this.freight1.setText(""+shop.getFreight1());
		this.freight2.setText(""+shop.getFreight2());
		this.minPrice.setText(""+shop.getMinPrice());
		this.Shop.setText(""+shop.getShop());
		this.compare.setText(""+shop.getCompare());
		this.tele.setText(""+shop.getTele());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.shopList.size();
		if(e.getSource()==this.btn[0]){
			this.showShop(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showShop(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showShop(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showShop(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.shopName.setText("");
				this.address.setText("");
				this.freight1.setText("");
				this.freight2.setText("");
				this.minPrice.setText("");
				this.Shop.setText("");
				this.compare.setText("");
				this.tele.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Shop shop=new Shop();
				shop.setShopName(this.shopName.getText().trim());
				shop.setAddress(this.address.getText().trim());
				shop.setFreight1(Double.parseDouble(this.freight1.getText().trim()));
				shop.setFreight2(Double.parseDouble(this.freight2.getText().trim()));
				shop.setMinPrice(Double.parseDouble(this.minPrice.getText().trim()));
				shop.setShop(Double.parseDouble(this.Shop.getText().trim()));
				shop.setCompare(this.compare.getText().trim());
				shop.setTele(this.tele.getText().trim());
				shopList.add(shop);
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
				Shop shop=(Shop)shopList.get(current);
				shop.setShopName(this.shopName.getText().trim());
				shop.setAddress(this.address.getText().trim());
				shop.setFreight1(Double.parseDouble(this.freight1.getText().trim()));
				shop.setFreight2(Double.parseDouble(this.freight2.getText().trim()));
				shop.setMinPrice(Double.parseDouble(this.minPrice.getText().trim()));
				shop.setShop(Double.parseDouble(this.Shop.getText().trim()));
				shop.setCompare(this.compare.getText().trim());
				shop.setTele(this.tele.getText().trim());

			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showShop(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			shopList.remove(current);
			count--;
			if(count==0){
				this.shopName.setText("");
				this.address.setText("");
				this.freight1.setText("");
				this.freight2.setText("");
				this.minPrice.setText("");
				this.Shop.setText("");
				this.compare.setText("");
				this.tele.setText("");
			}else{
				if(current>count-1){
					this.showShop(current-1);
					current=current-1;
				}
				else
					this.showShop(current);
			}
		}
		this.repaint();
	}
}



@SuppressWarnings("serial")
class AddrPanel extends JPanel implements ActionListener{
	private JTextField streetName=new JTextField();					//街道
	private JTextField cellAddress=new JTextField();				//小区
	Vector<SendAddress> addrList=new Vector<SendAddress>();
	int count=0,current=0,inserting=0;

	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];

	AddrPanel(){
		this.setLayout(null);
		//显示街道名称
		JLabel lb1=new JLabel("街道名称：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		streetName.setBounds(100,10, 100, 20);
		this.add(streetName);
		//显示小区名称
		JLabel lb2=new JLabel("小区名称：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		cellAddress.setBounds(100,60, 100, 20);
		this.add(cellAddress);


		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}

	}

	void showAddr(int offset){
		SendAddress addr=(SendAddress)addrList.get(offset);
		this.streetName.setText(addr.getStreetName());
		this.cellAddress.setText(""+addr.getCellAddress());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.addrList.size();
		if(e.getSource()==this.btn[0]){
			this.showAddr(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showAddr(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showAddr(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showAddr(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.streetName.setText("");
				this.cellAddress.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				SendAddress addr=new SendAddress();
				addr.setStreetName(this.streetName.getText().trim());
				addr.setCellAddress(this.cellAddress.getText().trim());
				addrList.add(addr);
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
				SendAddress addr=(SendAddress)addrList.get(current);
				addr.setStreetName(this.streetName.getText().trim());
				addr.setCellAddress(this.cellAddress.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showAddr(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			addrList.remove(current);
			count--;
			if(count==0){
				this.streetName.setText("");
				this.cellAddress.setText("");
			}else{
				if(current>count-1){
					this.showAddr(current-1);
					current=current-1;
				}
				else
					this.showAddr(current);
			}
		}
		this.repaint();
	}
}




class Variety {
	private String VNo;								//种类序号
	private String VName;							//种类名称
	private String freshName;						//生鲜名称

	public String getVNo() {
		return VNo;
	}
	public void setVNo(String vNo) {
		VNo = vNo;
	}
	public String getVName() {
		return VName;
	}
	public void setVName(String vName) {
		VName = vName;
	}
	public String getFreshName() {
		return freshName;
	}
	public void setFreshName(String freshName) {
		this.freshName = freshName;
	}

}	


class Information {
	private String freshName;							//生鲜名称
	private String shopName;							//店铺名称
	private String producingTime;						//出厂日期
	private double price;								//生鲜单价
	private double sales;								//月均销量
	private double grade;								//顾客评分

	public String getFreshName() {
		return freshName;
	}
	public void setFreshName(String freshName) {
		this.freshName = freshName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getProducingTime() {
		return producingTime;
	}
	public void setProducingTime(String producingTime) {
		this.producingTime = producingTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}

}


class Shop{
	private String shopName;					//店铺名称
	private String address;						//店铺地址
	private double freight1;					//3km内配送费
	private double freight2;					//超出配送费
	private double minPrice;					//起送价
	private double Shop;						//店铺信用度
	private String compare;						//信用度比较
	private String tele;						//联系方式

	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getFreight1() {
		return freight1;
	}
	public void setFreight1(double freight1) {
		this.freight1 = freight1;
	}
	public double getFreight2() {
		return freight2;
	}
	public void setFreight2(double freight2) {
		this.freight2 = freight2;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getShop() {
		return Shop;
	}
	public void setShop(double Shop) {
		this.Shop = Shop;
	}
	public String getCompare() {
		return compare;
	}
	public void setCompare(String compare) {
		this.compare = compare;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}

}

class SendAddress{
	private String streetName;				//街道名称
	private String cellAddress;				//小区名称

	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCellAddress() {
		return cellAddress;
	}
	public void setCellAddress(String cellAddress) {
		this.cellAddress = cellAddress;
	}

}
