import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;
import javax.swing.*;

public class Main{
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}
}

class MainFrame extends JFrame {
	Vector comList=new Vector();													//��̬���飬����һ������comList���������Ȳ�֪�������С
	
	MainFrame(){
		super("�����н����ϵͳ");
		
		this.setTitle("�����н����ϵͳ");											//��Ŀ
		this.setSize(700,320);														//�߿��С
		this.setResizable(false);													//�߿򲻿ɵ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						//���ƿ���		
		this.readComFile();															
		this.showComPanel();
		this.setVisible(true);														//�ɼ�ҳ��
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
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();													//�������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��
		}
		
	}
	
	void showComPanel(){
		ComPanel comPanel=new ComPanel();
		comPanel.comList=this.comList;
		this.add(comPanel,BorderLayout.CENTER);										//���ֹ�����
		comPanel.showCommunity(0);													//offset==0����ӵ�һ�����ݿ�ʼƫ��
		this.setVisible(true);
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

