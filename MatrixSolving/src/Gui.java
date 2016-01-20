import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Gui {

	private JFrame frmMatrixsolver;
	private JTable table;
	private JTable tableSolved;
	private JComboBox tableSizeSelection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmMatrixsolver.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMatrixsolver = new JFrame();
		frmMatrixsolver.setTitle("MatrixSolver");
		frmMatrixsolver.setIconImage(Toolkit.getDefaultToolkit().getImage(Gui.class.getResource("/sun/print/resources/orientRevPortrait.png")));
		frmMatrixsolver.setBounds(100, 100, 450, 300);
		frmMatrixsolver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tableSizeSelection = new JComboBox();
		tableSizeSelection.setModel(new DefaultComboBoxModel(new String[] {"3 x 4", "4 x 5", "5 x 6", "6 x 7"}));
		tableSizeSelection.setToolTipText("Select size of Table");
		frmMatrixsolver.getContentPane().add(tableSizeSelection, BorderLayout.NORTH);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"0", "1", "2", "3", "4", "5", "6"
			}
		) {
			Class[] columnTypes = new Class[] {
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		frmMatrixsolver.getContentPane().add(table, BorderLayout.CENTER);
		
		tableSolved = new JTable();
		tableSolved.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"0", "1", "2", "3", "4", "5", "6"
			}
		) {
			Class[] columnTypes = new Class[] {
				Double.class, Double.class, Double.class, Double.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableSolved.getColumnModel().getColumn(0).setResizable(false);
		tableSolved.getColumnModel().getColumn(1).setResizable(false);
		tableSolved.getColumnModel().getColumn(2).setResizable(false);
		tableSolved.getColumnModel().getColumn(3).setResizable(false);
		tableSolved.getColumnModel().getColumn(4).setResizable(false);
		tableSolved.getColumnModel().getColumn(5).setResizable(false);
		tableSolved.getColumnModel().getColumn(6).setResizable(false);
		tableSolved.setBorder(new BevelBorder(BevelBorder.RAISED, Color.GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.LIGHT_GRAY));
		frmMatrixsolver.getContentPane().add(tableSolved, BorderLayout.SOUTH);
		
		JButton btnSolve = new JButton("Solve");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					double[][] matrix = parseTableToMatrix(table);
					
					GaussJordan GJ = new GaussJordan();
					matrix = GJ.reduce(matrix);
					
					writeMatrixToTable(matrix, tableSolved);

				} catch (Exception e) {
					JOptionPane.showMessageDialog(frmMatrixsolver, e.getLocalizedMessage());
				}
			}
		});
		frmMatrixsolver.getContentPane().add(btnSolve, BorderLayout.EAST);
	}
	
	private double[][] parseTableToMatrix(JTable t) throws Exception {
		
		int matrixSize = getMatrixSize();
		if (matrixSize==0) {
			throw new Exception("MatrixSize is zero, unable to solve matrix.");
		}
		double[][] matrix = new double[matrixSize][matrixSize+1];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				Object o = table.getModel().getValueAt(i, j);
				matrix[i][j] = Double.parseDouble(o.toString());
			}
		}
		
		return matrix;
	}
	
	private void writeMatrixToTable(double[][] matrix, JTable table) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				table.getModel().setValueAt(matrix[i][j], i, j);
			}
		}
	}
	
	private int getMatrixSize() {
		try {
		Scanner sc = new Scanner(tableSizeSelection.getSelectedItem().toString()).useDelimiter(" ");
		
		int size = Integer.parseInt(sc.next());
		
		sc.close();

		System.out.println(size);
		
		return size;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmMatrixsolver, "Unable to determine table size!");
			return 0;
		}
	}

}
