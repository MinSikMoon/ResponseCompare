package tutorial;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import innerClasses.PropertyFactory;

public class WbTutorial {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private String configPath = "config/response_url.cfg";
	private JTextPane textPane_1;
	private JTextArea tarea;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WbTutorial window = new WbTutorial();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WbTutorial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. config파일 읽어오는 것 추가. Initialize the
	 * contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 0, 1300, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		// 1. config 읽어오기
		PropertyFactory pf = new PropertyFactory(configPath);
		////////////////////////////////////
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 6));
		panel.setBounds(12, 67, 600, 682);
		panel.setVisible(true);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(32, 34, 543, 627);
		panel.add(scrollPane);

		tarea = new JTextArea();
		tarea.setWrapStyleWord(true);
		tarea.setLineWrap(true);
		tarea.setBounds(66, 10, 129, 113);
		scrollPane.add(tarea);
		tarea.setText("asdfsdf");
		scrollPane.setViewportView(tarea);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 6));
		panel_1.setBounds(672, 67, 600, 682);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		textPane_1 = new JTextPane();
		textPane_1.setBounds(12, 11, 576, 661);
		panel_1.add(textPane_1);

		JLabel lblReponse = new JLabel("Reponse1");
		lblReponse.setFont(new Font("Arial", Font.BOLD, 18));
		lblReponse.setBounds(58, 37, 96, 20);
		frame.getContentPane().add(lblReponse);

		JLabel lblReponse_1 = new JLabel("Reponse2");
		lblReponse_1.setFont(new Font("Arial", Font.BOLD, 18));
		lblReponse_1.setBounds(697, 37, 96, 20);
		frame.getContentPane().add(lblReponse_1);

		textField = new JTextField(pf.getValue("url1"));
		textField.setBounds(166, 36, 403, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField(pf.getValue("url2"));
		textField_1.setColumns(10);
		textField_1.setBounds(794, 38, 403, 21);
		frame.getContentPane().add(textField_1);

		JButton btnNewButton = new JButton("Shoot!");
		btnNewButton.setFont(new Font("Consolas", Font.BOLD, 28));

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PropertyFactory pf = new PropertyFactory(configPath);
				Document doc1, doc2;
				try {
					doc1 = Jsoup.connect(pf.getValue("url1")).timeout(4 * 1000)
							.userAgent(
									"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
							.get();
					doc2 = Jsoup.connect(pf.getValue("url2")).timeout(4 * 1000)
							.userAgent(
									"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
							.get();
					textPane_1.setText(doc2.html());
					tarea.setText(doc1.html());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
				
				}
				
			}
		});
		btnNewButton.setBounds(567, 771, 154, 58);
		frame.getContentPane().add(btnNewButton);

		JButton btnEditRequest = new JButton("Edit Request");
		btnEditRequest.setBackground(Color.ORANGE);
		btnEditRequest.setForeground(UIManager.getColor("CheckBox.shadow"));
		btnEditRequest.setBounds(794, 791, 130, 23);
		frame.getContentPane().add(btnEditRequest);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenuItem mntmFile = new JMenuItem("FILE");
		menuBar.add(mntmFile);

		JMenuItem mntmView = new JMenuItem("VIEW");
		menuBar.add(mntmView);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
