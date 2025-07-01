package aff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ServletComponentScan("controllers")
@ImportResource("classpath:beans.xml")
public class Main extends SpringBootServletInitializer 
{
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) 
  {
    return builder.sources(Main.class);
  }
  public static void main(String[] poyz) 
  {
    SpringApplication.run(Main.class, poyz);
  }
}
