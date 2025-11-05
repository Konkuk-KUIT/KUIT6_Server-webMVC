<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf"%>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">

    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default content-main">
            <%-- 💡 폼 액션을 /qna/create로, 메서드는 POST로 설정 --%>
            <form name="question" method="post" action="/qna/create">

                <%-- 💡 1. 글쓴이 필드 (로그인한 사용자 ID 자동 입력) --%>
                <div class="form-group">
                    <label for="writer">글쓴이</label>
                    <input
                            type="text"
                            class="form-control"
                            value="${sessionScope.user.userId}" <%-- 세션에서 사용자 ID를 가져옴 --%>
                            id="writer"
                            name="writer"
                            placeholder="글쓴이"
                            readonly <%-- 사용자가 수정하지 못하도록 readonly 설정 --%>
                    />
                </div>

                <%-- 💡 2. 제목 필드 --%>
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" placeholder="제목" required/>
                </div>

                <%-- 💡 3. 내용 필드 (Textarea) --%>
                <div class="form-group">
                    <label for="contents">내용</label>
                    <textarea name="contents" id="contents" rows="5" class="form-control" required></textarea>
                </div>

                <%-- 💡 질문하기 버튼 --%>
                <button type="submit" class="btn btn-success clearfix pull-right">질문하기</button>
                <div class="clearfix"/>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../js/scripts.js"></script>
</body>
</html>