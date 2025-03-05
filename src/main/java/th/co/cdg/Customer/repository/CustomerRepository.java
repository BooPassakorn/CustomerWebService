package th.co.cdg.Customer.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import th.co.cdg.Customer.model.Customer;

import java.util.ArrayList;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(Transactional.TxType.SUPPORTS)
    public ArrayList<Customer> queryAllCustomer() {

        String sql = " SELECT *  " +
                " FROM CUSTOMER ";

        Query query = entityManager.createNativeQuery(sql);

        ArrayList<Object[]> resultList = (ArrayList<Object[]>) query.getResultList();

        ArrayList<Customer> customers = new ArrayList<>();

        resultList.forEach(result -> {
            Customer customer = new Customer();
            customer.setId(((Integer) result[0]).longValue());
            customer.setName((String) result[1]);
            customer.setSurname((String) result[2]);
            customer.setAddress(result[3].toString());
            customer.setAge(((Integer) result[4]).longValue());
            customer.setTel((String) result[5]);
            customers.add(customer);
        });

        return customers;
    }
}
