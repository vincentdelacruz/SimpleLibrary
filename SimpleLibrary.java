import java.util.ArrayList;
import java.util.HashMap;

public class SimpleLibrary {
	ArrayList<Book> books = new ArrayList<Book>();
	HashMap<String,ArrayList<Integer>> memberRentals = new HashMap<String, ArrayList<Integer>>();
	int bookID = 0;
	public Book findBookByTitle(String title){
		for(Book b : this.books){
			if(b.getTitle().equals(title)){
				return b;
			}
		}
		
		return null;
	}
	public Book findBookByID(int id)
	{
		for(Book b : this.books){
			if(b.getID() == id){
				return b;
			}
		}
		return null;
	}
	public void addBook(Book b){
		bookID++;
		b.setID(bookID);
		books.add(b);
	}
	public int borrowBook(String member, String title)
	{
		Book b = findBookByTitle(title);
		boolean success;
		if(b == null){
			System.out.println("Book not found");
			return -1;
		}
		else{
			success = b.borrow();
			if(!success){
				System.out.println("Book is not available");
				return -1;
			}
			else{
				checkOutBook(member, b.getID());
				return b.getID();
			}
		}
	}	
	public void checkOutBook(String member, int id)
	{
		if(memberRentals.containsKey(member)){
			memberRentals.get(member).add(id);
			System.out.println(member + " successfully borrowed " + findBookByID(id).getTitle());
		}
		else
		{
			ArrayList<Integer> rentals= new ArrayList<Integer>();
			rentals.add(id);
			memberRentals.put(member, rentals);
			System.out.println(member + " successfully borrowed " + findBookByID(id).getTitle());
		}
	}
	public void returnBook(String member, int id)
	{
		if(!memberRentals.get(member).contains(id)){
			System.out.println("This member is not borrowing this book");
		}
		else{
			Book b = findBookByID(id);
			b.returnBook();
			memberRentals.get(member).remove(memberRentals.get(member).indexOf(id));
			System.out.println(member + " successfully returned " + findBookByID(id).getTitle());
		}
	}
	
	public static void main(String[] args){
		SimpleLibrary lib = new SimpleLibrary();
		lib.borrowBook("Vincent", "Harry Potter"); //Expected: Book not Found
		
		Book hp = new Book("Harry Potter");
		lib.addBook(hp);
		
		int borrowed1 = lib.borrowBook("Vincent", "Harry Potter"); //Expected: Successfully borrowed book
		int borrowed2 = lib.borrowBook("Codrina", "Harry Potter"); //Expected: Book not available
		lib.returnBook("Vincent", borrowed1); //Expected: Successfully returned book
		lib.returnBook("Vincent", borrowed1); //Expected: Member is not borrowing this book
		int borrowed3 = lib.borrowBook("Jad", "Harry Potter"); //Expected: Successfully borrowed book
		
	}
}
