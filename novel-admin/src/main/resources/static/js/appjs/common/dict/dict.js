
var prefix = "/common/dict"
$(function() {
	
	//	var config = {
	//		'.chosen-select' : {},
	//		'.chosen-select-deselect' : {
	//			allow_single_deselect : true
	//		},
	//		'.chosen-select-no-single' : {
	//			disable_search_threshold : 10
	//		},
	//		'.chosen-select-no-results' : {
	//			no_results_text : 'データがありません'
	//		},
	//		'.chosen-select-width' : {
	//			width : "95%"
	//		}
	//	}
	//	for (var selector in config) {
	//		$(selector).chosen(config[selector]);
	//	}
	load();
});
function selectLoad() {
	var html = "";
	$.ajax({
		url : '/common/dict/type',
		success : function(data) {
			// データを読み込み
			for (var i = 0; i < data.length; i++) {
				html += '<option value="' + data[i].type + '">' + data[i].description + '</option>'
			}
			$(".chosen-select").append(html);
			$(".chosen-select").chosen({
				maxHeight : 200
			});
			// クリックイベント
			$('.chosen-select').on('change', function(e, params) {
				console.log(params.selected);
				var opt = {
					query : {
						type : params.selected,
					}
				}
				$('#exampleTable').bootstrapTable('refresh', opt);
			});
		}
	});
}
function load() {
	selectLoad();
	$('#exampleTable')
		.bootstrapTable(
			{
				method : 'get', // サーバーデータのリクエスト方式 get or post
				url : prefix + "/list", // サーバーデータの読み込み先URL
				//	showRefresh : true,
				//	showToggle : true,
				//	showColumns : true,
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
				//search : true, // 検索ボックスを表示するか
				showColumns : false, // 列選択ドロップダウンを表示するか（表示する列を選択）
				sidePagination : "server", // ページネーションの処理場所（"client" または "server"）
				queryParams : function(params) {
					return {
					// 説明：バックエンドに渡すパラメータには offset 開始インデックス、limit ステップ幅、sort ソート列、order: desc、および全列のキーと値のペアが含まれます
						limit : params.limit,
						offset : params.offset,
						// name:$('#searchName').val(),
						type : $('#searchName').val(),
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
						field : 'id',
						title : 'ID'
					},
					{
						field : 'name',
						title : 'タグ名'
					},
					{
						field : 'value',
						title : 'データ値',
						width : '100px'
					},
					{
						field : 'type',
						title : 'タイプ'
					},
					{
						field : 'description',
						title : '説明'
					},
					{
						visible : false,
						field : 'sort',
						title : '並び順（昇順）'
					},
					{
						visible : false,
						field : 'parentId',
						title : '親ID'
					},
					{
						visible : false,
						field : 'createBy',
						title : '作成者'
					},
					{
						visible : false,
						field : 'createDate',
						title : '作成日時'
					},
					{
						visible : false,
						field : 'updateBy',
						title : '更新者'
					},
					{
						visible : false,
						field : 'updateDate',
						title : '更新日時'
					},
					{
						visible : false,
						field : 'remarks',
						title : '備考'
					},
					{
						visible : false,
						field : 'delFlag',
						title : '削除フラグ'
					},
					{
						title : '操作',
						field : 'id',
						align : 'center',
						formatter : function(value, row, index) {
							var e = '<a class="btn btn-primary btn-sm ' + s_edit_h + '" href="#" mce_href="#" title="編集" onclick="edit(\''
								+ row.id
								+ '\')"><i class="fa fa-edit"></i></a> ';
							var d = '<a class="btn btn-warning btn-sm ' + s_remove_h + '" href="#" title="削除"  mce_href="#" onclick="remove(\''
								+ row.id
								+ '\')"><i class="fa fa-remove"></i></a> ';
							var f = '<a class="btn btn-success btn-sm ' + s_add_h + '" href="#" title="新規追加"  mce_href="#" onclick="addD(\''
								+ row.type +'\',\''+row.description
								+ '\')"><i class="fa fa-plus"></i></a> ';
							return e + d +f;
						}
					} ]
			});
}
function reLoad() {
	var opt = {
		query : {
			type : $('.chosen-select').val(),
		}
	}
	$('#exampleTable').bootstrapTable('refresh', opt);
}
function add() {
	layer.open({
		type : 2,
		title : '新規追加',
		maxmin : true,
		shadeClose : false, // マスククリックでレイヤーを閉じる
		area : [ '800px', '520px' ],
		content : prefix + '/add' // iframeのURL
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

function addD(type,description) {
	layer.open({
		type : 2,
		title : '新規追加',
		maxmin : true,
		shadeClose : false, // マスククリックでレイヤーを閉じる
		area : [ '800px', '520px' ],
		content : prefix + '/add/'+type+'/'+description // iframeのURL
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
			ids[i] = row['id'];
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