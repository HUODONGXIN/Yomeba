var E = window.wangEditor;
$("[id^='contentEditor']").each(function (index, ele) {
    var relName = $(ele).attr("id").substring(13);
    var editor = new E('#contentEditor' + relName);
// 自定义菜单配置
    editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'fontSize',  // 字号
        'fontName',  // 字体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        //'backColor',  // 背景颜色
        //'link',  // 插入链接
        'list',  // 列表
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        //'table',  // 表格
        //'video',  // 插入视频
        //'code',  // 插入代码
        'undo',  // 撤销
        'redo'  // 重复
    ];
    editor.customConfig.onchange = function (html) {
        // html 即变化之后的内容
        $("#" + relName).val(html);
    }
    editor.customConfig.uploadImgShowBase64 = true;
    editor.create();

})

$("[id^='picImage']").each(function (index, ele) {
    var relName = $(ele).attr("id").substring(8);
    layui.use('upload', function () {
        var upload = layui.upload;
        //执行实例
        var uploadInst = upload.render({
            elem: '#picImage' + relName, //绑定元素
            url: '/common/sysFile/upload', //上传接口
            size: 1000,
            accept: 'file',
            done: function (r) {
                $("#picImage" + relName).attr("src", r.fileName);
                $("#" + relName).val(r.fileName);
            },
            error: function (r) {
                layer.msg(r.msg);
            }
        });
    });

});






$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});
function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/novel/author/save",
        data: $('#signupForm').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    //自定义手机号格式校验（11 位，1 开头，第二位 3~9）
    $.validator.addMethod("isMobile", function (value, element) {
        return this.optional(element) || /^1[3-9]\d{9}$/.test(value);
    }, "请输入正确的手机号格式");
    $("#signupForm").validate({
        ignore: "",
        rules: {
            username: {required: true},
            password: {required: true, minlength: 6},
            penName: {required: true},
            telPhone: {required: true, isMobile: true},
            email: {email: true},
            workDirection: {required: true},
            createTime: {required: true},
            status: {required: true}
        },
        messages: {
            username: {required: icon + "请输入登录账号"},
            password: {required: icon + "请输入登录密码", minlength: icon + "登录密码至少6位"},
            penName: {required: icon + "请输入笔名"},
            telPhone: {required: icon + "请输入手机号码", isMobile: icon + "请输入正确的手机号格式（11位，1开头）"},
            email: {email: icon + "请输入正确的邮箱格式"},
            workDirection: {required: icon + "请选择作品方向"},
            createTime: {required: icon + "请选择入驻时间"},
            status: {required: icon + "请输入状态（0正常，1封禁）"}
        }
    })
}