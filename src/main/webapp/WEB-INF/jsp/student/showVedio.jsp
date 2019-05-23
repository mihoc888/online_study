<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>


<!DOCTYPE html>
<html>
<head>
    <title>教授课程</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>

</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"/>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-5">${courseName}</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <tr >
                        <td align="center">
                            <video width="800" height="600" controls="controls" autoplay="autoplay">
                                <source src="../vedio/v.mp4" type="video/mp4"/>
                            </video>
                        </td>
                    </tr>
                    <tr>
                        <td><button type="button" class="btn btn-default" id="addComment"
                                    onclick="answer(${comment.id})"   data-toggle="modal" data-target="#myModal2">提出问题</button></td>
                    </tr>
                    <tr>
                        <td >
                            <div>
                                    <div class="row" style="margin-left: 7%" class="panel panel-default">
                                        <c:forEach  items="${comments}" var="item" >
                                            <c:forEach items="${item}" var="comment" varStatus="idx">
                                                <tr>
                                                    <td>
                                                        <%! SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>
                                                            ${comment.userId}&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<script>document.write(new Date(${comment.createTime}).toLocaleString())</script>
                                                    </td>
                                                </tr>
                                                <tr class="panel-footer">
                                                    <c:if test="${idx.index==0}" var="<string>" >
                                                        <td>问&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;${comment.text}</td>
                                                    </c:if>
                                                    <c:if test="${idx.index==1}" var="<string>" >
                                                        <td>答&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;${comment.text}</td><br>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </c:forEach>
                                    </div>
                            </div>
                         </td>
                    </tr>
            </table>
            <div class="panel-footer">
                <c:if test="${pagingVO != null}">
                    <nav style="text-align: center">
                        <ul class="pagination">
                            <li><a href="${ctx}/student/showCourse?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
                            <li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
                            <c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
                                <li>
                                    <a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a>
                                </li>
                            </c:if>
                            <c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
                                <li>
                                    <a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a>
                                </li>
                            </c:if>
                            <c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
                                <li>
                                    <a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a>
                                </li>
                            </c:if>
                            <c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
                                <li>
                                    <a href="${ctx}/student/showCourse?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a>
                                </li>
                            </c:if>
                            <li><a href="${ctx}/student/showCourse?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>

    </div>
</div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
<%-- 模态框 --%>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">上传视频</h4>
            </div>
            <div class="modal-header">
                <form onsubmit="return check()" method="post" action="/upload/vedio" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name" id="vedioName" name="vedioName"></video>">视频名称</label>
                    <input type="text" class="form-control" id="name" name="name"
                           placeholder="请输入名称">
                </div>
                <div class="form-group">
                    <input type="file" id="file" name="file">
                </div>
                <input type="hidden" name="cid" id="cid">
                <button type="submit" class="btn btn-default" id="uploadButton">提交</button>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 模态框2（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="height: 350px;width: 600px">
            <div class="modal-body">
                <form onsubmit="return check2()" method="post" action="/student/add" enctype="multipart/form-data">
                    <input type="hidden" name="qid" id="qid">
                    <input type="hidden" name="courseId" id="courseId" value="${courseId}">
                    <textarea type="text" style="width: 570px;height: 250px" id="textarea" name="textarea">
                    </textarea>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">
                        提交
                    </button>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript">

    // 声明全局cid
    curcid = 0;
    quesid = 0;
    <%--设置菜单中--%>
    $("#nav li:nth-child(1)").addClass("active")
    <c:if test="${pagingVO != null}">
    if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
        $(".pagination li:last-child").addClass("disabled")
    }
    ;

    if (${pagingVO.curentPageNo} == ${1}) {
        $(".pagination li:nth-child(1)").addClass("disabled")
    }
    ;
    </c:if>

    $("#sub").click(function () {
        $("#form1").submit();
    });

    function check() {
        var fileName = $("#name").val();
        if (typeof(fileName) != "undefined" && fileName != null) {
            $("#cid").val(curcid);
            return true;
        } else {
            return false;
        }
    }
    function check2() {
        var textarea = $("#textarea").val();
        alert(textarea)
        if (typeof(textarea) != "undefined" && textarea != null) {
            alert("hahah");
            $("#qid").val(quesid);
            return true;
        } else {
            return false;
        }
    }

    function parseCid(cid) {
        curcid = cid;
    }
    function answer(qid) {
        quesid = qid;
    }

</script>
</html>