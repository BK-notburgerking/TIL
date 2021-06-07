# 패키지와 static

- 클래스 파일을 효율적으로 관리하기 위한 방법
- 객체 간의 속성 또는 기능을 공유하는 방법



## 패키지

- java 프로그램은 많은 클래스로 구성
- 이러한 클래스를 폴더 형식으로 관리하는 것을 패키지라 함
- 패키지 이름 결정 요령
  - 패키지 이름은 패키지에 속해 있는 클래스가 최대한 다른 클래스와 중복되는 것을 방지하도록 만듬
  - 패키지 이름은 일반적으로 도메인을 거꾸로 이용
    - 큰 규모 프로젝트 시
    - 패키지를 재사용 할 경우 패키지 이름이 중복될 경우가 발생한다.
    - 유니크한 이름을 사용하기 위함
      - 예) `kr.프로젝트이름.회사명`
  - 개발 중에 패키지의 이름과 구조는 변경 가능
  - 패키지 이름만 보고도 해당 패키지 안에 있는 클래스가 어떤 속성과 기능을 가지고 있는 예상이 될 수 있도록 이름을 만듬
- 패키지 생성시 `.`을 통해 트리로 관리 가능
  - 패키지 이름을 com.java.employee로 만들 경우
    - src/com/java/employee 폴더생성
  - com.java.salary을 만들 경우
    - src/com/java/salary 폴더 생성

- 패키지 안에 클래스 존재
  - 클래스를 만들면 클래스 파일 최상단에 클래스가 속해있는 패키지의 이름 명시
  - `package com.java.employee;`
  - 그 이후 클래스 이름 명시
  - `public class Employee{}`



## import

- 다른 패키지에 있는 클래스를 사용하기 위해 사용
- `import 패키지명.클래스명;`
  - 이후 해당 패키지의 클래스 안에 있는 메서드등을 사용 가능
- `import 패키지명.*;`
  - 해당 패키지안의 모든 클래스 import
- import를 쓰지 않고 객체 생성시 풀네임을 다 작성할 수도 있다.
  - 근데 굳이?



## static

- 클래스의 속성과 메서드에 static 키워드를 사용하면 어디서나 속성과 메서드를 공유 할 수 있음
- 같은 클래스로 부터 생성된 객체들 끼리 데이터를 static 키워드를 통해 공유 가능
- 단, 너무 많은 데이터를 static으로 처리하면 메모리에 부하가 발생함
  - 적절히 사용해야 함

```java
// MainClass

package com.java.main;

import com.java.employeeBank.EmployeeBank;

public class MainClass {

	public static void main(String[] args) {
		
		EmployeeBank parkBank = new EmployeeBank("박찬호");
		parkBank.saveMoney(100);
		
		EmployeeBank leeBank = new EmployeeBank("이승엽");
		leeBank.saveMoney(300);
    // 원래대로라면 각각의 객체는 독립적이기 때문에 amount가 100, 300 이어야 함
	// 그러나 해당 클래스를 보면 amount객체에 달린 static 키워드 때문에 EmployeeBank의 amount 변수의 값은 400이 된다.
	}
	
}
```

```java
// EmployeeBank

package com.java.employeeBank;

public class EmployeeBank {

	String name;
	static int amount = 0;
    // static이라는 키워드를 붙여서 해당 변수를 객체간에 공유가능하게 만들 수 있음
	
	
	public EmployeeBank(String name) {
		this.name = name;
	}
	
	public void saveMoney(int money) {
		
		amount += money;
		System.out.println("amount : " + amount);
		
	}
```

