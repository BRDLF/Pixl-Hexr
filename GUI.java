import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class gui extends JPanel implements ActionListener {

    static String msg = new String("uintXX_t IMAGE[YY] =\n");
    static int numby = 0x44;
    static int gridrow = 8;
    static int gridcol = 8;
    JToggleButton c1[] = new JToggleButton[64*64];
    static String testString;
    JLabel botLabel = new JLabel();
    JPanel centralPanel = new JPanel();
    Dimension buttoDimension = new Dimension(14,14);
    static JFrame toolFrame;
    static JTextArea results;
    static JScrollPane rightPanel;

    public gui() {
        super (new GridBagLayout());

        JRadioButton RBW8 = new JRadioButton("8", true); 
        RBW8.setActionCommand("RBW8");
        JRadioButton RBW16 = new JRadioButton("16");
        RBW16.setActionCommand("RBW16");
        JRadioButton RBW32 = new JRadioButton("32");
        RBW32.setActionCommand("RBW32");
        JRadioButton RBW64 = new JRadioButton("64"); 
        RBW64.setActionCommand("RBW64");
        ButtonGroup BGCol = new ButtonGroup();  
        
        JRadioButton RBH8 = new JRadioButton(" 8", true);
        RBH8.setActionCommand("RBH8");
        JRadioButton RBH16 = new JRadioButton("16");
        RBH16.setActionCommand("RBH16");
        JRadioButton RBH32 = new JRadioButton("32");
        RBH32.setActionCommand("RBH32");
        JRadioButton RBH64 = new JRadioButton("64");
        RBH64.setActionCommand("RBH64"); 
        ButtonGroup BGRow = new ButtonGroup();

        BGCol.add(RBW8); BGCol.add(RBW16); BGCol.add(RBW32); BGCol.add(RBW64);
        BGRow.add(RBH8); BGRow.add(RBH16); BGRow.add(RBH32); BGRow.add(RBH64);

        RBW8.addActionListener(this);
        RBW16.addActionListener(this);
        RBW32.addActionListener(this);
        RBW64.addActionListener(this);
        RBH8.addActionListener(this);
        RBH16.addActionListener(this);
        RBH32.addActionListener(this);
        RBH64.addActionListener(this);

        JPanel topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints t = new GridBagConstraints();
        topPanel.add(new JLabel("Select Width:"), t);
        topPanel.add(RBW8, t); 
        topPanel.add(RBW16, t);
        topPanel.add(RBW32, t);
        topPanel.add(RBW64, t);
        topPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints l = new GridBagConstraints();
        l.gridx = 0; l.gridy = 0; l.anchor = GridBagConstraints.WEST;
        leftPanel.add(new JLabel("Set Height\n"), l); l.gridy = 1;
        leftPanel.add(RBH8, l); l.gridy = 2;
        leftPanel.add(RBH16, l); l.gridy=3;
        leftPanel.add(RBH32, l); l.gridy=4;
        leftPanel.add(RBH64, l);
        leftPanel.setBorder(topPanel.getBorder());
        
        centralPanel.setLayout(new GridLayout(gridrow, gridcol, 1, 1));
        centralPanel.setBorder(topPanel.getBorder());
        
        for(int n = 0; n < gridrow*gridcol; n++){
            c1[n] = new JToggleButton();
            c1[n].setPreferredSize(buttoDimension);
            centralPanel.add(c1[n]);
        }

        String resultsString = msg.replace("XX", Integer.toString(gridcol)).replace("YY", Integer.toString(gridrow)) + computeHex();
        results = new JTextArea(resultsString);
        
        rightPanel = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_NEVER , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        results.setWrapStyleWord(true);
        results.setLineWrap(true);
        results.setColumns(12);
        rightPanel.setBorder(topPanel.getBorder());
        rightPanel.setMinimumSize(rightPanel.getSize());

        JPanel botPanel = new JPanel(new GridBagLayout());
        JButton updateButton = new JButton("Update");
        updateButton.setActionCommand("Update");
        updateButton.addActionListener(this);
        updateButton.setSize(120, 24);
        GridBagConstraints b = new GridBagConstraints();
        
        b.anchor = GridBagConstraints.FIRST_LINE_END;
        b.gridx = 1;
        b.weightx = 1; b.weighty = 1;
        botPanel.add(updateButton, b);
        botPanel.setBorder(topPanel.getBorder());

        
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0; c.gridx = 1;
        c.gridwidth = 1;
        c.weightx = 0;
        super.add(topPanel, c);

        c.gridy = 1; c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0; c.weighty = 0;
        super.add(leftPanel, c);

        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.gridy = 1; c.gridx = 1;
        c.weightx = 0; c.weighty = 0;
        super.add(centralPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 0; c.gridx = 2;
        c.gridheight = 3;
        c.weightx = 1; c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        super.add(rightPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 2; c.gridx = 1;        
        c.gridwidth = 1; c.gridheight = 1;
        c.weightx = 0; c.weighty = 1;
        super.add(botPanel, c);

        
    }

    public void actionPerformed(ActionEvent e){
        botLabel.setText(e.getActionCommand());

        String Source = e.getActionCommand();

        switch (Source){

            case "RBW8": 
            gridcol = 8;
            break;
            case "RBW16": 
            gridcol = 16;
            break;
            case "RBW32": 
            gridcol = 32;
            break;
            case "RBW64": 
            gridcol = 64;
            break;
            case "RBH8": 
            gridrow = 8;
            break;
            case "RBH16": 
            gridrow = 16;
            break;
            case "RBH32": 
            gridrow = 32;
            break;
            case "RBH64": 
            gridrow = 64;
            break;
            case "Update":
            results.setText(msg.replace("XX", Integer.toString(gridcol)).replace("YY", Integer.toString(gridrow)) + computeHex());
            default:
            break;
        }
        centralPanel.removeAll();
        for(int n = 0; n < gridrow*gridcol; n++){
            c1[n] = new JToggleButton();
            c1[n].setPreferredSize(buttoDimension);
            centralPanel.add(c1[n]);
        }

        centralPanel.setLayout(new GridLayout(gridrow,gridcol, 1, 1));
        toolFrame.pack();
        // toolFrame.setMinimumSize(toolFrame.getSize());
    }

    private static void createAndShowGUI(){
        toolFrame = new JFrame("Pixl Hexr");
        toolFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toolFrame.setResizable(true);
        JComponent newContentPane = new gui();
        newContentPane.setOpaque(true);
        toolFrame.setContentPane(newContentPane);
        toolFrame.setLocationRelativeTo(null);
        toolFrame.pack();
        // rightPanel.setPreferredSize(rightPanel.getSize());
        toolFrame.setMinimumSize(toolFrame.getSize());
        toolFrame.setVisible(true);
    }

    protected String computeHex(){
        long compInt[] = new long[gridrow];
        int ch; int cw;
        for(int cn = 0; cn < gridcol*gridrow; cn++){
            ch = cn/gridcol;
            cw = cn%gridcol;
            if(c1[cn].isSelected()){
            compInt[ch] = compInt[ch] + (long)Math.pow(2, (gridcol - cw) - 1);
            // compInt[ch] += 1<<((gridcol-cw)-1);
            System.out.println(compInt[ch]);
            }
        }

        String compresults;
        StringBuilder sb = new StringBuilder(1200);
        String formatString = new String("%0" + Integer.toString(gridcol/4) + "x");
        for(int rs=0;rs < gridrow;rs++){
            sb.append("0x" + String.format(formatString ,compInt[rs]).toUpperCase() + "\n");
        }
        compresults = sb.toString();
        return compresults;
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}