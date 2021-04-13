import java.util.ArrayList;

public class Main
{
	
public static int add(String numbers_string){
    String breaking_points =",|"; // i \n
    ArrayList<String> numbers = new ArrayList<String>();
    String number_placeholder="";
    int how_many_slashes_in_the_row = 0;
    int new_line = 0; // 1 - if \, 2 if n
    
    for (int i=0; i<numbers_string.length(); i++){ //"1,2"

        char c = numbers_string.charAt(i);
        if (how_many_slashes_in_the_row==2){
            breaking_points += c;
            how_many_slashes_in_the_row=0;
            continue;
        }

        if (c=='/'){how_many_slashes_in_the_row +=1;continue;}
        if (c=='\\'){new_line +=1; continue; }
        if (c=='n' && new_line==1){new_line +=1;continue;  }
        if (new_line==2){   number_placeholder="";  }
        
    
        if (breaking_points.indexOf(c)==-1 || new_line ==2){
            number_placeholder+=c;
            if (new_line ==2){
                new_line=0;
            }
            if (i==(numbers_string.length()-1)){
                numbers.add(number_placeholder);
            }
        }
        
        

        else{
            if (number_placeholder!=""){
                numbers.add(number_placeholder);
                number_placeholder="";
            }
        }
        
    }
    

    if (numbers.isEmpty()){
        return 0;
    }

    if (numbers.size()==1){
        return Integer.valueOf(numbers.get(0));
    }

    if (numbers.size()>1){
        ArrayList<Integer> intNumbers = new ArrayList<Integer>(numbers.size());
        for (String myInt : numbers) { 
              intNumbers.add(Integer.valueOf(myInt)); 
        }
        int sum = 0;
        for(int d : intNumbers)
            sum += d;
        return sum;

    }

    else{
        return -1;
    }
    
}

public static void main(String[] args){
    System.out.println(add("1,2,33"));
  }
}