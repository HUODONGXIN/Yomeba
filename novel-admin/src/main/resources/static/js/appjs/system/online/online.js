var prefix = "/sys/online"
$(function() {
	load();
});

function load() {
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
						sidePagination : "client", // ページネーションの処理場所（"client" または
						// "server"
						queryParams : function(params) {
							return {
								// 説明：バックエンドに渡すパラメータには offset 開始インデックス、limit ステップ幅、sort ソート列、order: desc、および全列のキーと値のペアが含まれます
								limit : params.limit,
								offset : params.offset,
								name : $('#searchName').val()
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
									field : 'id', // カラムフィールド名
									title : 'No.' // 列のタイトル
								},
								{
									field : 'username',
									title : 'アカウント'
								},
								{
									field : 'host',
									title : 'ホスト'
								},
								{
									field : 'startTimestamp',
									title : 'ログイン日時'
								},
								{
									field : 'lastAccessTime',
									title : '最終アクセス日時'
								},
								{
									field : 'timeout',
									title : '有効期限'
								},
								{
									field : 'status',
									title : '状態',
									align : 'center',
									formatter : function(value, row, index) {
										if (value == 'on_line') {
											return '<span class="label label-success">オンライン</span>';
										} else if (value == 'off_line') {
											return '<span class="label label-primary">オフライン</span>';
										}
									}
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										var d = '<a class="btn btn-warning btn-sm" href="#" title="削除"  mce_href="#" onclick="forceLogout(\''
												+ row.id
												+ '\')"><i class="fa fa-remove"></i></a> ';
										return d;
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
function forceLogout(id) {
	layer.confirm('選択中のユーザーを強制的にログアウトさせてもよろしいですか？', {
		btn : [ '確定', 'キャンセル' ]
	}, function() {
		$.ajax({
			url : prefix+"/forceLogout/" + id,
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
		shadeClose : true, // マスククリックでレイヤーを閉じる
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
	}, function() {
	});
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
		$('#exampleTable').bootstrapTable('refresh', opt);
	}

});