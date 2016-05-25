import java.awt.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;

public class Main{
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}
}

class MainFrame extends JFrame {
	Vector canteenList=new Vector();
	
	MainFrame(){
		super("ʳ����Ϣ����ϵͳ");
		this.setTitle("ʳ����Ϣ����ϵͳ");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.readcanteenFile();
		this.showCanteenPanel();
		this.setVisible(true);

		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	void readcanteenFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("canteen.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Canteen canteen=new Canteen();
				String[] temp=s.split(" ");
				canteen.setCanteenNo(temp[0]);
				canteen.setCanteenName(temp[1]);
				canteen.setCanteenLocation(temp[2]);
				canteen.setManager(temp[3]);
				canteenList.add(canteen);
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	void showCanteenPanel(){
		CanteenPanel canteenPanel=new CanteenPanel();
		CanteenPanel.canteenList=this.canteenList;
		this.add(canteenPanel,BorderLayout.CENTER);
		canteenPanel.showCanteen(0);
		this.setVisible(true);
	}
}

class CanteenPanel extends JPanel {
	private JTextField canteenNo=new JTextField();											//���
	private JTextField canteenName=new JTextField();										//��������
	private JTextField canteenLocation=new JTextField();									//����λ��			
	private JTextField manager=new JTextField();											//������
	static Vector canteenList=new Vector();
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	CanteenPanel(){
		this.setLayout(null);
		//���ڱ��
		JLabel lb1=new JLabel("���ڱ�ţ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		canteenNo.setBounds(100,10, 100, 20);
		this.add(canteenNo);
		//��������
		JLabel lb2=new JLabel("�������ƣ�");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		canteenName.setBounds(100,60, 100, 20);
		this.add(canteenName);
		//����λ��
		JLabel lb3=new JLabel("����λ�ã�");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		canteenLocation.setBounds(100,110, 100, 20);
		this.add(canteenLocation);
		//������
		JLabel lb4=new JLabel("�����ˣ�");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		manager.setBounds(100,160, 100, 20);
		this.add(manager);

		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
		}
	}
	

	void showCanteen(int offset){
		Canteen canteen=(Canteen) canteenList.get(offset);
		this.canteenNo.setText(canteen.getCanteenNo());
		this.canteenName.setText(canteen.getCanteenName());
		this.canteenLocation.setText(canteen.getCanteenLocation());	
		this.manager.setText(canteen.getManager());
	}
}

class Canteen {
	public String canteenNo;										//���
	public String canteenName;										//����
	public String canteenLocation;									//��ַ
	public String manager;											//������
	public String getCanteenNo() {
		return canteenNo;
	}
	public void setCanteenNo(String canteenNo) {
		this.canteenNo = canteenNo;
	}
	public String getCanteenName() {
		return canteenName;
	}
	public void setCanteenName(String canteenName) {
		this.canteenName = canteenName;
	}
	public String getCanteenLocation() {
		return canteenLocation;
	}
	public void setCanteenLocation(String canteenLocation) {
		this.canteenLocation = canteenLocation;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	
}
