package com.project.windows;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import com.project.algorithms.SortingAlgorithms;

public class BogoSortProcess implements ActionListener {

	private JFrame frame;
	private JTextField textArrayInput;
	private JButton btnTryToSort;
	private JLabel lblTime;
	private JLabel lblResultTime;
	private JLabel lblResult;
	private System.Logger logger;
	
	public BogoSortProcess() {
		initialize();
		frame.setVisible(true);
		logger = System.getLogger(BogoSortProcess.class.getName());
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 353);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sorting Algorithms");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		final String FONT = "Consolas";
		
		JLabel lblTittle = new JLabel("Bogo Sort");
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTittle.setFont(new Font(FONT, Font.BOLD, 36));
		lblTittle.setBounds(10, 11, 414, 55);
		frame.getContentPane().add(lblTittle);
		
		textArrayInput = new JTextField();
		textArrayInput.setHorizontalAlignment(SwingConstants.CENTER);
		textArrayInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textArrayInput.setBounds(10, 71, 414, 55);
		frame.getContentPane().add(textArrayInput);
		textArrayInput.setColumns(10);
		
		btnTryToSort = new JButton("Try To Sort");
		btnTryToSort.setBounds(147, 150, 143, 23);
		frame.getContentPane().add(btnTryToSort);
		btnTryToSort.addActionListener(this);
		
		lblResult = new JLabel("");
		lblResult.setFont(new Font(FONT, Font.PLAIN, 16));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(10, 190, 414, 55);
		frame.getContentPane().add(lblResult);
		
		lblTime = new JLabel("Time (seg)");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font(FONT, Font.PLAIN, 18));
		lblTime.setBounds(10, 263, 192, 40);
		frame.getContentPane().add(lblTime);
		
		lblResultTime = new JLabel("");
		lblResultTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultTime.setFont(new Font(FONT, Font.PLAIN, 18));
		lblResultTime.setBounds(232, 263, 192, 40);
		frame.getContentPane().add(lblResultTime);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (btnTryToSort == e.getSource()) {				
			var pattern = Pattern.compile("\\d+");
			var matcher = pattern.matcher(textArrayInput.getText());
			var array = new ArrayList<Integer>();
			var threadMXBean = ManagementFactory.getThreadMXBean();
			
			while (matcher.find()) {
				array.add(Integer.parseInt(matcher.group()));
			}
			
			final var start = threadMXBean.getCurrentThreadCpuTime();
			
			while(!SortingAlgorithms.isSorted(array)) {
				Collections.shuffle(array);
				logger.log(System.Logger.Level.DEBUG, array);			
			}		
			
			final var end = threadMXBean.getCurrentThreadCpuTime();		
			final var finalTime = (double) (end - start) / 1000000000;		
			lblResult.setText(array.toString());
			lblResultTime.setText(Double.toString(finalTime));	
		}	
	}
}
