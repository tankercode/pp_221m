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
   private final SessionFactory sessionFactory;
   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void addUserWithCar(User user, Car car) {
      user.setCar(car);
      sessionFactory.getCurrentSession().save(user);

   }

   @Override
   public User getUserByCar(String model, int series) {
      String hql = "Select user from User user join user.car car where car.model = (:carModel) and car.series = (:carSeries)";
      Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setParameter("carModel", model);
      query.setParameter("carSeries", series);

      return (User) query.getSingleResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
