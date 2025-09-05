public class Student{
    private String name;
    private int age;
    private String major;
    public Student(String name,int age,String major){
        this.name=name;
        this.age=age;
        this.major=major;
    }
    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public String getMajor(){
        return this.major;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setMajor(String major){
         this.major=major;
    }
    @Override
    public String toString(){
        return "Student Information : \n"
            +"Name : "+this.name+",\n"
            +"Age : "+this.age+",\n"
            +"Major : "+this.major;
    }
}