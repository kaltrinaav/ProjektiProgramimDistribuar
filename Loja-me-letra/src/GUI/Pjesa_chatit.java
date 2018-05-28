package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Logic.ICallBack;

public class Pjesa_chatit extends JPanel implements KeyListener {

	private JTextField txtSend;
	private JTextArea chatArea;
	private ICallBack callBack;
	private JScrollPane chatScroll;
	private JList playersList;
	private DefaultListModel playersNames;
	private Color Background = new Color(255,218,185);
	private Color Background1 = new Color(255,218,185);

	public Pjesa_chatit(ICallBack CallBack) {

		this.callBack = CallBack;
		this.setLayout(new BorderLayout(5, 5));

		this.setPreferredSize(new Dimension(180, 400));
		this.txtSend = new JTextField();
		this.txtSend.addKeyListener(this);
		this.txtSend.setPreferredSize(new Dimension(180, 30));
		this.txtSend.setBackground(Background1);
		this.txtSend.setForeground(Color.white);
		this.txtSend.setBorder(null);

		this.chatArea = new JTextArea();
		this.chatArea.setEditable(false);
		// this.chatArea.setPreferredSize(new Dimension(200,400));
		this.chatArea.setBackground(Background1);
		this.chatArea.setForeground(Color.white);
		this.chatArea.setLineWrap(true);
		this.chatScroll = new JScrollPane(chatArea);
		this.chatScroll.setBorder(null);
		this.chatScroll.getVerticalScrollBar().setBackground(Background1);
		this.playersNames = new DefaultListModel();
		this.playersList = new JList(playersNames);
		this.playersList.setPreferredSize(new Dimension(200, 80));
		this.playersList.setBorder(null);
		this.playersList.setBackground(Background1);
		this.playersList.setForeground(Color.white);
		this.playersList.setSelectionForeground(Background1);
		this.playersList.setSelectionBackground(Color.white);

		this.add(chatScroll, BorderLayout.CENTER);
		this.add(playersList, BorderLayout.NORTH);
		this.add(txtSend, BorderLayout.SOUTH);

	}
  /**
   * 
   * @param Str Teksti qe shkruhet nga klientet
   */
	public void AppendToChat(String Str) {
		this.chatArea.append(Str + "\n");
		this.chatArea.setCaretPosition(chatArea.getText().length());
	}
/**
 * 
 * @param Name emri i lojtarit
 * @param indx numri i lojtarit
 */
	public void AddPlayer(String Name, int indx) {
		System.out.println("Adding Players : " + Name);

		this.playersNames.addElement(Name);
		this.repaint();
	}
/**
 * 
 * @param indx zgjedh lojtarin me kete index
 */
	public void SelectPlayer(int indx) {
		this.playersList.setSelectedIndex(indx);
		this.repaint();
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER) { 
			this.callBack.CallBack("C_CHT" + txtSend.getText(), this);//dergon tekstin duke shtypur tastin Enter 
			txtSend.setText("");

		}

	}

	public JList getPlayersList() {
		return playersList;
	}

	public void keyReleased(KeyEvent e) {
	};

	public void keyTyped(KeyEvent e) {
	};
}