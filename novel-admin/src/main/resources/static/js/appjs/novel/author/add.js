var E = window.wangEditor;
$("[id^='contentEditor']").each(function (index, ele) {
    var relName = $(ele).attr("id").substring(13);
    var editor = new E('#contentEditor' + relName);
// カスタムメニュー設定
    editor.customConfig.menus = [
        'head',  // タイトル
        'bold',  // 太字
        'fontSize',  // フォントサイズ
        'fontName',  // フォント
        'italic',  // 斜体
        'underline',  // 下線
        'strikeThrough',  // 取り消し線
        'foreColor',  // 文字色
        //'backColor',  // 背景色
        //'link',  // リンク挿入
        'list',  // リスト
        'justify',  // 配置
        'quote',  // 引用
        'emoticon',  // 絵文字
        'image',  // 画像挿入
        //'table',  // テーブル
        //'video',  // 動画挿入
        //'code',  // コード挿入
        'undo',  // 元に戻す
        'redo'  // やり直し
    ];
    editor.customConfig.onchange = function (html) {
        // html は変更後の内容
        $("#" + relName).val(html);
    }
    editor.customConfig.uploadImgShowBase64 = true;
    editor.create();

})

$("[id^='picImage']").each(function (index, ele) {
    var relName = $(ele).attr("id").substring(8);
    layui.use('upload', function () {
        var upload = layui.upload;
        //実行インスタンス
        var uploadInst = upload.render({
            elem: '#picImage' + relName, //要素をバインド
            url: '/common/sysFile/upload', //アップロードAPI
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
        data: $('#signupForm').serialize(),// あなたのformid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作が成功しました");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // ウィンドウインデックスを取得
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    //携帯電話番号フォーマットチェック（日本の携帯：070/080/090 始まり、計 11 桁）
    $.validator.addMethod("isMobile", function (value, element) {
        return this.optional(element) || /^0[789]0\d{8}$/.test(value);
    }, "正しい携帯電話番号の形式で入力してください");
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
            username: {required: icon + "ログインアカウントを入力してください"},
            password: {required: icon + "ログインパスワードを入力してください", minlength: icon + "ログインパスワードは6文字以上で入力してください"},
            penName: {required: icon + "ペンネームを入力してください"},
            telPhone: {required: icon + "携帯電話番号を入力してください", isMobile: icon + "正しい日本の携帯電話番号形式で入力してください（070/080/090 始まり計11桁）"},
            email: {email: icon + "正しいメールアドレスの形式で入力してください"},
            workDirection: {required: icon + "作品方向を選択してください"},
            createTime: {required: icon + "登録日時を選択してください"},
            status: {required: icon + "状態を入力してください（0：正常、1：利用停止）"}
        }
    })
}
