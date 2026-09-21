
var prefix = "/system/sysDept"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
		.bootstrapTreeTable(
			{
				id : 'deptId',
				code : 'deptId',
                parentCode : 'parentId',
				type : "GET", // データリクエストのajaxタイプ
				url : prefix + '/list', // データリクエストのajax URL
				ajaxParams : {}, // データリクエストのajax data属性
				expandColumn : '1', // 展開ボタンを表示する列
				striped : true, // 行ごとに背景色を変えるか
				bordered : true, // 枠線を表示するか
				expandAll : false, // すべて展開するか
				// toolbar : '#exampleToolbar',
				columns : [
					{
						title : 'ID',
						field : 'deptId',
						visible : false,
						align : 'center',
						valign : 'center',
						width : '50px',
						checkbox : true
					},
					{
						field : 'name',
						title : '部門名',
                        valign : 'center',
						witth :20
					},
					{
						field : 'orderNum',
						title : '並び順',
                        align : 'center',
                        valign : 'center',
					},
					{
						field : 'delFlag',
						title : '状態',
						align : 'center',
                        valign : 'center',
						formatter : function(item, index) {
							if (item.delFlag == '0') {
								return '<span class="label label-danger">無効</span>';
							} else if (item.delFlag == '1') {
								return '<span class="label label-primary">正常</span>';
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
                        valign : 'center',
						formatter : function(item, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="編集" onclick="edit(\''
								+ item.deptId
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var a = '<a class="btn btn-primary btn-sm ' + s_add_h + '" href="#" title="下位を新規追加"  mce_href="#" onclick="add(\''
								+ item.deptId
								+ '\')"><i class="fa fa-plus"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="削除"  mce_href="#" onclick="removeone(\''
								+ item.deptId
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm＂ href="#" title="予備"  mce_href="#" onclick="resetPwd(\''
								+ item.deptId
								+ '\')"><i class="fa fa-key"></i></a> ';
							return e + a + d;
						}
					} ]
			});
}
function reLoad() {
	load();
}
function add(pId) {
	layer.open({
		type : 2,
		title : '新規追加',
		maxmin : true,
		shadeClose : false, // マスククリックでレイヤーを閉じる
		area : [ '800px', '520px' ],
		content : prefix + '/add/' + pId
	});
}
function edit(id) {
	layer.open({
		type : 2,
		title : '編集',
		maxmin : true,
		shadeClose : false, // マスククリックでレイヤーを閉じる
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframeのURL
	});
}
function removeone(id) {
	layer.confirm('選択したレコードを削除してもよろしいですか？', {
		btn : [ '確定', 'キャンセル' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'deptId' : id
			},
			success : function(r) {
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
		btn : [ '確定', 'キャンセル' ]
	// ボタン
	}, function() {
		var ids = new Array();
		// 選択中の全行を走査し、各行に対応するIDを取得します
		$.each(rows, function(i, row) {
			ids[i] = row['deptId'];
		});
		$.ajax({
			type : 'POST',
			data : {
				"ids" : ids
			},
			url : prefix + '/batchRemove',
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
					reLoad();
				} else {
					layer.msg(r.msg);
				}
			}
		});
	}, function() {});
}

