// StudentRegistry class to test the Student class
public class StudentRegistry {
    public static void main(String[] args) {
        // Step 1: Create two instances of the Student class
        Student yogeshwar = new Student();
        Student archana = new Student();
        
        // Step 2: Use setter methods to set values for all attributes of first student
        // Example values: ID "S001", name "John Doe", grade 85.5, active true
        yogeshwar.setName("Yogeshwar");
        yogeshwar.setGrade(79);
        yogeshwar.setIsActive(true);
        yogeshwar.setStudentId("YOGI0803");
        
        // Step 3: Set values for second student
        // Example values: ID "S002", name "Jane Smith", grade 92.0, active true
        archana.setName("Archana");
        archana.setIsActive(true);
        archana.setGrade(92);
        archana.setStudentId("ARU2510");
        
        // Step 4: Display details of both students
        yogeshwar.getStudentDetails();
        archana.getStudentDetails();
        // Step 5: Compare the grades of the two students and print who has the higher grade
        // Hint: Create a separate method for this comparison
        System.out.println("Student with Highest Marks this year : ");
        compareStudents(yogeshwar,archana).getStudentDetails();
        
        // Step 6: Test the letter grade method for both students
        System.out.println(yogeshwar.getName()+" is awarded grade of "+yogeshwar.getLetterGrade()+" based on his performance this academic year");
        System.out.println(archana.getName()+" is awarded grade of "+archana.getLetterGrade()+" based on her performance this academic year");
        
        // Step 7: Test the passing status method for both students
        System.out.println(yogeshwar.getName()+" has "+(yogeshwar.isPass()?" passed ":" failed")+" his examinations.");
        System.out.println(archana.getName()+" has "+(archana.isPass()?" passed ":" failed")+" her examinations.");
        // Step 8: Change one student to inactive and display the updated information
        archana.setIsActive(false);
        archana.getStudentDetails();   
    }
    
    // Step 9: Create a static method to compare two students' grades and return the student with the higher grade
    // Hint: Take two Student objects as parameters
    private static Student compareStudents(Student student1 ,Student student2){
        return student1.getGrade() > student2.getGrade()?student1:student2;
    }
}
