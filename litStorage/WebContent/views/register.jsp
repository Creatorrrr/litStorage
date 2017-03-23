<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<title>회원가입</title>
<script type="text/javascript">

function validate() {
        var id = document.getElementById('id');
        var password = document.getElementById('password');
        var password2 = document.getElementById('password2');
        var name = document.getElementById('name');
        var email = document.getElementById('email');
    
 		var frm = document.forms['frm'];
      
        if(!chk(/^[\w]{4,}@[\w]+(\.[\w-]+){1,3}$/, email, "이메일 형식에 어긋납니다."))
               return false;
       
     
        if(!chk(/^[a-z][a-z\d]{3,11}$/, id, "비밀번호 첫글자는 영문 소문자, 4~12자 입력해주세요."))
               return false;
        if(!chk(/[0-9]/, id, "아이디는 숫자 하나 이상 포함해주세요."))
               return false;
 
       
        if(password.value!=password2.value) {
               alert("비밀번호가 일치하지 않습니다.");
        }
       
        
        if(!/^[가-힝]{2,}$/, name, "한글을 입력해주세요")
               return false;
       
		frm.submit();
 
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
       
        <form name="frm" action="${ctx }/join.do" method="post">       <!-- 제출시 행동 -->
               <label for="id">아이디</label>
               <input type="text" name="id" id="id" /><br />
               <label for="password">비밀번호</label>
               <input type="password" name="password" id="password" /><br />
               <label for="password2">비밀번호 재입력</label>
               <input type="password" name="password2" id="password2" /><br />
               <label for="name">이름</label>
               <input type="text" name="name" id="name" /><br />
               <label for="email">이메일</label>
               <input type="text" name="email" id="email" /><br />
               <br />
               
               <br /><br /><br />
               
               <input type="reset" value="리셋" />
               <input type="submit" value="완료" onclick="javascript:validate();" />
       
        </form>
 
</body>
</html>