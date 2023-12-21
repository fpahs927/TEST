package please.begin.controller;

import org.springframework.web.bind.annotation.*;

import please.begin.DTO.ResponseDTO;
import please.begin.DTO.ItemDTO;

@RestController
public class TestController {
    @GetMapping("/")
    public String test() {
        return "123123123123";
    }
    @GetMapping("/member") //간단한 데이터를 전달하거나 조회
    public String getcompany(@RequestParam("name")String name, @RequestParam("year") int year,
                             @RequestParam("id") int id){
        String response = "Name: " + name + ", Year: " + year + "id" +id;
        System.out.print(response);
        return response;
    }
    @GetMapping("/company/{id}")  //company/13 접근할 경우 예상
        public String getCompany(@PathVariable("id") String id){
        System.out.print(id);
        return id;
    }

    @PostMapping("/item") //데이터를 제출, 수정할 때 사용
    public ResponseDTO registerItem(@RequestBody ItemDTO item){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("good");
        return responseDTO;
    }

}
