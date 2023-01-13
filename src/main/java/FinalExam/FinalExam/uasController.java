/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalExam.FinalExam;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
@RestController
@CrossOrigin
@RequestMapping("/data")
public class uasController {
    
    Person data = new Person();
    PersonJpaController pbjp = new PersonJpaController();
//Get Data
    @GetMapping
    public List<Person> getAll() {
        List<Person> dummy = new ArrayList<>();
        try {
            dummy = pbjp.findPersonEntities();
        } catch (Exception e) {
            dummy = List.of();
        }
        return dummy;
    }
    

    //Post Data
    @PostMapping()
    public String createData(HttpEntity<String> paket) {
        String message = "";

        try {
            String json_receive = paket.getBody();

            ObjectMapper map = new ObjectMapper();

            Person data = new Person();

            data = map.readValue(json_receive, Person.class);

            pbjp.create(data);
            message = data.getNama()+ " Data Saved";

        } catch (Exception e) {
            message = "Failed";
        }

        return message;
    }
    //Method Delete
      @PutMapping()
    public String editData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Person newdatas = new Person();

            newdatas = mapper.readValue(json_receive, Person.class);
            pbjp.edit(newdatas);
            message = newdatas.getNama()+ " has been Updated";
        } catch (Exception e) {
        }
        return message;
    }
    //Delete Method
      @DeleteMapping()
    public String deleteData(HttpEntity<String> kiriman) {
        String message = "no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();

            Person newdatas = new Person();

            newdatas = mapper.readValue(json_receive, Person.class);
            pbjp.destroy(newdatas.getId());

            message = "Data has been Deleted";
        } catch (Exception e) {
        }
        return message;
    }

}

