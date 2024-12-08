# 2024-2-SWEngineering-Fav_I-IdolBom
2024-2 소프트웨어공학 프로젝트 팀 최애의 아이 '어르신 아이돌 봄'은 시니어 팬들의 오프라인 팬 활동과 티켓팅을 돕기 위한 플랫폼입니다. 티켓팅이 어려운 팬들에게 신뢰할 수 있는 티켓팅 도우미를 매칭하고, 실시간 채팅을 통해 소통하며, 편리하게 동행자를 찾을 수 있도록 설계되었습니다.

# 🌸기술 스택
<div>
  <img src="https://img.shields.io/badge/React Native-61DAFB?style=for-the-badge&logo=React&logoColor=black"/>
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>
  <img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white"> 
  <img src="https://img.shields.io/badge/AmazonAWS-000000?style=for-the-badge&logo=AmazonAWS&logoColor-000000">
</div>

# 🌸팀원
|이름|학과|학번|
|------|---|---|
|김명서|컴퓨터공학과|2021111962|
|박지형|컴퓨터공학과|2021113417|
|안성현|컴퓨터공학과|2020112736|
|임사랑|컴퓨터공학과|2021113414|

# 🌸프로젝트 배경 및 개요
최근 한국 사회에서는 **액티브 시니어**, 즉 경제적 여유와 시간적 여건을 갖춘 중장년층의 문화 소비와 여가 활동이 두드러지게 증가하고 있다. 특히 50대와 60대는 K-팝 및 트로트 등 다양한 음악 장르에서 활발한 팬 활동을 보이며, 이는 **K-팝 시장에서 중요한 소비층**으로 자리잡고 있다.

그러나 기존의 팬 관련 서비스는 주로 1020 세대를 겨냥하고 있으며, 팬카페의 경우 가입 절차가 복잡하고 규칙이 많아 **덕질을 시작하는 방법에 대한 어려움**을 겪는 경우가 많았다. 공연을 즐기려는 시니어 팬들에게 0.1초 단위의 빠른 반응 속도가 요구되는 **온라인 티켓팅은 큰 장벽**이 된다. 복잡한 예매 사이트의 인터페이스, 시력 저하 등의 신체적 제약은 티켓 구매를 더욱 힘들게 만들며, 이를 악용한 티켓팅 대행 사기 사례도 증가하고 있다. 2023년 한국소비자원 조사에 따르면, 온라인 티켓 구매 관련 피해 사례 중 50대 이상이 차지하는 비율이 30%를 넘어섰다. 

이러한 상황에서 ‘어르신 아이돌 봄’ 프로젝트는 중장년층 팬들의 특성과 요구에 맞춘 서비스를 제공한다. **신뢰할 수 있는 티켓팅 도우미 매칭** 서비스와 더불어, 함께 공연을 관람할 **동행을 모집하는 기능** 및 아티스트의 일정을 확인할 수 있는 **스케줄 조회 기능**을 통해 시니어 팬들의 팬 활동을 지원하고자 한다. 이를 통해 중장년층 팬 활동 접근성을 높이고, 세대를 아우르는 팬 문화의 다양성에 기여하고자 한다. 또한, 시니어들의 삶의 질 향상과 사회적 연결 강화에도 긍정적인 영향을 미칠 것으로 기대된다.

# 🌸주요 기능 상세
![image](https://github.com/user-attachments/assets/cf5b44fa-f855-44d0-abd4-f252ff039524)
### 1. 회원 관리 기능
 카카오톡 소셜 로그인을 통해 간편한 회원가입 및 로그인을 제공하며, 사용자 프로필 정보(닉네임, 프로필 사진 등)를 가져와 계정을 생성 및 관리한다. 마이페이지에서는 닉네임, 한줄 소개, 신뢰도, 아이돌, 활동 지역 등의 개인 정보를 조회 및 수정할 수 있다. 

### 2. 아이돌 스케줄 조회 기능
 사용자가 선택한 아이돌의 스케줄을 달력 형태로 보여주며, 특정 스케줄 클릭 시 상세 정보를 통해 원본 링크 및 티켓팅 정보를 조회한다. 사용자 편의를 위해 캘린더 보기, 어제/오늘/내일 보기 2가지 방식을 제공한다.

### 3. 티켓팅 대리 해주기 / 구하기 기능
 대리인과 신청인을 매칭해준 뒤 단계별 진행사항과 채팅방 생성을 통해 대리 티켓팅이 이루어질 수 있게 한다. 티켓팅 신청인은 대리인과 실시간 채팅 기능을 통해 대화를 나눌 수 있고, 대리인은 티켓팅 결과를 업로드 하여 확인한 후에 송금을 진행할 수 있다.
* 티켓팅 대리 신청하기 – 달력 UI에서 해당 콘서트가 열리는 날짜를 클릭하여 대리인의 희망 조건을 입력하여 대리인을 매칭한다.
* 티켓팅 대리하기 – 달력 UI에서 해당 콘서트가 열리는 날짜를 클릭하여 매칭 요청 리스트 중에 선택하여 신청인을 매칭한다.

### 4. 동행 모집 기능
선택한 스케줄에 사용자가 원하는 동행을 만들어 구할 수 있다. 동행 모집 시 입력하는 상세 정보는 희망하는 날짜, 시간, 연령대, 인원 수, 성별, 하고 싶은 말, 규칙으로 구성된다. 동행이 만들어지면 ‘전체’, ‘모집 중’, ‘완료’ 를 선택해 필터링해 볼 수 있다. 

### 5. 채팅 및 신고 기능
실시간 채팅 기능을 통해 매칭된 티켓팅 대리인과의 일대일 대화 및 동행 모집원들과의 다대다 대화를 지원한다. 채팅 내에서는 프로필 확인, 신고 기능을 제공하여 신뢰도와 안전성을 확보할 수 있다. 사용자는 부적절한 행동을 한 유저를 신고할 수 있고, 관리자는 신고 내용을 확인 후 적절한 조치를 취한다.

# 🌸UI 설계

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center;">
    <img src="https://github.com/user-attachments/assets/0c2649a7-02e4-48a7-8b18-99b0210b73a8" alt="★로딩 화면" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/d8deb75e-8da9-40e0-816e-062655d9ab19" alt="★로그인 및 회원가입" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/7c5bcf3a-01a6-4be5-aac7-d5225c383846" alt="★아이돌 선택" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/44fc0d62-9717-40a0-8a05-4a6492eea2ff" alt="★아이돌 선택 (1)" style="width: 23%; height: auto;">
</div>

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center; margin-top: 10px;">
    <img src="https://github.com/user-attachments/assets/f5662176-a008-48a1-b02b-9c11238da89c" alt="완료) ★메인 페이지" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/26744a9a-b2be-4e8f-adc1-0df0e9a40bf4" alt="완료) ★메인 - 달력 특정 날짜 클릭" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/1bc6c96a-b652-47eb-9c1b-60788abaff44" alt="완료) ★스케줄 상세 정보 클릭 (1)" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/4e4fd797-0e68-4c68-b46c-dc1b2168edf0" alt="image" style="width: 23%; height: auto;">
</div>

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center; margin-top: 10px;">
    <img src="https://github.com/user-attachments/assets/49c2bb91-0cdd-4427-8693-4c3d022239ae" alt="완료) ★매칭" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/7670b052-8bf5-49ed-9e40-f43d68996ee3" alt="image" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/565a89d8-0d7d-4ae2-84bc-88e891f2b847" alt="image" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/7a96cbac-43a4-47de-9efb-9040fee7b60b" alt="image" style="width: 23%; height: auto;">
</div>

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center; margin-top: 10px;">
    <img src="https://github.com/user-attachments/assets/e4bd6f7e-e82b-4ba8-9778-7a868a29af9f" alt="image" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/0a1d0d7b-3b74-4956-8f21-7df169be0c9d" alt="완료) ★채팅" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/1f18a92c-2374-4c47-a649-36094ff65961" alt="완료) ★채팅방" style="width: 23%; height: auto;">
    <img src="https://github.com/user-attachments/assets/b43efd14-0ccd-4952-8daa-0b80224278e2" alt="★신고하기 클릭시" style="width: 23%; height: auto;">
</div>
