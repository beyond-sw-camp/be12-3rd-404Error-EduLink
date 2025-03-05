# be12-3rd-404Error-EduLink

## 📌 프로젝트 주제 
<div align="center">
    <img src="./images/readme_img/service.png" /> <br>
</div>



### EduLink: 혁신적인 학습 관리 플랫폼
빠르게 증가하는 국비지원 부트캠프 수요에 맞춰 학습 환경의 질적 향상을 목표로 개발된 EduLink는 기존 LMS(HRD-Net)의 한계를 보완합니다.   

이 플랫폼은 단순 출결 확인을 넘어 커리큘럼 관리, 공지사항 통합, 학습 성과 분석 등 다양한 기능을 통해 학습 효율을 극대화합니다.   

수강생들은 EduLink를 통해 학습 전반을 체계적으로 관리하고 편리하게 활용할 수 있습니다.

[상세보기](https://github.com/beyond-sw-camp/be12-1st-404Error-EduLink/blob/main/README.md)

## 💡팀

<div align=center>
  <img src="./images/readme_img/team.png" /> <br>
    <h3>한화시스템 BEYOND SW캠프 </h3>
    <p>12기 2차 프로젝트 <strong>팀 404Error</strong></p>
</div>


## 🤚 404Error 팀원
<div style="display: flex; justify-content: center;">
  <table  align="center">
    <tbody>
      <tr>
        <td align="center"><a href="https://github.com/museongkim0" style="text-decoration: none; color: lightgray;"><img src="./images/팀원소개/ms.jpg" width="100px;" height="100px;" background-size="cover;" alt=""/><br /><sub><b> 🐯 김무성</b></sub></a><br /></td>
        <td align="center"><a href="https://github.com/kuj7882" style="text-decoration: none; color: lightgray;"><img src="./images/팀원소개/yj.jpg" width="100px;"  alt=""/><br /><sub><b> 🐶 김유진</b></sub></a><br /></td>
        <td align="center"><a href="https://github.com/GoodLeaf" style="text-decoration: none; color: lightgray;"><img src="./images/팀원소개/jy.png" width="100px;" height="100px;" alt=""/><br /><sub><b> 🐺 김정엽</b></sub></a><br /></td>
        <td align="center"><a href="https://github.com/gunha0405" style="text-decoration: none; color: lightgray;"><img src="./images/팀원소개/gh.jpg" width="100px;" alt=""/><br /><sub><b> 🐱 오건하</b></sub></a><br /></td>
        <td align="center"><a href="https://github.com/leewoojin12" style="text-decoration: none; color: lightgray;"><img src="./images/팀원소개/wj.jpg" width="100px;" alt=""/><br /><sub><b> 🦁 이우진</b></sub></a><br /></td>
      </tr>
    </tbody>
  </table>
</div>


## ⭐ 접속 주소
[www.edulink.kro.kr](http://www.edulink.kro.kr)


## 🔧 기술 스택
프론트엔드  
![Vue.js](https://img.shields.io/badge/vue.js-%2335495e.svg?style=for-the-badge&logo=vuedotjs&logoColor=%234FC08D) 
![pinia](https://img.shields.io/badge/Pinia-f7d336?style=for-the-badge&logo=pinia&logoColor=white) 
![NginX](https://img.shields.io/badge/NginX-009639?style=for-the-badge&logo=nginx&logoColor=white)  

백엔드  
![](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=Spring-Boot&logoColor=white) ![](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-%232F7D32.svg?style=for-the-badge&logo=json-web-tokens&logoColor=white) 

DB<br>
![MariaDB](https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariadb&logoColor=white) 
![Amazon RDS](https://img.shields.io/badge/Amazon%20RDS-527FFF?style=for-the-badge&logo=Amazon%20RDS&logoColor=white) 
<!-- ![Redis](https://img.shields.io/badge/Redis-%23D92D2A.svg?style=for-the-badge&logo=redis&logoColor=white)    -->

클라우드  
![](https://img.shields.io/badge/Amazon%20EC2-FF9900?style=for-the-badge&logo=Amazon%20EC2&logoColor=white) 
![](https://img.shields.io/badge/Amazon%20S3-569A31?style=for-the-badge&logo=Amazon%20S3&logoColor=white) 

협업 툴  
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) 
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Figma](https://img.shields.io/badge/Figma-%232C2E3A.svg?style=for-the-badge&logo=figma&logoColor=white)
![Discord](https://img.shields.io/badge/Discord-00599C?style=for-the-badge&logo=discord&logoColor=white) 

## 🔧 시스템 아키텍처
 <img src="./images/readme_img/sys_arc.png" alt="시스템 아키텍처" style="width:100%;"/>
<details>
<summary> Amazon RDS </summary>
 
- 별도의 설치과정 및 서버 IP & PORT 설정 없이 DB를 구성하기 위해 Amazon RDS를 사용하였습니다.
</details>

<details>
<summary> Amazon S3 </summary>

- 사용자 이미지 파일, 게시판 첨부 파일, 과제 첨부 파일, 테스트 첨부 파일 등 서비스에 필요한 파일을 저장하기 위해 S3를 사용하였습니다.
</details>

<details>
<summary> EC2 </summary>

- 2대의 EC2를 이용하여 한대는 프론트서버, 한대는 백엔드 서버를 설정하였습니다. 
</details>

<details>

## ⭐ 소프트웨어 아키텍처
<!-- [www.edulink.kro.kr](http://www.edulink.kro.kr) -->

## ⭐ 코딩 커벤션션
<!-- [www.edulink.kro.kr](http://www.edulink.kro.kr) -->

## ⭐ API 명세서
<!-- [www.edulink.kro.kr](http://www.edulink.kro.kr) -->

## ⭐ 기능 테스트
<!-- [www.edulink.kro.kr](http://www.edulink.kro.kr) -->

## ⭐ 성능 개선
<!-- [www.edulink.kro.kr](http://www.edulink.kro.kr) -->