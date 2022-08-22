import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
class Server extends JFrame implements ActionListener
{
  static Socket sckt;
  ServerSocket ssckt;
  static DataInputStream dtinpt;
  static DataOutputStream dtotpt;
 JTextField t;
static  JTextArea ta;
 JButton send;
   Server() throws Exception
    {
	   setVisible(true);
	   setLayout(new FlowLayout());
	   t=new JTextField(15);
	     ta=new JTextArea();
	     send=new JButton("Send");
	     add(ta);
	     add(t);
	     add(send);
     ssckt = new ServerSocket(1201);
     sckt = ssckt.accept();
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
             Server c=new Server();
             String msgin = "";
	 	      while (!msgin.equals("Exit")) {
	 	      msgin = dtinpt.readUTF();
             ta.setText(ta.getText().trim() + "\n Client:" + msgin);
           }
  }
}