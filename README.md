# Real_Estate_Application
- 아키텍처
  - front: Vue.js
  - back: SpringBoot
  
- 기능구현
  - 로그인, 회원가입
  - 구글맵 API를 활용하여, 시도별 검색 -> 구군별 검색 -> 동별 검색 -> 아파트 매매매가별 검색, 주택 매매가별 검색, 주택 월세가별 검색 가능
    (csv파일을 다운 받아 db에 올림)
  - 로그인시, 원하는 매물에 대한 상세정보(가격등) 공공 데이터API를 활용(실시간으로 JSON형식의 데이터를 받아옴)
  - 게시판 기능(CRUD), 로그인시에만 본인이 작성한 게시물 수정
  - 마이 페이지, 개인 정보 수정
  - 8개 언론사별 부동산 뉴스기사 제공
  - 회원과 비회원과의 서비스 차이를 두기 위해 로그인시 더 다양한 맵 정보를 확인할 수 있음(카카오맵API활용);거리뷰,편의시설과의 거리차이,도착시간
