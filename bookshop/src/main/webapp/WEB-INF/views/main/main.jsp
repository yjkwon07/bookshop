<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"	isELIgnored="false"
	%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%>  

<body>
<!-- 
    광고글을 3초마다 한 번씩 바뀌고 버튼 클릭 시 이동하게 끔 만들기
-->
<div class="cs_main_Ad_Banner">
    <ul>
        <li><img src="${contextPath}/resources/image/main_banner02.jpg"></li>
        <li><img src="${contextPath}/resources/image"></li>
        <li><img src="${contextPath}/resources/image"></li>
    </ul>
</div>
<div class="cs_main_Book">
    <c:set var="goods_count" value="0"/>
    <h3>베스트셀러</h3>
    <c:forEach var="item" items="${goodsMap.bestseller}">
        <c:set var ="goods_count" value="${goods_count+1}"/>
        <div class="cs_book">
            <a href = "${contextPath}/">
                
            </a>
        </div>
    </c:forEach>
</div>
</body>