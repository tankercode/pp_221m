package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.addUserWithCar(
              new User("name1", "last_name1", "mail1@.com"),
              new Car("model1", 1));

      userService.addUserWithCar(
              new User("name2", "last_name2", "mail2@.com"),
              new Car("model2", 2));

      userService.addUserWithCar(
              new User("name3", "last_name3", "mail3@.com"),
              new Car("model3", 3));

      userService.addUserWithCar(
              new User("name4", "last_name4", "mail4@.com"),
              new Car("model4", 4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      User userWithCar = userService.getUserByCar("model4", 4);
      System.out.println(userWithCar);

      context.close();
   }
}
