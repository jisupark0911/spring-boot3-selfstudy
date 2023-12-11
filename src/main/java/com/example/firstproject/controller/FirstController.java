package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러 선언시 위에 자동으로 임포트
public class FirstController {
    @GetMapping("/hi") //요청 url 매핑
    public String niceToMeetYou(Model model){ //모델 선언
        model.addAttribute("username", "jisupark"); // model.attribute("변수명","변수"); 모델 변수명 선언
        return "greetings"; //이렇게 하면 서버가 알아서 greetings 템플릿을 찾아감 반환할 뷰 선언
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","박지수");
        return "goodbye";
    }


}
