import java.math.BigInteger;

/*
 * Book Object
 * 
 * 1. Number of lines for the specific book
 * 2. Validate Values
 */
public class Book {
	
	public String[] genres = {"коцотевмийо","епистгломийо"};
	public String genre;
	public String title;
	public String author;
	public BigInteger  ISBN;
	public int year;
	public String[] specifyGenres= {"пеяиодийо","бибкио","пяайтийа сумемдяиым"};
	public String specificGenre;
	public String scientificDescription;
	
	
	public int GetBookLength() {
		if (genre.equals(genres[1]) ) return 7;
		else return 6;
	}
}
