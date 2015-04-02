package cn.edu.ahpu.quartz.demo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println(reverse(123456789));
    }
    
   public static String reverse(int num){
    	String result = num+"";
    	if(result.length() > 1){
    		return result.charAt(result.length() -1)+reverse(Integer.parseInt(result.substring(0,result.length()-1)));
    	}
    	return result;
    }
}
