package th.co.cdg.Customer.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.co.cdg.Customer.model.Customer;
import th.co.cdg.Customer.repository.CustomerRepository;

import java.util.ArrayList;

@RestController
@RequestMapping("/customer/api/")
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    // ---- Service สำหรับข้อมูล Customer ทั้งหมด ---- //
    @GetMapping(value = "get-all-customer")
    public ResponseEntity<ArrayList<Customer>> getAllCustomerController() {
        return ResponseEntity
                .ok()
                .body(customerRepository.queryAllCustomer());
    }

    // ---- Service สำหรับข้อมูลตาม Id ---- //
    // ---- Service สำหรับเพิ่มข้อมูล Customer ---- //
    // ---- Service สำหรับอัพเดตข้อมูล Customer ---- //
    // ---- Service สำหรับลบข้อมูล Customer ตาม Id ---- //
}
