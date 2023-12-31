package hellojpa;

import jdk.swing.interop.SwingInterOpUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class jpaMain {
    public static void main(String[] args) {
        /**
        * 1.persistence class에서 시작
        * 2.META-INF/persistence.xml 설정 정보를 읽어서
        * 3.EntityManagerFactory를 만든다
        * */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        /**
        * 1.EntityManagerFactory에서 createEntityManager()를 꺼내고
        * 2.entityManager를 꺼내고 실제 동작하는 코드를 작성함
        * */
        EntityManager em = emf.createEntityManager();

        /**
         * ★ 1.JPA에서는 트랜잭션이 중요하다.
         * */
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /**
             * 1.회원 등록
             * */
//            Member member = new Member();
//            member.setId(7L);
//            member.setName("HelloA");
//            em.persist(member);

            /**
             * 2.회원 조회
             * */
//            Member findMember = em.find(Member.class, 4L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

            
            /**
             * 3. 회원 수정
             * Java 객체에서 값만 바꿔도 DB에 UPDATE 됨.
             * 어떻게?
             * 1) JPA를 통해서 entity를 가져오면, 해당 entity는 JPA가 관리함
             * 2) 그리고 JPA가 entity가 변경됐는지 트랜잭션 commit하는 시점에 확인
             * 3) 변경된 것이 있으면 commit 직전에 update 쿼리 날리고 commit 완료함
             * */
//            findMember.setName("HelloJPA");

            /**
             * 4.회원 삭제
             * */
//            em.remove(findMember);


            /**
             * 5-1.JPQL 전체 회원 조회
             * 1) JPA 입장에서 테이블 대상으로 코드 짜지 않는다.
             * 2) Member '객체'를 대상으로 쿼리를 짠다. (Member 객체 다 가져와!)
             * 3) setFirstResult, setMaxResult 등으로 페이징 처리에 용이하다.
             * */
//            List<Member> result = em.createQuery("SELECT M FROM Member as M", Member.class)
//                    .getResultList();
//
//            for (Member member : result){
//                System.out.println("member = " + member.getId());
//                System.out.println("member = " + member.getName());
//            }

            /**
             * 6.영속-비영속 상태
             * */
            //비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("Hello_JPA");

            //여기서부터 영속상태
            //em 안에 있는 영속성 컨텍스트라는데를 통해서 member가 관리됨
            //::: after persist ::: 지나서 insert 쿼리가 실행된다. 이유는?
            //영속 상태가 된다고 바로 DB로 쿼리 날라가는 것 아니다.
            //트랜잭션을 commit하는 시점에 영속성 컨텍스트에 있는 것들이 DB에 쿼리로 날아간다!
//            System.out.println("::: before persist :::");
//            em.persist(member);
//            System.out.println("::: after persist :::");

            /**
             * 7-1.1차 캐시 체크
             * 1) find를 했는데 소스에 SELECT 쿼리가 안 나갔다.
             * 2) 바로 1차 캐시에서 먼저 조회하여 값을 얻었기 때문에.
             * */
//            Member findMember = em.find(Member.class, 101L);
//            System.out.println("findMember.id = " + findMember.getId() );
//            System.out.println("findMember.name = " + findMember.getName() );

            /**
             * 7-2.영속 엔티티의 동일성 보장 (by 1차 캐시)
             * */
//            Member a = em.find(Member.class, 101L);
//            Member b = em.find(Member.class, 101L);
//            //>> true
//            System.out.println("result = " + (a==b));

            /**
             * 7-3.엔티티 등록 트랜잭션을 지원하는 쓰기 지원
             * 1) JPA는 insert문을 '쓰기 지원 저장소'에 쌓는다
             * 2) 이후 트랜잭션을 commit 하는 순간, 쓰기 지원 저장소 내 쿼리를 DB로 flush하고
             * 3) 이후 실제 DB 트랜잭션이 commit된다.
             * */
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            //여기서는 영속성 컨텍스트에 쌓임
//            em.persist(member1);
//            em.persist(member2);
//            //하기 출력 이후에 쿼리가 나가는 것을 확인할 수 있다.
//            System.out.println("==========================");

            /**
             * 7-4.변경 감지
             * 1) JPA를 통해서 entity를 가져오면, 해당 entity는 JPA가 관리함
             * 2) 그리고 JPA가 entity가 변경됐는지 트랜잭션 commit하는 시점에 확인
             * 3) 변경된 것이 있으면 commit 직전에 update 쿼리 날리고 commit 완료함
             * */
//            Member member = em.find(Member.class, 150L);
//            //이렇게만 해도 무조건 update 쿼리가 나간다.
//            member.setName("ZZZZZ");

            /**
             * 8.플러시
             * 1) 직접 호출, 자동 호출 존재
             * */
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            //직접 호출
//            em.flush();
//            System.out.println("================");

            /**
             * 9.준영속
             * */
//            Member member = em.find(Member.class, 150L);
//            member.setName("AAAAA");
//
//            //영속성 컨텍스트에서 관리하기 싫을 경우 준영속 상태로 만든다.
//            //entity를 수정했지만 영속성 컨텍스트에서 준영속 시켰기에 update가 발생하지 않는다.
//            //(직접 쓸 일은 거의 없음)
//            em.detach(member);
//            System.out.println("================");

            /**
             * 10.기본 키 매핑
             * */
//            Member member = new Member();
//            //DB가 알아서 넣어줌
////            member.setId("A");
//            member.setUsername("흥민");
//            em.persist(member);
//
//            //최종 commit
//            tx.commit();


            /**
             * @ 2023-07-29
             * @ 단방향 연관관계
             *
             * 객체를 테이블에 맞춰 데이터 중심으로 모델링하면, 협력 관계를 만들 수 없다.
             * Why?
             * 1. 테이블은 FK로 join하여 연관 테이블을 찾는다.
             * 2. 객체는 참조를 사용하여 연관 객체를 찾는다.
             * --------------------------------------------------------------
             * (1) 단방향 연관관계
             * team의 id가 아닌, Team의 '참조값'을 그대로 가져오게 만듦.
            * */

//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("member1");
////            member.setTeamId(team.getId());
//            member.setTeam(team);
//            em.persist(member);
//
//            Member findMember = em.find(Member.class, member.getId());
//            Team findTeam = findMember.getTeam();
//            System.out.println("findTeam = " + findTeam.getName());

            /**
             *
             * @ 2023-08-01
             * @ 양방향 연관관계
             * */

            /*
             * 테이블: 테이블 연관관계는 외래키 1개로 양방향을 다 조회할 수 있다. (사실상 방향의 의미가 없다.)
             * 객체: members를 알려면 List members를 넣어줘야 알 수 있다.
             btw, 양방향 연관관계가 좋을까? 사실 객체는 가급적 단방향이 좋다.
             테이블 연관관계: 1개 | why? 회원<->팀의 연관관계 1개(양방향)
             >>> 테이블은 외래키 하나로 두 테이블의 연관관계를 관리
             * 객체 연관관계: 회원->팀 1개(단방향), 팀->회원 1개(단방향) 총 2개
             >>> 즉, 객체의 양방향 관계는 사실 정말 양방향이 아니라, 서로 다른 단방향 관계 2개이다.
             * '연관관계의 주인': 양방향 매핑 규칙
             1. 객체의 두 관계 중 하나를 연관관계의 주인으로 지정
             2. 연관관계의 주인만이 외래키를 관리(등록, 수정)
             3. 주인이 아닌 쪽은 읽기만 가능
             4. 주인은 mappedBy 속성 사용X
             5. 주인이 아니면 mappedBy 속성으로 주인 지정
             * 누구를 주인으로?
             * ★★테이블로 나타냈을 때 외래 키가 있는 곳을 주인으로 정해라!!★★
             */

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
//            member.setTeamId(team.getId());
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m: members){
                System.out.println("m = " + m.getUsername());
            }

            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        } finally {
            //EntityManager 닫고
            em.close();
        } //try-catch-finally

        //EntityManagerFactory 닫음
        emf.close();
    }
}
