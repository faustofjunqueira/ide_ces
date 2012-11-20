package ide_ces;

import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/*
 * Fazer File Filter no save e no Load
 */

public class prime_frame extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	File file;
	TextArea text_center;
	TextArea console;
	JTextField txt_passos;
	JTextField txt_org;
	JButton btn_sair;
	JTextField terminal;
	TextField regP, regT, regC;
	static String title = "CES - Computador Extremamente Simples - v2.0"; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prime_frame frame = new prime_frame();
					frame.setVisible(true);
				} catch (Exception e) {
					Aviso a = new Aviso(e.getLocalizedMessage());
					System.out.println(e.getLocalizedMessage());
					a.setVisible(true);
				}
			}
		});
	}

	public prime_frame() {
		file = null;
		setTitle(prime_frame.title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btn_novo = new JButton("");
		btn_novo.setToolTipText("Novo");
		btn_novo.setIcon(new ImageIcon("./icon/newp.png"));
		btn_novo.setBounds(655, 0, 50, 34);
		contentPane.add(btn_novo);
		btn_novo.addActionListener(new Novo(this));
		btn_novo.setMnemonic(KeyEvent.VK_N);
		
		JButton btn_abrir = new JButton("");
		btn_abrir.setToolTipText("Abrir");
		btn_abrir.setIcon(new ImageIcon("./icon/openp.png"));
		btn_abrir.setBounds(717, 0, 50, 34);
		contentPane.add(btn_abrir);
		btn_abrir.addActionListener(new Abrir(this));
		btn_abrir.setMnemonic(KeyEvent.VK_A);
		
		JButton btn_salvar = new JButton("");
		btn_salvar.setToolTipText("Salvar");
		btn_salvar.setIcon(new ImageIcon("./icon/savep.png"));
		btn_salvar.setBounds(779, 0, 50, 34);
		contentPane.add(btn_salvar);
		btn_salvar.addActionListener(new Salvar(this));
		btn_salvar.setMnemonic(KeyEvent.VK_S);
		
		JButton btn_exec = new JButton("");
		btn_exec.setIcon(new ImageIcon("./icon/runp.png"));
		btn_exec.setToolTipText("Executar");
		btn_exec.setBounds(822, 95, 50, 34);
		contentPane.add(btn_exec);
		btn_exec.addActionListener(new Executar(this));
		btn_exec.setMnemonic(KeyEvent.VK_E);
		
		this.text_center = new TextArea();
		this.text_center.setBounds(12, 21, 576, 681);
		contentPane.add(this.text_center);
		
		Label l_console = new Label("Console:");
		l_console.setBounds(594, 176, 68, 21);
		contentPane.add(l_console);
		
		this.console= new TextArea();
		console.setForeground(Color.WHITE);
		console.setBackground(Color.BLACK);
		console.setEditable(false);
		this.console.setBounds(594, 203, 294, 281);
		contentPane.add(this.console);
		
		Label label_1 = new Label("Saida:");
		label_1.setBounds(636, 532, 68, 21);
		contentPane.add(label_1);
		
		Label label_2 = new Label("Registador P:");
		label_2.setBounds(636, 559, 99, 21);
		contentPane.add(label_2);
		
		Label label_3 = new Label("Registador T:");
		label_3.setBounds(636, 585, 99, 21);
		contentPane.add(label_3);
		
		Label label_4 = new Label("Registador C:");
		label_4.setBounds(636, 612, 99, 21);
		contentPane.add(label_4);
		
		regP = new TextField();
		regP.setEditable(false);
		regP.setBounds(741, 559, 99, 21);
		contentPane.add(regP);
		
		regT = new TextField();
		regT.setEditable(false);
		regT.setBounds(741, 585, 99, 21);
		contentPane.add(regT);
		
		regC = new TextField();
		regC.setEditable(false);
		regC.setBounds(741, 612, 99, 21);
		contentPane.add(regC);
		
		JLabel lblPassos = new JLabel("Passos:");
		lblPassos.setBounds(596, 97, 62, 15);
		contentPane.add(lblPassos);
		
		this.txt_passos = new JTextField();
		this.txt_passos.setBounds(659, 95, 62, 19);
		contentPane.add(this.txt_passos);
		this.txt_passos.setColumns(10);
		
		JLabel lblOrg = new JLabel("Org:");
		lblOrg.setBounds(596, 116, 62, 15);
		contentPane.add(lblOrg);
		
		this.txt_org = new JTextField();
		this.txt_org.setColumns(10);
		this.txt_org.setBounds(659, 114, 62, 19);
		contentPane.add(this.txt_org);
		
		JLabel lblEmHexadecimal = new JLabel("em hexadecimal");
		lblEmHexadecimal.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblEmHexadecimal.setBounds(728, 116, 112, 15);
		contentPane.add(lblEmHexadecimal);
		
		btn_sair = new JButton("");
		btn_sair.setIcon(new ImageIcon("/home/fausto/documentos/prog/java/ide_ces/icon/exitp.jpg"));
		btn_sair.setToolTipText("Sair");
		btn_sair.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_sair.setBounds(838, 0, 50, 34);
		contentPane.add(btn_sair);
		btn_sair.addActionListener(new Sair(this));
		btn_sair.setMnemonic(KeyEvent.VK_F4);
		
		terminal = new JTextField();
		terminal.setBounds(596, 488, 292, 19);
		contentPane.add(terminal);
		terminal.setColumns(10);
		
		terminal.addKeyListener(new KeyAdapter() {public void keyPressed(java.awt.event.KeyEvent e) {  
				      if(e.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {  
				        printConsole(terminal.getText());
				        try {
							if(terminal.getText().startsWith("save")){
								String path = terminal.getText().substring(5);					        	 
								CesFile.salvar(path, console.getText(), false);								
							}else if (terminal.getText().startsWith("!:")) {
								Runtime.getRuntime().exec(terminal.getText().substring(2));
								printConsole("Executado no terminal!");
							}else if(terminal.getText().startsWith("exit")){
								printConsole("Programa encerrado");
								setVisible(false);
								Runtime.getRuntime().exit(0);
							}else{
								printConsole("Use:\n   save <path> :para salvar o console\n   !:<comando> : para comando no terminal\n   exit :encerra o programa\n");
							}
				        } catch (Exception e1) {							
								printConsole(e1.getLocalizedMessage());
						}
				         terminal.setText("");
				      }
				   };
				});
		this.printConsole("------------------------------------------------------------------\nComputador Extremamente Simples v2.0\nNelson Quilula Vasconcelos\n------------------------------------------------------------------\n");
	}
	
	void printConsole(String text){
		this.console.setText(this.console.getText()+"\n"+text+"\n");
		System.out.println(text);
	}
}

class Novo implements ActionListener{
	
	prime_frame f;
	
	public Novo(prime_frame f) {
		this.f = f;
	}

	public void actionPerformed(ActionEvent arg0) {
		this.novo();
	}
	
	public void novo(){
		if(f.file != null){
			f.file = null;
		}
		f.text_center.setText("");
		this.f.printConsole("Novo Arquivo Criado\n");
		this.f.setTitle(prime_frame.title);
	}
}

class Abrir implements ActionListener{
	
	prime_frame f;
	
	public Abrir(prime_frame f) {
		this.f = f;
	}
		
	public void actionPerformed(ActionEvent e) {
		try {
			this.abrir();
		} catch (Exception e1) {
			Aviso a = new Aviso(e1.getLocalizedMessage());
			System.out.println(e1.getLocalizedMessage());
			a.setVisible(true);
		}
	}
	public void abrir() throws Exception{
		JFileChooser c	= new JFileChooser();
		if(c.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			this.f.file = c.getSelectedFile();
			this.f.printConsole("Abriu arquivo: " +c.getSelectedFile().getPath());
			FileReader r = new FileReader(c.getSelectedFile());
			BufferedReader b = new BufferedReader(r);
			String text = b.readLine();
			String text_result = text+"\n";
			while((text = b.readLine()) != null) text_result += text+"\n";
			this.f.text_center.setText(text_result);
			this.f.setTitle(prime_frame.title + " - " + this.f.file.getName());
			b.close();
			r.close();			

		}
	}
	
}

class Salvar implements ActionListener{
	
	prime_frame f;
	
	public Salvar(prime_frame f) {
		this.f = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.salvar();
		} catch (Exception e1) {
			Aviso a = new Aviso(e1.getLocalizedMessage());
			System.out.println(e1.getLocalizedMessage());
			a.setVisible(true);
		}
	}
	public boolean salvar() throws Exception{
		if (this.f.file == null){
			JFileChooser c = new JFileChooser();
			if(c.showSaveDialog(f) == JFileChooser.APPROVE_OPTION){
				this.f.file = c.getSelectedFile();
				this.f.setTitle(prime_frame.title + " - " + this.f.file.getName());
				this.f.printConsole("Arquivo Salvo:"+this.f.file.getPath());
				CesFile.salvar(this.f.file.getPath(), this.f.text_center.getText(), false);
				return true;
			}
		}else{
			this.f.printConsole("Arquivo Salvo:"+this.f.file.getPath());
			CesFile.salvar(this.f.file.getPath(), this.f.text_center.getText(), false);
			return true;
		}
		return false;
	}
	
}

class Executar implements ActionListener{
	
	prime_frame f;
	
	public Executar(prime_frame f) {
		this.f = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		try {
			this.exec();
		} catch (Exception e1) {
			Aviso a = new Aviso(e1.getLocalizedMessage());
			System.out.println(e1.getLocalizedMessage());
			a.setVisible(true);
		}
	}
	
	public void exec() throws Exception{		
		if(this.f.txt_org.getText().equals("") || this.f.txt_passos.getText().equals("")){
			throw new Exception("Parametros de execução vazios");
		}else {
			Salvar s = new Salvar(this.f);
			if(s.salvar()){
				this.f.printConsole("Executado arquivo: "+this.f.file.getPath()+" Org: "+this.f.txt_org.getText()+" Passos: "+this.f.txt_passos.getText());			
				String command = "./ces/ces -f "+this.f.file.getPath()+" --org "+this.f.txt_org.getText()+" -i "+this.f.txt_passos.getText()+" -o";
				System.out.println(command);
				Process line_comand = Runtime.getRuntime().exec(command);
				line_comand.waitFor();				
				this.f.regP.setText(CesFile.LeOutputCes("output.ces").substring(0, 4));
				this.f.regT.setText(CesFile.LeOutputCes("output.ces").substring(4, 8));
				this.f.regC.setText(CesFile.LeOutputCes("output.ces").substring(8, 9));
				this.f.printConsole(CesFile.LeOutputCes("output.ces").substring(9));
			}
		}
		
	}
	
}

class Sair implements ActionListener{
	prime_frame f;
	public Sair(prime_frame f) {
		this.f = f;
	}
	public void actionPerformed(ActionEvent e) {
		this.sair();
	}
	public void sair(){
		this.f.printConsole("Programa encerrado");
		this.f.setVisible(false);
		Runtime.getRuntime().exit(0);
	}	
}
