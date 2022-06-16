import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class gui extends JPanel implements ActionListener {

    static final Dimension buttonDimension = new Dimension(14,14);
    static String msg = "uIntXX_t IMAGE[YY] = {\n";
    static int gridRow = 8;
    static int gridCol = 8;
    JToggleButton[] jToggleButtons = new JToggleButton[64*64];
    JLabel botLabel = new JLabel();
    JPanel centralPanel = new JPanel();
    static JFrame toolFrame;
    static JTextArea results;
    static JScrollPane rightPanel;
    // static boolean gridBool[] = new boolean[64*64];

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
        
        centralPanel.setLayout(new GridLayout(gridRow, gridCol, 1, 1));
        centralPanel.setBorder(topPanel.getBorder());
        
        makeGrid();

        String resultsString = msg.replace("XX", Integer.toString(gridCol)).replace("YY", Integer.toString(gridRow)) + computeHex();
        results = new JTextArea(resultsString);
        
        rightPanel = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_NEVER , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        results.setWrapStyleWord(true);
        results.setLineWrap(true);
        results.setColumns(12);
        rightPanel.setBorder(topPanel.getBorder());
        rightPanel.setMinimumSize(rightPanel.getSize());

        JPanel botPanel = new JPanel(new GridBagLayout());
        JButton updateButton = new JButton("Compute");
        JButton clearButton = new JButton("Clear");
        updateButton.setActionCommand("Update");
        clearButton.setActionCommand("Clear");
        updateButton.addActionListener(this);
        clearButton.addActionListener(this);
        GridBagConstraints b = new GridBagConstraints();

        b.weightx = 1; b.weighty = 1;
        b.anchor = GridBagConstraints.FIRST_LINE_START;
        botPanel.add(clearButton, b);
        b.anchor = GridBagConstraints.FIRST_LINE_END;
        b.gridx = 1;
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

        switch (Source) {
            case "RBW8" -> resizeGrid(8, gridRow);
            case "RBW16" -> resizeGrid(16, gridRow);
            case "RBW32" -> resizeGrid(32, gridRow);
            case "RBW64" -> resizeGrid(64, gridRow);
            case "RBH8" -> resizeGrid(gridCol, 8);
            case "RBH16" -> resizeGrid(gridCol, 16);
            case "RBH32" -> resizeGrid(gridCol, 32);
            case "RBH64" -> resizeGrid(gridCol, 64);
            case "Update" -> updateGrid();
            case "Clear" -> clearGrid();
            default -> {
            }
        }

        centralPanel.setLayout(new GridLayout(gridRow, gridCol, 1, 1));
        toolFrame.pack();
        // toolFrame.setMinimumSize(toolFrame.getSize());
    }

    private void updateGrid(){
        results.setText(msg.replace("XX", Integer.toString(gridCol))
                .replace("YY", Integer.toString(gridRow))
                + computeHex());
    }

    private void resizeGrid(int newWidth, int newHeight){
        boolean[] gridBool = new boolean[64*64];
        int tempWidth; int tempHeight;
        int properIndex;
        for (int n = 0; n < gridCol * gridRow; n++){
            if (jToggleButtons[n].isSelected()){
                tempWidth = n% gridCol;
                tempHeight = n/ gridCol;
                properIndex = (tempHeight*newWidth) + tempWidth;
                // properIndex = (((n/gridCol)*newWidth) + (n%gridCol));
                if (tempWidth >= newWidth) {
                    continue;
                }
                gridBool[properIndex] = true;
            }
        }

        gridCol = newWidth;
        gridRow = newHeight;
        centralPanel.removeAll();
        for(int n = 0; n < newHeight*newWidth; n++){
            jToggleButtons[n] = new JToggleButton();
            if (gridBool[n]){
                jToggleButtons[n].setSelected(true);
            }
            jToggleButtons[n].setPreferredSize(buttonDimension);
            centralPanel.add(jToggleButtons[n]);
        }
    }

    private void clearGrid(){
        for(int n = 0; n < gridCol * gridRow; n++){
            jToggleButtons[n].setSelected(false);
        }
        updateGrid();
    }
    
    private void makeGrid(){
        centralPanel.removeAll();
        for(int n = 0; n < gridCol * gridRow; n++){
            jToggleButtons[n] = new JToggleButton();
            jToggleButtons[n].setPreferredSize(buttonDimension);
            centralPanel.add(jToggleButtons[n]);
        }
    }

    private static void createAndShowGUI(){
        toolFrame = new JFrame("Pixl-Hexr");
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

    private String computeHex(){
        long[] compInt = new long[gridRow];
        int currentRow, currentCol, columnIndex;
        long toAdd;
        for(int currentNumber = 0; currentNumber < gridCol * gridRow; currentNumber++){
            currentRow = currentNumber/ gridCol;
            currentCol = currentNumber% gridCol;
            columnIndex = gridCol -(currentCol+1);

            if(jToggleButtons[currentNumber].isSelected()){
            toAdd = ((long) Math.pow(2,(columnIndex%4)) ) << (4*(columnIndex/4));
            compInt[currentRow] = compInt[currentRow] + toAdd;
            }
        }

        String compResults;
        StringBuilder sb = new StringBuilder(1200);
        String formatString = "%0" + gridCol /4 + "x";
        for(int n = 0; n < gridRow; n++){
            sb.append("0x").append(String.format(formatString, compInt[n]).toUpperCase()).append(",\n");
        }
        sb.append("};");
        compResults = sb.toString();
        return compResults;
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (gui::createAndShowGUI
        );
    }
}