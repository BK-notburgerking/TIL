# Collections

- 배열과 같이 자료를 효율적으로 관리하기 위한 방법



## List

- List는 인터페이스임
- 이를 구현한 클래스는 인덱스를 이용해서 데이터를 관리
- 데이터 중복이 가능
- List
  - Vector
  - ArrayList
  - LinkedList

```java
package testPJT2;

import java.util.ArrayList;

public class MainClass {
	
	public static void main(String[] args) {
		
		ArrayList<String> list = new ArrayList<String>();
		
		// 데이터 추가
		list.add("Hello");
		list.add("Java");
		list.add("World");
		
		// 특정 인덱스에 데이터 삽입
		list.add(2, "this is index 2");
		
		// 특정 인덱스의 데이터 수정
		list.set(0, "update");
		
		// 데이터 추출
		String str = list.get(1);
		
		// 데이터 제거
		list.remove(1);
		
		// 데이터 전체 제거
        // 객체는 살아 있음!! 안에 데이터만 제거
		list.clear();
		
		// 데이터 유무
		boolean b = list.isEmpty();
		
	}
}
```



## Map

- HashMap으로 구현
- key : value
- 파이썬의 딕셔너리

```java
package testPJT2;

import java.util.HashMap;

public class MainClass {
	
	public static void main(String[] args) {
		
		// HashMap 객체 생성
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		// 데이터 추가
		map.put(5, "hello");
		map.put(1, "java");
		map.put(3, "world");
		
		System.out.println(map);
		// {1=java, 3=world, 5=hello}
		// 중괄호 안에 = 으로 키 밸류를 묶음
		
		// 해시맵의 크기
		System.out.println(map.size());
		
		// 기존에 값이 있는 키값에 다시 밸류를 할당하면 밸류 데이터가 교체됨
		map.put(1, "Python");
		System.out.println(map);
		// {1=Python, 3=world, 5=hello}
		
		// 데이터 추출
		String str = map.get(5);
		System.out.println(str);
		// hello
		
		// 데이터 제거
		map.remove(1);
		
		// 특정 데이터 포함 유무 (해당 키값이 존재하는지?)
		boolean b = map.containsKey(5);
		System.out.println(b);
		// true
		
		// 특정 데이터 포함 유무 (해당 밸류 값이 존재하는지?)
		boolean b2 = map.containsValue("hello");
		System.out.println(b2);
		// true
		
		// 데이터 전체 제거
		map.clear();
		
		// 데이터 유무
		boolean b3 = map.isEmpty();
		System.out.println(b3);
		// true
		
	}
	
}
```

