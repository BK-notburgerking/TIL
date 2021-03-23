# Hellojava 출력하기

- 왜 공부하는가?
  - 개인적인 이유 : 다른 언어에 대한 호기심
  - 현실적인 이유 : 파이썬보단 자바를 사용하고 채용시 우대하는 기업이 많음
- 계획 : Java의 문법과 OOP에 대해 학습하고 싸피 자바반의 Spring수업을 다시보기로 보며 독학

- 수업 : 인프런 ( 자바 프로그래밍 입문 강좌 (renew ver.) - [초보부터 개발자 취업까지!!](https://www.inflearn.com/course/%EC%8B%A4%EC%A0%84-%EC%9E%90%EB%B0%94_java-renew/dashboard) )

## 순서

- java project

  - 두개의 폴더 생성
  - src 폴더
    - `source`
    - 코드들이 저장될 폴더
  - bin 폴더
    - `binary`
    - 컴파일된 파일이 저장될 폴더

- src 폴더에 class 생성

  - class name 설정

- main method 생성

  - 수많은 파일, 기능 중 main method를 가장 먼저 실행함
  - `public static void main(String[] args) { }`
  - main 입력 후 ctrl + spacebar 하면 mainmethod가 있음 이걸로 자동완성 가능

- `System.out.println("Hello world");`

  - sysout 입력 후 자동완성 가능

  - ```java
    package hellojava;
    
    public class MainClass {
    	
    	public static void main(String[] args) {
    		
    		System.out.println("Hello world");
    		
    	}
    	
    }
    ```

- 저장 시 컴파일이 됨
  - bin 파일에서 .class파일 확인 가능

- 실행



