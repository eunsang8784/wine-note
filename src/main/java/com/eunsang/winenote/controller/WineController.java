package com.eunsang.winenote.controller;

import com.eunsang.winenote.domain.Wine;
import com.eunsang.winenote.repository.WineRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//Controller: 이클래스가 웹 브라우저의 요청을 받는 안내데스크 스프링에 알림
@Controller
public class WineController {

    //창고지기(Repository) 불러옴
    private final WineRepository wineRepository;

    //생성자 주입: 스프링아 창고지기 데려와줘
    public WineController(WineRepository wineRepository){
        this.wineRepository = wineRepository;
    }

    @GetMapping("/wines")//("/wines"): 웹브라우저 주소창에 'localhost:8081/wines' 라고 치면 메서드 실행됩니다.
    public String list(Model model){
                        //@param model: 컨트롤러에서 만든 데이터를 HTML(화면)로 전달해주는 '배달 상자' 역할을 하는 객체입니다.
        //창고지기에게 와인 목록 다 가져와 시킨다.
        List<Wine> wines = wineRepository.findAll();

        //가져온 와인 목록은 'wines'라는 이름 바구니(model)에 담는다.
        //이 바구니는 나중에 HTML 화면으로 전달된다.
        model.addAttribute("wines", wines);

        //wineList 라는 이름으 html 파일을 화면에 보여주라고 명령한다.
        return "wineList";

        //Model은 스프링 MVC 패턴에서 **컨트롤러(Controller)**와 뷰(View, HTML) 사이를 연결해주는 '데이터 셔틀'
        // 또는 **'운반용 박스'**라고 생각하면 가장 쉽습니다.

    }

}
