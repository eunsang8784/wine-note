package com.eunsang.winenote.controller;

import com.eunsang.winenote.domain.Wine;
import com.eunsang.winenote.repository.WineRepository;
import com.eunsang.winenote.service.WineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//Controller: 이클래스가 웹 브라우저의 요청을 받는 안내데스크 스프링에 알림
@Controller
@RequiredArgsConstructor // 서비스 클래스를 자동으로 불러온다.
public class WineController {

    //창고지기(Repository) 불러옴 service로 repository 변경
   // private final WineRepository wineRepository;
    private final WineService wineService;

    //생성자 주입: 스프링아 창고지기 데려와줘 // 해당 기능 service로 이동
    //public WineController(WineRepository wineRepository){this.wineRepository = wineRepository;}

    //와인목록조회
    @GetMapping("/wines")//("/wines"): 웹브라우저 주소창에 'localhost:8081/wines' 라고 치면 메서드 실행됩니다.
    public String list(Model model){
                        //@param model: 컨트롤러에서 만든 데이터를 HTML(화면)로 전달해주는 '배달 상자' 역할을 하는 객체입니다.
        //service에서 목록을 달라고 요청
        List<Wine> wines = wineService.findAllWines();
        //가져온 와인 목록은 'wines'라는 이름 바구니(model)에 담는다.
        //이 바구니는 나중에 HTML 화면으로 전달된다.
        model.addAttribute("wines", wines);
        //wineList 라는 이름으 html 파일을 화면에 보여주라고 명령한다.
        return "wineList";

        //Model은 스프링 MVC 패턴에서 **컨트롤러(Controller)**와 뷰(View, HTML) 사이를 연결해주는 '데이터 셔틀'
        // 또는 **'운반용 박스'**라고 생각하면 가장 쉽습니다.

    }

    //와인 등록폼을 보여주는 창구(GET) 와인 등록 화면
    //주소: localhost:8081/wines/add
    @GetMapping("/wines/add")
    public String addForm(){
        return "addWineForm"; //addWineForm.html 파일보여줌

    }

    //실제 와인을 DB에 저장하는 창구(POST)
    @PostMapping("/wines/add")
    public String addWine(@ModelAttribute Wine wine){
        //화면에서 보낸 데이터(wine객체)를 그대로 db 창고지기에 전달해서 저장
        //wineRepository.save(wine);
        wineService.saveWine(wine);  //service에게 저장요청

        //저장이 끝나면 다시 와인 목록 페이지("/wines")로 강제 이동 시킨다.
        return "redirect:/wines";
    }

    //와인삭제 요청 처리 창구
    //@pathVariable Long id: 주소창에 들어온(/wines/delete/1(id))를 낚아채서 id변수에 넣어준다.
    @GetMapping("/wines/delete/{id}")
    public String deleteWine(@PathVariable Long id){

        //창고지기에게(repository) 해당 번호id 와인을 지우라고 시킨다.
       // wineRepository.deleteById(id); 해당 기능 service로 이전
        wineService.deleteWine(id); //service 해당 id  삭제

        //삭제하면 다시 목록띄우기
        return "redirect:/wines";
    }

    //수정화면 보여주기(기존데이터 채워짐)
    @GetMapping("/wines/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        //DB에서 해당id 와인을 찾아서 'wine'상자에 담아서 보낸다
       // Wine wine = wineRepository.findById(id).orElse(null);//박스를 열어봐! 만약 안에 와인이 있으면 꺼내주고, 없으면 그냥 null(빈 값)을 줘!"라고 말하는 명령
        Wine wine = wineService.findWineById(id); // service가 찾아준 와인을 상자에 담는다 수정페이지
        model.addAttribute("wine", wine);
        return "editWineForm";// editWineForm.html 부른다
    }

    //실제 수정데이터 저장하기
    @PostMapping("/wines/edit/{id}")
    public String editWine(@PathVariable Long id, @ModelAttribute Wine wine){
        //Id 명시해줘야 JPA가 '새로만들기' 가 아닌 '기존것 고치기'로 인식
        wine.setId(id);
        //wineRepository.save(wine);
        wineService.saveWine(wine); //수정후 저장 service에게 시키기
        return "redirect:/wines";//수정후 목록으로
    }

}
