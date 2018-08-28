package starapp.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication/*标注springboot项目，若不加此注解会报错*/
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    	System.out.println("App开始启动...");
    }
}
