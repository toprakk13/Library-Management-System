import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fileReader {
	//hold the items and users
	public List<Users> usersList = new ArrayList<>();
	public List<Items> itemsList = new ArrayList<>();
	
	
    // read the users information
    public void loadUsersFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String classType = userData[0];
                String name = userData[1];
                String id = userData[2];
                String phoneNumber = userData[3];

                Users user = new Users(classType, name, id, phoneNumber);//object from users
                

                if (classType.equals("S")) {
                    String department = userData[4];
                    String faculty = userData[5];
                    String grade = userData[6];
                    user.student(department, faculty, grade);
                } else if (classType.equals("A")) {
                    String department = userData[4];
                    String faculty = userData[5];
                    String title = userData[6];
                    user.academicMember(department, faculty, title);
                } else if (classType.equals("G")) {
                    String occupation = userData[4];
                    user.guest(occupation);
                }

                usersList.add(user);
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
    //read the items information
    public void loadItemsFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] itemData = line.split(",");
                String itemType = itemData[0];
                String id = itemData[1];
                String name = itemData[2];
                String properties=itemData[3];
                String category = itemData[4];
                

                Items item = new Items(itemType, id, name, properties,category);//object from items
                

                if (itemType.equals("B")) {
                    String type = itemData[5];
                    
                    item.book(type);
                } else if (itemType.equals("M")) {
                    String type = itemData[5];
                    
                    item.magazine(type);
                } else if (itemType.equals("D")) {
                    String runTime = itemData[5];
                    String type=itemData[6];
                    item.dvd(runTime,type);
                }

                itemsList.add(item);
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
    
    
    //read the commands
    public void processCommandsFromFile(String inputFile, BufferedWriter writer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             ) {  
        	
            String line;
            String previousDate=null;
            while ((line = reader.readLine()) != null) {
                String[] commandData = line.split(",");
                
                String types = commandData[0];//get the command type
                
                
                
                
                
               
                
                if (types.equals("borrow")) {
                	String userId = commandData[1];
                	Users currentUser=findUserById(userId);
                	String date=commandData[3];
                	String itemId=commandData[2];
                	Items currentItem=findItemById(itemId);
                	currentUser.setBorrowedate(date);
               	 if(currentUser.getPreviousDate()!=null ) {//controf if currentuser gets item 
               		
               		
                   	
                   	int difference = calculateDateDifference(currentUser.getPreviousDate(), currentUser.getBorrowedate());//check  currentuser borrow date
                           if(currentUser.getClass_type().equals("S")) {
                           	if(difference>30) {
                           		currentUser.setPenalty(currentUser.getCount()*2);
                           		for (String items:currentUser.getBorrowedItems()) {
                           			Items currentItemm=findItemById(items);
                           			currentItemm.returnItem();
                           			currentUser.decCount();
                           			currentUser.setPenalty(currentUser.getPenalty()+2);
                           		}
                           		currentUser.clearBorrowedItems();
                           		
                           		
                           		
                           	}
                           }
                           if(currentUser.getClass_type().equals("A")) {
                           	if(difference>15) {
                           		
                           		for (String items:currentUser.getBorrowedItems()) {
                           			Items currentItemm=findItemById(items);
                           			currentItemm.returnItem();
                           			currentUser.decCount();
                           			currentUser.setPenalty(currentUser.getPenalty()+2);
                           		}
                           		currentUser.clearBorrowedItems();
                           		
                           		
                           		
                           	}
                           }
                           if(currentUser.getClass_type().equals("G")) {
                           	if(difference>7) {
                           		
                           		for (String items:currentUser.getBorrowedItems()) {
                           			Items currentItemm=findItemById(items);
                           			currentItemm.returnItem();
                           			currentUser.decCount();
                           			currentUser.setPenalty(currentUser.getPenalty()+2);
                           		}
                           		currentUser.clearBorrowedItems();
                           		
                           		
                           		
                           	}
                           }
                           
                   }
                   	currentUser.setPreviousDate(date);
                	
                	
                	
                
                	if(currentItem.getBorrowedDate()!=null) {//control if currentitem borrowed by user 
                		Users penaltyUser=findUserById(currentItem.getBorrowedId());
                		
                    		
                		
                    		
                		switch(penaltyUser.getClass_type()) {
                		
                		case "S":
                			int itemDifference=calculateDateDifference(currentItem.getBorrowedDate(),date);//check currentitem borrow date
                			if(itemDifference>=30) {
                				penaltyUser.setPenalty(penaltyUser.getPenalty()+penaltyUser.getCount()*2);
                				for(String item:penaltyUser.getBorrowedItems()) {
                					Items itemm=findItemById(item);
                    				int fark=calculateDateDifference(itemm.getBorrowedDate(),date);
                    				if(fark>=30) {
                    				itemm.returnItem();
                    				
                    				penaltyUser.decCount();
                    				}
                				}penaltyUser.clearBorrowedItems();
                    			
                			}		
                					
                    			break;
                    			
                				
                    			
                    			
                    			
                		case "A":
                			int itemmDifference=calculateDateDifference(currentItem.getBorrowedDate(),date);//check currentitem borrow date
                			if(itemmDifference>=15) {
                				
                				for(String item:penaltyUser.getBorrowedItems()) {
                					Items itemm=findItemById(item);
                    				int fark=calculateDateDifference(itemm.getBorrowedDate(),date);
                    				if(fark>=15) {
                    				itemm.returnItem();
                    				
                    				penaltyUser.decCount();
                    				penaltyUser.setPenalty(penaltyUser.getPenalty()+2);
                    				}
                				}penaltyUser.clearBorrowedItems();
                    			
                    		}
                			break;
                		case "G":
                			int itemmmDifference=calculateDateDifference(currentItem.getBorrowedDate(),date);//check currentitem borrow date
                			if(itemmmDifference>=7) {
                				penaltyUser.setPenalty(penaltyUser.getPenalty()+penaltyUser.getCount()*2);
                				for(String item:penaltyUser.getBorrowedItems()) {
                					Items itemm=findItemById(item);
                    				int fark=calculateDateDifference(itemm.getBorrowedDate(),date);
                    				if(fark>=7) {
                    				itemm.returnItem();
                    				
                    				penaltyUser.decCount();
                    				}
                				}penaltyUser.clearBorrowedItems();
                    			
                    		}
                    			
                    		break;
                			
                			
                		}
                	}
                        
                    switch(currentUser.getClass_type()) {
                    case"S":
                    	
                    	
                    	if (currentUser.getCount()>=5) {//maxitem control
                    		 writer.write(currentUser.getName()+" cannot borrow "+currentItem.getName()+ ", since the borrow limit has been reached!"+"\n");
                    	}
                    	else if(currentUser.getPenalty()>=6) {//penalty control
                    		writer.write(currentUser.getName()+ " cannot borrow "+currentItem.getName()+", you must first pay the penalty amount! "+currentUser.getPenalty()+"$"+"\n");
                    	}
                    	else if(currentItem.getType().equals("reference")) {//item type control
            				writer.write (currentUser.getName()+" cannot borrow reference item!"+"\n");
                    	}
                    	else if(!currentItem.isAvailable()) {//item available control
                    		writer.write(currentUser.getName()+" cannot borrow "+currentItem.getName()+", it is not available!"+"\n");
                    	}
                    	else {//borow process
                    		currentItem.borrow(currentUser.getName(), date,currentUser.getId());
                    		currentUser.setBorrowedate(date);
                    		currentUser.borrowItem(currentItem.getId());
                    		currentUser.incCount();
                    		writer.write(currentUser.getName()+" successfully borrowed! "+currentItem.getName()+"\n");
                    	}
                    	break;
                    case"A":
                    	if (currentUser.getCount()>=3) {//maxitem control
                    		 writer.write(currentUser.getName()+" cannot borrow "+currentItem.getName()+ ", since the borrow limit has been reached!"+"\n");
                    	}
                    	else if(currentUser.getPenalty()>=6) {//penalty control
                    		writer.write(currentUser.getName()+ " cannot borrow "+currentItem.getName()+", you must first pay the penalty amount! "+currentUser.getPenalty()+"$"+"\n");
                    	}
                    	else if(!currentItem.isAvailable()) {//item available control
                    		writer.write(currentUser.getName()+" cannot borrow "+currentItem.getName()+", it is not available!"+"\n");
                    	}
                    	else {//borrow process
                    		currentItem.borrow(currentUser.getName(), date,currentUser.getId());
                    		currentUser.setBorrowedate(date);
                    		currentUser.borrowItem(currentItem.getId());
                    		currentUser.incCount();
                    		writer.write(currentUser.getName()+" successfully borrowed! "+currentItem.getName()+"\n");
                    	}
                    	break;
                    case"G":
                    	if (currentUser.getCount()>=1) {//maxitem control
                    		 writer.write(currentUser.getName()+" cannot borrow "+currentItem.getName()+ ", since the borrow limit has been reached!"+"\n");
                    	}
                    	else if(currentUser.getPenalty()>=6) {//penalty control
                    		writer.write(currentUser.getName()+ " cannot borrow "+currentItem.getName()+", you must first pay the penalty amount! "+currentUser.getPenalty()+"$"+"\n");
                    	}
                    	else if(currentItem.getType().equals("rare")||currentItem.getType().equals("limited")) {//item type control
            				writer.write (currentUser.getName()+" cannot borrow "+currentItem.getType()+ " item!"+"\n");
                    	}
                    	else if(!currentItem.isAvailable()) {//item available control
                    		writer.write(currentUser.getName()+" cannot borrow "+currentItem.getName()+", it is not available!"+"\n");
                    	}
                    	else {//borrow process
                    		currentItem.borrow(currentUser.getName(), date,currentUser.getId());
                    		currentUser.setBorrowedate(date);
                    		currentUser.borrowItem(currentItem.getId());
                    		currentUser.incCount();
                    		writer.write(currentUser.getName()+" successfully borrowed! "+currentItem.getName()+"\n");
                    	}
                    	break;
                    	
                    }
                    
                    
                      
                 }
                //return process
                else if(types.equals("return")) {
                	
                	String userId = commandData[1];
                	Users currentUser=findUserById(userId);
                	String itemId=commandData[2];
                	Items currentItem=findItemById(itemId);
                	if(!currentItem.isAvailable()) {
                	currentItem.returnItem();
                	currentUser.decCount();
                	currentUser.returnItem(currentItem.getId());
                	
                	writer.write(currentUser.getName()+" successfully returned "+currentItem.getName()+"\n");
                	}
                }
                //pay process
                else if(types.equals("pay")) {
                	String userId = commandData[1];
                	Users currentUser=findUserById(userId);
                	currentUser.setPenalty(0);
                	writer.write(currentUser.getName()+" has paid penalty\n");
                }
                
                

                
               
                

                
               
                

                
                
            }
        } catch (IOException e) {
            System.out.println("Komut dosyası okuma hatası: " + e.getMessage());
        }
    }
    //find the user matching with userid 
    private Users findUserById(String userId) {
        for (Users user : usersList) {
            if (user.getId().equals(userId)) {
                return user; 
            }
        }
        return null; 
    }
    //find the item matching with itemid
    private Items findItemById(String itemId) {
        for (Items item : itemsList) {
            if (item.getId().equals(itemId)) {
                return item; 
            }
        }
        return null; 
    }
    //calculate date difference
    public int calculateDateDifference(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate d1 = LocalDate.parse(date1, formatter);
        LocalDate d2 = LocalDate.parse(date2, formatter);
        return (int) ChronoUnit.DAYS.between(d1, d2);
    }
    

    
}
