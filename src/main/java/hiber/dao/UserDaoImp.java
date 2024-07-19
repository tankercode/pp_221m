package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addUserWithCar(User user, Car car) {
      sessionFactory.getCurrentSession().save(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public User getUserByCar(String model, int series) {
      String hql = "Select user from User user join user.car car where car.model = (:carmodel) and car.series = (:carseries)";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("carmodel", model);
      query.setParameter("carseries", series);
      List<User> users = query.getResultList();


      if (users.isEmpty()) {
         return null;
      } else {
         return users.get(0);
      }
   }


   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
