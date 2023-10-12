package com.project.windows;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.*;
import com.project.algorithms.SortingAlgorithms;
import com.project.algorithms.SortingAlgorithms.AlgorithmName;

public class AlgorithmsProcess implements ActionListener {

	private JFrame frame;
	private JTextField textArrayInput;
	private JButton btnSort;
	private JButton btnGenerateRandomNumbers;
	private JLabel lblSortedArray;
	private JLabel lblResultTime;
	private AlgorithmName typeAlgorithm;
	private System.Logger logger;
	private Random generateRandomNumbers;

	public AlgorithmsProcess(final String algorithmName, final AlgorithmName typeAlgorithm) {
		this.typeAlgorithm = typeAlgorithm;
		initialize(algorithmName);
		frame.setVisible(true);
		logger = System.getLogger(AlgorithmsProcess.class.getName());
		generateRandomNumbers = new Random();
		
		logger.log(System.Logger.Level.INFO, String.format("Chosen Algorithm: %s", algorithmName));
	}

	private void initialize(final String algorithmName) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 424);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sorting Algorithms");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		final String FONT = "Consolas";
		
		JLabel lblAlgorithmName = new JLabel(algorithmName);
		lblAlgorithmName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlgorithmName.setFont(new Font(FONT, Font.BOLD, 36));
		lblAlgorithmName.setBounds(10, 11, 414, 37);
		frame.getContentPane().add(lblAlgorithmName);
		
		textArrayInput = new JTextField();
		textArrayInput.setBounds(33, 59, 352, 100);
		frame.getContentPane().add(textArrayInput);
		textArrayInput.setColumns(10);
		textArrayInput.addActionListener(this);
		
		btnSort = new JButton("Sort");
		btnSort.setBounds(212, 170, 173, 37);
		frame.getContentPane().add(btnSort);
		btnSort.addActionListener(this);
		
		lblSortedArray = new JLabel("");
		lblSortedArray.setFont(new Font(FONT, Font.PLAIN, 12));
		lblSortedArray.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortedArray.setBounds(33, 223, 352, 100);
		frame.getContentPane().add(lblSortedArray);
		
		lblResultTime = new JLabel("");
		lblResultTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultTime.setFont(new Font(FONT, Font.PLAIN, 18));
		lblResultTime.setBounds(232, 334, 192, 40);
		frame.getContentPane().add(lblResultTime);
		
		var lblTime = new JLabel("Time (ms)");
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font(FONT, Font.PLAIN, 18));
		lblTime.setBounds(10, 334, 192, 40);
		frame.getContentPane().add(lblTime);
		
		btnGenerateRandomNumbers = new JButton("Generate Random Numbers");
		btnGenerateRandomNumbers.setBounds(33, 170, 169, 37);
		frame.getContentPane().add(btnGenerateRandomNumbers);
		btnGenerateRandomNumbers.addActionListener(this);
	}
	
	void executeAlgorithm(final AlgorithmName algorithmName) {
		
		var pattern = Pattern.compile("\\d+");
		var matcher = pattern.matcher(textArrayInput.getText());
		var array = new ArrayList<Integer>();
		var threadMXBean = ManagementFactory.getThreadMXBean();
		
		while (matcher.find()) {
			array.add(Integer.parseInt(matcher.group()));
		}

		logger.log(System.Logger.Level.INFO, String.format("Initial Array: %s", array));
		
		final var start = threadMXBean.getCurrentThreadCpuTime();
					
		switch (algorithmName) {
		case BUBBLE_SORT: {
			SortingAlgorithms.bubbleSort(array);
			break;
		}
		case INSERTION_SORT: {
			SortingAlgorithms.insertionSort(array);
			break;
		}
		case SELECTION_SORT: {
			SortingAlgorithms.selectionSort(array);
			break;
		}
		case SHELL_SORT: {
			SortingAlgorithms.shellSort(array);
			break;
		}
		case HEAP_SORT: {
			SortingAlgorithms.heapSort(array);
			break;
		}
		case MERGE_SORT: {
			SortingAlgorithms.mergeSort(array);
			break;
		}
		case QUICK_SORT: {
			SortingAlgorithms.quickSort(array);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + algorithmName);
		}
			
		logger.log(System.Logger.Level.INFO, String.format("Sorted Array: %s", array));
		
		final var end = threadMXBean.getCurrentThreadCpuTime();
		
		final var finalTime = (double) (end - start) / 1000000;
		
		lblSortedArray.setText(array.toString());
		lblResultTime.setText(Double.toString(finalTime));	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (btnSort == e.getSource()) {		
			executeAlgorithm(typeAlgorithm);
		} 
		
		else if (btnGenerateRandomNumbers == e.getSource()) {			
			
			var patternRangeNumbers = Pattern.compile("(-?\\d+),(-?\\d+)");
			var patternOnlyNumbers = Pattern.compile("\\d+");
			Matcher matchernOnlyNumbers;
			Matcher matcherRangeNumbers;		
			var randomArray = new ArrayList<Integer>();		
		    String sizeNumbersRandom;
		    String rangeGenerationRandomNumbers;
		
			do {
				sizeNumbersRandom = JOptionPane.showInputDialog("Size of random numbers: ");
				rangeGenerationRandomNumbers = JOptionPane.showInputDialog("Range of random numbers 10,40: ");
				matchernOnlyNumbers = patternOnlyNumbers.matcher(sizeNumbersRandom);
				matcherRangeNumbers = patternRangeNumbers.matcher(rangeGenerationRandomNumbers);
				
				if (!matchernOnlyNumbers.matches() || !matcherRangeNumbers.matches()) {
					JOptionPane.showMessageDialog(null, matcherRangeNumbers, "Error, enter values valid!", JOptionPane.ERROR_MESSAGE);
				}
				
			} while (!matchernOnlyNumbers.matches() || !matcherRangeNumbers.matches());
								
			final int N = Integer.parseInt(matchernOnlyNumbers.group(0));
			final int BEGIN = Integer.parseInt(matcherRangeNumbers.group(1));
			final int END = Integer.parseInt(matcherRangeNumbers.group(2));
			
			logger.log(System.Logger.Level.INFO, "Input Valid");
			
			for (int i = 0; i < N; i++) {
				randomArray.add(generateRandomNumbers.nextInt(BEGIN, END));
			}	
			
			textArrayInput.setText(randomArray.toString());		
		}
	}
}
