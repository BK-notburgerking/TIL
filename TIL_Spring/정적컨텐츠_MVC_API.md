# 정적 컨텐츠

- [스프링 부트 정적 컨텐츠]([Spring Boot Features](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content))
- /resources/static 경로에 html 파일생성
- 스프링부트 실행 후 해당 파일명과 같은 url로 접근 가능



## 정적 컨텐츠의 실행 순서

- 웹브라우저에서 url입력 (정적컨텐츠의 접근 url)
- 내장톰켓서버를 통해 스프링 컨테이너에 접근
- 관련 컨트롤러가 있는지 확인
  - 즉, 컨트롤러가 먼저 우선순서가 있음
- 없으면 `resources/static/파일명`을 찾음
- 찾아서 해당 파일을 반환



---



# MVC와 템플릿 엔진

- MVC
  - Model, View, Controller
  - 장고의 MTV와 대응(Model, Template, View)
    - View의 이름이 겹치나 기능이 다른 것 주의!

```java
package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello-mvc")
    // @RequestParam() 으로 파라미터를 외부에서 메서드로 받을 수 있음!
    // required = false로 해주면 파라미터를 입력하지 않아도 에러가 발생하지 않음
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
}
```

```html
<html xmlns:th="http://www.thymeleaf.org">
<body>
<!-- 데이터가 없으면 즉, name이 안들어오면 p태그 안의 텍스트가 출력-->
<!-- 데이터가 있으면 th:text=""에 해당하는 텍스트가 출력-->
<p th:text="'hello ' + ${name}">hello! empty</p>
</body>
</html>
```

- 접근시 `/hello-template?name=spring` 이렇게 파라미터로 데이터를 넘겨주면 됨!



## MVC, 템플릿 엔진 실행 순서

- 웹브라우저에서 url로 접근
- 톰캣 내장서버를 거침
- 스프링은 매칭되는 Controller를 찾음
- 메서드의 return을 통해 찾을 템플릿의 이름과 넘겨줄 모델(데이터를 담고있는)을 viewResolver로 넘겨줌
- viewResolver가 templates/return된이름.html 파일을 찾음
- Thymeleaf 템플릿 엔진에서 렌더링, 웹브라우저에 반환



---



# API

- 뷰 없이 데이터를 그대로 클라이언트에게 리턴 가능

```java
    @GetMapping("hello-string")
    // html의 바디태그가 아니라
    // http의 헤더-바디 할때 바디부를 의미, 해당 바디에 데이터를 직접 넣어주겠다라는 의미
    @ResponseBody
    public String helloString(@RequestParam(value = "name") String name) {
        // view 없이 데이터를 그대로 클라이언트에게 리턴해줌
        return "hello " + name;
    }
```

- 그러나 이런식으로 할 일은 거의 없고 아래부터가 진짜임

```java
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
```

- url을 맞춰서 접근해보면 `{"name":"입력한데이터"}` 를 보여줌
- 즉 JSON형식으로 전달



## `@ResponseBody`의 사용원리

- 웹브라우저에서 url입력
- 톰캣 내장 서버에서 스프링으로 알려줌
- 해당하는 컨트롤러를 찾음
- 근데 해당 컨트롤러에 `@ResponseBody` 어노테이션이 붙어있음
- 그러면 http응답에 데이터를 그대로 넘기게 동작
  - HttpMessageConverter가 동작
  - 단순 문자면 StringConverter
    - 데이터를 응답
  - 객체면 JsonConverter
    - JSON형식으로 데이터를 만들어 준 후 응답

---

- 다시정리
- `@ResponseBody`를 사용
  - HTTP의 BODY에 문자 내용을 직접 반환
  - `viewResolver`대신 `HttpMessageConverter`가 동작
  - 기본 문자 처리
    - `StringHttpMessageConverter`
  - 기본 객체 처리
    - `MappingJackson2HttpMessageConverter`
  - byte 처리 등 기타 여러 컨버터들이 기본적으로 등록되어 있음
- 어떤 `HttpMessageConverter`가 선택되는가?
  - 클라이언트의 HTTP Accept 헤더
    - 클라이언트가 어떤 포맷으로 데이터를 받고싶은지에 대한 데이터가 남겨 있음
    - 만약 내용이 있으면 원하는 데이터 타입의 컨버터를 동작시킴
    - 없으면...
    - 서버 컨트롤러 반환타입
    - 이 둘의 정보를 조합해서 선택
