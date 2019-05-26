<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>
    <style type="text/css">
        body {
            background: url(${ctx}/images/a.jpg) repeat;
        }

        #login-box {
            padding: 35px;
            border-radius: 15px;
            background: #56666B;
            color: #fff;
        }

    </style>
</head>
<body>
<div class="container" id="top">
    <div class="row" style="margin-top: 280px; ">
        <div class="col-md-4"></div>
        <div class="col-md-4" id="login-box">
            <h2>&nbsp;&nbsp;在线课程学习系统</h2>
            <div></div>
            <form onsubmit="return checkPass()" class="form-horizontal" role="form" action="${ctx}/register" id="from1"
                  method="post">
                <div class="form-group">
                    <label class="col-sm-3 control-label">名称：</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" placeholder="请输入登录名" name="name" required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">密码：</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password"
                               required="required">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">确认：</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" id="password2" placeholder="请确认密码"
                               required="required">
                    </div>
                </div>

                <div class="form-group pull-right" style="margin-right: 15px;">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default btn-info">确认</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-4"></div>
    </div>
</div>
</body>
<script>
    function checkPass() {
        var p1 = $("#password").val();
        var p2 = $("#password2").val();
        var name = $("#name").val();
        if ( name ? false : true) {
            return "名称不能为空";
        }
        if (p1 != p2) {
            alert("两次密码输入错误");
            return false;
        }
        return true;
    }
</script>
</html>