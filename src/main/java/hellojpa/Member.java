package hellojpa;

import javax.persistence.*;

//jpa 사용한다고 인식
//기본값: 클래스명 그대로 사용
@Entity
public class Member {

//    //PK 명시
//    @Id
//    private Long id;

    //직접 기본키를 할당하여 세팅
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    //기본 생성자가 필요하다.
    public Member(){

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
