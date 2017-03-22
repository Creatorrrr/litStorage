<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript">

function validate() {
        var id = document.getElementById('userid');
        var pass = document.getElementById('pass');
        var pass1 = document.getElementById('pass1');
        var name = document.getElementById('name');
        var email = document.getElementById('email');
    
 
        // 이메일 검사
        // 4글자 이상(\w = [a-zA-Z0-9_], [\w-\.]) @가 나오고
        // 1글자 이상(주소). 글자 가 1~3번 반복됨
        if(!chk(/^[\w]{4,}@[\w]+(\.[\w-]+){1,3}$/, email, "이메일 형식에 어긋납니다."))
               return false;
       
        // 아이디 검사
        // 첫 글자는 반드시 영문 소문자, 4~12자로 이루어지고, 숫자가
        // 하나 이상 포함되어야 한다. 영문소문자와 숫자로만 이루어져야한다.
        // \d : [0-9]와 같다.       {n,m} : n에서 m까지 글자수
        if(!chk(/^[a-z][a-z\d]{3,11}$/, id, "비밀번호 첫글자는 영문 소문자, 4~12자 입력해주세요."))
               return false;
        if(!chk(/[0-9]/, id, "아이디는 숫자 하나 이상 포함해주세요."))
               return false;
 
        // 비밀번호 확인 검사
        if(pass.value!=pass1.value) {
               alert("비밀번호가 일치하지 않습니다.");
        }
       
        // 이름 검사
        // 2글자 이상, 한글만
        // 통과하지 못하면 한글로 2글자 이상을 넣으세요~ alert 출력!
        if(!/^[가-힝]{2,}$/, name, "한글을 입력해주세요")
               return false;
       

 
        }
 
        function chk(re, e, msg) {
               if (re.test(e.value)) {
                       return true;
               }
 
               alert(msg);
               e.value = "";
               e.focus();
               return false;
        }
</script>
</head>
<body>
 
        
       
        <h1>회원가입 유효성 체크</h1>
       
        <form action="" method="post" onsubmit="return validate();">       <!-- 제출시 행동 -->
               <label for="userid">아이디</label>
               <input type="text" name="userid" id="userid" /><br />
               <label for="pass">비밀번호</label>
               <input type="password" name="pass" id="pass" /><br />
               <label for="pass1">비밀번호 재입력</label>
               <input type="password" name="pass1" id="pass1" /><br />
               <label for="name">이름</label>
               <input type="text" name="name" id="name" /><br />
               <label for="email">이메일</label>
               <input type="text" name="email" id="email" /><br />
               <br />
               
               <br /><br /><br />
               
               <input type="reset" value="리셋" />
               <input type="submit" value="완료" />
       
        </form>
 
</body>
</html>