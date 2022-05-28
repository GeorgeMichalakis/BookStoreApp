import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 1. Constructor: Check if database file exists, in case it doesn't, create it. Create bookDB and update it in case there are entries.
 * 2. Show Books
 * 3. Find a Book
 * 4. Add a book
 * 5. Delete a book
 */
public class BookDBManager {
	
	public List<Book> bookDB=new ArrayList<Book>(); 
	private File databaseFile0=new File("filename.txt");
	
	//Constructor
	public BookDBManager() throws IOException {
		databaseFile0.createNewFile();
		if(databaseFile0.length()!=0) {
			UpdateBookDB();
		}
	}
	
	// -------------------------------------- Register DB -------------------------------------- //
	//Update the DB
	public void UpdateBookDB() throws FileNotFoundException {
		bookDB.clear();
		Scanner scanner = new Scanner(databaseFile0);
		boolean done=false;
		while (!done) {
			Book bookInDb=new Book();
			done=readABookAndRegisterIt(scanner,bookInDb);
		}
		scanner.close();
	}
	
	//Length Of DB
	public int getBookLength() {
		return bookDB.size();
	}
	
	//Read every book that's in and register it into the db
		private boolean readABookAndRegisterIt(Scanner scanner, Book book) {
			
			int i=0;
			String initialLine=new String();
			while (scanner.hasNextLine()) {
			   String line = scanner.nextLine();
			   
			   //Just for the first time
			   if (i==0) {
				   initialLine=line;
				   
			   }
			   
			   if (initialLine.equals(book.genres[1])) {
				   
				   switch (i) {
				   
				   	case 0: {
				   		book.genre=line;
				   		break;
				   	}
				   	case 1: {
				   		book.title=line;
				   		break;
				   	}
				   	case 2: {
				   		book.author=line;
				   		break;
				   	}
				   	case 3: {
				   		book.ISBN=new BigInteger((line));
				   		break;
				   	}
				   	case 4: {
				   		book.year=Integer.parseInt(line);
				   		break;
				   	}
				   	case 5: {
				   		book.specificGenre=line;
				   		break;
				   	}
				   	case 6: {
				   		book.scientificDescription=line;
				   	}
				default:
					bookDB.add(book);
					i=-1;
					if (scanner.hasNextLine()) {
						scanner.nextLine();
						return false;
					}
					else return true;
				}
			   }
			   else {
				   switch (i) {
				   	case 0: {
				   		book.genre=line;
				   		break;
				   	}
				   	case 1: {
				   		book.title=line;
				   		break;
				   	}
				   	case 2: {
				   		book.author=line;
				   		break;
				   	}
				   	case 3: {
				   		book.ISBN=new BigInteger((line));
				   		break;
				   	}
				   	case 4: {
				   		book.year=Integer.parseInt(line);
				   		break;
				   	}
				   	case 5: {
				   		book.specificGenre=line;
				   	}
				default:
					bookDB.add(book);
					i=-1;
					if (scanner.hasNextLine()) {
						scanner.nextLine();
						return false;
					}
					else return true;
				}
			   }
			   i++;
			}
			return true;
		}
	// -------------------------------------- For Finding Books -------------------------------------- //
	
	public String DoIHaveThisBook(String titleOrAuthor) {
		StringTransformer stringTransformObject=new StringTransformer();
		String titleOrAuthor_Transformed=stringTransformObject.AccentRemovalUpperCase(titleOrAuthor);
		for (Book book : bookDB){
			if (titleOrAuthor_Transformed.equals(book.title)||titleOrAuthor_Transformed.equals(book.author)) return null;
		}
		return "Can't find any related results";
	}
	
	public String HereIsYourBook(String titleOrAuthor) {
		Book bookToBeDisplayedBook=new Book();
		StringTransformer stringTransformObject=new StringTransformer();
		String titleOrAuthor_Transformed=stringTransformObject.AccentRemovalUpperCase(titleOrAuthor);
		for (Book book : bookDB){
			if (titleOrAuthor_Transformed.equals(book.title)||titleOrAuthor_Transformed.equals(book.author)) bookToBeDisplayedBook=book;;
		}
		String desiredString=bookToBeDisplayedBook.genre+"\n"
		+bookToBeDisplayedBook.title+"\n"
		+bookToBeDisplayedBook.author+"\n"
		+bookToBeDisplayedBook.ISBN+"\n"
		+bookToBeDisplayedBook.year+"\n"
		+bookToBeDisplayedBook.specificGenre+"\n";
		if(bookToBeDisplayedBook.scientificDescription!=null)desiredString=desiredString+bookToBeDisplayedBook.scientificDescription+"\n";
		
		return desiredString;
	}
	// -------------------------------------- For Adding Books -------------------------------------- //
	// Create an empty Book
	public Book CreateBook() {
		Book newBook = new Book();
		return newBook;
	}
	//Transform the values
	public void TransformBookValues(Book emptyBook,String genre, String title, String author, String ISBN, String year,String specificKind, String ScientificField) {
		
		StringTransformer stringTransformObject=new StringTransformer();
		String title_Transformed=stringTransformObject.AccentRemovalUpperCase(title);
		String author_Transformed =stringTransformObject.AccentRemovalUpperCase(author);
		BigInteger ISBN_Transformed= new BigInteger((ISBN));
		int year_transformed=Integer.parseInt(year);
		
		AssignBookValues(emptyBook,genre, title_Transformed, author_Transformed, ISBN_Transformed, year_transformed,specificKind, ScientificField);
		
	}
	//Assign Book Values
	public void AssignBookValues(Book emptyBook,String genre, String title, String author, BigInteger ISBN, int year, String specificKind,String ScientificField) {
	
		
		emptyBook.genre=genre;
		emptyBook.title=title;
		emptyBook.author=author;
		emptyBook.ISBN=ISBN;
		emptyBook.year=year;
		emptyBook.specificGenre=specificKind;
		emptyBook.scientificDescription=ScientificField;
	
		AddBookToDB(emptyBook);
		
	}
	
	//Write it on file
	private void AddBookToDB(Book unregisteredBook) {
		
		FileWriter writer;
		try {
			
			writer = new FileWriter(databaseFile0,true);
			writer.write(unregisteredBook.genre+"\n");
			writer.write(unregisteredBook.title+"\n");
			writer.write(unregisteredBook.author+"\n");
			writer.write(unregisteredBook.ISBN+"\n");
			writer.write(unregisteredBook.year+"\n");
			writer.write(unregisteredBook.specificGenre+"\n");
			if(unregisteredBook.genre==unregisteredBook.genres[1])writer.write(unregisteredBook.scientificDescription+"\n");
			writer.write("\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			UpdateBookDB();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// -------------------------------------- For Deleting Books -------------------------------------- //
	public void DeleteABook(String title) {
	
		for (Book book : bookDB) {
			if (book.title.equals(title)) {
				try {
					bookDB.remove(book);
					ReWriteTxt();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void ReWriteTxt() throws IOException {
	
	File inputFile = new File("filename.txt");
	File tempFile = new File("myTempFile.txt");
	
	BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
	for (Book bookInDb : bookDB) {
		
		writer.write(bookInDb.genre+"\n");
		writer.write(bookInDb.title+"\n");
		writer.write(bookInDb.author+"\n");
		writer.write(bookInDb.ISBN.toString()+"\n");
		writer.write(String.valueOf(bookInDb.year)+"\n");
		writer.write(bookInDb.specificGenre+"\n");
		if (bookInDb.genre==bookInDb.genres[1]) writer.write(bookInDb.scientificDescription);
		writer.write("\n");
		
	}
	writer.close(); 
	inputFile.delete();
	tempFile.renameTo(inputFile);
	}
	// -------------------------------------- Initial Book VAlue Check -------------------------------------- //
	public String ValidateValues (String bookTitle,String bookAuthor,String  bookISBN,String bookYearPublish) {
		BigInteger  ISBN= new BigInteger("0");
		int Year=0;
		if (bookTitle.isEmpty()) 
			return "Title Of Book is empty";
		else if (bookAuthor.isEmpty()) 
			return "Author's name is empty";
		try {
			ISBN= new BigInteger((bookISBN));
			if (String.valueOf(ISBN).length()<13)
				return "ISBN must be a 13 digit number";
			else if (Integer.parseInt(""+ISBN.toString().charAt(0))!=9 || Integer.parseInt(""+ISBN.toString().charAt(1))!=7 || (Integer.parseInt(""+ISBN.toString().charAt(2))!=8 && Integer.parseInt(""+ISBN.toString().charAt(2))!=9)) 
				return "ISBN needs to start either 978 or 979";
			try{
				Year=Integer.parseInt(bookYearPublish);
				if (!(Year>1000)) return "Year of Publishing needs to be a 4 digit number";
				}
			catch (Exception e1) {
				return "Year of Publishing invalid";
			}
		} catch (Exception e2) {
			return "Invalid ISBN";
		}
		return null;
	}
	
}
