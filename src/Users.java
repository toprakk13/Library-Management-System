import java.util.ArrayList;
import java.util.List;

public class Users {
	private String class_type;
	private String name;
	private String phone_number;
	private String id;
	private String faculty;
	private String department;
	private String title;
	private String grade;
	private String occupation;
	private int penalty;//penalty price
	private int count;//maxitem
	private String borrowedate;
	private String previousDate;
	private List<String> borrowedItems; 
	


	//getters and setters
	public List<String> getBorrowedItems() {
        return borrowedItems;
    }
	public void borrowItem(String itemId) {
        borrowedItems.add(itemId);
    }
	public void clearBorrowedItems() {
        borrowedItems.clear();
    }
	public void returnItem(String itemId) {
        borrowedItems.remove(itemId);
    }
	public String getPreviousDate() {
		return previousDate;
	}


	public void setPreviousDate(String previousDate) {
		this.previousDate = previousDate;
	}


	public String getBorrowedate() {
		return borrowedate;
	}


	public void setBorrowedate(String borrowedate) {
		this.borrowedate = borrowedate;
	}
	public int getCount() {
		return count;
	}


	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}


	public int getPenalty() {
		return penalty;
	}


	public String getClass_type() {
		return class_type;
	}


	public String getName() {
		return name;
	}


	public String getPhone_number() {
		return phone_number;
	}


	public String getId() {
		return id;
	}


	public String getFaculty() {
		return faculty;
	}


	public String getDepartment() {
		return department;
	}


	public String getTitle() {
		return title;
	}


	public String getGrade() {
		return grade;
	}


	public String getOccupation() {
		return occupation;
	}
	//maxitem decrease
	public void decCount() {
		this.count--;
	}
	//maxitem increase
	public void incCount() {
		this.count++;
	}

	//constructor
	public Users(String class_type,String name,String id,String phone_number) {

		
		this.class_type=class_type;
		this.name=name;
		this.id=id;
		this.phone_number=phone_number;
		this.borrowedItems = new ArrayList<>();
		}
	
		
	//student	
	public void student(String department,String faculty,String grade) {
		this.department=department;
		this.faculty=faculty;
		this.grade=grade;
	}
	//academicmember	
	public void academicMember(String department,String faculty,String title) {
		this.department=department;
		this.faculty=faculty;
		this.title=title;
	}
	//guest
	public void guest(String occupation) {
		this.occupation=occupation;
	}
	//part of the displayuser
	public String toString() {
		if (class_type.equals("S")) {
			return "------ User Information for "+id+" ------\n"+
					"Name: "+name+" Phone: "+ phone_number+"\n"+
					"Faculty: "+faculty+ " Department: " +department+" Grade: "+grade+"th"+"\n";
					
					
		}
		else if (class_type.equals("A")) {
			return "------ User Information for "+id+" ------\n"+
					title+" Name: "+name+" Phone: "+ phone_number+"\n"+
					"Faculty: "+faculty+ " Department: " +department+"\n";
		}
		else {
			return "------ User Information for "+id+" ------\n"+
					"Name: "+name+" Phone: "+ phone_number+"\n"+
					"Occupation: "+occupation+"\n"
					;
		}
	}

}
