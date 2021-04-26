# OOP



## 객체

- 세상에 존재하는 모든 것
- 프로그래밍에서 **속성**과 **기능**을 가지는 프로그램
- 예) 날씨 프로그램
  - 속성 : 온도, 습도, 미세먼지
  - 기능 : 날씨 예보



## 클래스

- 객체를 생성하기 위한 틀
- 모든 객체는 클래스로부터 생성됨
- 예) 자동차 클래스
  - 속성 : 모델명, 색상
  - 기능 : 배기량
  - 해당 클래스를 사용해서
    - 그랜저, 검은색
    - 2000 cc
    - 이런식으로 여러가지 객체를 만들 수 있다
- 객체의 부모역할



## 클래스의 구성 요소

- **속성(멤버 변수)**
- **기능(메서드)**

- 예) 자전거 클래스
  - 속성(멤버 변수)
    - 안장, 핸들, 바구니, 기어, 페달, 바퀴
  - 기능(메서드)
    - 기어 변속, 가속, 브레이크



## 객체지향 프로그래밍을 지향하는 이유

- 모듈화
- 언어의 확장성 증가
- ...



## 클래스 제작

- 클래스는 멤버 변수(속성). 메서드(기능), 생성자 등으로 구성

```java
public class Grandeur { //클래스 이름 : 일반적으로 첫글자는 대문자로 한다
    
    // 이하 멤버 변수 (속성)
    public String color;
    public String gear;
    public int price;
    
    // 이하 생성자
    // 클래스의 이름과 동일한 메서드
    // 반환형 없음
    public Grandeur() {
        System.out.println("grandeur constructor"); // 생성할 때 필요한 기능 입력, 없으면 안적어도 됨
    }
    
    // 이하 메서드
    public void run() {
        System.out.println("--run--"); //메서드의 기능
    }
    // public -> 접근제한자
    // void -> 반환형, void의 경우 반환값이 없다. 만약 반환값이 String이면
    // public String 메서드명 .. return 리턴값
    public void stop() {
        System.out.println("--stop--");
    }
}
```



## 객체 생성

- 클래스로 부터 `new`라는 키워드를 이용해 객체를 생성

```java
// MainClass

// 모든 실행은 MainClass에서 시작한다.
public class MainClass {
	// 객체 생성, 클래스의 생성자 실행
	// 클래스명, 객체명 = new 클래스생성자
	public static void main(String[] args) {
        Grandeur myCar1 = new Grandeur();
        // 만든 객체의 멤버 변수 설정
        myCar1.color = "red";
        myCar1.gear = "auto";
        myCar1.price = 30000000;

        // 만든 객체로 메서드 사용 가능
        myCar1.run();
        myCar1.stop();
        myCar1.info();

        // 같은 클래스로 다른 객체 생성
        Grandeur myCar2 = new Grandeur();

        myCar2.color = "blue";
        myCar2.gear = "manual";
        myCar2.price = 25000000;

        myCar2.run();
        myCar2.stop();
        myCar2.info();
    }
}
```

- 메모리 상에 두개의 객체가 만들어져 있음
- 객체의 주소를 객체명에 담아둠
  - 즉, myCar1에 객체의 주소가 들어있음
  - myCar1를 레퍼런스라고 함
  - 레퍼런스와 객체의 관계가 끊어지면 GC(Garbage Collector)가 회수





## 생성자

- 클래스에서 객체를 생성할 때 가장 먼저 호출됨
- 객체를 생성할 때 생성자에 아규먼트를 안주고 생성 가능
- 그러나 안에 인자를 줄 수도 있다.
- 단, class에서 미리 정의를 해줘야함

```java
public class Bicycle {
	
	public String color;
	public int price;
	
    public Bicycle() { // 이렇게 괄호 안에 아무것도 안넣을 수도 있고
		System.out.println("Bicycle constructor 1");
	}
	
	public Bicycle(String c, int p) { // 이렇게 생성자를 만들 때 받을 인자를 넣을 수도 있음
		System.out.println("Bicycle constructor 2");
        //this -> 내가 갖고 있는 객체를 의미
        this.color = c; // 이렇게 생성할때 받아서 멤버변수에 할당 가능
        // color = c; 이렇게 this 없이도 가능하다
        price = p; // 이렇게 생성할때 받아서 멤버변수에 할당 가능
	}
	
	public void info() {
		System.out.println("--info--");
		System.out.println("color : " + color);
		System.out.println("price : " + price);
	}
}
```

```java
public class MainClass {
	
	public static void main(String[] args) {
		
        // 객체 생성자에 이렇게 인자를 넣어서 생성하면 됨
		Bicycle myBicycle = new Bicycle("red", 100);
		
		myBicycle.info();
	}
}

// 실행결과
// Bicycle constructor 2
// --info--
// color : red
// price : 100
```

- 이름이 같은 클래스여도 인자에 따라 다르게 구분한다



- 인자를 넣어서 생성했더라도 멤버 변수로 불러와서 수정도 가능하다.

```java
public class MainClass {
	
	public static void main(String[] args) {
		
        // 객체 생성자에 이렇게 인자를 넣어서 생성하면 됨
		Bicycle myBicycle = new Bicycle("red", 100);
		
        myBicycle.color = "yellow";
        
		myBicycle.info();
	}
}

// 실행결과
// Bicycle constructor 2
// --info--
// color : yellow
// price : 100
```

