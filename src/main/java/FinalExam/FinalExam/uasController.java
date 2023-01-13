/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalExam.FinalExam;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
@RestController
@CrossOrigin
public class uasController {
    
    Person data = new Person();
    PersonJpaController pbjp = new PersonJpaController();
    
     @RequestMapping( value = "/GET/{id}",method= RequestMethod.GET )
    public String getNameById(@PathVariable("id") int id){
        try{
            data = pbjp.findPerson(id);
        }catch (Exception e){
            
        }
        return data.getNama();
    }
    
    @RequestMapping(value = "/GET" ,method= RequestMethod.GET,consumes = APPLICATION_JSON_VALUE )
    public List<Person> getAll(){
        List<Person> dummy = new ArrayList();
        try {
            dummy = pbjp.findPersonEntities();
        } catch (Exception e) {
            dummy = List.of();
                    
        }
        return dummy;
    }
}
