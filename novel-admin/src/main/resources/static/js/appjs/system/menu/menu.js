var prefix = "/sys/menu"
$(document).ready(function () {
    load();
});
var load = function () {
    $('#exampleTable')
        .bootstrapTreeTable(
            {
                id: 'menuId',
                code: 'menuId',
                parentCode: 'parentId',
                type: "GET", // データリクエストのajaxタイプ
                url: prefix + '/list', // データリクエストのajax URL
                ajaxParams: {sort:'order_num'}, // データリクエストのajax data属性
                expandColumn: '1',// 展開ボタンを表示する列
                striped: true, // 行ごとに背景色を変えるか
                bordered: true, // 枠線を表示するか
                expandAll: false, // すべて展開するか
                // toolbar : '#exampleToolbar',
                columns: [
                    {
                        title: 'ID',
                        field: 'menuId',
                        visible: false,
                        align: 'center',
                        valign: 'center',
                        width: '5%'
                    },
                    {
                        title: '名称',
                        valign: 'center',
                        field: 'name',
                        width: '20%'
                    },

                    {
                        title: 'アイコン',
                        field: 'icon',
                        align: 'center',
                        valign: 'center',
                        width : '5%',
                        formatter: function (item, index) {
                            return item.icon == null ? ''
                                : '<i class="' + item.icon
                                + ' fa-lg"></i>';
                        }
                    },
                    {
                        title: 'タイプ',
                        field: 'type',
                        align: 'center',
                        valign: 'center',
                        width : '10%',
                        formatter: function (item, index) {
                            if (item.type === 0) {
                                return '<span class="label label-primary">ディレクトリ</span>';
                            }
                            if (item.type === 1) {
                                return '<span class="label label-success">メニュー</span>';
                            }
                            if (item.type === 2) {
                                return '<span class="label label-warning">ボタン</span>';
                            }
                        }
                    },
                    {
                        title: 'URL',
                        valign: 'center',
                        width : '20%',
                        field: 'url'
                    },
                    {
                        title: '権限識別子',
                        valign: 'center',
                        width : '20%',
                        field: 'perms'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        valign: 'center',
                        formatter: function (item, index) {
                            var e = '<a class="btn btn-primary btn-sm '
                                + s_edit_h
                                + '" href="#" mce_href="#" title="編集" onclick="edit(\''
                                + item.menuId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var p = '<a class="btn btn-primary btn-sm '
                                + s_add_h
                                + '" href="#" mce_href="#" title="下位を追加" onclick="add(\''
                                + item.menuId
                                + '\')"><i class="fa fa-plus"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm '
                                + s_remove_h
                                + '" href="#" title="削除"  mce_href="#" onclick="remove(\''
                                + item.menuId
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return e + d + p;
                        }
                    }]
            });
}

function reLoad() {
    load();
}

function add(pId) {
    layer.open({
        type: 2,
        title: 'メニュー新規追加',
        maxmin: true,
        shadeClose: false, // マスククリックでレイヤーを閉じる
        area: ['800px', '520px'],
        content: prefix + '/add/' + pId // iframeのURL
    });
}

function remove(id) {
    layer.confirm('選択したレコードを削除してもよろしいですか？', {
        btn: ['確定', 'キャンセル']
    }, function () {
        $.ajax({
            url: prefix + "/remove",
            type: "post",
            data: {
                'id': id
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg("削除しました");
                    reLoad();
                } else {
                    layer.msg(data.msg);
                }
            }
        });
    })
}

function edit(id) {
    layer.open({
        type: 2,
        title: 'メニュー編集',
        maxmin: true,
        shadeClose: false, // マスククリックでレイヤーを閉じる
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframeのURL
    });
}

function batchRemove() {
    // var rows = $('#exampleTable').bootstrapTable('getSelections');

}