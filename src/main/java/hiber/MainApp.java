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

      User user1 = new User("name1", "last_name1", "mail1@.com");
      Car car1 = new Car("model1", 1);
      user1.setCar(car1);

      User user2 = new User("mail2", "last_name2", "mail2@.com");
      Car car2 = new Car("model2", 2);
      user2.setCar(car2);

      User user3 = new User("mail3", "last_name3", "mail3@.com");
      Car car3 = new Car("model3", 3);
      user3.setCar(car3);

      User user4 = new User("mail4", "last_name4", "mail4@.com");
      Car car4 = new Car("model4", 4);
      user4.setCar(car4);

      userService.addUserWithCar(user1, car1);
      userService.addUserWithCar(user2, car2);
      userService.addUserWithCar(user3, car3);
      userService.addUserWithCar(user4, car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user);
      }

      User userWithCar = userService.getUserByCar("model4", 4);
      System.out.println(userWithCar);

      context.close();
   }
}
