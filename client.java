import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
class client extends JFrame implements ActionListener
{
  static Socket sckt;
  static DataInputStream dtinpt;
  static DataOutputStream dtotpt;
 JTextField t;
static  JTextArea ta;
 JButton send;
   client() throws Exception
    {
	     setVisible(true);
	     setSize(100,100);
	     setLayout(new FlowLayout());
	     t=new JTextField(15);
	     ta=new JTextArea();
	     send=new JButton("Send");
	     add(ta);
	     add(t);
	     add(send);
	     sckt = new Socket("localhost", 1201);
	     dtinpt = new DataInputStream(sckt.getInputStream());
	     dtotpt = new DataOutputStream(sckt.getOutputStream());
	    send.addActionListener(this);
	}
 public void actionPerformed(ActionEvent evt) {
  try {

    String msgout = "";
   msgout = t.getText().trim();
   dtotpt.writeUTF(msgout);
  } catch (Exception e) {}
 }
 public static void main(String args[]) throws Exception
 {
             client c=new client();
             String msgin = "";
	 	      while (!msgin.equals("Exit")) {
	 	      msgin = dtinpt.readUTF();
             ta.setText(ta.getText().trim() + "\n Server:" + msgin);
           }
  }
}