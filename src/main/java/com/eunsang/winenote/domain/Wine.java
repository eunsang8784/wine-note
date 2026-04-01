package com.eunsang.winenote.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//Wine 클래스: 이클래스는 데이터베이스 'WINE' 테이블과 1:1로 매칭되는 Entity 역할
@Entity //[JPA] 이클래스가 데이터 베이스 테이블과 매핑되는 클래스 선언
@Getter //[Lombok] 클래스 내부 필드들의 데이터를 가져오는 메서드(getName 등) 자동 생성가능
@Setter //[Lombok] 클래스 내부 필드들의 데이터를 저장하는 메서드(setName 등) 자동 생성가능
public class Wine {

    @Id //이필드가 데이터베이스테이블 primary key(기본키) 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)//데이터베이스 저장시 자동(1,2,3)생성
    private Long id;

    @Column(nullable = false) //와인이름 필수(null 불가) 설정
    private String name;  //예: g7 까베르네쇼비뇽

    private String type;  //와인 종류(레드,화이트,스파클링 등)

    private String variety; //포도 품종(예: 카베르네쇼비뇽, 메를로)

    private String aroma; // 향기 태그(우디향, 과일향 등)

    private String price; // 가격

    private int rating; // 별점(1~5점) 추가하기
}
