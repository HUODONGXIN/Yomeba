var prefix = "/system/roleDataPerm"
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // サーバーデータのリクエスト方式 get or post
                url: prefix + "/list", // サーバーデータの読み込み先URL
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
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
                //search : true, // 検索ボックスを表示するか
                showColumns: false, // 列選択ドロップダウンを表示するか（表示する列を選択）
                sidePagination: "server", // ページネーションの処理場所（"client" または "server"）
                queryParams: function (params) {
                    // 説明：バックエンドに渡すパラメータには offset 開始インデックス、limit ステップ幅、sort ソート列、order: desc、および全列のキーと値のペアが含まれます
                    var queryParams = getFormJson("searchForm");
                    queryParams.limit = params.limit;
                    queryParams.offset = params.offset;
                    return queryParams;
                },
                // // サーバーにデータをリクエストする際、パラメータを上書きして追加できます（例: toolbar のパラメータ）。
                // queryParamsType = 'limit' の場合、戻り値には以下を含める必要があります:
                // limit, offset, search, sort, order それ以外の場合は以下を含める必要があります:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // false を返すとリクエストが中止されます
                responseHandler: function (rs) {

                    if (rs.code == 0) {
                        return rs.data;
                    } else {
                        parent.layer.alert(rs.msg)
                        return {total: 0, rows: []};
                    }
                },
                columns: [
                    {
                        checkbox: true
                    },
                    {
                        title: 'No.',
                        formatter: function () {
                            return arguments[2] + 1;
                        }
                    },
                                                                        {
                                field: 'id',
                                title: ''
                            },

                        
                                                                        {
                                field: 'roleId',
                                title: 'ロールID'
                            },

                        
                                                                        {
                                field: 'permId',
                                title: '権限ID'
                            },

                        
                                        {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            var d = '<a class="btn btn-primary btn-sm ' + s_detail_h + '" href="#" mce_href="#" title="詳細" onclick="detail(\''
                                + row.id
                                + '\')"><i class="fa fa-file"></i></a> ';
                            var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="編集" onclick="edit(\''
                                + row.id
                                + '\')"><i class="fa fa-edit"></i></a> ';
                            var r = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="削除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return d + e + r;
                        }
                    }]
            });
}
function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}
function add() {
    layer.open({
        type: 2,
        title: '新規追加',
        maxmin: true,
        shadeClose: false, // マスククリックでレイヤーを閉じる
        area: ['800px', '520px'],
        content: prefix + '/add' // iframeのURL
    });
}
function detail(id) {
    layer.open({
        type: 2,
        title: '詳細',
        maxmin: true,
        shadeClose: false, // マスククリックでレイヤーを閉じる
        area: ['800px', '520px'],
        content: prefix + '/detail/' + id // iframeのURL
    });
}
function edit(id) {
    layer.open({
        type: 2,
        title: '編集',
        maxmin: true,
        shadeClose: false, // マスククリックでレイヤーを閉じる
        area: ['800px', '520px'],
        content: prefix + '/edit/' + id // iframeのURL
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
            success: function (r) {
                if (r.code == 0) {
                    layer.msg(r.msg);
                    reLoad();
                } else {
                    layer.msg(r.msg);
                }
            }
        });
    })
}

function resetPwd(id) {
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