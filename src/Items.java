

public class Items {
	private String itemType;
	private String id;
	private String name;
	private String type;
	private String category;
	private String properties;
	private boolean isAvailable = true;
	private String borrowedName;
	private String borrowedId;
	private String date;
	
	//getters and setters
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBorrowedId() {
		return borrowedId;
	}
	public String getBorrowedName() {
		return borrowedName;
	}
	public String getBorrowedDate() {
		return borrowedDate;
	}
	private String borrowedDate;
	public void setBorrowedDate(String date) {
		this.borrowedDate=date;
	}
	
	private String runTime;
	
	public String getItemType() {
		return itemType;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getCategory() {
		return category;
	}
	
	public String getRunTime() {
		return runTime;
	}
	public String getProperties() {
		return properties;
	}
	//constructor
	public  Items(String itemType,String id,String name,String properties,String category) {
				this.itemType=itemType;
				this.id=id;
				this.name=name;
				this.category=category;
				this.properties=properties;
				
				
				
			}
	
	
	//available control
	 public boolean isAvailable() {
	        return isAvailable;
	    }
	 	//borrow process
	    public void borrow(String name,String date,String borrowedId) {
	        isAvailable = false;
	        borrowedName=name;
	        borrowedDate=date;
	        this.borrowedId=borrowedId;
	       
	    }
	    //return process
	    public void returnItem() {
	        isAvailable = true;
	        this.borrowedDate=null;
	    }
	 //books   
	public void book(String type) {
		this.type=type;
		
	}
	//dvd's
	public void dvd(String runTime,String type) {
		this.runTime=runTime;
		this.type=type;
		
	}
	//magazines
	public void magazine(String type) {
		this.type=type;
	}
	//part of the displayitem
	public String toString() {
		if(itemType.equals("B")) {
			return "------ Item Information for "+ id+" ------\n"+
					"ID: "+id+" Name: "+name+" Status: "+(isAvailable? "Available" : "Borrowed Borrowed Date: "+borrowedDate+" Borrowed by: "+borrowedName)+"\n"+
					"Author: "+properties+" Genre: "+category+"\n";
		}
		else if(itemType.equals("M")) {
			return "------ Item Information for "+ id+" ------\n"+
					"ID: "+id+" Name: "+name+" Status: "+(isAvailable ? "Available" : "Borrowed Borrowed Date: "+borrowedDate+" Borrowed by: "+borrowedName)+"\n"+
					"Publisher: "+properties+" Category: "+category+"\n";
		}
		else  {
			return "------ Item Information for "+ id+" ------\n"+
					"ID: "+id+" Name: "+name+" Status: "+(isAvailable ? "Available" : "Borrowed Borrowed Date: "+borrowedDate+" Borrowed by: "+borrowedName)+"\n"+
					"Director: "+properties+" Category: "+category+" Runtime: "+runTime+"\n";
		}
	}



}
