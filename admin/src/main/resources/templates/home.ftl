<!doctype html>
<html lang="en">
<head>
<#include "comment.ftl">
<link rel="stylesheet" type="text/css" href="/static/css/home.css">
    <title>登录页面</title>
</head>
<body>
<section class="g-login u-pos-re">
    <div style="height: 20%"></div>
    <div class="g-login-bd">
        <div class="g-login-container">
            <div class="g-login-main">
                <div class="login-tab" style="display: block;">
                    <form id="login-form"  method="post" class="g-login-form" action="/login/proving">
                        <label class="u-pos-re">
                            <input type="text" class="ui-login-input" placeholder="用户名" name="username" id="username"/>
                            <img src="/static/vendor/image/login-input-user.png" alt="" class="img-loginInput"/>
                        </label>
                        <label class="u-pos-re">
                            <input type="password" class="ui-login-input" placeholder="密码" name="password" id="password"/>
                            <img src="/static/vendor/image/login-input-pd.png" alt="" class="img-loginInput"/>
                        </label>

                        <input type="submit" value="登&nbsp;&nbsp;录" class="g-login-btn ui-btn-primary"/>
                    </form>
                    <p>${(message)!}</p>
                </div>

            </div>
        </div>
    </div>
</section>



</body>
<script type="text/javascript" src="/static/js/home.js?_=${.now?string('yyyyMMddHHmmss')}"></script>
</html>
