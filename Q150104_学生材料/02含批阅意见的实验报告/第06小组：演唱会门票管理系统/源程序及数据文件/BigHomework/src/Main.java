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
	Vector conList=new Vector();
	
	MainFrame(){
		super("�ݳ�����Ʊ����ϵͳ");
		
		this.setTitle("�ݳ�����Ʊ����ϵͳ");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.readConFile();
		this.showConPanel();
		this.setVisible(true);

		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	void readConFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Concert.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Concert con=new Concert();
				String[] temp=s.split(" ");
				con.setstaNo(temp[0]);
				con.setperNo(temp[1]);
				con.setconNo(temp[2]);
				con.setconTime(temp[3]);
				con.setconName(temp[4]);
				con.setconCost(temp[5]);
				conList.add(con);
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	
	void showConPanel(){
		ConPanel conPanel=new ConPanel();
		conPanel.conList=this.conList;
		this.add(conPanel,BorderLayout.CENTER);
		conPanel.showConcert(0);
		this.setVisible(true);
	}
}

class ConPanel extends JPanel {
	private JTextField staNo=new JTextField();											//ѧ��
	private JTextField perNo=new JTextField();										//����
	private JTextField conNo=new JTextField();											//�Ա�
	private JTextField conTime=new JTextField();									//��������
	private JTextField conName=new JTextField();
    private JTextField conCost=new JTextField();	
	Vector conList=new Vector();
	private String[] btnStr={"��һ��","��һ��","��һ��","���һ��","���","�޸�","ɾ��"};
	private JButton[] btn= new JButton[btnStr.length];
	
	ConPanel(){
		this.setLayout(null);
		//��ʾ����
		JLabel lb1=new JLabel("���ݣ�");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		staNo.setBounds(100,10, 100, 20);
		this.add(staNo);
		//��ʾ����
		JLabel lb2=new JLabel("���ˣ�");
		lb2.setBounds(30, 40, 100, 20);
		this.add(lb2);
		perNo.setBounds(100,40, 100, 20);
		this.add(perNo);
		//��ʾ���
		JLabel lb3=new JLabel("��ţ�");
		lb3.setBounds(30, 70, 100, 20);
		this.add(lb3);
		conNo.setBounds(100,70, 100, 20);
		this.add(conNo);
		//��ʾʱ��
		JLabel lb4=new JLabel("ʱ�䣺");
		lb4.setBounds(30, 100, 100, 20);
		this.add(lb4);
		conTime.setBounds(100,100, 100, 20);
		this.add(conTime);
		//��ʾ����
		JLabel lb5=new JLabel("���ƣ�");
		lb5.setBounds(30, 130, 100, 20);
		this.add(lb5);
		conName.setBounds(100,130, 100, 20);
		this.add(conName);
		//��ʾƱ��
		JLabel lb6=new JLabel("Ʊ�ۣ�");
		lb6.setBounds(30, 160, 100, 20);
		this.add(lb6);
		conCost.setBounds(100,160, 100, 20);
		this.add(conCost);
		
		//��ʾ���ư�ť
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
		}
	}
	
	void showConcert(int offset){
		Concert con=(Concert) conList.get(offset);
		this.staNo.setText(con.getstaNo());
		this.perNo.setText(con.getperNo());
		this.conNo.setText(con.getconNo());
		this.conTime.setText(con.getConTime());
	    this.conName.setText(con.getConTime());
	    this.conCost.setText(con.getConCost());
	}
}

class Concert {
	private String staNo; // ���ݱ��
	private String perNo; // ���˱��
	private String conNo;//�ݳ�����
	private String conTime; // ʱ��
	private String conName; // �ݳ�������
	private String conCost; // �ݳ���Ʊ��
	public String getStaNo() {
		return staNo;
	}
	public String getconNo() {
		// TODO �Զ����ɵķ������
		return null;
	}
	public String getperNo() {
		// TODO �Զ����ɵķ������
		return null;
	}
	public String getstaNo() {
		// TODO �Զ����ɵķ������
		return null;
	}
	public void setconCost(String string) {
		// TODO �Զ����ɵķ������
		
	}
	public void setconName(String string) {
		// TODO �Զ����ɵķ������
		
	}
	public void setconTime(String string) {
		// TODO �Զ����ɵķ������
		
	}
	public void setconNo(String string) {
		// TODO �Զ����ɵķ������
		
	}
	public void setperNo(String string) {
		// TODO �Զ����ɵķ������
		
	}
	public void setstaNo(String string) {
		// TODO �Զ����ɵķ������
		
	}
	public void setStaNo(String staNo) {
		this.staNo = staNo;
	}
	public String getPerNo() {
		return perNo;
	}
	public void setPerNo(String perNo) {
		this.perNo = perNo;
	}
	public String getConNo() {
		return conNo;
	}
	public void setConNo(String conNo) {
		this.conNo = conNo;
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
	public String getConCost() {
		return conCost;
	}
	public void setConCost(String conCost) {
		this.conCost = conCost;
	}
	
	}