package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class AddressBook extends JPanel implements ActionListener, FocusListener 
{

	
	
	
	JTextField streetField, cityField, searchField;
	JFormattedTextField zipField, phoneField;
	JSpinner stateSpinner;
    Font regularFont, italicFont;
    JLabel addressDisplay;
    final static int GAP = 10;
    
    public static void main(String[] args)
	{
		//Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run() 
            {
                //Turn off metal's use of bold fonts
            	UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI();
            }
        });
	}
    
    public AddressBook()
    {
    	setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftHalf = new JPanel() 
        {
            //Don't allow us to stretch vertically.
            public Dimension getMaximumSize()
            {
                Dimension pref = getPreferredSize();
                return new Dimension(Integer.MAX_VALUE,
                                     pref.height);
            }
        };
        leftHalf.setLayout(new BoxLayout(leftHalf,
                                         BoxLayout.PAGE_AXIS));
        JPanel panel = new JPanel(new SpringLayout());
        
        //Add text input fields
        streetField = new JTextField();
        streetField.setColumns(20);
        panel.add(new JLabel("Street Address:",JLabel.TRAILING));
        panel.add(streetField);
        streetField.addActionListener(this);
        streetField.addFocusListener(this);
        
        cityField = new JTextField();
        cityField.setColumns(20);
        panel.add(new JLabel("City:",JLabel.TRAILING));
        panel.add(cityField);
        cityField.addActionListener(this);
        cityField.addFocusListener(this);
        
        String[] stateStrings = getStateStrings();
        stateSpinner = new JSpinner(new SpinnerListModel(stateStrings));
        panel.add(new JLabel("State:" , JLabel.TRAILING));
        panel.add(stateSpinner);
        getTextField((JSpinner)stateSpinner).addActionListener(this);
        getTextField((JSpinner)stateSpinner).addFocusListener(this);
        
        zipField = new JFormattedTextField(createFormatter("#####"));
        panel.add(new JLabel("Zip Code:",JLabel.TRAILING));
        panel.add(zipField);
        zipField.addActionListener(this);
        zipField.addFocusListener(this);
        
        phoneField = new JFormattedTextField(createFormatter("##########"));
        panel.add(new JLabel("Phone Number(no dashes):",JLabel.TRAILING));
        panel.add(phoneField);
        phoneField.addActionListener(this);
        phoneField.addFocusListener(this);
        
        SpringUtilities.makeCompactGrid(panel,
                5, 2,
                GAP, GAP, //init x,y
                GAP, GAP/2);//xpad, ypad
        leftHalf.add(panel);
        
        //add buttons
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        
        JButton button = new JButton("Add address");
        button.addActionListener(this);
        panel2.add(button);
        
        panel2.setBorder(BorderFactory.createEmptyBorder(0, 0,
                GAP-5, GAP-5));
        
        leftHalf.add(panel2);
        

        add(leftHalf);
        JPanel rightHalf = new JPanel(new BorderLayout());
        addressDisplay = new JLabel();
        addressDisplay.setHorizontalAlignment(JLabel.CENTER);
        rightHalf.setBorder(BorderFactory.createEmptyBorder(
                GAP/2, //top
                0,     //left
                GAP/2, //bottom
                0));   //right
        rightHalf.add(new JSeparator(JSeparator.VERTICAL),
                BorderLayout.LINE_START);
        rightHalf.add(addressDisplay,
                BorderLayout.CENTER);
      	rightHalf.setPreferredSize(new Dimension(200, 150));
        add(rightHalf);
        
    }
    
    protected MaskFormatter createFormatter(String s)
    {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }

	@Override
	public void focusGained(FocusEvent e) 
	{
		Component c = e.getComponent();
        if (c instanceof JFormattedTextField)
        {
            selectItLater(c);
        } else if (c instanceof JTextField) 
        {
            ((JTextField)c).selectAll();
        }
	}
	protected void selectItLater(Component c) 
	{
        if (c instanceof JFormattedTextField) 
        {
            final JFormattedTextField ftf = (JFormattedTextField)c;
            SwingUtilities.invokeLater(new Runnable() 
            {
                public void run()
                {
                    ftf.selectAll();
                }
            });
        }
    }

	@Override
	public void focusLost(FocusEvent e) {}
	
	public String[] getStateStrings() {
        String[] stateStrings = {
            "Alabama (AL)",
            "Alaska (AK)",
            "Arizona (AZ)",
            "Arkansas (AR)",
            "California (CA)",
            "Colorado (CO)",
            "Connecticut (CT)",
            "Delaware (DE)",
            "District of Columbia (DC)",
            "Florida (FL)",
            "Georgia (GA)",
            "Hawaii (HI)",
            "Idaho (ID)",
            "Illinois (IL)",
            "Indiana (IN)",
            "Iowa (IA)",
            "Kansas (KS)",
            "Kentucky (KY)",
            "Louisiana (LA)",
            "Maine (ME)",
            "Maryland (MD)",
            "Massachusetts (MA)",
            "Michigan (MI)",
            "Minnesota (MN)",
            "Mississippi (MS)",
            "Missouri (MO)",
            "Montana (MT)",
            "Nebraska (NE)",
            "Nevada (NV)",
            "New Hampshire (NH)",
            "New Jersey (NJ)",
            "New Mexico (NM)",
            "New York (NY)",
            "North Carolina (NC)",
            "North Dakota (ND)",
            "Ohio (OH)",
            "Oklahoma (OK)",
            "Oregon (OR)",
            "Pennsylvania (PA)",
            "Rhode Island (RI)",
            "South Carolina (SC)",
            "South Dakota (SD)",
            "Tennessee (TN)",
            "Texas (TX)",
            "Utah (UT)",
            "Vermont (VT)",
            "Virginia (VA)",
            "Washington (WA)",
            "West Virginia (WV)",
            "Wisconsin (WI)",
            "Wyoming (WY)"
        };
        return stateStrings;
    }
	
	public JFormattedTextField getTextField(JSpinner spinner) 
	{
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) 
        {
            return ((JSpinner.DefaultEditor)editor).getTextField();
        } 
        else 
        {
            System.err.println("Unexpected editor type: "
                               + spinner.getEditor().getClass()
                               + " isn't a descendant of DefaultEditor");
            return null;
        }
    }

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		updateDisplays();		
	}
	
	protected void updateDisplays() 
	{
        addressDisplay.setText(formatInput());
    }
	protected String formatInput() {

        String street = streetField.getText();
        String city = cityField.getText();
        String state = (String)stateSpinner.getValue();
        String zip = zipField.getText();
        String phone = phoneField.getText();
        String empty = "";

        if ((street == null) || empty.equals(street)) 
        {
            street = "<em>(no street specified)</em>";
        }
        if ((city == null) || empty.equals(city)) 
        {
            city = "<em>(no city specified)</em>";
        }
        if ((state == null) || empty.equals(state)) 
        {
            state = "<em>(no state specified)</em>";
        } 
        else 
        {
            int abbrevIndex = state.indexOf('(') + 1;
            state = state.substring(abbrevIndex,
                                    abbrevIndex + 2);
        }
        if ((zip == null) || empty.equals(zip)) 
        {
            zip = "";
        }
        if ((phone == null) || empty.equals(phone)) {
            phone = "<em>(no phone number specified)<em>";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<html><p align=center>");
        sb.append(street);
        sb.append("<br>");
        sb.append(city);
        sb.append(" ");
        sb.append(state); //should format
        sb.append(" ");
        sb.append(zip);
        sb.append("<br>");
        sb.append(phone);
        sb.append("</p></html>");

        return sb.toString();
    }
	
/*	protected JComponent createAddressDisplay() 
	{
		JPanel panel = new JPanel(new BorderLayout());
        addressDisplay = new JLabel();
        addressDisplay.setHorizontalAlignment(JLabel.CENTER);
        regularFont = addressDisplay.getFont().deriveFont(Font.PLAIN,
                                                            16.0f);
        italicFont = regularFont.deriveFont(Font.ITALIC);
        updateDisplays();

        //Lay out the panel.
        panel.setBorder(BorderFactory.createEmptyBorder(
                                GAP/2, //top
                                0,     //left
                                GAP/2, //bottom
                                0));   //right
        panel.add(new JSeparator(JSeparator.VERTICAL),
                  BorderLayout.LINE_START);
        panel.add(addressDisplay,
                  BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(200, 150));

        return panel;
    }*/
	
	private static void createAndShowGUI() 
	{
        //Create and set up the window.
        JFrame frame = new JFrame("Your Address Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add contents to the window.
        frame.add(new AddressBook());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
	
}
