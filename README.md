# Scribbly

**프로젝트 기간:** (2025.06 ~ 진행중) 

---

## 프로젝트 개요  
Scribbly는 사용자 간 소통을 중심으로 한 커뮤니티 플랫폼입니다.  
회원가입, 로그인, 로그아웃 기능을 기본으로 제공하며, 블로그 게시글 CRUD, 댓글 및 대댓글, 좋아요, 이웃맺기(Follow) 기능을 통해 활발한 소통 환경을 지원합니다.  
관리자 기능을 통해 사용자 및 게시글 관리를 효율적으로 할 수 있습니다.  

---

## 주요 기능

**사용자 관리**  
- 회원가입, 로그인, 로그아웃  
- 회원정보 수정  

**게시글 관리**  
- 블로그 게시글 작성, 조회, 수정, 삭제(CRUD)  
- 게시글에 대한 댓글 및 대댓글 기능  
- 게시글 좋아요 기능  

**소셜 기능**  
- 이웃맺기(Follow) 기능으로 사용자 간 네트워크 형성  
- 채팅 기능 (필요 시 추가)  

**관리자 기능**  
- 사용자 관리 (정지, 삭제 등)  
- 게시글 및 댓글 관리  
- 통계 및 신고 처리  

---

## 시나리오 요약  
사용자는 Scribbly에서 회원가입 후 다양한 게시글을 작성하고, 댓글과 좋아요로 소통할 수 있습니다.  
이웃맺기 기능을 통해 관심사 기반 네트워크를 형성하며, 관리자는 시스템을 효율적으로 운영할 수 있습니다.  

---

## 주요 기술 스택  
- Java, Spring Boot, JPA, Spring Security  
- Thymeleaf, JavaScript, Ajax, CSS  
- Oracle DB    

---

## 서비스 구조

사용자  
├─ 메인 페이지  
│  ├─ 게시글 목록 조회  
│  └─ 로그인/회원가입 링크  
├─ 회원 기능  
│  ├─ 회원가입  
│  ├─ 로그인 / 로그아웃  
│  └─ 마이페이지 (회원 정보 수정)  
├─ 블로그 기능  
│  ├─ 게시글 작성  
│  ├─ 게시글 조회  
│  ├─ 게시글 수정  
│  ├─ 게시글 삭제  
│  └─ 좋아요 기능  
├─ 댓글 기능  
│  ├─ 댓글 작성  
│  ├─ 대댓글 작성  
│  ├─ 댓글 수정  
│  └─ 댓글 삭제  
├─ 이웃(Follow) 기능  
│  ├─ 사용자 팔로우  
│  └─ 팔로잉/팔로워 목록 조회  
└─ 관리자 기능  
   ├─ 회원 목록 조회  
   ├─ 게시글/댓글 관리  
   └─ 신고 처리 (필요 시)  

---

감사합니다!
