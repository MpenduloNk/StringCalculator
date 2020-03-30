import java.util.Scanner;
import java.util.regex.Pattern;

public class StrCalculator {
    public String[] arr = {""};

     public int add(int number, String numStr) throws Exception{
         int total = 0;


         if (numStr.equals(null) || numStr.equals("")) {
             total = 0;
         } else {
//             int number = 0;
             switch (number){
                 case (1): case (2):  {
                     arr = numStr.split(",");
                     for (String arrObject : arr) {
                         total += Integer.parseInt(arrObject);
                     }
                     break;
                 }
                 case 3:{
                     String regex = "[,\n;]";
                     arr = numStr.split(regex);
                     for (String arrObject : arr) {
                         total += Integer.parseInt(arrObject);
                     }
                     break;
                 }
                 case (4): case (7):{
                     String fixedStr = organizeStr(numStr);
                     String del1 = "\\\\";
                     String del2 = fixedStr.substring(1, 2);

                     char[] array = fixedStr.toCharArray();
                     for (int i = 0; i < fixedStr.length(); i++) {

                         if (array[i] != ';' && array[i] != '4' && array[i] != '*'){
                             total += Integer.parseInt(String.valueOf(array[i]));
                         }
                     }
                     break;
                 }
                 case 5:{
                     int index = 0;
                     String n ="";
                     int[] arr = {0};
                     for (int i = 0; i < numStr.length(); i++) {
                         //"-1,-2,3,4"
                         n += numStr.substring(numStr.indexOf(i), numStr.indexOf(i+1));
                         if(numStr.charAt(i) == ','){
                             n = n.substring(0, n.length()-1);
                             arr[index] = Integer.parseInt(n);
                             index++;
                             n = "";
                         }
                     }

                     for (int i = 0; i < arr.length; i++) {
                         if (arr[i] < 0){
                             throw new Exception("ERROR: negatives not allowed"+arr[i]);
                         }
                     }
                 }
                 case 6:{
                     String value = organizeStr(numStr);
                     String numX = value.substring(0, value.indexOf(","));
                     String chaLeft = value.substring(value.indexOf(",")+1, value.length());
                     System.out.println("Value : "+value + " Number : "+numX+" ChaLeft : "+chaLeft);
                     for (int i = 0; i < chaLeft.length(); i++) {
                         if (Integer.parseInt(numX) >= 1000){
                             arr = chaLeft.split(";");
                             for (String x: arr) {
                                 total = Integer.parseInt(x);
                             }
                         }
                     }
                 }
             }
         }
         return total;
     }


    public String organizeStr(String numStr){
        StringBuilder builder = new StringBuilder(numStr);
        String str = "";

        builder.delete(0, numStr.indexOf("\n"));
        builder.deleteCharAt(builder.indexOf("\n"));
        str = builder.toString();

        return str;
    }

    public static void main(String[] args) throws Exception{
        StrCalculator cal = new StrCalculator();
        System.out.println(cal.organizeStr("//;\n1;2"));
        try{
            System.out.println(cal.add(6,"//;\n1000,2;3"));
        }catch (StringIndexOutOfBoundsException e){
            System.out.println(e.getLocalizedMessage());
        }



    }
}
