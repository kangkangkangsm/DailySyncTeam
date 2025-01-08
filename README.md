### 프로젝트 배경 및 목적

- 현재 시중에 나와 있는 스케줄 관리, 식단 관리, To-Do 리스트, 가계부 관리 등의 웹/앱 서비스는 기능이 지나치게 복잡하거나, 필요한 기능을 사용하려면 유료 버전으로 업그레이드해야 하는 경우가 많습니다.
    
     일부 서비스는 **불필요한 기능**이 포함되어 있어 사용자가 원하는 기능만을 간편하게 사용하기 어렵고 반대로, 기능이 너무 제한적인 서비스들은 여러 개의 앱을 동시에 사용해야 하는 불편함이 존재합니다.
    
- 이러한 문제를 해결하고자, **스케줄 관리**, **식단 관리**, **To-Do 리스트**, **가계부 관리** 등을 하나의 웹과 앱에서 간편하게 제공하는 시스템을 만들기로 했습니다.
    
     웹에서는 **세부 설정**을 통해 사용자가 맞춤형 관리가 가능하도록 하고, 앱에서는 **간단하고 직관적인 사용**이 가능하도록 설계하여, 여러 서비스를 사용하는 번거로움 없이 하나의 시스템으로 모든 관리 작업을 효율적으로 처리할 수 있도록 하는 것이 목표입니다.
    

---

### 개발 환경

- BackEnd - Spring Boot
    - 기본적인 설정이 잘 갖추어져 있고 생태계가 풍부해 정보를 찾기 수월.
    - Express 서버에 비해 체계적인 프로젝트 구조를 갖추고 유지보수가 유연하고 가독성 향상
    - JPA 를 사용해 DB에 종속되지 않으며 개발 편의성 증가.
    - Spring Security 를 사용해 기본적인 인증/인가 기능을 구현.
- FrontEnd - Vue.js
    - Component 기반 구조 덕분에 프로젝트 구조화에 용이.
    - 비교적 편한 Vue의 상태관리 라이브러리인 Pinia의 편리함.
    - React 보다 속도가 빠르고 직관적인 문법과 Script,template,style 로 명확하게 나누어진 구조이기 때문에 직관적인 문법 및 개발속도 증가
- DataBase - MySQL
    - AWS
- Tools
    - Git&GitHub - 협업 및 코드 변경 사항 추적, 원격 저장소
    - Visual Studio Code ( VsCode ) - Frontend (Vue)
    - IntelliJ - Backend (Spring boot)
    - BeeKeeper Studio - DB 확인
    - Termius - Ubuntu 원격 SSH 연결
    - AWS - 서버 및 DB 배포 
- 개발기간 
    - **2024.12 Team 협업 시작 및 종료.**
- 아이디어 & 회의 
    -  [프로젝트 회의](https://adjoining-acorn-1b0.notion.site/DailySync-1467dd3a4f0e80518440dc7a01a04667?p=1467dd3a4f0e80d6988dd1d10202b301&pm=s)
- 배포 
    - [프로젝트 웹사이트](http://52.79.238.2)
## 👥 팀원 

### **강선민**  [![GitHub](https://img.shields.io/badge/GitHub-kangseonmin-blue?logo=github)](https://github.com/kangkangkangsm)
- To-Do 리스트 / 메모장 / 대시보드 
### **김태환**  [![GitHub](https://img.shields.io/badge/GitHub-kimtaehwan-blue?logo=github)](https://github.com/KTH8962)
- 식단 관리 / 대시보드 / CSS 전반 
### **하현수**  [![GitHub](https://img.shields.io/badge/GitHub-haHyeonsoo-blue?logo=github)](https://github.com/nihilism011)
- 가계부 / 로그인 / 회원가입 / 대시보드  
### **이혜인** [![GitHub](https://img.shields.io/badge/GitHub-leehyein-blue?logo=github)](https://github.com/dgl1111)
- 일정 / 대시보드
