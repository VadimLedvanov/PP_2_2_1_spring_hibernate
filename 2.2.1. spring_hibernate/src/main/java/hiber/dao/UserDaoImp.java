package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Transactional
   @Override
   public User getUserByCar(int series, String model) {
      String hql = "SELECT u FROM User u WHERE u.id IN (SELECT c.id FROM Car c WHERE c.series = :seriesParam AND c.model = :modelParam)";
      return (User) sessionFactory.getCurrentSession()
              .createQuery(hql)
              .setParameter("seriesParam", series)
              .setParameter("modelParam", model)
              .uniqueResult();
   }

}
