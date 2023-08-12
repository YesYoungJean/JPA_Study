# JPA에 대해 알아보자
## ▶ JPA 개념
    ● Java 진영에서 ORM (Object-Relational Mapping) 기술 표준으로 사용되는 인터페이스 모음
    ● JPA를 구현한 대표적인 오픈소스로는 Hibernate가 존재
![image](https://github.com/YesYoungJean/JPA_Study/assets/107979338/c9035694-82b4-46ad-9496-91605356ba35)


### ※ ORM (Object-Relational Mapping)
    ● 객체와 관계형 데이터베이스의 데이터를 자동으로 Mapping 해주는 것을 말함
    ● 객체지향 프로그래밍 - 클래스 사용 / 관계형 데이터베이스 - 테이블 사용
      객체 모델과 관계형 모델 간에 존재하는 패러다임 불일치를 ORM으로 객체관의 관계를 바탕으로 SQL을 자동으로 생성하여 해결
<br/>

## ▶ JPA 동작
    ● 애플리케이션과 JDBC 사이에서 동작
    ● JPA 사용 시, JPA 내부에서 JDBC API를 사용하여 SQL 호출 및 DB와 통신 실행
![image](https://github.com/YesYoungJean/JPA_Study/assets/107979338/cb25ed8a-9a59-42e5-85be-94a201d2d242)

### ※ JDBC (Java Database Connectivity)
    ● Java가 DB에 연결되어 데이터를 주고 받을 수 있게 해주는 프로그래밍 인터페이
<br/>

## ▶ JPA 사용 이유
    ● SQL 중심 개발에서 객체중심적 개발이 가능
    ● 생산성 증가
     - DDL문 자동 생성
     - 간단한 메서드를 통한 CRUD 가능
     - SQL을 작성하고 JDBC API를 사용하는 반복적인 일을 대신함
    ● 유지보수 용이
     - 기존: 필드 변경 시 관련 SQL 모두 수정
     - JPA : 필드 추가 시 SQL은 JPA가 처리
    ● Object와 RDB 간 패러다임 불일치 해결
<br/>

## ▶ JPA 장/단점
    ● 장점
     - SQL문이 아닌 메서드로 DB 조작하기에, 개발자는 비즈니스 로직을 구성하는데만 집중 가능
     - 객체지향적 코드 작성 가능
     - 유지보수 및 리팩토링 유리
    ● 단점
     - 설계 오류 시 속도 저하 및 일관성을 헤치는 문제점 발생 가능
     - 복잡한 Query는 별도의 튜닝이 필요하기에 SQL문을 쓰는게 나은 경우도 발생 가능
     - 학습 비용이 비싸고 시간이 오래 걸림
     
