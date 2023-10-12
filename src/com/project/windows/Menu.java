package com.project.windows;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import com.project.algorithms.SortingAlgorithms.AlgorithmName;

public class Menu implements ActionListener {

	private JFrame frame;
	private JButton btnBogoSort;
	private JButton btnBubbleSort;
	private JButton btnInsertionSort;
	private JButton btnSelectionSort;
	private JButton btnShellSort;
	private JButton btnHeapSort;
	private JButton btnMergeSort;
	private JButton btnQuickSort;
	private AlgorithmsProcess algorithmProcess;
	private System.Logger logger;
	
	public Menu() {
		initialize();	
		frame.setVisible(true);
		logger = System.getLogger(Menu.class.getName());
		logger.log(System.Logger.Level.INFO, "Main menu initialized");	
		algorithmProcess = null;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 474, 380);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sorting Algorithms");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		final String FONT = "Consolas";
		
		JLabel lblTittle = new JLabel("Sorting Algorithms");
		lblTittle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTittle.setFont(new Font(FONT, Font.BOLD | Font.ITALIC, 36));
		lblTittle.setBounds(10, 11, 440, 43);
		frame.getContentPane().add(lblTittle);
		
		btnBubbleSort = new JButton("BubbleSort");
		btnBubbleSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnBubbleSort.setBounds(10, 111, 217, 43);
		frame.getContentPane().add(btnBubbleSort);
		btnBubbleSort.addActionListener(this);
		
		btnInsertionSort = new JButton("InsertionSort");
		btnInsertionSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnInsertionSort.setBounds(233, 111, 217, 43);
		frame.getContentPane().add(btnInsertionSort);
		btnInsertionSort.addActionListener(this);
		
		btnSelectionSort = new JButton("SelectionSort");
		btnSelectionSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnSelectionSort.setBounds(10, 165, 217, 43);
		frame.getContentPane().add(btnSelectionSort);
		btnSelectionSort.addActionListener(this);
			
		btnShellSort = new JButton("ShellSort");
		btnShellSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnShellSort.setBounds(233, 165, 217, 43);
		frame.getContentPane().add(btnShellSort);
		btnShellSort.addActionListener(this);
		
		btnHeapSort = new JButton("HeapSort");
		btnHeapSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnHeapSort.setBounds(10, 219, 217, 43);
		frame.getContentPane().add(btnHeapSort);
		btnHeapSort.addActionListener(this);
		
		btnMergeSort = new JButton("MergeSort");
		btnMergeSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnMergeSort.setBounds(233, 219, 217, 43);
		frame.getContentPane().add(btnMergeSort);
		btnMergeSort.addActionListener(this);
		
		btnQuickSort = new JButton("QuickSort");
		btnQuickSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnQuickSort.setBounds(10, 273, 217, 43);
		frame.getContentPane().add(btnQuickSort);
		btnQuickSort.addActionListener(this);
		
		btnBogoSort = new JButton("BogoSort");
		btnBogoSort.setFont(new Font(FONT, Font.BOLD, 16));
		btnBogoSort.setBounds(233, 273, 217, 43);
		frame.getContentPane().add(btnBogoSort);
		btnBogoSort.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
		if (btnBubbleSort == e.getSource()) {
			algorithmProcess = new AlgorithmsProcess("BubbleSort", AlgorithmName.BUBBLE_SORT);
		} 
		
		else if (btnInsertionSort == e.getSource()) {
			algorithmProcess = new AlgorithmsProcess("InsertionSort", AlgorithmName.INSERTION_SORT);
		} 
		
		else if (btnSelectionSort == e.getSource()) {
			algorithmProcess = new AlgorithmsProcess("SelectionSort", AlgorithmName.SELECTION_SORT);
		} 
		
		else if (btnShellSort == e.getSource()) {
			algorithmProcess = new AlgorithmsProcess("ShellSort", AlgorithmName.SHELL_SORT);
		} 
		
		else if (btnHeapSort == e.getSource()) {
			algorithmProcess = new AlgorithmsProcess("HeapSort", AlgorithmName.HEAP_SORT);
		} 
		
		else if (btnMergeSort == e.getSource()) {
			algorithmProcess = new AlgorithmsProcess("MergeSort", AlgorithmName.MERGE_SORT);
		} 
		
		else if (btnQuickSort == e.getSource()) {
			algorithmProcess = new AlgorithmsProcess("QuickSort", AlgorithmName.QUICK_SORT);
		}
		else if (btnBogoSort == e.getSource()) {
			var bogoSortProcess = new BogoSortProcess();
		}
	}
}
