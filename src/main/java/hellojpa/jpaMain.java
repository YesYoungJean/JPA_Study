package hellojpa;

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
//            member.setId(1L);
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
            List<Member> result = em.createQuery("SELECT M FROM Member as M", Member.class)
                    .getResultList();

            for (Member member : result){
                System.out.println("member = " + member.getId());
                System.out.println("member = " + member.getName());
            }

            /**
             * commit 끝!
             * */
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
