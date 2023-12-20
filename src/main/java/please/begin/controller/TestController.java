package please.begin.controller;

import org.springframework.web.bind.annotation.*;

import please.begin.DTO.ResponseDTO;
import please.begin.DTO.itemDTO;

@RestController
public class TestController {
    @GetMapping("/")
    public String test() {
        return "123123123123";
    }
    @GetMapping("/member")
    public String getcompany(@RequestParam("name")String name, @RequestParam("year") int year){
        String response = "Name: " + name + ", Year: " + year;
        System.out.print(response);
        return response;
    }
//    @GetMapping("/company/{id}")
//    public String getCompany(@PathVariable("id") String id){
//
//    }

    @PostMapping("/item")
    public ResponseDTO registerItem(@RequestBody itemDTO item){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("good");
        return responseDTO;
    }
}
