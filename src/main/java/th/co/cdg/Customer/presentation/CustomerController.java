package th.co.cdg.Customer.presentation;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping(value = "add-customer")
    public ResponseEntity<String> addCustomerController(@RequestBody Customer customer) {
        try {
            int result = customerRepository.insertCustomer(customer);

            if (result != 0) {
                return ResponseEntity
                        .ok()
                        .body("Add new customer successfully");
            } else {
                return ResponseEntity
                        .badRequest()
                        .body("Can't add new customer");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body("Can't add new customer cause SQL problem.");
        }
    }

    // ---- Service สำหรับอัพเดตข้อมูล Customer ---- //

    // ---- Service สำหรับลบข้อมูล Customer ตาม Id ---- //
    @DeleteMapping(value = "delete-customer/{id}")
    public ResponseEntity<String> deleteCustomerController(@PathVariable(name = "id") Long id) {
        int result = customerRepository.deleteCustomerById(id);

        if (result != 0) {
            return ResponseEntity
                    .ok()
                    .body("Delete customer successfully");
        } else {
            return ResponseEntity
                    .badRequest()
                    .body("Can't delete customer");
        }
    }

}
