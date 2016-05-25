import java.awt.Font;
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

class MyFrame extends JFrame implements ActionListener{


	public MyFrame() throws HeadlessException {
		super();
		
		JButton b1=new JButton("ȷ��");
	b1.setBounds(420, 240, 100, 20);
	b1.addActionListener((ActionListener) this);
    this.add(b1);
	JButton b2=new JButton("����");
	b2.setBounds(550, 240, 100, 20);
	b2.addActionListener((ActionListener) this);
    this.add(b2);
    
		this.setTitle("��ϼ���������͹���ϵͳ");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		VarietyPanel p1 = new VarietyPanel();
		this.add(p1);
		this.setVisible(true);
		
		
	}
	void readStuFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Variety.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String v;
			while((v=reader.readLine())!=null)
			{
				Variety var=new Variety();
				String[] temp=v.split(" ");
				var.setMeat(temp[0]);
				var.setEgg(temp[1]);
				var.setVegetable(temp[2]);
				var.setSeafood(temp[3]);
				var.setCereal(temp[4]);
				
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	
}

class VarietyPanel extends JPanel{

	private JTextField meat=new JTextField();											//ѧ��
	private JTextField egg=new JTextField();										//����
	private JTextField vegetable=new JTextField();											//�Ա�
	private JTextField seafood=new JTextField();
	private JTextField cereal=new JTextField();
	


	
	
	VarietyPanel(){
		this.setLayout(null);
		//
		JLabel lb0=new JLabel("�������������ƣ�");
		lb0.setBounds(30, 10, 200, 20);
		this.add(lb0);
		meat.setBounds(100,10, 100, 20);
		this.add(meat);
		JLabel lb1=new JLabel("���ࣺ");
		lb1.setBounds(30, 40, 100, 20);
		this.add(lb1);
		meat.setBounds(100,40, 100, 20);
		this.add(meat);
		//��ʾ����
		JLabel lb2=new JLabel("���ࣺ");
		lb2.setBounds(30, 90, 100, 20);
		this.add(lb2);
		egg.setBounds(100,90, 100, 20);
		this.add(egg);
		//��ʾ����
		JLabel lb3=new JLabel("�߲��ࣺ");
		lb3.setBounds(30, 140, 100, 20);
		this.add(lb3);
		vegetable.setBounds(100,140, 100, 20);
		this.add(vegetable);
		//��ʾ��������
		JLabel lb4=new JLabel("�����ࣺ");
		lb4.setBounds(30, 190, 100, 20);
		this.add(lb4);
		seafood.setBounds(100,190, 100, 20);
		this.add(seafood);
		JLabel lb5=new JLabel("�����ࣺ");
		lb5.setBounds(30, 240, 100, 20);
		this.add(lb5);
		cereal.setBounds(100,240, 100, 20);
		this.add(cereal);
	}
}

	public class Main {

		public static void main(String[] args) {
			//����һ���������
			MyFrame f = new MyFrame();
		}

	}

	class Variety {
		private String meat;											//����
	    private String egg;											//����
		private String vegetable;										//�߲���
		private String seafood;										//������
		private String cereal;
		public String getMeat() {
			return meat;
		}
		public void setMeat(String meat) {
			this.meat = meat;
		}
		public String getEgg() {
			return egg;
		}
		public void setEgg(String egg) {
			this.egg = egg;
		}
		public String getVegetable() {
			return vegetable;
		}
		public void setVegetable(String vegetable) {
			this.vegetable = vegetable;
		}
		public String getSeafood() {
			return seafood;
		}
		public void setSeafood(String seafood) {
			this.seafood = seafood;
		}
		public String getCereal() {
			return cereal;
		}
		public void setCereal(String cereal) {
			this.cereal = cereal;
		}											//������
	}
		