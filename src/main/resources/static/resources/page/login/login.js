layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer;
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    });

    //登录按钮
    form.on("submit(login)",function(data){
        console.log(data.field);
        var btn =  $(this);
        //将密码加密
        var encrypt = new JSEncrypt();
        encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnuNmfvm/Xec4NU39TjIkwZi9B4NwyOTVJ03X06V7+gR4rBoFRXqWkNHLC65Z2r6npCcx6ygcc5y2IswDYBh4l5mSx7fLxhWOSWxMOksdRkMgivJmIZALeabvd7G5FvHD0e9LX2dQwjy5WHFckTK4lCCmkKy/6lTZjDZe8gF7L7wIDAQAB");
        var data1 = encrypt.encrypt(data.field.loginName + "|||" + data.field.pwd);
        var code = data.field.code;

        $.post("/login/login", {data: data1, code: code}, function (result) {
            //设置登录按钮 恢复可点击   在前端防止 重复点击
            btn.text("登录").attr("disabled",false).removeClass("layui-disabled");
            layer.msg(result.msg);
            if(result.code != 200){
                layer.msg(result.msg);
            }else{
                //跳转到templates/system/index/index.html页面
                layer.msg(result.msg);
                location.href="/sys/index";
            }
        });


        return false;
    });

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    });
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
});
