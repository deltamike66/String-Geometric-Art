import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;

import java.awt.Font;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JSlider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

public class StringGeometricArt {

	private Color backgroundColor;
	private Art art;
	private JFrame frame;
	private JLabel labelColor;
	private JButton btnBackgroundColor;
	private JSpinner spinnerNails;
	private JLabel labelArt;
	private JLabel lblA;
	private JSlider sliderTickness;
	
	private int numPanels;
	private JPanel[] panelString;
	private JCheckBox[] en;
	private JSpinner[] A;
	private JSpinner[] B;
	private JSpinner[] C;
	private JSpinner[] D;
	private JSpinner[] rotate;
	private JSpinner[] repeat;
	private JLabel[] color;
	private JComboBox<String> comboBoxShape;
	private JLabel lblNails;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StringGeometricArt window = new StringGeometricArt();
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
	public StringGeometricArt() {
		backgroundColor = new Color(0,0,0);
		numPanels = 6;
		initialize();
		art = new Art(labelArt, Color.BLACK, Color.YELLOW, 180);
		labelArt.setIcon(new ImageIcon(art.drawNails()));
		
		sliderTickness.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateTickness();
			}
		});	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1310, 890);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label = new JLabel("");
		label.setBounds(12, 12, 442, 438);
		ImageIcon img = new ImageIcon(StringGeometricArt.class.getResource("/images/cerchio.png"));
		frame.getContentPane().setLayout(null);
		label.setIcon(img);
		frame.getContentPane().add(label);
		
		labelArt = new JLabel("");
		labelArt.setBounds(466, 12, 830, 830);
		labelArt.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(labelArt);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(12, 462, 442, 72);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(221, 22, 0, 0);
		panel.add(label_2);
		
		lblNails = new JLabel("Nails:");
		lblNails.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNails.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNails.setBounds(101, 18, 111, 15);
		panel.add(lblNails);
		
		spinnerNails = new JSpinner();
		spinnerNails.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				updateNails();
			}
		});
		spinnerNails.setModel(new SpinnerNumberModel(Integer.valueOf(180), Integer.valueOf(1), null, Integer.valueOf(1)));
		spinnerNails.setBounds(217, 15, 53, 20);
		panel.add(spinnerNails);
		
		btnBackgroundColor = new JButton("background");
		btnBackgroundColor.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnBackgroundColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeBackground();
			}
		});
		btnBackgroundColor.setBounds(277, 11, 117, 25);
		panel.add(btnBackgroundColor);
		
		labelColor = new JLabel("");
		labelColor.setBounds(401, 11, 29, 25);
		panel.add(labelColor);
		labelColor.setOpaque(true);
		labelColor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		labelColor.setBackground(backgroundColor);
		
		JLabel lblNewLabel = new JLabel("Thickness");
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblNewLabel.setBounds(12, 48, 70, 15);
		panel.add(lblNewLabel);
		
		sliderTickness = new JSlider(SwingConstants.HORIZONTAL, 0, 1000, 500);
		sliderTickness.setMinorTickSpacing(25);
		sliderTickness.setMinimum(1);
		sliderTickness.setMaximum(1000);
		sliderTickness.setBounds(80, 47, 200, 19);
		panel.add(sliderTickness);
		
		JButton btnSaveFile = new JButton("Save File");
		btnSaveFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnSaveFile.setBounds(313, 43, 117, 25);
		panel.add(btnSaveFile);
		
		comboBoxShape = new JComboBox<String>();
		comboBoxShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changeShape();
			}
		});
		comboBoxShape.setBounds(12, 13, 82, 24);
		panel.add(comboBoxShape);
		comboBoxShape.addItem("Circle");
		comboBoxShape.addItem("Square");
		
		lblA = new JLabel("   en        A          B           C          D       rotate         repeat       color");
		lblA.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblA.setBounds(12, 547, 431, 15);
		frame.getContentPane().add(lblA);
		
		panelString = new JPanel[numPanels];
		en = new JCheckBox[numPanels];
		A = new JSpinner[numPanels];
		B = new JSpinner[numPanels];
		C = new JSpinner[numPanels];
		D = new JSpinner[numPanels];
		rotate = new JSpinner[numPanels];
		repeat = new JSpinner[numPanels];
		color = new JLabel[numPanels];
		
		int startY = 570;
		for (int i=0; i<numPanels; i++) {
			panelString[i] = new JPanel();
			panelString[i].setOpaque(false);
			panelString[i].setBounds(12, startY+i*45, 442, 43);
			panelString[i].setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
			frame.getContentPane().add(panelString[i]);
			panelString[i].setLayout(null);
			
			en[i] = new JCheckBox("");
			en[i].setFont(new Font("Dialog", Font.PLAIN, 12));
			en[i].setBounds(12, 10, 21, 23);
			panelString[i].add(en[i]);
			
			A[i] = new JSpinner();
			A[i].setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
			A[i].setBounds(40, 12, 47, 20);
			panelString[i].add(A[i]);
			
			B[i] = new JSpinner();
			B[i].setModel(new SpinnerNumberModel(Integer.valueOf(90), Integer.valueOf(0), null, Integer.valueOf(1)));
			B[i].setBounds(90, 12, 47, 20);
			panelString[i].add(B[i]);
			
			C[i] = new JSpinner();
			C[i].setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(0), null, Integer.valueOf(1)));
			C[i].setBounds(140, 12, 47, 20);
			panelString[i].add(C[i]);
			
			D[i] = new JSpinner();
			D[i].setModel(new SpinnerNumberModel(Integer.valueOf(91), Integer.valueOf(0), null, Integer.valueOf(1)));
			D[i].setBounds(190, 12, 47, 20);
			panelString[i].add(D[i]);
			
			rotate[i] = new JSpinner();
			rotate[i].setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
			rotate[i].setBounds(240, 12, 47, 20);
			panelString[i].add(rotate[i]);
			
			repeat[i] = new JSpinner();
			repeat[i].setModel(new SpinnerNumberModel(Integer.valueOf(90), Integer.valueOf(1), null, Integer.valueOf(1)));
			repeat[i].setBounds(318, 12, 47, 20);
			panelString[i].add(repeat[i]);
			
			color[i] = new JLabel("");
			color[i].setBounds(383, 12, 47, 20);
			color[i].setOpaque(true);
			color[i].setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			color[i].setBackground(Color.WHITE);
			panelString[i].add(color[i]);
			
			en[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					updateArt();
				}
			});
			
			A[i].addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateArt();
				}
			});
			B[i].addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateArt();
				}
			});
			C[i].addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateArt();
				}
			});
			D[i].addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateArt();
				}
			});
			rotate[i].addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateArt();
				}
			});
			repeat[i].addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					updateArt();
				}
			});
			color[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changeStringColor(e);
				}
			});
		}
	}
	
	private void save() {
		System.out.println(Arrays.deepToString(art.getSet()));
	}
	
	private void changeStringColor(MouseEvent e) {
		Color c = JColorChooser.showDialog(null, "Choose String Color", Color.WHITE);
		if (c != null) {
			JLabel l = (JLabel)e.getSource();
			l.setBackground(c);
			updateArt();
		}
	}
	
	private void updateArt() {
		labelArt.setIcon(new ImageIcon(art.delete()));
		labelArt.setIcon(new ImageIcon(art.drawNails()));
		for (int i=0;i<numPanels;i++) {
			if (en[i].isSelected()) {
				int rot = (int)rotate[i].getValue();
				int a = (int)A[i].getValue() + rot;
				int b = (int)B[i].getValue() + rot;
				int c = (int)C[i].getValue() + rot;
				int d = (int)D[i].getValue() + rot;
				int r = (int)repeat[i].getValue();
				Color col = color[i].getBackground();
				labelArt.setIcon(new ImageIcon(art.addString(i,a,b,c,d,r,col)));
			} 
		}
	}
	
	private void updateTickness() {
		float t = sliderTickness.getValue()/500f;
		art.setTickness(t);
		updateArt();
	}
	
	private void changeBackground() {
		backgroundColor = JColorChooser.showDialog(null, "Choose Background Color", Color.BLACK);
		if (backgroundColor != null) {
			labelColor.setBackground(backgroundColor);
			art.setBackground(backgroundColor);
			labelArt.setIcon(new ImageIcon(art.delete()));
			updateArt();
		}
	}
	
	private void updateNails() {
		int nails = (int)spinnerNails.getValue();
		art.setNails(nails);
		updateArt();
	}
	
	private void changeShape() {
		switch (comboBoxShape.getSelectedIndex()) {
		case 0: // Circle
			lblNails.setText("Nails");
			spinnerNails.setValue(180);
			label.setIcon(new ImageIcon(StringGeometricArt.class.getResource("/images/cerchio.png")));
			break;
		case 1: // Square
			lblNails.setText("Nails on side");
			spinnerNails.setValue(40);
			label.setIcon(new ImageIcon(StringGeometricArt.class.getResource("/images/quadrato.png")));
			break;
		default:
			break;
		}
	}
}
