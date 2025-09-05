// TestEncapsulation class to test the Employee class
public class TestEncapsulation {
    public static void main(String[] args) throws CloneNotSupportedException{
        // Step 1: Create two instances of the Employee class
        // One using the parameterized constructor and one using default constructor with setters
        Employee yogeshwar = new Employee();
        yogeshwar.setName("Jaya Yogeshwar Reddy");
        yogeshwar.setAge(20);
        yogeshwar.setSalary(400000);
        Employee archana= new Employee("K Archana",18,200000);

        // Step 2: Print details of both employees
        yogeshwar.getEmpDetails();
        archana.getEmpDetails();
        // Step 3: Try setting invalid values (null name, age outside range, negative salary)
        // and see if your validation works
        try{
            yogeshwar.setAge(16);
        }
        catch(IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        try{
            String name = null;
            yogeshwar.setName(name);
        }
        catch(IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        try{
            yogeshwar.setName("");
        }
        catch(IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        try{
            archana.setSalary(-200000);
        }
        catch(IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
        // Step 4: Give both employees a 10% raise and display their details again
        yogeshwar.giveRaise(10);
        archana.giveRaise(10);
        yogeshwar.getEmpDetails();
        archana.getEmpDetails();
        // Step 5: Clone the first employee and display the cloned employee details
        // Hint: Use try-catch block to handle CloneNotSupportedException
        // Employee clonedEmployee = (Employee) employee1.clone();
        Employee yogiClone = null;
        try {
             yogiClone = (Employee) yogeshwar.clone();
        } catch (CloneNotSupportedException cnse) {
            System.out.println(cnse.getMessage());
        }
        // Step 6: Modify the original employee and verify that the clone remains unchanged
        // This demonstrates that cloning creates a separate object
        yogeshwar.setSalary(600000);
        yogeshwar.setAge(21);
        yogeshwar.getEmpDetails();
        yogiClone.getEmpDetails();
        if(yogeshwar.getSalary()!=yogiClone.getSalary() && yogeshwar.getAge()!=yogiClone.getAge()){
            System.out.println("clone remained unchanged");
        }else{
            System.out.println("clone also changed");
        }
        // Step 7: Create a method that compares the salaries of two employees
        // and returns the name of the employee with the higher salary
        // If salaries are equal, return "Equal salaries"
        System.out.println("Employee with highest salary among yogeshwar and yogi clone :"+getEmpWithHighestSalary(yogeshwar,yogiClone));
        System.out.println("Employee with highest salary among archana and yogeshwar :"+getEmpWithHighestSalary(archana,yogeshwar));
        System.out.println("Employee with highest salary among yogi clone and yogi clone2 :"+getEmpWithHighestSalary(yogiClone,(Employee)yogiClone.clone()));
    }
    private static String getEmpWithHighestSalary(Employee emp1,Employee emp2){
        if(emp1.getSalary()>emp2.getSalary()){
            return emp1.getName();
        }
        else if(emp1.getSalary()<emp2.getSalary()){
            return emp2.getName();
        }
        else{
            return "Equal salaries";
        }
    }
}
