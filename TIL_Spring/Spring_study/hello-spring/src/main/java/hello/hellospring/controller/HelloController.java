package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") // 이부분이 url이 됨 즉, /hello로 접근하면 해당 메서드를 호출
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    // @RequestParam() 으로 파라미터를 외부에서 메서드로 받을 수 있음!
    // required = false로 해주면 파라미터를 입력하지 않아도 에러가 발생하지 않음
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    // html의 바디태그가 아니라
    // http의 헤더-바디 할때 바디부를 의미, 해당 바디에 데이터를 직접 넣어주겠다라는 의미
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name) {
        // view 없이 데이터를 그대로 클라이언트에게 리턴해줌
        return "hello " + name;
    }
    // 이렇게 할일은 거의 없고 지금부터가 진짜임

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam(value = "name") String name) {
        // 2. 해당 클래스로 객체  생성
        Hello hello = new Hello();
        // 3. setter로 데이터를 넣어줌
        hello.setName(name);
        // 4. 해당 객체를 리턴
        return hello;
    }
    // 1. 클래스를 생성하고 getter와 setter만들어줌 (윈도우 단축키 컨트롤+인서트)
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}