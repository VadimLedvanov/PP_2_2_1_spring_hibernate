package hiber.dao;


import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

   @Override
   public User getUserByCar(int series, String model) {
      String hql = "SELECT u FROM User u WHERE u.car IN (SELECT c.id FROM Car c WHERE c.series = :seriesParam AND c.model = :modelParam)";

      return (User) sessionFactory.getCurrentSession()
              .createQuery(hql)
              .setParameter("seriesParam", series)
              .setParameter("modelParam", model)
              .uniqueResult();
   }

}
