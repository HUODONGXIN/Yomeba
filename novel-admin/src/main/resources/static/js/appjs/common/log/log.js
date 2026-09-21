var prefix = "/common/log"
$(function () {
    load();

});
$('#exampleTable').on('load-success.bs.table', function (e, data) {
    if (data.total && !data.rows.length) {
        $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
    }
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // サーバーデータのリクエスト方式 get or post
                url: prefix + "/list", // サーバーデータの読み込み先URL
                // showRefresh : true,
                // showToggle : true,
                // showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // trueにすると行ごとに背景色が変わります
                dataType: "json", // サーバーが返すデータ型
                pagination: true, // trueにすると下部にページネーションを表示します
                // queryParamsType : "limit",
                // // limitに設定するとRESTFull形式のパラメータを送信します
                singleSelect: false, // trueにすると複数選択が禁止されます
                // contentType : "application/x-www-form-urlencoded",
                // // サーバーに送信するデータのエンコード形式
                pageSize: 10, // ページネーション設定時の1ページあたりの件数
                pageNumber: 1, // ページネーション設定時の初期ページ番号
                // search : true, // 検索ボックスを表示するか
                // showColumns : true, // 列選択ドロップダウンを表示するか（表示する列を選択）
                sidePagination: "server", // ページネーションの処理場所（"client" または
                // "server"
                queryParams: function (params) {
                    return {
                        limit: params.limit,
                        offset: params.offset,
                        name: $('#searchName').val(),
                        sort: 'gmt_create',
                        order: 'desc',
                        operation: $("#searchOperation").val(),
                        username: $("#searchUsername").val()
                    };
                },
                // // サーバーにデータをリクエストする際、パラメータを上書きして追加できます（例: toolbar のパラメータ）。
                // queryParamsType = 'limit' の場合、戻り値には以下を含める必要があります:
                // limit, offset, search, sort, order それ以外の場合は以下を含める必要があります:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // false を返すとリクエストが中止されます
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        field: 'id', // カラムフィールド名
                        title: 'No.' // 列のタイトル
                    },
                    {
                        field: 'userId',
                        title: 'ユーザーID'
                    },
                    {
                        field: 'username',
                        title: 'アカウント'
                    },
                    {
                        field: 'operation',
                        title: '操作'
                    },
                    {
                        field: 'time',
                        title: '処理時間'
                    },
                    {
                        field: 'method',
                        title: 'メソッド'
                    },
                    {
                        field: 'params',
                        title: 'パラメータ'
                    },
                    {
                        field: 'ip',
                        title: 'IPアドレス'
                    },
                    {
                        field: 'gmtCreate',
                        title: '作成日時'
                    },
                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var e = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="編集" onclick="edit(\''
                                + row.userId
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var d = '<a class="btn btn-warning btn-sm" href="#" title="削除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            var f = '<a class="btn btn-success btn-sm" href="#" title="パスワードリセット"  mce_href="#" onclick="resetPwd(\''
                                + row.userId
                                + '\')"><i class="fa fa-key"></i></a> ';
                            return d;
                        }
                    }]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
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
            beforeSend: function (request) {
                index = layer.load();
            },
            success: function (r) {
                if (r.code == 0) {
                    layer.close(index);
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 選択中の全行を返します。選択レコードがない場合は空配列を返します
    if (rows.length == 0) {
        layer.msg("削除するデータを選択してください");
        return;
    }
    layer.confirm("選択した'" + rows.length + "'件のデータを削除してもよろしいですか?", {
        btn: ['確定', 'キャンセル']
        // ボタン
    }, function () {
        var ids = new Array();
        // 選択中の全行を走査し、各行に対応するIDを取得します
        $.each(rows, function (i, row) {
            ids[i] = row['id'];
        });
        $.ajax({
            type: 'POST',
            data: {
                "ids": ids
            },
            url: prefix + '/batchRemove',
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    }, function () {
    });
}