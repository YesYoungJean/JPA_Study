package hellojpa;

import javax.persistence.*;

//jpa 사용한다고 인식
//기본값: 클래스명 그대로 사용
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    /**
     * JPA에게 관계를 알려줘야 함
     * 1) ManyToOne: 1개 팀에 여러 회원이 들어올 수 있음
     * 2) JoinColumn: Team 테이블에서 join할 컬럼 명시
     * */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
