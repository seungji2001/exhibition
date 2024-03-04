package com.example.demo.controller.V3;

import com.example.demo.domain.V3.Magazine;
import com.example.demo.dto.V3.MagazineDto;
import com.example.demo.service.V3.MagazineService;
import com.example.demo.type.MUserType;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/magazine")
public class MagazineController {

    private final MagazineService magazineService;

    /*
    magazine을 등록하는 사람은 기획자밖에 없다
     */
    @PostMapping("/{userId}")
    public ResponseEntity<Long> saveMagazine(@PathVariable Long userId, @RequestBody MagazineDto.SaveMagazineDto saveMagazineDto){
        Long magazineId = magazineService.save(saveMagazineDto);
        magazineService.saveRole(userId, magazineId, MUserType.PLANNER);
        return ResponseEntity.ok(magazineId);
    }

    /*
    isPublish가 true인 경우 발매된 magazine만 가져옴,
    isPublish가 false인 경우 미발매된 magazine만 가져옴
     */
    @GetMapping("/")
    public ResponseEntity<List<MagazineDto.getMagazineDto>> getMagazines(Boolean isPublish){
        return ResponseEntity.ok().body(magazineService.getMagazines(isPublish));
    }

    //하나의 magazine 만 가져오기
    @GetMapping("/{magazineId}")
    public ResponseEntity<MagazineDto.getMagazineDto> getMagazine(@PathVariable Long magazineId){
        return ResponseEntity.ok().body(magazineService.getMagazine(magazineId));
    }

    //발매로 변경
    @PutMapping("/{magazineId}/publish")
    public ResponseEntity<Long> publishMagazine(@PathVariable Long magazineId){
        return ResponseEntity.ok().body(magazineService.publish(magazineId));
    }

}
