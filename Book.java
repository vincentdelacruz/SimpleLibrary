
public class Book {
	private String title;
	private int id;
	private boolean isAvailable;
	
	public Book(String title){
		this.title = title;
		this.isAvailable = true;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	public int getID(){
		return this.id;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public boolean isAvailable(){
		return isAvailable;
	}
	
	public boolean borrow(){
		if(isAvailable()){
			this.isAvailable = false;
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean returnBook(){
		if(!isAvailable()){
			this.isAvailable = true;
			return true;
		}
		else{
			return false;
		}
	}
}
