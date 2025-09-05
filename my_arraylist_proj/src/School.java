import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
public class School{
    public static void main(String[] args){
        ArrayList<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try{
            while(true){
                System.out.println("Welcome to school console app : \n"
                                    +"Press 1 to register student \n"
                                    +"Press 2 to list all students \n"
                                    +"Press 3 to deregister a student \n"
                                    +"Press 4 to update student details \n"
                                    +"Press 5 to sort students by age \n"
                                    +"Press any key to exit.");
                int option = Integer.parseInt(sc.nextLine());
                if(option == 1){
                    System.out.println("Enter the student name :");
                    String name = sc.nextLine();
                    if(name==null || name.isEmpty()){
                        throw new IllegalArgumentException("name should not be null or empty");
                    }
                    System.out.println("Enter the student age :");
                    int age = Integer.parseInt(sc.nextLine());
                    if(age < 0 ){
                        throw new IllegalArgumentException("age should be a positive number.");
                    }
                    System.out.println("Enter the student major :");
                    String major = sc.nextLine();
                    students.add(new Student(name,age,major));
                }
                else if(option == 2){
                    for (Student student : students) {
                        System.out.println(student);
                    }
                }
                else if(option ==3){
                    System.out.println("Enter the index of the student whom you want to deregister :");
                    int index = Integer.parseInt(sc.nextLine());
                    students.remove(index);
                }
                else if(option ==4){
                    // as its practise for arralylist update- hanlding one attribute update of student object.
                    System.out.println("Enter the index of the student for whom you want to update major :");
                    int index = Integer.parseInt(sc.nextLine());
                    System.out.println("Enter the latest student major :");
                    String major = sc.nextLine();
                    Student student = students.get(index);
                    student.setMajor(major);
                    /*students.add(index,student); no need to add back to the list as we are getting 
                    the object reference and making changes to it. they will get reflected automatically 
                    as arraylist is already pointing to the reference.*/
                }
                else if(option ==5){
                    Collections.sort(students,new Comparator<Student>(){
                        @Override
                        public int compare(Student student1,Student student2){
                            return Integer.compare(student1.getAge(),student2.getAge());
                        }
                    });
                }
                else{
                    return;
                }
            }
        }
        catch(IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        sc.close();
    }
}