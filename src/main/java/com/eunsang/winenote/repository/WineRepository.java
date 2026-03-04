package com.eunsang.winenote.repository;

import com.eunsang.winenote.domain.Wine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//WineRepository:와인DB에 접근하는 관리 역할 인터페이스
//인터페이스만 만들어두면, 스피링 JPA가 실제 일하는 코드를 자동으로 생성

@Repository // 이 인터페이스가 데이터베이스에 접근하는 저장소 스프링지정
public interface WineRepository extends JpaRepository<Wine, Long> {

    /* JpaRepository를 상속(extends)받는 것만으로도 아래 기능들을 공짜로 얻게 됩니다:
       1. save(wine): 와인 정보 저장
       2. findById(id): 고유 번호로 와인 찾기
       3. findAll(): 저장된 모든 와인 목록 가져오기
       4. delete(wine): 와인 정보 삭제

       <Wine, Long>은 "우리가 생성한 domain 패키지에 Wine 데이터를 관리할 거고,
        그 데이터의 고유번호(ID)는 Long 타입이다"라는 뜻입니다.
    */
}
