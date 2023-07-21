package restservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import restservice.demo.entity.Address;
import restservice.demo.repository.AddressRepository;
import restservice.demo.dto.AddressDto;

@RestController
@RequestMapping("/api")
public class MyController {
    private final AddressRepository addressRepository;

    @Autowired
    public MyController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @PostMapping("/address")
    public ResponseEntity<String> saveAddress(@RequestBody AddressDto addressDto) {

        try {
            Address addressEntity = new Address();
            addressEntity.setStreet(addressDto.getStreet());
            addressEntity.setCity(addressDto.getCity());
            addressEntity.setPostalCode(addressDto.getPostalCode());


            Address savedAddress = addressRepository.save(addressEntity);

            String responseMessage = "Data accepted:\n"
                    + "Street: " + savedAddress.getStreet() + "\n"
                    + "City: " + savedAddress.getCity() + "\n"
                    + "Postal Code: " + savedAddress.getPostalCode();
            return ResponseEntity.ok(responseMessage);


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save address");
        }
    }

}

