//import java.awt.Dimension;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
//public class ScrollableBookList extends JFrame{
//
//	public ScrollableBookList() {
//		
//        this.setVisible(true);
//        this.setTitle("Συλλογή Βιβλίων");
//        this.setSize(400, 400);
//        JPanel panel;
//		try {
//			panel = getPanel(this);
//	        JScrollPane scrollBar = new JScrollPane(panel,
//	                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//	                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//	            this.add(scrollBar);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
//    private JPanel getPanel(JFrame myFrame) throws IOException {
//    	
//    	BookDBManager manager=new BookDBManager();
//    	JPanel wholePanel=new JPanel();
//    	JPanel p = new JPanel(new GridLayout(manager.getBookLength(),2));
//        
//        JButton cancelButton = new JButton("Επιστροφή");//added for cancel button
//        cancelButton.addActionListener(new ActionListener() {
//		
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//			}
//		});
//        
//        JButton[] deleteButtons=new JButton[manager.bookDB.size()];
//        int i=0;
//    	for (Book book : manager.bookDB) {
//        	JPanel indiviDualpanel=new JPanel(new GridLayout(book.GetBookLength()+2,1));
//        	
//            JLabel label1=new JLabel();
//            JLabel label2=new JLabel();
//            JLabel label3=new JLabel();
//            JLabel label4=new JLabel();
//            JLabel label5=new JLabel();
//            JLabel label6=new JLabel();
//            JLabel label7=new JLabel();
//            JLabel emptyLabel=new JLabel("\n");
//            
//            label1.setText(book.genre);
//            label2.setText(book.title);
//            label3.setText(book.author);
//            label4.setText(book.ISBN.toString());
//            label5.setText(String.valueOf(book.year));
//            label6.setText(book.specificGenre);
//            if (book.scientificDescription!=null) label7.setText(book.scientificDescription);
//            indiviDualpanel.add(label1);
//            indiviDualpanel.add(label2);
//            indiviDualpanel.add(label3);
//            indiviDualpanel.add(label4);
//    		indiviDualpanel.add(label5);
//    		indiviDualpanel.add(label6);
//    		if (book.scientificDescription!=null) indiviDualpanel.add( label7);
//    		indiviDualpanel.add(emptyLabel);
//    		p.add(indiviDualpanel);
//    		
//    		JPanel customPanel=new JPanel();
//    		deleteButtons[i]= new JButton();
//            try {
//    		    Image img = ImageIO.read(getClass().getResource("del.png"));
//    		    Image newImage = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
//    		    deleteButtons[i].setIcon(new ImageIcon(newImage));
//    		    deleteButtons[i].setPreferredSize(new Dimension(20,20));
//    		  } catch (Exception ex) {
//    		    System.out.println(ex);
//    		  }
////            deleteButtons[i].addActionListener(new ActionListener() {
////    			
////    			public void actionPerformed(ActionEvent e) {
////    				DeleteBookButton DeleteDialog = new DeleteBookButton(myFrame,manager,book.title);
////    			}
////    		});
//      		customPanel.add(deleteButtons[i]);
//      		p.add(customPanel);
//    		if (book==manager.bookDB.get(manager.bookDB.size() - 1)) indiviDualpanel.add(cancelButton);
//    	}
//    	wholePanel.add(new JScrollPane(p));
//    	wholePanel.setSize(1000, 1000);
//        return wholePanel;
//    }
//}
