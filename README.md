# BackEnd

## 프로젝트 소개
CleanUp은 불필요한 쓰레기 발생을 예방하고, 원활한 배출을 위한 정보를 제공하여 편리한 환경을 조성하고자 하는 프로젝트입니다. 이 BackEnd 프로젝트는 CleanUp의 백엔드를 담당합니다.

### 개발 환경
- Java 11
- Spring Boot 2.7.9
- Spring Boot
- MySQL
- Spring Data JPA
- Spring Security
- Swagger
- JUnit5
- Mockito

## 프로젝트 구조
![image](https://user-images.githubusercontent.com/76714304/222457409-897778e5-d986-489e-987f-0773a6e15c6f.png)

---

## ERD
![화면 캡처 2023-02-27 110435](https://user-images.githubusercontent.com/95588392/221455174-e75defee-8f5d-48d5-acaa-2ba700b47fdb.png)

## API 명세서
![image](https://user-images.githubusercontent.com/76714304/222444968-69a3d17b-cb7a-4361-8bcd-2fc1e4c8a1c6.png)

## 프로젝트 API구현 요소

### 1. UserController
    - 회원가입 API 
    - 로그인 API
    - 회원탈퇴 API

### 2. BoardController
    - 게시글 전체 조회 API
    - 게시글 상세 조회 API
    - 게시글 생성 (이미지 업로드) API 
    - 게시글 상세 정보 수정 API
    - 게시글 삭제 API
    
### 3.CommentController
    - 댓글 생성 API
    - 댓글 수정 API
    - 댓글 삭제 API
    
### 4.Likescontroller
    - 게시글 좋아요 API
    - 댓글 좋아요 API
    
---

## 주요기능
  - S3를 이용한 이미지 업로드 구현
  - Enum을 이용한 전역 얘외처리 구현
  - SWagger를 통한 API명세서 구현
  - JWT TOKEN과 SPING SECURITY를 이용한 로그인 구현
  
---

## Trouble Shooting
1. MultipartFile Request 오류
    - 프론트와 맞춰보는 도중 MultipartFile 을 전달받을때 기존 @RequestPart 를 이용해서 json 객체와 MultipartFile 을 따로 받았었는데 depth 차이로 인해 전달받지 못한다는 피드벡을 받았습니다.
    - 해결 : MultipartFile 도 dto에 넣어 @ModelAttribute 로 한번에 받았습니다.
 
2. API명세서의 중요성
    - 보통 백엔드가 먼저 작업을 하고 프론트가 진행하게 되지만 같이 진행하면서 api명세서가 수시로 바뀌게 되어 소통 과정의 오류가 생겼습니다.
    - 해결 : 많은 소통을 통한 정확한 API 명세서 작성 및 수정 시 다시 소통
-----

## Contributors
- [오세영](https://github.com/osy9536)
- [김근호](https://github.com/GEUNHOKIM)
- [이건호](https://github.com/Kuno1734)
