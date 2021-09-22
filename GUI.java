import javax.swing.*;

import java.awt.*;

class gui{

    static String msg = new String("uintXX_t[YY] IMAGE =\n");
    static int numby = 0x44;
    static int gridrow = 8;
    static int gridcol = 32;
    
    public static void makeme(){

        // String resultsString = "uint32_t = 0x" + numby;

        JRadioButton RBW8 = new JRadioButton("8"); 
        JRadioButton RBW16 = new JRadioButton("16");
        JRadioButton RBW32 = new JRadioButton("32", true);
        JRadioButton RBW64 = new JRadioButton("64"); 
        ButtonGroup BGCol = new ButtonGroup();
        BGCol.add(RBW8); BGCol.add(RBW16); BGCol.add(RBW32); BGCol.add(RBW64);

        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.add(new JLabel("Select Width:"));
        topPanel.add(RBW8); 
        topPanel.add(RBW16);
        topPanel.add(RBW32);
        topPanel.add(RBW64);
        topPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));


        JRadioButton RBH8 = new JRadioButton(" 8", true);
        JRadioButton RBH16 = new JRadioButton("16");
        JRadioButton RBH32 = new JRadioButton("32");
        JRadioButton RBH64 = new JRadioButton("64"); 
        ButtonGroup BGRow = new ButtonGroup();
        BGRow.add(RBH8); BGRow.add(RBH16); BGRow.add(RBH32); BGRow.add(RBH64);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints l = new GridBagConstraints();
        l.gridx = 0; l.gridy = 0; l.anchor = GridBagConstraints.WEST;
        leftPanel.add(new JLabel("Set Height\n"), l); l.gridy = 1;
        leftPanel.add(RBH8, l); l.gridy = 2;
        leftPanel.add(RBH16, l); l.gridy=3;
        leftPanel.add(RBH32, l); l.gridy=4;
        leftPanel.add(RBH64, l);
        leftPanel.setBorder(topPanel.getBorder());


        JPanel centralPanel = new JPanel();
        Dimension buttoDimension = new Dimension(14,14);
        GridLayout viewGridLayout = new GridLayout(gridrow, gridcol);
        centralPanel.setLayout(viewGridLayout);
        viewGridLayout.setHgap(1); viewGridLayout.setVgap(1);
        centralPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        
        JToggleButton c1[] = new JToggleButton[gridrow*gridcol];
        for(int n = 0; n < gridrow*gridcol; n++){
            c1[n] = new JToggleButton();
            c1[n].setPreferredSize(buttoDimension);
            centralPanel.add(c1[n]);
        }

        String resultsString = msg.replace("XX", Integer.toString(gridcol)).replace("YY", Integer.toString(gridrow));
        JTextArea results = new JTextArea(resultsString);
        JScrollPane rightPanel = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_NEVER , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        results.setWrapStyleWord(true);
        results.setLineWrap(true);
        results.setColumns(12);
        rightPanel.setBorder(topPanel.getBorder());


        JPanel botPanel = new JPanel(new GridBagLayout());
        JButton updateButton = new JButton("Update");
        updateButton.setSize(120, 24);
        GridBagConstraints b = new GridBagConstraints();
        
        b.weightx = 1; b.weighty = 1;
        botPanel.add(new JLabel(" "));
        b.gridx = 1;
        b.anchor = GridBagConstraints.FIRST_LINE_END;
        botPanel.add(updateButton, b);
        b.gridx = 1; b.gridy = 1;
        botPanel.add(new JLabel(" "), b);
        botPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JPanel allPanel = new JPanel();
        allPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0; c.gridx = 1;
        c.gridwidth = 1;
        c.weightx = 0;
        allPanel.add(topPanel, c);

        c.gridy = 1; c.gridx = 0;
        c.gridwidth = 1;
        c.weightx = 0; c.weighty = 0;
        allPanel.add(leftPanel, c);

        c.fill = GridBagConstraints.NONE;
        c.gridy = 1; c.gridx = 1;
        c.weightx = 0; c.weighty = 0;
        // c.insets = new Insets(2, 2, 2, 2);
        // c.anchor = GridBagConstraints.CENTER;
        allPanel.add(centralPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0; c.gridx = 2;
        c.gridheight = 3;
        c.weightx = 1; c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 0);
        allPanel.add(rightPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridy = 2; c.gridx = 1;        
        c.gridwidth = 1; c.gridheight = 1;
        c.weightx = 0; c.weighty = 1;
        allPanel.add(botPanel, c);
        
        JFrame testFrame = new JFrame("Pixl Hexr");
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        testFrame.setContentPane(allPanel);
        testFrame.setResizable(true);

        // testFrame.setMinimumSize(new DimensionUIResource(640,480));        
        testFrame.setLocationRelativeTo(null);
        testFrame.pack();
        testFrame.setMinimumSize(testFrame.getSize());
        testFrame.setVisible(true);
    }
    public static void main(String[] args) {
    makeme();;
    }
}