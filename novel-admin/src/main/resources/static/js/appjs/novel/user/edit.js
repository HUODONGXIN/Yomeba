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
    editor.txt.html($("#" + relName).val());

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
        update();
    }
});
function update() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/novel/user/update",
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
    $("#signupForm").validate({
        ignore: "",
        rules: {
                                                                                                                                },
    messages: {
                                                                                                                                                                                                                    }
})
}
