var prefix = "/sys/user"
$(function() {
	var deptId = '';
	getTreeData();
	load(deptId);
});

function load(deptId) {
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // サーバーデータのリクエスト方式 get or post
				url : prefix + "/list", // サーバーデータの読み込み先URL
				// showRefresh : true,
				// showToggle : true,
				// showColumns : true,
				iconSize : 'outline',
				toolbar : '#exampleToolbar',
				striped : true, // trueにすると行ごとに背景色が変わります
				dataType : "json", // サーバーが返すデータ型
				pagination : true, // trueにすると下部にページネーションを表示します
				// queryParamsType : "limit",
				// // limitに設定するとRESTFull形式のパラメータを送信します
				singleSelect : false, // trueにすると複数選択が禁止されます
				// contentType : "application/x-www-form-urlencoded",
				// // サーバーに送信するデータのエンコード形式
				pageSize : 10, // ページネーション設定時の1ページあたりの件数
				pageNumber : 1, // ページネーション設定時の初期ページ番号
				// search : true, // 検索ボックスを表示するか
				showColumns : false, // 列選択ドロップダウンを表示するか（表示する列を選択）
				sidePagination : "server", // ページネーションの処理場所（"client" または
				// "server"
				queryParams : function(params) {
					return {
						// 説明：バックエンドに渡すパラメータには offset 開始インデックス、limit ステップ幅、sort ソート列、order: desc、および全列のキーと値のペアが含まれます
						limit : params.limit,
						offset : params.offset,
						name : $('#searchName').val(),
						deptId : deptId
					};
				},
				// // サーバーにデータをリクエストする際、パラメータを上書きして追加できます（例: toolbar のパラメータ）。
				// queryParamsType = 'limit' の場合、戻り値には以下を含める必要があります:
				// limit, offset, search, sort, order それ以外の場合は以下を含める必要があります:
				// pageSize, pageNumber, searchText, sortName,
				// sortOrder.
				// false を返すとリクエストが中止されます
				columns : [
					{
						checkbox : true
					},
					{
						field : 'userId', // カラムフィールド名
						title : 'No.' // 列のタイトル
					},
					{
						field : 'name',
						title : '氏名'
					},
					{
						field : 'username',
						title : 'アカウント'
					},
					{
						field : 'email',
						title : 'メールアドレス'
					},
					{
						field : 'status',
						title : '状態',
						align : 'center',
						formatter : function(value, row, index) {
							if (value == '0') {
								return '<span class="label label-danger">無効</span>';
							} else if (value == '1') {
								return '<span class="label label-primary">正常</span>';
							}
						}
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a  class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="編集" onclick="edit(\''
								+ row.userId
								+ '\')"><i class="fa fa-edit "></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="削除"  mce_href="#" onclick="remove(\''
								+ row.userId
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm ' + s_resetPwd_h + '" href="#" title="パスワードリセット"  mce_href="#" onclick="resetPwd(\''
								+ row.userId
								+ '\')"><i class="fa fa-key"></i></a> ';
							return e + d + f;
						}
					} ]
			});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function add() {
	// iframeレイヤー
	layer.open({
		type : 2,
		title : 'ユーザー新規追加',
		maxmin : true,
		shadeClose : false, // マスククリックでレイヤーを閉じる
		area : [ '800px', '520px' ],
		content : prefix + '/add'
	});
}
function remove(id) {
	layer.confirm('選択したレコードを削除してもよろしいですか？', {
		btn : [ '確定', 'キャンセル' ]
	}, function() {
		$.ajax({
			url : "/sys/user/remove",
			type : "post",
			data : {
				'id' : id
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
function edit(id) {
	layer.open({
		type : 2,
		title : 'ユーザー編集',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframeのURL
	});
}
function resetPwd(id) {
	layer.open({
		type : 2,
		title : 'パスワードリセット',
		maxmin : true,
		shadeClose : false, // マスククリックでレイヤーを閉じる
		area : [ '400px', '260px' ],
		content : prefix + '/resetPwd/' + id // iframeのURL
	});
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
			ids[i] = row['userId'];
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
function getTreeData() {
	$.ajax({
		type : "GET",
		url : "/system/sysDept/tree",
		success : function(tree) {
			loadTree(tree);
		}
	});
}
function loadTree(tree) {
	$('#jstree').jstree({
		'core' : {
			'data' : tree
		},
		"plugins" : [ "search" ]
	});
	$('#jstree').jstree().open_all();
}
$('#jstree').on("changed.jstree", function(e, data) {
	if (data.selected == -1) {
		var opt = {
			query : {
				deptId : '',
			}
		}
		$('#exampleTable').bootstrapTable('refresh', opt);
	} else {
		var opt = {
			query : {
				deptId : data.selected[0],
			}
		}
		$('#exampleTable').bootstrapTable('refresh',opt);
	}

});