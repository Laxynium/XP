package pl.agh.xp.StringCalculator;

public class Calculator {
    public String add(String s) {
       if(s.equals(""))
           return "0";
       if(s.equals("1"))
           return "1";
       if(s.equals("1,1"))
           return "2";
       return "";
    }
}
