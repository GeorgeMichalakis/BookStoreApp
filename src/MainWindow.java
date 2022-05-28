import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


/*
 * 1.The static object which is responsible for opening and closing the respective frames
 * 2. Constructor: Instantiates a BookDBManager object
 * 
 */

public class MainWindow{
	
	
	//Possible Frames
    private JFrame mainFrame;
    private JFrame scrollDBFrame;
    private JFrame deleteFrame;
    private JFrame findFrame;
    private JFrame registerFrameInit;
    private JFrame registerFrameSec;
    //Book Database Manager
    private BookDBManager manager;
    
    //Constructor
    public MainWindow() {
    	try {
			manager=new BookDBManager();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	// -------------------------------------- Main Menu Frame -------------------------------------- //
    public void openMainFrame() {
    	
		// -------------------------------------- Settings -------------------------------------- //
    	mainFrame=new JFrame();
    	mainFrame.setVisible(true);
    	mainFrame.setTitle("Book Database Manager");
    	mainFrame.setBounds(100, 100, 450, 300);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	mainFrame.getContentPane().setLayout(null);
		
		// -------------------------------------- Button for Viewing/Deleting Books -------------------------------------- //
		JButton viewOrDeleteButton = new JButton("1 - View Books");
		viewOrDeleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If database is empty, dsplay appropriate message
				if (manager.bookDB.isEmpty()) {
					JOptionPane.showMessageDialog(null, "There are no books!");
				}
				//If not, close main window and open Database Panel
				else {
					mainFrame.dispose();
					openScrollDBFrame();
				}
			}
		});
		viewOrDeleteButton.setBounds(10, 11, 414, 31);
		mainFrame.getContentPane().add(viewOrDeleteButton);
		// -------------------------------------- Button for Finding Books -------------------------------------- //
		JButton findBooksButton = new JButton("2 - Find a book");
		findBooksButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Same with previous button
				if (manager.bookDB.isEmpty()) JOptionPane.showMessageDialog(null, "There are no books!");
				else {
					mainFrame.dispose();
					OpenFindBookFrame();
				}
			}
		});
		findBooksButton.setBounds(10, 53, 414, 31);
		mainFrame.getContentPane().add(findBooksButton);
		// -------------------------------------- Button for Adding Books -------------------------------------- //
		JButton addBooksButton = new JButton("3 - Insert a book");
		addBooksButton.setBounds(10, 95, 414, 31);
		mainFrame.getContentPane().add(addBooksButton);
		addBooksButton.addActionListener(new ActionListener() {
			//Open Register frame
			public void actionPerformed(ActionEvent e) {
				getMainFrame().dispose();
				openRegisterFrame();
			}
		});
		// -------------------------------------- Button for Quiting the application -------------------------------------- //
		JButton quitButton = new JButton("4 - Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		quitButton.setBounds(153, 172, 129, 51);
		mainFrame.getContentPane().add(quitButton);
	}
 // -------------------------------------- In case I need to dispose a frame from another frame -------------------------------------- //
    public JFrame getMainFrame(){
        return mainFrame;
    }
    
    public JFrame getSCrollDBFrame() {
    	return scrollDBFrame;
    }
	// -------------------------------------- Main Database Scrolling Frame -------------------------------------- //
    public void openScrollDBFrame() {
    	scrollDBFrame=new JFrame();
    	scrollDBFrame.setVisible(true);
    	scrollDBFrame.setTitle("Book Collection");
    	//Hacky stuff, should resize it
    	scrollDBFrame.setSize(1000, 500);
        JPanel panel;
			try {
				panel = getPanel(scrollDBFrame);
		        JScrollPane scrollBar = new JScrollPane(panel,
		                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		        scrollDBFrame.add(scrollBar);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 // --------------------------------------Additional Panels for the aforementioned Scrolling Frame -------------------------------------- //
    private JPanel getPanel(JFrame myFrame) throws IOException {
    	
    	BookDBManager manager=new BookDBManager();
    	JPanel wholePanel=new JPanel();
    	JPanel p = new JPanel(new GridLayout(manager.getBookLength(),2));
    	//Go Back Button
        JButton cancelButton = new JButton("Return");
        cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getSCrollDBFrame().dispose();
				try {
					openMainFrame();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
        //Get same buttons as books inside the db
        JButton[] deleteButtons=new JButton[manager.bookDB.size()];
        int i=0;
    	for (Book book : manager.bookDB) {
        	JPanel indiviDualpanel=new JPanel(new GridLayout(book.GetBookLength()+2,1));
        	
            JLabel label1=new JLabel();
            JLabel label2=new JLabel();
            JLabel label3=new JLabel();
            JLabel label4=new JLabel();
            JLabel label5=new JLabel();
            JLabel label6=new JLabel();
            JLabel label7=new JLabel();
            JLabel emptyLabel=new JLabel("\n");
            
            label1.setText(book.genre);
            label2.setText(book.title);
            label3.setText(book.author);
            label4.setText(book.ISBN.toString());
            label5.setText(String.valueOf(book.year));
            label6.setText(book.specificGenre);
            if (book.scientificDescription!=null) label7.setText(book.scientificDescription);
            indiviDualpanel.add(label1);
            indiviDualpanel.add(label2);
            indiviDualpanel.add(label3);
            indiviDualpanel.add(label4);
    		indiviDualpanel.add(label5);
    		indiviDualpanel.add(label6);
    		if (book.scientificDescription!=null) indiviDualpanel.add( label7);
    		indiviDualpanel.add(emptyLabel);
    		p.add(indiviDualpanel);
    		
    		JPanel customPanel=new JPanel();
    		deleteButtons[i]= new JButton();
            try {
    		    Image img = ImageIO.read(getClass().getResource("del.png"));
    		    Image newImage = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    		    deleteButtons[i].setIcon(new ImageIcon(newImage));
    		    deleteButtons[i].setPreferredSize(new Dimension(20,20));
    		  } catch (Exception ex) {
    		    System.out.println(ex);
    		  }
    	            deleteButtons[i].addActionListener(new ActionListener() {
   	    			
  	    			public void actionPerformed(ActionEvent e) {
  	    			getSCrollDBFrame().dispose();
  	    			openDeleteFrame(book.title);
   	    			}
    			});
      		customPanel.add(deleteButtons[i]);
      		p.add(customPanel);
      		//Add the aforementioned button for returning to the main menu on last element.
    		if (book==manager.bookDB.get(manager.bookDB.size() - 1)) indiviDualpanel.add(cancelButton);
    	}
    	wholePanel.add(new JScrollPane(p));
        return wholePanel;
    }
    
	// -------------------------------------- Delete Frame -------------------------------------- //
    public void openDeleteFrame(String title) {
    	
    	deleteFrame=new JFrame();
    	JPanel contentPane = new JPanel();
    	
    	deleteFrame.setVisible(true);
    	deleteFrame.setTitle("Delete book");
    	deleteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	deleteFrame.setBounds(100, 100, 450, 129);
    	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    	deleteFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Are you sure you want to delete it?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 39);
		contentPane.add(lblNewLabel);
		
		JButton yesDeleteItButton = new JButton("Õ·È");
		yesDeleteItButton.setBounds(262, 61, 89, 23);
		contentPane.add(yesDeleteItButton);
		yesDeleteItButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manager.DeleteABook(title);
					manager.UpdateBookDB();
					JOptionPane.showMessageDialog(null, "Book deleted successfully");
					deleteFrame.dispose();
					if (manager.bookDB.isEmpty())
						try {
							openMainFrame();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					else openScrollDBFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JButton noGoBackButton = new JButton("No");
		noGoBackButton.setBounds(85, 61, 89, 23);
		deleteFrame.add(noGoBackButton);
		noGoBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteFrame.dispose();
				openScrollDBFrame();
			}
		});
	}

	// -------------------------------------- Find a Book Frame -------------------------------------- //
	public void OpenFindBookFrame() {

		JPanel contentPane;
		JTextField textField;
		
		findFrame=new JFrame();
		findFrame.setVisible(false);
		findFrame.setVisible(true);
		findFrame.setTitle("Find a book");
		findFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		findFrame.setBounds(100, 100, 404, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		findFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton FindButton = new JButton("Find");
		FindButton.setBounds(210, 147, 119, 44);
		contentPane.add(FindButton);
		
		textField = new JTextField();
		textField.setBounds(61, 92, 268, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Insert the title of the book or the author");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 368, 67);
		contentPane.add(lblNewLabel);
		
		FindButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg=manager.DoIHaveThisBook(textField.getText());
				if (msg!=null) {
					JOptionPane.showMessageDialog(null, msg);
				}
				else {
					JOptionPane.showMessageDialog(null, manager.HereIsYourBook(textField.getText()));
				}
			}
		});
		JButton BackButton;
		BackButton = new JButton("Return");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				findFrame.dispose();
				try {
					openMainFrame();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		BackButton.setBounds(61, 147, 119, 44);
		contentPane.add(BackButton);
	}
	// -------------------------------------- Pick a genre Frame -------------------------------------- //
	public void openRegisterFrame() {
		registerFrameInit=new JFrame();
		Book randomBook = new Book();
		JPanel contentPane;
		JComboBox<PickAnItem> genreOfBook = new JComboBox<PickAnItem>();
		registerFrameInit.setVisible(true);
		registerFrameInit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrameInit.setBounds(100, 100, 375, 139);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			registerFrameInit.setContentPane(contentPane);
			contentPane.setLayout(null);
			genreOfBook.setBounds(190, 23, 159, 20);
			for (int i=0;i<randomBook.genres.length;i++) {
				genreOfBook.addItem(new PickAnItem(randomBook.genres[i].toString(), randomBook.genres[i].toString()));
			}
			contentPane.add(genreOfBook);
			
			JLabel lblNewLabel_1_3_1 = new JLabel("Select Genre:");
			lblNewLabel_1_3_1.setBounds(10, 24, 159, 19);
			contentPane.add(lblNewLabel_1_3_1);
			
			JButton btnNewButton = new JButton("Continue");
			btnNewButton.setBounds(94, 66, 149, 23);
			contentPane.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					Object item = genreOfBook.getSelectedItem();
					String value = ((PickAnItem)item).getValue();
					registerFrameInit.dispose();
					secondRegisterFrame(value);
				}
			});
		}
	// -------------------------------------- Insert the rest of info for the book Frame -------------------------------------- //
	public void secondRegisterFrame(String genre) {
		registerFrameSec=new JFrame();
		registerFrameSec.setVisible(true);
		registerFrameSec.setTitle("Insert a new book");
		registerFrameSec.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		registerFrameSec.setBounds(100, 100, 450, 328);
		registerFrameSec.getContentPane().setLayout(null);
		JTextField bookTitle=new JTextField();
		JTextField bookAuthor=new JTextField();
		JTextField bookISBN=new JTextField();
		JTextField bookYearPublish=new JTextField();
		JTextField bookScientificField=new JTextField();
		 Book randomBook= new Book();
		//----------------------------------------------------//
		JLabel lblNewLabel_1 = new JLabel("Insert a title:");
		lblNewLabel_1.setBounds(20, 11, 159, 19);
		registerFrameSec.getContentPane().add(lblNewLabel_1);
		
		bookTitle.setBounds(265, 11, 159, 20);
		registerFrameSec.getContentPane().add(bookTitle);
		bookTitle.setColumns(10);
		
		//----------------------------------------------------//
		JLabel lblNewLabel_1_1 = new JLabel("Insert Author:");
		lblNewLabel_1_1.setBounds(20, 42, 159, 19);
		registerFrameSec.getContentPane().add(lblNewLabel_1_1);
		
		bookAuthor.setColumns(10);
		bookAuthor.setBounds(265, 42, 159, 20);
		registerFrameSec.getContentPane().add(bookAuthor);
		
		//----------------------------------------------------//
		
		JLabel lblNewLabel_1_2 = new JLabel("Insert ISBN:");
		lblNewLabel_1_2.setBounds(20, 73, 159, 19);
		registerFrameSec.getContentPane().add(lblNewLabel_1_2);
		
		bookISBN.setColumns(10);
		bookISBN.setBounds(265, 73, 159, 20);
		registerFrameSec.getContentPane().add(bookISBN);
		//----------------------------------------------------//
		
		JLabel lblNewLabel_1_3 = new JLabel("Insert Date of being Published:");
		lblNewLabel_1_3.setBounds(20, 107, 159, 19);
		registerFrameSec.getContentPane().add(lblNewLabel_1_3);
		
		bookYearPublish.setColumns(10);
		bookYearPublish.setBounds(265, 107, 159, 20);
		registerFrameSec.getContentPane().add(bookYearPublish);
		//----------------------------------------------------//
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Select Book Genre:");
		lblNewLabel_1_3_1.setBounds(20, 138, 159, 19);
		registerFrameSec.getContentPane().add(lblNewLabel_1_3_1);
		
		JComboBox<PickAnItem> specificKindOfBook = new JComboBox<PickAnItem>();
		specificKindOfBook.setBounds(265, 137, 159, 20);
		for (int i=0;i<randomBook.specifyGenres.length;i++) {
			specificKindOfBook.addItem(new PickAnItem(randomBook.specifyGenres[i].toString(), randomBook.specifyGenres[i].toString()));
		}
		registerFrameSec.getContentPane().add(specificKindOfBook);
		if (genre==randomBook.genres[1]) {
			JLabel lblNewLabel_1_3_1_1 = new JLabel("Field:");
			lblNewLabel_1_3_1_1.setBounds(20, 168, 207, 19);
			registerFrameSec.getContentPane().add(lblNewLabel_1_3_1_1);
			
			bookScientificField.setColumns(10);
			bookScientificField.setBounds(265, 168, 159, 20);
			registerFrameSec.getContentPane().add(bookScientificField);
		}
		//----------------------------------------------------//
		
		JButton registerButton = new JButton("Register Book");
		registerButton.setBounds(117, 255, 197, 23);
		registerFrameSec.getContentPane().add(registerButton);
		
		
		registerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String msg=manager.ValidateValues( bookTitle.getText(), bookAuthor.getText(), bookISBN.getText(), bookYearPublish.getText());
				if (msg==null) {
					registerFrameSec.dispose();
					Object item = specificKindOfBook.getSelectedItem();
					String value = ((PickAnItem)item).getValue();
					manager.TransformBookValues(randomBook,genre,bookTitle.getText(), bookAuthor.getText(), bookISBN.getText(), bookYearPublish.getText(),value,bookScientificField.getText());
					try {
						openMainFrame();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, msg);
				}
			}
		});
	}
}