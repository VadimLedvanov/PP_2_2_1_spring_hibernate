package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.DoubleStream;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);


      UserService userService = context.getBean(UserService.class, Car.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car(555, "bmw")));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car(222, "mercedes")));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car(111, "audi")));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car(7, "lada")));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user.toString());
      }

      context.close();
   }
}
