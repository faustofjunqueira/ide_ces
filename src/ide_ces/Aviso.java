package ide_ces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Aviso extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	String aviso;
	JButton btnOk;

	public Aviso(String aviso) {
		this.aviso = aviso;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 340, 177);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(108, 111, 117, 25);
		contentPane.add(btnOk);
		btnOk.addActionListener(new Ok(this));
		btnOk.setMnemonic(KeyEvent.VK_ENTER);
		
		JLabel label = new JLabel(this.aviso);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 12, 314, 87);
		contentPane.add(label);
	}
}

class Ok implements ActionListener{
	
	Aviso a;
	
	public Ok(Aviso a) {
		this.a = a;
	}
	
	public void actionPerformed(ActionEvent e) {
		a.setVisible(false);
	}
}
