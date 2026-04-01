package com.eunsang.winenote.service;

import com.eunsang.winenote.domain.Wine;
import com.eunsang.winenote.repository.WineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//  DB 창고에 직접 손을 대는 건 오직 이 Service만 할 수 있게 한다.
@RequiredArgsConstructor//Repository를 자동으로 데려오는 롬복마법
public class WineService {

    private final WineRepository wineRepository;

    //전체 와인 목록조회
    public List<Wine> findAllWines(){
        // 창고지기한테 받은 와인 뭉치를 '나(Service)'를 부른 '너(Controller)'에게 줄게!
        return wineRepository.findAll();
    }

    //와인 저장
    public void saveWine(Wine wine){
        wineRepository.save(wine);// 끝! 나한테 줄 건 없고, DB에서 저장만 하면 됨.
    }

    //와인 삭제
    public void deleteWine(Long id){
        wineRepository.deleteById(id);// 끝! 나한테 줄 건 없고, DB에서 지우기만 하면 됨.
    }

    //와인 한병찾기
    public Wine findWineById(Long id){
        return wineRepository.findById(id).orElse(null);
    }


    public List<Wine> searchWines(String keyword){
        //검색어가 없으면 전체목록을, 있으면 검색된 목록을 가져온다.
        if (keyword == null || keyword.isEmpty()){
            return wineRepository.findAll();
        }
        return wineRepository.findByNameContaining(keyword);
    }
}
