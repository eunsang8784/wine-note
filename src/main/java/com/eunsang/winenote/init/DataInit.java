package com.eunsang.winenote.init;

import com.eunsang.winenote.domain.Wine;
import com.eunsang.winenote.repository.WineRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


//Component: 이클래스를 스프링이 직접 관리하는 도구로 등록
//CommandLineRunner: 애플리케이션이 실행되자마자 특정한 작업을 하고 싶을때 사용 하는 도구
@Component
public class DataInit implements CommandLineRunner {

    // repository 패키지안에 WineRepository불러온다
    // final 을 붙여서 한번 연결하면 바뀌지않도록 고정
    private final WineRepository wineRepository;

    //[생성자주입]: 스프링이 WineRepository가 필요하구나 하고 하고 알아서 연결해줌
    public DataInit(WineRepository wineRepository){
        this.wineRepository = wineRepository;
    }

    //프로그램이 시동을 걸면 자동으로 이 run 메서드가 호출된다.
    @Override //위에서 시킨대로 재정의 합니다.
    public void run(String... args) throws Exception{
                                    //throws Exception : 에러날씨 스프링에게 전달
        //새로운 와인 객체(데이터 꾸러미) 하나 만든다
        Wine wine = new Wine();

        //와인정보를 하나씩 채운다 (setter 사용)
        wine.setName("G7 Cabernet Sauvignon");//와인이름
        wine.setType("Red"); //종유
        wine.setVariety("Cabernet Sauvignon"); //품종
        wine.setPrice("7,900"); //가격
        wine.setAroma("Blackberry, Oak"); //향

        //[핵심]창고지기(Repository)에게 이와인을 DB 창고에 넣으라고시킴
        //save() 메서드를 호출하는 순간, 어제 확인했던 WINE 테이블에 데이터가 들어간다.
        wineRepository.save(wine);


        //잘들어 갔는지 콘솔창에 메시지 띄우기
        System.out.println("===================================");
        System.out.println("==와인 데이터 저장 완료==");
        System.out.println("=====================================");
    }

}
