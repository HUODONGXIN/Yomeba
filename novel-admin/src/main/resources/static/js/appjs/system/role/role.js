var prefix = "/sys/role";
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // サーバーデータのリクエスト方式 get or post
						url : prefix + "/list", // サーバーデータの読み込み先URL
						striped : true, // trueにすると行ごとに背景色が変わります
						dataType : "json", // サーバーが返すデータ型
						pagination : true, // trueにすると下部にページネーションを表示します
						// queryParamsType : "limit",
						// // limitに設定するとRESTFull形式のパラメータを送信します
						singleSelect : false, // trueにすると複数選択が禁止されます
						iconSize : 'outline',
						toolbar : '#exampleToolbar',
						// contentType : "application/x-www-form-urlencoded",
						// // サーバーに送信するデータのエンコード形式
						pageSize : 10, // ページネーション設定時の1ページあたりの件数
						pageNumber : 1, // ページネーション設定時の初期ページ番号
						search : true, // 検索ボックスを表示するか
						showColumns : true, // 列選択ドロップダウンを表示するか（表示する列を選択）
						sidePagination : "client", // ページネーションの処理場所（"client" または
						// "server"
						// queryParams : queryParams,
						// // サーバーにデータをリクエストする際、パラメータを上書きして追加できます（例: toolbar のパラメータ）。
						// queryParamsType = 'limit' の場合、戻り値には以下を含める必要があります:
						// limit, offset, search, sort, order それ以外の場合は以下を含める必要があります:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// false を返すとリクエストが中止されます
						columns : [
								{ // カラム設定項目
									// データ型。詳細パラメータ設定はドキュメントを参照 http://bootstrap-table.wenzhixin.net.cn/zh-cn/documentation/
									checkbox : true
								// 一覧にチェックボックスを表示
								},
								{
									field : 'roleId', // カラムフィールド名
									title : 'No.' // 列のタイトル
								},
								{
									field : 'roleName',
									title : 'ロール名'
								},
								{
									field : 'remark',
									title : '備考'
								},
								{
									field : '',
									title : '権限'
								},
								{
									title : '操作',
									field : 'roleId',
									align : 'center',
									formatter : function(value, row, index) {
										var e = '<a class="btn btn-primary btn-sm '+s_edit_h+'" href="#" mce_href="#" title="編集" onclick="edit(\''
												+ row.roleId
												+ '\')"><i class="fa fa-edit"></i></a> ';
										var d = '<a class="btn btn-warning btn-sm '+s_remove_h+'" href="#" title="削除"  mce_href="#" onclick="remove(\''
												+ row.roleId
												+ '\')"><i class="fa fa-remove"></i></a> ';
										return e + d;
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
		title : 'ロール新規追加',
		maxmin : true,
		shadeClose : false, // マスククリックでレイヤーを閉じる
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframeのURL
	});
}
function remove(id) {
	layer.confirm('選択したレコードを削除してもよろしいですか？', {
		btn : [ '確定', 'キャンセル' ]
	}, function() {
		$.ajax({
			url : prefix + "/remove",
			type : "post",
			data : {
				'id' : id
			},
			success : function(r) {
				if (r.code === 0) {
					layer.msg("削除しました");
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
		title : 'ロール編集',
		maxmin : true,
		shadeClose : true, // マスククリックでレイヤーを閉じる
		area : [ '800px', '520px' ],
		content : prefix + '/edit/' + id // iframeのURL
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
	}, function() {
		var ids = new Array();
		$.each(rows, function(i, row) {
			ids[i] = row['roleId'];
		});
		console.log(ids);
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