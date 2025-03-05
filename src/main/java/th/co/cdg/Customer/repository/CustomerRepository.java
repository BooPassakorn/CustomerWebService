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

    // ---- Service สำหรับข้อมูล Customer ทั้งหมด ---- //
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

    // ---- Service สำหรับอัพเดตข้อมูล Customer ---- //
    @Transactional(Transactional.TxType.REQUIRED)
    public int updateCustomerById(Customer customer) {

        String sql = " UPDATE CUSTOMER SET ";

        if (null != customer.getName()) {
            sql += " NAME = :name ";
            if (null != customer.getSurname() || null != customer.getAddress() || null != customer.getAge() || null != customer.getTel()) {
                sql += " , ";
            }
        }

        if (null != customer.getSurname()) {
            sql += " SURNAME = :surname ";
            if (null != customer.getAddress() || null != customer.getAge() || null != customer.getTel()) {
                sql += " , ";
            }
        }

        if (null != customer.getAddress()) {
            sql += " ADDRESS = :address ";
            if (null != customer.getAge() || null != customer.getTel()) {
                sql += " , ";
            }
        }

        if (null != customer.getAge()) {
            sql += " AGE = :age ";
            if (null != customer.getTel()) {
                sql += " , ";
            }
        }

        if (null != customer.getTel()) {
            sql += " TEL = :tel ";
        }

        sql += " WHERE ID = :id ";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", customer.getId());

        if (null != customer.getName()) {
            query.setParameter("name", customer.getName());
        }

        if (null != customer.getSurname()) {
            query.setParameter("surname", customer.getSurname());
        }

        if (null != customer.getAddress()) {
            query.setParameter("address", customer.getAddress());
        }

        if (null != customer.getAge()) {
            query.setParameter("age", customer.getAge());
        }

        if (null != customer.getTel()) {
            query.setParameter("tel", customer.getTel());
        }

        return query.executeUpdate();
    }

    // ---- Service สำหรับเพิ่มข้อมูล Customer ---- //
    @Transactional(Transactional.TxType.REQUIRED)
    public int insertCustomer(Customer customer) {

        String sql = " INSERT INTO CUSTOMER " +
                " VALUES(:id, :name, :surname, :address, :age, :tel) ";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", customer.getId());
        query.setParameter("name", customer.getName());
        query.setParameter("surname", customer.getSurname());
        query.setParameter("address", customer.getAddress());
        query.setParameter("age", customer.getAge());
        query.setParameter("tel", customer.getTel());

        return query.executeUpdate();

    }

    // ---- Service สำหรับลบข้อมูล Customer ตาม Id ---- //
    @Transactional(Transactional.TxType.REQUIRED)
    public int deleteCustomerById(Long id) {

        String sql = "DELETE FROM CUSTOMER " +
                " WHERE ID = :id ";

        Query query = entityManager.createNativeQuery(sql);

        query.setParameter("id", id);

        return query.executeUpdate();
    }
}
