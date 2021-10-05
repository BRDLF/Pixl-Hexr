import javax.swing.*;

import java.awt.event.*;
import java.awt.*;

// Kirk: Always name your classes PascalCase rather than c_names in Java.
class PHGui extends JPanel implements ActionListener {

    static final int gridMaxSize = 64*64;
    static String msg = new String("uintXX_t IMAGE[YY] = {\n");
    static int gridrow = 8;
    static int gridcol = 8;
    JToggleButton c1[] = new JToggleButton[gridMaxSize]; // Kirk: Define 64x64 as a const with a significant name, like MAX_GRID_SIZE
    boolean gridmemory[] = new boolean[gridMaxSize]; // see above
    static String testString;
    JLabel botLabel = new JLabel();
    JScrollPane centralzone;
    JPanel centralPanel = new JPanel();
    Dimension buttoDimension = new Dimension(14,14);
    static JFrame toolFrame;
    static JTextArea results;
    static JScrollPane rightPanel;
    static GridBagConstraints r;
    

    public PHGui() {
        super (new GridBagLayout());

        // Kirk: If you might like to adjust these values, consider using a const array, such as X_WIDTH = [8,16,32,64]
        // Then use a loop to initialize these. When you need to add or remove one, it's all done in one place. It also cuts down
        // on program length, which helps others understand your program more clearly.

        // Kirk: Consider grouping these methods into initialization methods that describe your actions, similar to your main program.
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
        l.gridx = 0; l.gridy = 0; l.anchor = GridBagConstraints.WEST; // Kirk: Pretty common in c, less common in Java. No issues tho.
        leftPanel.add(new JLabel("Set Height\n"), l); l.gridy = 1;
        leftPanel.add(RBH8, l); l.gridy = 2;
        leftPanel.add(RBH16, l); l.gridy=3;
        leftPanel.add(RBH32, l); l.gridy=4;
        leftPanel.add(RBH64, l);
        leftPanel.setBorder(topPanel.getBorder());
        
        centralzone = new JScrollPane(centralPanel);
        centralPanel.setLayout(new GridLayout(gridrow, gridcol, 1, 1));
        centralPanel.setBorder(topPanel.getBorder());
        
        makeGrid();

        String resultsString = msg.replace("XX", Integer.toString(gridcol)).replace("YY", Integer.toString(gridrow)) + computeHex();
        results = new JTextArea(resultsString);
        
        rightPanel = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_NEVER , JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        results.setWrapStyleWord(true);
        results.setLineWrap(true);
        results.setColumns(12);
        rightPanel.setBorder(topPanel.getBorder());
        // rightPanel.setMinimumSize(rightPanel.getSize());

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
        b.gridy = 1;
        botPanel.add(new JLabel("a"), b);
        
        
        botPanel.setBorder(topPanel.getBorder());

        //All Border Fill
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
        // c.weightx = 1; c.weighty = 1;
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

    // Kirk: Rename this to a significant action name. That way when you add the function as an action, it is clearer.
    public void actionPerformed(ActionEvent e){
        botLabel.setText(e.getActionCommand());

        String Source = e.getActionCommand();

        // Kirk: Yay switches :3
        switch (Source){

            case "RBW8":
            resizeGrid(8, gridrow);
            break;
            case "RBW16":
            resizeGrid(16, gridrow);
            break;
            case "RBW32":
            resizeGrid(32, gridrow);
            break;
            case "RBW64":
            resizeGrid(64, gridrow);
            break;
            case "RBH8":
            resizeGrid(gridcol, 8);
            break;
            case "RBH16": 
            resizeGrid(gridcol, 16);
            break;
            case "RBH32": 
            resizeGrid(gridcol, 32);
            break;
            case "RBH64": 
            resizeGrid(gridcol, 64);
            break;
            // case "Update":
            // toolFrame.setMinimumSize(toolFrame.getSize());
            // results.setText(msg.replace("XX", Integer.toString(gridcol)).replace("YY", Integer.toString(gridrow)) + computeHex());
            // break;
            case "Clear":
            clearGrid();
            break;
            default:
            break;
        }
        results.setText(msg.replace("XX", Integer.toString(gridcol)).replace("YY", Integer.toString(gridrow)) + computeHex());
        
        
        
        // rightPanel.setSize(rightPanel.getPreferredSize());
        // toolFrame.setMinimumSize(toolFrame.getSize());
    }

    // Kirk: Unit testing :3
    private void resizeGrid(int nW, int nH){
        // System.out.println("Resizing from " + gridcol + " to " + nW + " and from " + gridrow + " to " + nH);

        // Kirk: This is where naming a const would help; someone might not change this value if they wanted to make it 128x128 instead.
        boolean gridbool[] = new boolean[64*64];
        int tW; int tH;
        int p;
        for (int n = 0; n < gridcol*gridrow; n++){
        
                tW = n%gridcol;
                tH = n/gridcol;
                p = (tH*64) + tW;
                // p = (((n/gridcol)*nW) + (n%gridcol));
                if (tW >= 64) { // Kirk: nitpicky, but feels like this boolean should be inverted and include the below functions.
                    continue;
                }
                if (gridmemory[p] != c1[n].isSelected()){
                gridmemory[p] = c1[n].isSelected(); 
                // System.out.println("n = " + n + " and p = " + p);
                // System.out.println("c1[n] = " + c1[n].isSelected());
                }
        }

        for (int m = 0; m < 64*64; m++){
            if(gridmemory[m]){
                tW = m%64;
                tH = m/64;
                p = (tH*nW) + tW;
                if (tW >= nW){
                    continue;
                }
                gridbool[p] = true;
                // System.out.println("m = " + m + " and p = " + p);
            }
        }


        gridcol = nW;
        gridrow = nH;
        centralPanel.removeAll();
        for(int n = 0; n < nH*nW; n++){
            c1[n] = new JToggleButton();
            if (gridbool[n]){
                c1[n].setSelected(true);
            }
            c1[n].setPreferredSize(buttoDimension);
            centralPanel.add(c1[n]);
        }
        centralPanel.setLayout(new GridLayout(gridrow,gridcol, 1, 1));
        results.setRows(nH + 2);
        //TODO:if not Maximized
        //  IDE's will group these together
        toolFrame.setMinimumSize(new Dimension(100,100));
        toolFrame.pack();
        toolFrame.setMinimumSize(toolFrame.getSize());
        toolFrame.setVisible(true);
        
    }

    // Kirk: An alternative way to implement this is to re-initialize the gridmemory function, and then "sync" the UI.
    //  Having a UI-sync method can be VERY helpful to call after every update to the backend data structures.
    private void clearGrid(){
        for(int n = 0; n < gridcol*gridrow; n++){
            c1[n].setSelected(false);
        }
        // Kirk: This is where a constant I mentioned at the top would help.
        for(int n = 0; n < 64*64; n++){
            gridmemory[n] = false;
        }
    }
    
    // Kirk: This is a great method; this is more of the ideal for a Java program I'd suggest.
    private void makeGrid(){
        centralPanel.removeAll();
        for(int n = 0; n < gridcol*gridrow; n++){
            c1[n] = new JToggleButton();
            c1[n].setPreferredSize(buttoDimension);
            centralPanel.add(c1[n]);
        }
    }

    private static void createAndShowGUI(){
        toolFrame = new JFrame("Pixl Hexr");
        toolFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        toolFrame.setResizable(true);
        JComponent newContentPane = new PHGui();
        newContentPane.setOpaque(true);
        toolFrame.setContentPane(newContentPane);
        toolFrame.setLocationRelativeTo(null);
        toolFrame.pack();
        toolFrame.setVisible(true);
        // toolFrame.setPreferredSize(toolFrame.getSize());
        toolFrame.setMinimumSize(toolFrame.getSize());
    }

    // Kirk: Unit testing! :3
    protected String computeHex(){
        long compInt[] = new long[gridrow];
        int ch; int cw; int cwi;
        long addme;

        for(int cn = 0; cn < gridcol*gridrow; cn++){
            ch = cn/gridcol;
            cw = cn%gridcol;
            cwi = gridcol -(cw+1);
            if(c1[cn].isSelected()){
            addme = ((long)Math.pow(2, (cwi%4)))<<(4*(cwi/4));
            compInt[ch] = compInt[ch] + addme;
            // System.out.println("cwa = " + cwi);
            // System.out.println("Makes 2^" + (cwi%4));
            // System.out.println("Shifted "+4*(cwi/4)+" bits");
            // System.out.println("Adds " + addme + " to CompInt[" + ch +"]" + " of " + compInt[ch]);
            }
        }

        String compresults;
        StringBuilder sb = new StringBuilder(1200);
        String formatString = new String("%0" + Integer.toString(gridcol/4) + "x");
        for(int n=0;n < gridrow;n++){
            sb.append("0x" + String.format(formatString ,compInt[n]).toUpperCase() + ",\n");
        }
        sb.append("};");
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

// Kirk: final comment. Consider refactoring out the UI from the backend data structure into separate files.
//  Use a sync method that accepts the backend data structure to update the UI accordingly.