## Project-Flow_Android ⍙⌯

 개발자들의 프로젝트를 관리를 도와주는 앱


### 기술스택


+ 언어 : Kotlin


### 개발규칙


+ 모든 개발은 master branch가 아닌 feature에서 진행하며, 개발 완료시 develop으로 merge 한다.

+ Resource는 아래 해당 사항에 맞추어 사용한다.

  ###### drawable
    + `what_description_where]`
    + where은 여러곳에 사용되면 쓰지 않습니다.
    + icon -> ic, background -> back  (what)
    + ex) ic_list, back_broder_black
  
  ###### layout
    + `what_description`
    + ex) activity_main, fragment_chatting
  
  ###### ID
    + `what_description`
    + textview -> tv, editText -> et, button -> btn, customview → cv 등등등 줄여서 사용
    + ex) `intro_tv`, `submit_btn`
  
  ###### colors.xml
    + color[What] 으로 작성하여 사용합니다.


### 	Git 관련  

  ##### Commit message 

  + [UPDATE] — 신규파일 추가 또는 코드 변경이 일어날때
  + [REFACTOR] — 코드를 리팩토링 했을때
  + [FIX] — 잘못된 링크 정보 변경, 필요한 모듈 추가 및 삭제
  + [REMOVE] — 파일 제거
  + [STYLE] — 디자인 관련 변경사항
  + *커밋 메시지는 최대한 상세하게 적어서 그 목적을 알게 한다*

