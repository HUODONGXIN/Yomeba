var prefix = "/monitor/bugLog"
$(function () { load(); });
$('#exampleTable').on('load-success.bs.table', function (e, data) {
    if (data.total && !data.rows.length) {
        $('#exampleTable').bootstrapTable('selectPage').bootstrapTable('refresh');
    }
});

function load() {
    $('#exampleTable').bootstrapTable({
        method: 'get',
        url: prefix + "/list",
        iconSize: 'outline',
        toolbar: '#exampleToolbar',
        striped: true,
        dataType: "json",
        pagination: true,
        singleSelect: false,
        pageSize: 15,
        pageNumber: 1,
        sidePagination: "server",
        queryParams: function (params) {
            return {
                limit: params.limit,
                offset: params.offset,
                sort: 'gmt_create',
                order: 'desc',
                level: $('#searchLevel').val(),
                message: $('#searchMessage').val(),
                url: $('#searchUrl').val()
            };
        },
        columns: [
            { checkbox: true },
            { field: 'id', title: 'ID' },
            {
                field: 'level', title: 'ログレベル',
                formatter: function (v) {
                    var color = v === 'ERROR' ? 'danger' : (v === 'WARN' ? 'warning' : 'info');
                    return '<span class="label label-' + color + '">' + v + '</span>';
                }
            },
            { field: 'message', title: 'エラーメッセージ', formatter: function (v) { return v ? v.substring(0, 80) : ''; } },
            { field: 'url', title: 'URL' },
            { field: 'ip', title: 'IP' },
            { field: 'gmtCreate', title: '日時' },
            {
                title: '操作', field: 'id', align: 'center',
                formatter: function (value, row) {
                    return '<a class="btn btn-info btn-sm" onclick="viewStack(' + row.id + ',\'' + (row.message||'').replace(/'/g,"\\'") + '\')"><i class="fa fa-eye"></i> 詳細</a> '
                         + '<a class="btn btn-warning btn-sm" onclick="remove(' + row.id + ')"><i class="fa fa-remove"></i></a>';
                }
            }
        ]
    });
}

function viewStack(id, message) {
    $.get(prefix + "/list?limit=1&offset=0&message=" + encodeURIComponent(message), function (data) {
        // 行データのstackをそのまま使用
        var rows = $('#exampleTable').bootstrapTable('getData');
        var row = rows.find(function (r) { return r.id == id; });
        var stack = row ? (row.stack || 'スタックトレースなし') : 'データがありません';
        layer.open({
            type: 1, title: 'エラー詳細 #' + id, area: ['700px', '500px'],
            content: '<div style="padding:15px;max-height:430px;overflow:auto;white-space:pre-wrap;font-family:monospace;font-size:12px;">' + stack + '</div>'
        });
    });
}

function reLoad() { $('#exampleTable').bootstrapTable('refresh'); }

function remove(id) {
    layer.confirm('このレコードを削除してもよろしいですか？', { btn: ['確定', 'キャンセル'] }, function () {
        $.post(prefix + "/remove", { id: id }, function (r) {
            if (r.code == 0) { layer.msg('削除しました'); reLoad(); }
            else layer.msg(r.msg);
        });
    });
}

function batchRemove() {
    var rows = $('#exampleTable').bootstrapTable('getSelections');
    if (rows.length == 0) { layer.msg("選択してください"); return; }
    layer.confirm(rows.length + "件削除してもよろしいですか？", { btn: ['確定', 'キャンセル'] }, function () {
        var ids = rows.map(function (r) { return r.id; });
        $.post(prefix + "/batchRemove", { "ids[]": ids }, function (r) {
            if (r.code == 0) { layer.msg('削除しました'); reLoad(); }
            else layer.msg(r.msg);
        });
    });
}
