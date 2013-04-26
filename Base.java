import java.awt.*;
import java.awt.Dialog.*;
import javax.swing.*;
import java.awt.event.*;

public class Base implements ActionListener 
{
	JFrame f;
	JTextField text1,text2,text3,text4,text5,text6,text7;
	JLabel name1,name2,name3,name4,name5,name6,name7;
	JButton button,button1,button2,button3,button4,button5,button6,button7,button8;
	JPanel panel1,panel2,panel3,panel4,panel5;
	public int f1=0;
	public int f2=0;
	
	public String mul(String s , String s1 , int b)
	{
		int dot=0,i=0,j=0;
		String cno="";
		char[] num1 = s.toCharArray();
		i = num1.length-1;
		while(num1[i] != '.')
		{
			i--;
		}
		int k = i;
		dot = dot + (num1.length-i);
		for(i=k;i<num1.length-1;i++)
		{
			num1[i] = num1[i+1];
		}
		num1[num1.length-1]=0;
		char[] num2 = s1.toCharArray();
		i = num2.length-1;
		while(num2[i] != '.')
		{
			i--;
		}
		k = i;
		dot = dot + (num2.length-i);
		for(i=k;i<num2.length-1;i++)
		{
			num2[i] = num2[i+1];
		}
		num2[num2.length-1]=0;
		for(i=0;i<num1.length;i++)
		{
			num1[i] = convert(num1[i],b);
		}
		for(i=0;i<num2.length;i++)
		{
			num2[i] = convert(num2[i],b);
		}
		char[][] res;
		res = new char[num2.length][100];
		int p=99,x=0,y=0;
		long r=0,sum=0,l=0;
		char c;
		for(i=num2.length-1;i>=0;i--)
		{
			l=0;
			k=num2.length-1-i;
			for(j=num1.length-1;j>=0;j--)
			{
				sum = ((int)num1[j] * (int)num2[i]) + l;
				r = sum%b;
				r = convertb((int)r);
				res[num2.length-1-i][99-k-(num1.length-1-j)] = (char)r;
				l = sum/b;
				y = 99-k-(num1.length-1-j);
				x = num2.length-1-i;
			}
			j = y-1;
			if(l != 0)
			{	
				do
				{
					r=l%b;
					r = convertb((int)r);
					res[num2.length-1-i][j] = (char)r;
					l=l/b;
					j--;
				}
				while(l > 0);
			}
		}
		for(i=99;i>=j;i--)
		{
			r=0;
			for(k=0;k<num2.length;k++)
			{
				r = r + (long)convert(res[k][i],b);
			}
			if(i == (99-dot))
			{
				cno='.'+cno;
			}
			r = convertb((int)r);
			c = (char)r;
			cno=c+cno;
		}
		cno=cno.substring(1,cno.length()-3);
		return cno;
	}
	
	public String sub(String s , String s1 , int b) /* Subtraction */
	{
		char[] t;
		t = new char[100];
		int l=0,r,x,i,lsb=0;
		String sum="";
		char c;
		String[] sb = lenc(s,s1);
		sb[1] = comp(sb[1],b,'s');
		String[] s01 = sb[0].split("\\.");
		String[] s02 = sb[1].split("\\.");
		char[] digit1int = s01[0].toCharArray();
		char[] digit2int = s02[0].toCharArray();
		char[] digit1flt = s01[1].toCharArray();
		char[] digit2flt = s02[1].toCharArray();
		for(i=0;i<digit1int.length;i++)
		{
			digit1int[i] = convert(digit1int[i],b);
			digit2int[i] = convert(digit2int[i],b);
		}
		for(i=0;i<digit1flt.length;i++)
		{
			digit1flt[i] = convert(digit1flt[i],b);
			digit2flt[i] = convert(digit2flt[i],b);
		}
		for(i=digit2flt.length-1;i>=0;i--)
			{
				if(digit2flt[i] != 0)
				{
					lsb = i +1;
					
					break;
				}
			}
		for(i=(digit1flt.length+digit1int.length);i>=(digit1int.length+1);i--)
		{
			x = (int)digit1flt[i-(digit1int.length+1)] + (int)digit2flt[i-(digit1int.length+1)] + l;
			r = x%b;
			r = convertb(r);
			t[i] = (char)r;
			l = x/b;
		}
		c='.';
		t[digit1int.length]=c;
		for(i=digit1int.length-1;i>=0;i--)
		{
			x = (int)digit1int[i] + (int)digit2int[i] + l;
			r = x%b;
			r = convertb(r);
			t[i] = (char)r;
			l = x/b;
		}
		if(l != 0)
		{
			i = lsb+digit1int.length;
			while(l > 0)
			{
				if(i == digit1int.length)
				{
					i--;
				}
				else 
				{
					t[i]=convert(t[i],b);						
					x = (int)t[i] + l;
					r = x%b;
					r = convertb(r);
					t[i] = (char)r;
					l = x/b;
					i--;
				}
			}
			for(i=(digit1flt.length+digit1int.length);i>=0;i--)
			{
				c = t[i];
				sum=c+sum;
			}
		}
		else
		{
			for(i=(digit1flt.length+digit1int.length);i>=0;i--)
			{
				c = t[i];
				sum=c+sum;
			}
			sum=comp(sum,b,'s');
			s01=sum.split("\\.");
			digit1int=s01[0].toCharArray();
			digit1flt=s01[1].toCharArray();
			f1=f2=0;
			for(i=0;i<digit1int.length;i++)
			{
				if((int)digit1int[i]==48)
				{
					continue;
				}
				else
				{
					f1=1;
					break;
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				if((int)digit1flt[i]==48)
				{
					continue;
				}
				else
				{
					f2=1;
					break;
				}
			}
			if(f1 == 1 || f2 == 1)
			{
				sum='-'+sum;
			}
		}
		return sum;
	}
	int vcom(String s , String s1) /* Value Comparison */
	{
		int i,j=0;
		String[] sb = lenc(s,s1);
		String[] s01,s02;
		s01 = sb[0].split("\\.");
		s02 = sb[1].split("\\.");
		char[] digit1int = s01[0].toCharArray();
		char[] digit1flt = s01[1].toCharArray();
		char[] digit2int = s02[0].toCharArray();
		char[] digit2flt = s02[1].toCharArray();
		for(i=0;i<digit1int.length;i++)
		{
			if((int)digit1int[i] > (int)digit2int[i])
			{
				j = 1;
				break;
			}
			else if((int)digit1int[i] < (int)digit2int[i])
			{
				j = -1;
				break;
			}
			else
			{
				continue;
			}
		}
		if(j == 0)
		{
			for(i=0;i<digit1flt.length;i++)
			{
				if((int)digit1flt[i] > (int)digit2flt[i])
				{
					j = 1;
					break;
				}
				else if((int)digit1flt[i] < (int)digit2flt[i])
				{
					j = -1;
					break;
				}
				else
				{
					continue;
				}
			}
		}
		return j;
	}
		
	public String cbase(String s , int b , int b1) /* BASE CONVERSION */
	{
		f1=f2=0;
		String[] s1 = null;
		s1 = s.split("\\.");
		char[] digit = s1[0].toCharArray();
		char[] digit1 = s1[1].toCharArray();
		char[] digit2;
		digit2 = new char[1000];
		String cno="";
		int r=0,no1=0;
		double z;
		double no=0,no2=0;
		int i;
		char c;
		for(i=0;i<digit.length;i++)
		{
			digit[i] = convert(digit[i],b);
			if((int)digit[i]>=b)
			{
				f1 = 1;
				break ;
			}
		}
		for(i=0;i<digit1.length;i++)
		{
			digit1[i] = convert(digit1[i],b);
			if((int)digit1[i]>=b)
			{
				f2 = 1;
				break ;
			}
		}
		if(f1 == 0 && f2 == 0 && b1 > 1 && b1 <= 62 && b <=62 && b >=2)
		{
			for(i=0;i<digit.length;i++)
			{
				no = no + (double)(Math.pow(b,(digit.length-1)-i))*(double)digit[i];
			}
			for(i=0;i<digit1.length;i++)
			{
				no = no + (double)(Math.pow(b,-(i+1)))*(double)digit1[i];
			}
			if(b1 == 10)
			{
				cno=String.valueOf(no);
				return cno;
			}
			else
			{
			no1 = (int)no;
			no2 =  no - (double)((int)no);
			i=0;
			do
			{
				z=(no2*b1);
				r = convertb((int)z);
				c=(char)r;
				cno=cno+c;
				no2 = z%1;
			}
			while((int)(no2*1000000000) > 0 && i<1000);
			int k=i;
			char d='.';
			cno=d+cno;
			do
			{
				r=no1%b1;
				r = convertb(r);
				c = (char)r;
				cno=c+cno;
				no1 = no1/b1;
			}  
			while(no1>0);
			return cno;
			}
		}
		else
		{
			text2.setText("Invalid number");
			text5.setText(" ");
			cno="Invalid number";
			return cno;
		}
	}
	public String comp(String s , int b , char c) /* COMPLEMENT */
	{
		int i,j;
		f1=f2=0;
		String[] s1 = s.split("\\.");
		char[] digitint = s1[0].toCharArray();
		char[] digitflt = s1[1].toCharArray();
		for(i=0;i<digitflt.length;i++)
		{
			digitflt[i] = convert(digitflt[i],b);
			if((int)digitflt[i]>=b)
			{
				f2 = 1;
				break;
			}
		}
		for(i=0;i<digitint.length;i++)
		{
			digitint[i] = convert(digitint[i],b);
			if((int)digitint[i]>=b)
			{
				f1 = 1;
				break;
			}
		}
		if(f1 == 0 && f2 == 0 &&  b >= 2 && b <= 62)
		{
			String cno="";
			i=0;
			int m=0,n=0;
			if(c == 'c')
			{
				for(j=digitflt.length-1;j>=0;j--)
				{
					if(digitflt[j] != 0)
					{
						n = j +1;
						break;
					}
				}
				for(j=0;j<digitint.length;j++)
				{
					if(digitint[j] != 0)
					{
						m = digitint.length - j;
						break ;
					}
				}
			}
			if(c == 's')
			{
				m = digitint.length;
				n = digitflt.length;
			}
			for(i=digitflt.length-1;i>=0;i--)
			{
				if(i<n)
				{
					digitflt[i] = (char)((b-1) - (int)digitflt[i]);
					digitflt[i] = (char)convertb((int)digitflt[i]);
				}
				else
				{
					digitflt[i] = (char)convertb((int)digitflt[i]);
				}
				cno=digitflt[i]+cno;
			}
			cno='.'+cno;
			for(i=digitint.length-1;i>=0;i--)
			{
				if(i>=(digitint.length -m))
				{
					digitint[i] = (char)((b-1) - (int)digitint[i]);
					digitint[i] = (char)convertb((int)digitint[i]);
				}
				else
				{
					digitint[i] = (char)convertb((int)digitint[i]);
				}
				cno=digitint[i]+cno;
			}
			return cno;
		}
		else
		{
			text2.setText("Invalid Number");
			text5.setText(" ");
			String cno="Invalid Number";
			return cno;
		}
	}
	public String[] lenc(String s , String s1) /* LENGTH COMPARISON AND MAKING IT EQUAL */
	{
		String[] s11 = null;
		s11 = s.split("\\.");
		String[] s12 = null;
		s12 = s1.split("\\.");
		String v1 = s11[0];
		String v2 = s12[0];
		int j = (v1.length()) - (v2.length());
		int i;
		if(j < 0)
		{
			for(i=0;i<-j;i++)
			{
				v1='0'+v1;
			}
		}
		if(j > 0)
		{
			for(i=0;i<j;i++)
			{
				v2='0'+v2;
			}
		}
		j = 0;
		j = (s11[1].length()) - (s12[1].length());
			if(j < 0)
			{
				for(i=0;i<-j;i++)
				{
					s11[1]=s11[1].concat("0");
				}
			}
			if(j > 0)
			{
				for(i=0;i<j;i++)
				{
					s12[1]=s12[1].concat("0");
				}
			}
		v1=v1.concat(".");
		v2=v2.concat(".");
		v1=v1.concat(s11[1]);
		v2=v2.concat(s12[1]);
		String[] sb = {v1 , v2};
		return sb;
	}
	char convert(char digit , int b) /* CONVERT ASCII VALUE TO NUMBER */
	{
		if((int)digit>47 && (int)digit<58)
		{
			digit = (char)((int)digit - 48);
		}
		if((int)digit>64 && (int)digit<91)
		{
			digit = (char)((int)digit - 55);
		}
		if((int)digit>96 && (int)digit<123)
		{
			digit = (char)((int)digit - 61);
		}
		return digit;
	}	
	int convertb(int r) /* CONVERT NUMBER TO ASCII VALUE */
	{
		if(r>9 && r<36)
		{	
			r+=55;
		}
		else if(r>35)
		{
			r+=61;
		}
		else if(r<10)
		{
			r+=48;				
		}
		return r;
	}
	Base(String j)
	{
		f = new JFrame(j);
		f.getContentPane().setLayout(new BoxLayout(f.getContentPane(), BoxLayout.Y_AXIS));
		f.setLocationRelativeTo(null);
		text1 = new JTextField(20);
		text3 = new JTextField(3);
		text4 = new JTextField(3);
		text2 = new JTextField(20);
		text5 = new JTextField(3);
		text6 = new JTextField(20);
		text7 = new JTextField(3);
		name1 = new JLabel("number 1");
		name2 = new JLabel("base 1");
		name4 = new JLabel("new base 1");
		name6 = new JLabel("number 2");
		name7 = new JLabel("base 2");
		name3 = new JLabel("converted number");
		name5 = new JLabel("new base");
		button = new JButton("Convert");
		button1 = new JButton("Add");
		button2 = new JButton("Subtract");
		button3 = new JButton("NOT");
		button4 = new JButton("AND");
		button5 = new JButton("OR");
		button6 = new JButton("Multiply");
		button7 = new JButton("Divide");
		button8 = new JButton("HELP");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.add(name1);
		panel1.add(text1);
		panel1.add(name2);
		panel1.add(text3);
		panel1.add(name4);
		panel1.add(text4);
		panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel4.add(name6);
		panel4.add(text6);
		panel4.add(name7);
		panel4.add(text7);
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel5.add(button);
		panel5.add(button3);
		panel5.add(button4);
		panel5.add(button5);
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.add(button1);
		panel3.add(button2);
		panel3.add(button6);
		panel3.add(button7);
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(name3);
		panel2.add(text2);
		panel2.add(name5);
		panel2.add(text5);
		panel2.add(button8);
		f.getContentPane().add(panel1);
		f.getContentPane().add(panel4);
		f.getContentPane().add(panel5);
		f.getContentPane().add(panel3);
		f.getContentPane().add(panel2); 
		ActionListener m = new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{
				JOptionPane.showMessageDialog(f, "Thank You for using this Calculator \n \n -Please input float type string (A.0 or 10.0) \n \n -When Using Convert just fill up the first number base and new base \n \n -When using other functions do not fill new base \n \n -When using convert or OR only input number 1 and base 1");
			}
		};
		ActionListener l = new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				System.out.println("Action event from a text field");
			}
		};
		button8.addActionListener(m);
		text1.addActionListener(l);
		text2.addActionListener(l);
		text3.addActionListener(l);
		text4.addActionListener(l);
		text5.addActionListener(l);
		text6.addActionListener(l);
		text7.addActionListener(l);
		button.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		f.setSize(530,210);
		f.setVisible(true);
		JOptionPane.showMessageDialog(f, "Thank You for using this Calculator \n \n -Please input float type string (A.0 or 10.0) \n \n -When Using Convert just fill up the first number base and new base \n \n -When using other functions do not fill new base \n \n -When using convert or OR only input number 1 and base 1");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{ 
		String s = text1.getText();
		int b = Integer.parseInt(text3.getText()); 
		if(e.getSource()==button) /* BASE CONVERSION */
		{
			int i=0;
			int b1 = Integer.parseInt(text4.getText());
			String[] s11 = s.split("\\.");
			char[] digit1int = s11[0].toCharArray();
			char[] digit1flt = s11[1].toCharArray();
			f1=f2=0;
			for(i=0;i<digit1int.length;i++)
			{
				digit1int[i] = convert(digit1int[i],b);
				if((int)digit1int[i] >= b)
				{
					f1=1;
					break;
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				digit1flt[i] = convert(digit1flt[i],b);
				if((int)digit1flt[i] >= b)
				{
					f2=1;
					break;
				}
			}
			if(f1 == 0 && f2 == 0 && b1 > 1 && b1 <= 62 && b >= 2 && b <= 62)
			{
				String cno = cbase(s,b,b1);
				text2.setText(cno);
				text5.setText(String.valueOf(b1));
			}
			else
			{
				text2.setText("Invalid Number");
				text5.setText(" ");
			}
		}
		if(e.getSource() == button1) /* ADDITION */
		{
			String s1 = text6.getText();
			int b1 = Integer.parseInt(text7.getText());
			if(b1 != b)
			{
				s1 = cbase(s1,b1,b);
				b1 = b;
			}
			String[] sb = lenc(s,s1);
			String[] s11 = sb[0].split("\\.");
			String[] s12 = sb[1].split("\\.");
			char[] digit1int = s11[0].toCharArray();
			char[] digit2int = s12[0].toCharArray();
			char[] digit1flt = s11[1].toCharArray();
			char[] digit2flt = s12[1].toCharArray();
			f1=f2=0;
			int i,j;
			for(i=0;i<digit1int.length;i++)
			{
				digit1int[i] = convert(digit1int[i],b);
				if((int)digit1int[i] >= b)
				{
					f1 = 1;
					break;
				}
				if(f1 == 0)
				{
					digit2int[i] = convert(digit2int[i],b);
					if((int)digit2int[i] >= b)
					{
						f1 = 1;
						break;
					}
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				digit1flt[i] = convert(digit1flt[i],b);
				if((int)digit1flt[i] >= b)
				{
					f2 = 1;
					break;
				}
				if(f2 == 0)
				{
					digit2flt[i] = convert(digit2flt[i],b);
					if((int)digit2flt[i] >= b)
					{
						f2 = 1;
						break;
					}
				}
			}
			if(f1 == 0 && f2 == 0)
			{
				int t,l=0,r;
				String sum=" ";
				char c;
				for(i=digit1flt.length-1;i>=0;i--)
				{
					t = (int)digit1flt[i] + (int)digit2flt[i] + l;
					r = t%b;
					r = convertb(r);
					c = (char)r;
					sum=c+sum;
					l = t/b;
				}
				sum='.'+sum;
				for(i=digit1int.length-1;i>=0;i--)
				{
					t = (int)digit1int[i] + (int)digit2int[i] + l;
					r = t%b;
					r = convertb(r);
					c = (char)r;
					sum=c+sum;
					l = t/b;
				}
				if(l != 0)
				{
					l=convertb(l);
					c=(char)l;
					sum=c+sum;
				}
				text2.setText(sum);
				text5.setText(String.valueOf(b));
			}
			else
			{
				text2.setText("Invalid Number");
				text5.setText(" ");
			}
		}
		if(e.getSource() == button2)     /* SUBTRACTION */
		{
			String s1 = text6.getText();
			int b1 = Integer.parseInt(text7.getText());
			int j,i;
			if(b1 != b)
			{
				s1 = cbase(s1,b1,b);
				b1 = b;
			}
			String[] sb = lenc(s,s1);
			String[] s01 = sb[0].split("\\.");
			String[] s02 = sb[1].split("\\.");
			char[] digit1int = s01[0].toCharArray();
			char[] digit2int = s02[0].toCharArray();
			char[] digit1flt = s01[1].toCharArray();
			char[] digit2flt = s02[1].toCharArray();
			f1=f2=0;
			for(i=0;i<digit1int.length;i++)
			{
				digit1int[i] = convert(digit1int[i],b);
				if((int)digit1int[i] >= b)
				{
					f1 = 1;
					break;
				}
				if(f1 == 0)
				{
					digit2int[i] = convert(digit2int[i],b);
					if((int)digit2int[i] >= b)
					{
						f1 = 1;
						break;
					}
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				digit1flt[i] = convert(digit1flt[i],b);
				if((int)digit1flt[i] >= b)
				{
					f2 = 1;
					break;
				}
				if(f2 == 0)
				{
					digit2flt[i] = convert(digit2flt[i],b);
					if((int)digit2flt[i] >= b)
					{
						f2 = 1;
						break;
					}
				}
			}
			if(f1 == 0 && f2 == 0 && b1 >= 2 && b1 <= 62 && b >= 2 && b <= 62)
			{
				String cno = sub(s,s1,b);
				text2.setText(cno);
				text5.setText(String.valueOf(b));				
			}
			else
			{
				text2.setText("Invalid Number");
				text5.setText(" ");
			}
		}
		if(e.getSource()==button3) /* NOT FUNCTION */
		{
			String cno = comp(s,b,'c');
			text2.setText(cno);
			if(cno!="Invalid Number")
			{
				text5.setText(String.valueOf(b));
			}
			else
			{
				text5.setText(" ");
			}
		}
		if(e.getSource()==button4) /* AND FUNCTION */
		{
			String s1 = text6.getText();
			int b1 = Integer.parseInt(text7.getText());
			int i,j;
			char c;
			if(b1 != b)
			{
				s1 = cbase(s1,b1,b);
				b1=b;
			}
			String cno="";
			String[] sb = lenc(s,s1);
			String[] s01 = sb[0].split("\\.");
			String[] s02 = sb[1].split("\\.");
			char[] digit1int = s01[0].toCharArray();
			char[] digit2int = s02[0].toCharArray();
			char[] digit1flt = s01[1].toCharArray();
			char[] digit2flt = s02[1].toCharArray();
			f1=f2=0;
			for(i=0;i<digit1int.length;i++)
			{
				digit1int[i] = convert(digit1int[i],b);
				if((int)digit1int[i] >= b)
				{
					f1 = 1;
					break;
				}
				if(f1 == 0)
				{
					digit2int[i] = convert(digit2int[i],b);
					if((int)digit2int[i] >= b)
					{
						f1 = 1;
						break;
					}
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				digit1flt[i] = convert(digit1flt[i],b);
				if((int)digit1flt[i] >= b)
				{
					f2 = 1;
					break;
				}
				if(f2 == 0)
				{
					digit2flt[i] = convert(digit2flt[i],b);
					if((int)digit2flt[i] >= b)
					{
						f2 = 1;
						break;
					}
				}
			}
			if(f1 == 0 && f2 == 0 && b1 >= 2 && b1 <= 62 && b >= 2 && b <= 62)
			{
				cno="";
				sb[1]=cbase(sb[1],b,2);
				sb[0]=cbase(sb[0],b,2);
				sb = lenc(sb[0],sb[1]);
				s01 = sb[0].split("\\.");
				s02 = sb[1].split("\\.");
				char[] digit1int2 = s01[0].toCharArray();
				char[] digit2int2 = s02[0].toCharArray();
				char[] digit1flt2 = s01[1].toCharArray();
				char[] digit2flt2 = s02[1].toCharArray();
				for(i=digit1flt2.length-1;i>=0;i--)
				{
					if(digit1flt2[i] == '1' && digit2flt2[i] == '1')
					{
						cno='1'+cno;
					}
					else
					{
						cno='0'+cno;
					}
				}
				cno='.'+cno;
				for(i=digit1int2.length-1;i>=0;i--)
				{
					if(digit1int2[i] == '1' && digit2int2[i] == '1')
					{
						cno='1'+cno;
					}
					else
					{
						cno='0'+cno;
					}
				}
				cno=cbase(cno,2,b);
				cno="";
				for(i=digit1flt.length-1;i>=0;i--)
				{
					j =(int)digit1flt[i] & (int)digit2flt[i];
					j = convertb(j);
					c = (char)j;
					cno=c+cno;
				}
				cno='.'+cno;
				for(i=digit1int.length-1;i>=0;i--)
				{
					j =(int)digit1int[i] & (int)digit2int[i];
					j = convertb(j);
					c = (char)j;
					cno=c+cno;
				}
				text2.setText(cno);
				text5.setText(String.valueOf(b));				
			}
			else
			{
				text2.setText("Invalid Number");
				text5.setText(" ");
			}
		}
		if(e.getSource()==button5) /* OR FUNCTION */
		{
			String s1 = text6.getText();
			int b1 = Integer.parseInt(text7.getText());
			int i,j;
			char c;
			if(b1 != b)
			{
				s1 = cbase(s1,b1,b);
				b1=b;
			}
			String cno="";
			String[] sb = lenc(s,s1);
			String[] s01 = sb[0].split("\\.");
			String[] s02 = sb[1].split("\\.");
			char[] digit1int = s01[0].toCharArray();
			char[] digit2int = s02[0].toCharArray();
			char[] digit1flt = s01[1].toCharArray();
			char[] digit2flt = s02[1].toCharArray();
			f1=f2=0;
			for(i=0;i<digit1int.length;i++)
			{
				digit1int[i] = convert(digit1int[i],b);
				if((int)digit1int[i] >= b)
				{
					f1 = 1;
					break;
				}
				if(f1 == 0)
				{
					digit2int[i] = convert(digit2int[i],b);
					if((int)digit2int[i] >= b)
					{
						f1 = 1;
						break;
					}
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				digit1flt[i] = convert(digit1flt[i],b);
				if((int)digit1flt[i] >= b)
				{
					f2 = 1;
					break;
				}
				if(f2 == 0)
				{
					digit2flt[i] = convert(digit2flt[i],b);
					if((int)digit2flt[i] >= b)
					{
						f2 = 1;
						break;
					}
				}
			}
			if(f1 == 0 && f2 == 0 && b1 >= 2 && b1 <= 62 && b >= 2 && b <= 62)
			{
				cno="";
				sb[1]=cbase(sb[1],b,2);
				sb[0]=cbase(sb[0],b,2);
				sb = lenc(sb[0],sb[1]);
				s01 = sb[0].split("\\.");
				s02 = sb[1].split("\\.");
				char[] digit1int2 = s01[0].toCharArray();
				char[] digit2int2 = s02[0].toCharArray();
				char[] digit1flt2 = s01[1].toCharArray();
				char[] digit2flt2 = s02[1].toCharArray();
				for(i=digit1flt2.length-1;i>=0;i--)
				{
					if(digit1flt2[i] == '0' && digit2flt2[i] == '0')
					{
						cno='0'+cno;
					}
					else
					{
						cno='1'+cno;
					}
				}
				cno='.'+cno;
				for(i=digit1int2.length-1;i>=0;i--)
				{
					if(digit1int2[i] == '0' && digit2int2[i] == '0')
					{
						cno='0'+cno;
					}
					else
					{
						cno='1'+cno;
					}
				}
				cno=cbase(cno,2,b);
				cno="";
				for(i=digit1flt.length-1;i>=0;i--)
				{
					j =(int)digit1flt[i] | (int)digit2flt[i];
					j = convertb(j);
					c = (char)j;
					cno=c+cno;
				}
				cno='.'+cno;
				for(i=digit1int.length-1;i>=0;i--)
				{
					j =(int)digit1int[i] | (int)digit2int[i];
					j = convertb(j);
					c = (char)j;
					cno=c+cno;
				}
				text2.setText(cno);
				text5.setText(String.valueOf(b));				
			}
			else
			{
				text2.setText("Invalid Number");
				text5.setText(" ");
			}
		}
		if(e.getSource()==button6) /* MULTIPLICATION */
		{
			String s1 = text6.getText();
			int b1 = Integer.parseInt(text7.getText());
			if(b1 != b)
			{
				s1 = cbase(s1,b1,b);
			}
			int i=0,j=0;
			String[] sb = lenc(s,s1);
			String cno="";
			String[] s01 = sb[0].split("\\.");
			String[] s02 = sb[1].split("\\.");
			char[] digit1int = s01[0].toCharArray();
			char[] digit2int = s02[0].toCharArray();
			char[] digit1flt = s01[1].toCharArray();
			char[] digit2flt = s02[1].toCharArray();
			f1=f2=0;
			for(i=0;i<digit1int.length;i++)
			{
				digit1int[i] = convert(digit1int[i],b);
				if((int)digit1int[i] >= b)
				{
					f1 = 1;
					break;
				}
				if(f1 == 0)
				{
					digit2int[i] = convert(digit2int[i],b);
					if((int)digit2int[i] >= b)
					{
						f1 = 1;
						break;
					}
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				digit1flt[i] = convert(digit1flt[i],b);
				if((int)digit1flt[i] >= b)
				{
					f2 = 1;
					break;
				}
				if(f2 == 0)
				{
					digit2flt[i] = convert(digit2flt[i],b);
					if((int)digit2flt[i] >= b)
					{
						f2 = 1;
						break;
					}
				}
			}
			if(f1 == 0 && f2 == 0 && b1 >= 2 && b1 <= 62 && b >= 2 && b <= 62)
			{
				cno = mul(s,s1,b);
				text2.setText(cno);
				text5.setText(String.valueOf(b));
			}
			else
			{
				text2.setText("Invalid Number");
				text5.setText(" ");
			}
		}
		if(e.getSource()==button7) /* DIVIDE */
		{
			String s1 = text6.getText();
			int b1 = Integer.parseInt(text7.getText());
			if(b1 != b)
			{
				s1 = cbase(s1,b1,b);
			}
			int i=0,j=0,z=0,x=0;
			String[] sb = lenc(s,s1);
			String cno="";
			String[] s01 = sb[0].split("\\.");
			String[] s02 = sb[1].split("\\.");
			char[] digit1int = s01[0].toCharArray();
			char[] digit2int = s02[0].toCharArray();
			char[] digit1flt = s01[1].toCharArray();
			char[] digit2flt = s02[1].toCharArray();
			f1=f2=0;
			for(i=0;i<digit1int.length;i++)
			{
				digit1int[i] = convert(digit1int[i],b);
				if((int)digit1int[i] >= b)
				{
					f1 = 1;
					break;
				}
				if(f1 == 0)
				{
					digit2int[i] = convert(digit2int[i],b);
					if((int)digit2int[i] >= b)
					{
						f1 = 1;
						break;
					}
					if((int)digit2int[i] == 0)
					{
						z++;
					}
				}
			}
			for(i=0;i<digit1flt.length;i++)
			{
				digit1flt[i] = convert(digit1flt[i],b);
				if((int)digit1flt[i] >= b)
				{
					f2 = 1;
					break;
				}
				if(f2 == 0)
				{
					digit2flt[i] = convert(digit2flt[i],b);
					if((int)digit2flt[i] >= b)
					{
						f2 = 1;
						break;
					}
					if((int)digit2flt[i] == 0)
					{
						x++;
					}
				}
			}
			if(f1 == 0 && f2 == 0 && b1 >= 2 && b1 <= 62 && b >= 2 && b <= 62 && (x+z) != (digit2flt.length + digit1flt.length))
			{
				int q=0,r=0;
				String q1="",q2="";
				if(vcom(s,s1) >= 0)
				{
					do
					{
						s=sub(s,s1,b);
						r=vcom(s,s1);
						q++;
					}
					while(r>=0);
				}
				else
				{
					q=0;
				}
				q1=String.valueOf(q);
				q1=q1+'.';
				char[] digit;
				digit = new char[100];
				String xy="";
				for(i=0;i<4;i++)
				{
					q=0;
					j=0;
					xy = cbase("10.0",10,b);
					s = mul(s,xy,b);
					sb = lenc(s,s1);
					s = sb[0];
					s1 = sb[1];
					if(vcom(s,s1)>0)
					{
						do
						{
							s=sub(s,s1,b);
							
							r=vcom(s,s1);
							q++;
						}
						while(r>=0);
					}
					else
					{
						q=0;
					}
					q2=String.valueOf(q);
					q1=q1+q2;
				}
				cno=cbase(q1,10,b);
				text2.setText(cno);
				text5.setText(String.valueOf(b));
			}
			else if(z == digit2int.length && x == digit2flt.length)
			{
				text2.setText("Cannot divide number by 0");
				text5.setText(" ");
			}
			else
			{
				text2.setText("Invalid Number");
				text5.setText(" ");
			}
		}
	}
	public static void main(String[] args)
	{
		new Base("Calculator by Moonis Javed ©");
	}
}