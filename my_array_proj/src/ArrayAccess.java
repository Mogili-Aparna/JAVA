public class ArrayAccess {
    public static void main(String s[]) {
        int years[] = {2020,2021,2022,2023,2024,2025};
        System.out.println(years[0]);
        System.out.println(years[1]);
        System.out.println(years[2]);
        System.out.println(years[3]);
        System.out.println(years[4]);
        System.out.println(years[5]);
        int years1[] = new int[6];
        years1[1] = 2021;
        years1[3] = 2023;
        System.out.println(years1[0]);
        System.out.println(years1[1]);
        System.out.println(years1[2]);
        System.out.println(years1[3]);
        System.out.println(years1[4]);
        System.out.println(years1[5]);
        int years2[] = new int[6];
        years2[0] = 2020;
        years2[1] = 2021;
        years2[2] = 2022;
        years2[3] = 2023;
        years2[4] = 2024;
        years2[5] = 2025;
        int count_years = years2.length;
        System.out.println("the length of the array is " + count_years);
        for (int i=0; i<count_years; i++) {
            System.out.println(years2[i]);
        }
        int num_args = s.length;
        System.out.println("the length of the array is " + num_args);
        for (int i=0; i<num_args; i++) {
            System.out.println(s[i]);
        }
        String[] arr = {"Mon","Tues","Wed","Thru","Fri",
    "Sat","Sun","Week","Weekend","days"};
        for(String ele : arr){
            System.out.println(ele);
        }
        int j = 0;
        while(j<arr.length){
            System.out.println(arr[j++]);
        }
        int k =0;
        do{
            System.out.println(arr[k++]);
        }while(k<arr.length);
        String str = "Happy birthday to me!";
        char[] charArr = str.toCharArray();
        for(char ch : charArr){
            System.out.println(ch);
        }
    }
}