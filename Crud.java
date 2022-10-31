import java.sql.*;
import java.util.*;

public class Crud {

    static final String JDBC_driver="com.mysql.jdbc.Driver";
    static final String DB_url = "jdbc:mysql://localhost:3306/db";
    static final String USER = "root";
    static final String PASS = "rishabh00";


    public static void main(String[] args) {
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con= null;        
        Scanner sc = new Scanner(System.in);

        try
        {
            Class.forName(JDBC_driver);
            con = DriverManager.getConnection(DB_url,USER,PASS);
            System.out.println("Database connected");
            System.out.flush();
        }catch(Exception e){
            System.out.println(e);
        }    
            
            while(true){
            System.out.println("\t\t Welcome to Learn_Together Coaching");   
            System.out.println(); 
            System.out.println("To insert data press 1");
			System.out.println("To show data press 2");
            System.out.println("To update data press 3");
			System.out.println("To delete data press 4");
			System.out.println("To exit press 5");
            System.out.println("Enter Your choice:");
            int x=sc.nextInt();
            System.out.println();    
                           
            switch(x){
			case 1:
            System.out.println("Enter student's details:");
            System.out.print("Registration_id: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Name : ");
            String name = sc.nextLine();
            System.out.print("Gender : ");
            String gender = sc.nextLine();
            System.out.print("Marks : ");
            int marks = sc.nextInt(); sc.nextLine(); 
            try{
                pstmt = con.prepareStatement("insert into student(id,name, gender, marks) values (?,?,?,?)");
                pstmt.setInt(1,id);
                pstmt.setString(1,name);
                pstmt.setString(3,gender);
                pstmt.setInt(4,marks);
                int i=pstmt.executeUpdate(); 
                System.out.println(i+" inserted successfully");
            }
            catch(Exception e){
                System.out.println(e.getMessage());}
            break;

			case 2: 
            System.out.print("\nEnter student's name : ");
            name = sc.nextLine();
            try{
                pstmt = con.prepareStatement("select * from student where name = ?");
                pstmt.setString(1,name);
                rs = pstmt.executeQuery();
                Display(rs);
            }
            catch(Exception e){ System.out.println(e.getMessage());}
            break;
                        case 3:
                        System.out.print("\nEnter Student's Registration_id: ");
                            int reg_id = sc.nextInt();
                            try{
                                System.out.print("Name :");
                                sc.nextLine();
                                name = sc.nextLine();
                                System.out.print("Gender :");
                                sc.nextLine();
                                gender = sc.nextLine();
                                System.out.print("Marks :");
                                marks = sc.nextInt();
                                pstmt = con.prepareStatement("update student set name = ?,gender=?,marks=? where reg_id = ?");
                                pstmt.setInt(4,reg_id);
                                pstmt.setInt(3,marks);
                                pstmt.setString(2,gender);
                                pstmt.setString(1,name);
                                int i = pstmt.executeUpdate();
                                System.out.println(i+"Updated successfully");
                            }catch(Exception e){
                                System.out.println(e.getMessage());
                            }
                        break;
                        case 4:
                            System.out.print("Enter the Name of student : ");
                            name = sc.nextLine();
                            try{
                                pstmt = con.prepareStatement("delete from student where name = ?");
                                pstmt.setString(1,name);
                                int i = pstmt.executeUpdate();
                                System.out.println(i+" Deleted");
                            }catch(Exception e){System.out.println(e.getMessage());}
                            break;
                        case 5:
                            System.out.println("\n Visit Again...");
                            break;
                        default:
                            System.out.print("Invalid choice....Enter again");
                    }

                    System.out.print("\n\nDo you want to continue to Main Menu (y/n) :");
                    if(sc.next().charAt(0) == 'n'){
                    break;
                    }
                }
                    try{
                        pstmt.close();
                        con.close();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
            }
                public static void Display(ResultSet rs){

                    System.out.flush();
                    
                    try{
                        while(rs.next()){
                            
                                int id = rs.getInt("Registration_id");
                                String name = rs.getString("name");
                                String gender = rs.getString("gender");
                                int marks = rs.getInt("marks");
                                
                                System.out.println("\n\nDetails of student : ");
                                System.out.println("Registration ID :" + id);
                                System.out.println("Name : " + name);
                                System.out.println("Gender : " + gender);
                                System.out.println("Marks : " + marks);
                        }
                    } 
        
                    catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                            
                }
        }