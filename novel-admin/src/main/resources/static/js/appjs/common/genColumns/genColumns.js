var prefix = "/common/generator/genColumns";
var columnsData = [];
var tableName = "";
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // サーバーデータのリクエスト方式 get or post
                url: prefix + "/list?tableName=" + $("#tableName").val(), // サーバーデータの読み込み先URL
                //	showRefresh : true,
                //	showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // trueにすると行ごとに背景色が変わります
                dataType: "json", // サーバーが返すデータ型
                pagination: false, // trueにすると下部にページネーションを表示します
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
                // // サーバーにデータをリクエストする際、パラメータを上書きして追加できます（例: toolbar のパラメータ）。
                // queryParamsType = 'limit' の場合、戻り値には以下を含める必要があります:
                // limit, offset, search, sort, order それ以外の場合は以下を含める必要があります:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // false を返すとリクエストが中止されます
                responseHandler: function (rs) {

                    if (rs.code == 0) {
                        columnsData[0]=rs.data.rows[0];
                        tableName=columnsData[0].tableName;
                        rs.data.rows.splice(0,1);
                        return rs.data;
                    } else {
                        parent.layer.alert(rs.msg)
                        return {total: 0, rows: []};
                    }
                },
                onPostBody: function() {
                    loadDict();

                    $.ajax({
                        url : '/common/dict/type',
                        success : function(data) {
                            $("select[name=dictType]").each(function (index, domEle) {
                                var html = "";
                                // データを読み込み
                                for (var i = 0; i < data.length; i++) {
                                    html += '<option value="' + data[i].type + '">' + data[i].description + '</option>'
                                }
                                $(domEle).append(html);
                                $(domEle).chosen({
                                    maxHeight: 200
                                });
                                $(domEle).val($(domEle).attr("select-value"));
                                $(domEle).trigger("chosen:updated");

                            });


                        }
                    });
                },
                columns: [
                    {
                        title: 'No.',
                        formatter: function () {
                            return arguments[2] + 1;
                        }
                    },
                    {
                        field: 'columnName',
                        title: 'カラム名'
                    },
                    {
                        field: 'columnType',
                        title: 'カラム型'
                    },
                    {
                        field: 'javaType',
                        title: 'マッピングJava型',
                        formatter: function (value, row, index) {

                            return "<select style='width: 100px' class=\"form-control chosen-select\" tabindex=\"2\" dict-value='"+value+"' dict-type=\"java_type\" >\n" +
                                "                        </select>";
                        }
                    },
                    {
                        field: 'columnComment',
                        title: 'カラムコメント'
                    },
                    {
                        field: 'columnLabel',
                        title: 'カラムラベル名',
                        formatter: function (value, row, index) {

                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
                        }
                    },
                    { /*<select data-placeholder="--カテゴリを選択--" name="catid" id="catid"
                    class="form-control chosen-select" tabindex="2" dict-type="novel_category" >
                        </select>*/
                        field: 'pageType',
                        title: '画面表示タイプ',
                        formatter: function (value, row, index) {

                            return "<select style='width: 100px' class=\"form-control chosen-select\" tabindex=\"2\" dict-value='"+value+"' dict-type=\"page_type\" >\n" +
                                "                        </select>";
                        }
                    },
                    {
                        field: 'dictType',
                        title: 'データ辞書型',
                        formatter: function (value, row, index) {
                            return "<select name='dictType' style='width: 150px' class=\"form-control chosen-select\" tabindex=\"2\" select-value='"+value+"' >\n" +
                                "                        </select>";

                        }
                    },
                    {
                        field: 'isRequired',
                        title: '必須かどうか',
                        formatter: function (value, row, index) {
                            return "<input class=\"form-control\" type='checkbox' "+(value==1?'checked':'')+"/>";
                        }
                    },
                    {
                        field: 'columnSort',
                        title: 'カラム並び順（昇順）',
                        formatter: function (value, row, index) {
                            return "<input style='width: 100px' class=\"form-control\" type='text' value='"+value+"'/>";
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


function save() {

    $('#exampleTable').find("tbody").find("tr").each(function (index, trEle){
        var columnData = {};
        columnData.tableName = tableName;
        columnsData[index+1]=columnData;

        $(trEle).find("td").each(function (index, tdEle){
            switch (index) {
                case 1:{
                    columnData.columnName = $(tdEle).text();
                    break;
                }
                case 2:{
                    columnData.columnType = $(tdEle).text();
                    break;
                }
                case 3:{
                    columnData.javaType = $(tdEle).find("select").eq(0).val();
                    break;
                }
                case 4:{
                    columnData.columnComment = $(tdEle).text();
                    break;
                }
                case 5:{
                    columnData.columnLabel = $(tdEle).find("input").eq(0).val();
                    break;
                }
                case 6:{
                    columnData.pageType = $(tdEle).find("select").eq(0).val();
                    break;
                }
                case 7:{
                    columnData.dictType = $(tdEle).find("select").eq(0).val();
                    break;
                }
                case 8:{
                    columnData.isRequired = $(tdEle).find("input").eq(0).is(':checked')?1:0;
                    break;
                }
                case 9:{
                    columnData.columnSort = $(tdEle).find("input").eq(0).val();
                    break;
                }



            }

        });
    });

console.log(columnsData)
    $.ajax({
        cache : true,
        type : "POST",
        url : prefix+"/save",
        headers : {
            "Content-Type": "application/json"
        },
        data : JSON.stringify(columnsData),
        async : false,
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
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