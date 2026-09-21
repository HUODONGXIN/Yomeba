var prefix = "/novel/book"
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
                striped: true, // true に設定すると行ごとに背景色が変わります
                dataType: "json", // サーバーが返すデータ型
                pagination: true, // true に設定すると下部にページネーションが表示されます
                // queryParamsType : "limit",
                // //limit に設定すると RESTful 形式のパラメータが送信されます
                singleSelect: false, // true に設定すると複数選択が禁止されます
                // contentType : "application/x-www-form-urlencoded",
                // //サーバーに送信するデータのエンコード形式
                pageSize: 10, // ページネーションを設定した場合、1ページあたりのデータ件数
                pageNumber: 1, // ページネーションを設定した場合、最初のページ番号
                //search : true, // 検索ボックスを表示するか
                showColumns: false, // 表示する列を選択するドロップダウンを表示するか
                sidePagination: "server", // ページネーションをどこで行うか（"client" または "server"）
                queryParams: function (params) {
                    //説明：バックエンドに渡すパラメータには offset 開始インデックス、limit ステップ幅、sort ソート列、order：desc など、および全列のキーと値のペアが含まれます
                    var queryParams = getFormJson("searchForm");
                    queryParams.limit = params.limit;
                    queryParams.offset = params.offset;
                    return queryParams;
                },
                // //サーバーデータをリクエストする際、パラメータを上書きして追加のパラメータ（例：toolbar 内のパラメータ）を追加できます
                // queryParamsType = 'limit' の場合、戻り値のパラメータには以下を含める必要があります
                // limit, offset, search, sort, order それ以外の場合は以下を含める必要があります：
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // false を返すとリクエストが中断されます
                responseHandler: function (rs) {

                    if (rs.code == 0) {
                        return rs.data;
                    } else {
                        parent.layer.alert(rs.msg)
                        return {total: 0, rows: []};
                    }
                },
                onCheck: function () { toggleBatchDeleteBtn(); },
                onUncheck: function () { toggleBatchDeleteBtn(); },
                onCheckAll: function () { toggleBatchDeleteBtn(); },
                onUncheckAll: function () { toggleBatchDeleteBtn(); },
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
                        title: '小説ID'
                    },

                    {
                        field: 'workDirection',
                        title: '作品方向',
                        formatter: function (value, row, index) {
                            return value === 0 ? '男性向け' : '女性向け';
                        }
                    },

                    {
                        field: 'catName',
                        title: 'ジャンル名'
                    },

                    {
                        field: 'bookName',
                        title: '小説名'
                    },

                    {
                        field: 'authorName',
                        title: '作家名'
                    },

                    {
                        field: 'bookStatus',
                        title: '作品状態',
                        formatter: function (value, row, index) {
                            return value === 0 ? '連載中' : '完結';
                        }
                    },

                    {
                        field: 'visitCount',
                        title: 'アクセス数'
                    },

                    {
                        field: 'wordCount',
                        title: '総文字数'
                    },

                    {
                        field: 'commentCount',
                        title: 'コメント数'
                    },

                    {
                        field: 'yesterdayBuy',
                        title: '昨日購読数'
                    },

                    {
                        field: 'lastIndexName',
                        title: '最新話'
                    },


                    {
                        field: 'lastIndexUpdateTime',
                        title: '最新話更新日時'
                    },


                    {
                        field: 'isVip',
                        title: '有料か',
                        formatter: function (value, row, index) {
                            return value === 1 ? '有料' : '無料';
                        }
                    },

                    {
                        field: 'createTime',
                        title: '登録日時'
                    },

                    {
                        title: '操作',
                        field: 'id',
                        align: 'center',
                        formatter: function (value, row, index) {
                            // ダウンロードボタンを追加
                            var d = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="TXTをダウンロード" onclick="downloadBook(\''
                                + row.id
                                + '\',\'' + row.bookName + '\')"><i class="fa fa-cloud-download"></i></a><br><br> ';
                            var r = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="削除"  mce_href="#" onclick="remove(\''
                                + row.id
                                + '\')"><i class="fa fa-remove"></i></a> ';
                            return d + r;
                        }
                    }

                ]
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
        shadeClose: false, // オーバーレイをクリックしてレイヤーを閉じる
        area: ['800px', '520px'],
        content: prefix + '/add' // iframeのURL
    });
}

function detail(id) {
    layer.open({
        type: 2,
        title: '詳細',
        maxmin: true,
        shadeClose: false, // オーバーレイをクリックしてレイヤーを閉じる
        area: ['800px', '520px'],
        content: prefix + '/detail/' + id // iframeのURL
    });
}

function edit(id) {
    layer.open({
        type: 2,
        title: '編集',
        maxmin: true,
        shadeClose: false, // オーバーレイをクリックしてレイヤーを閉じる
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

function toggleBatchDeleteBtn() {
    var rows = $('#exampleTable').bootstrapTable('getSelections');
    $('#batchDelBtn').toggle(rows.length > 0);
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections'); // 選択された全行を返します。選択されたレコードがない場合は空配列を返します
    if (rows.length == 0) {
        layer.msg("削除するデータを選択してください");
        return;
    }
    layer.confirm("選択した'" + rows.length + "'件のデータを削除してもよろしいですか?", {
        btn: ['確定', 'キャンセル']
        // ボタン
    }, function () {
        var ids = new Array();
        // 選択された全行データを走査し、各データに対応するIDを取得します
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

function downloadBook(bookId, bookName) {
    window.open(prefix + '/download?bookId='+bookId+'&bookName='+bookName);

}
