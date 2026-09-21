var prefix = "/common/generator"
$(function() {
	load();
});

function load() {
	$('#exampleTable')
			.bootstrapTable(
					{
						method : 'get', // サーバーデータのリクエスト方式 get or post
						url : prefix + "/list", // サーバーデータの読み込み先URL
						showRefresh : false,
						showToggle : false,
						showColumns : true,
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
						search : false, // 検索ボックスを表示するか
						showColumns : false, // 列選択ドロップダウンを表示するか（表示する列を選択）
						sidePagination : "client", // ページネーションの処理場所（"client" または
						// "server"
						// queryParams : queryParams,
						// // サーバーにデータをリクエストする際、パラメータを上書きして追加できます（例: toolbar のパラメータ）。
						// queryParamsType = 'limit' の場合、戻り値には以下を含める必要があります:
						// limit, offset, search, sort, order それ以外の場合は以下を含める必要があります:
						// pageSize, pageNumber, searchText, sortName,
						// sortOrder.
						// false を返すとリクエストが中止されます
						queryParams : function(params) {
							return {
								// 説明：バックエンドに渡すパラメータには offset 開始インデックス、limit ステップ幅、sort ソート列、order: desc、および全列のキーと値のペアが含まれます
								limit : params.limit,
								offset : params.offset,
								tableName : $('#tableName').val(),
							};
						},
						columns : [
								{
									checkbox : true
								},
								{
									field : 'tableName', // カラムフィールド名
									title : 'テーブル名' // 列のタイトル
								},
								{
									field : 'engine',
									title : 'engine'
								},
								{
									field : 'tableComment',
									title : 'テーブル説明'
								},
								{
									field : 'createTime',
									title : '作成日時'
								},
								{
									title : '操作',
									field : 'id',
									align : 'center',
									formatter : function(value, row, index) {
										/*var d = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="オンラインでコードをダウンロード" onclick="downloadCode(\''
												+ row.tableName
												+ '\')"><i class="fa fa-cloud-download"></i></a> ';*/
										var g = '<a class="btn btn-primary btn-sm" href="#" mce_href="#" title="ローカルでコードを生成" onclick="columnEdit(\''
											+ row.tableName
											+ '\')"><i class="fa fa-bug"></i></a> ';

										return g;
									}
								} ]
					});
}
function reLoad() {
	$('#exampleTable').bootstrapTable('refresh');
}
function downloadCode(tableName) {
	location.href = prefix + "/downLoadCode/" + tableName;
}
function genCode(tableName) {
	layer.confirm('ローカルプロジェクトのルートパスに選択レコードのコードを生成してもよろしいですか？', {
		btn : [ '確定', 'キャンセル' ]
	}, function() {
		$.ajax({
			url : prefix + "/genCode",
			type : "post",
			data : {
				'tableName' : tableName
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})
}
function batchDownload() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 選択中の全行を返します。選択レコードがない場合は空配列を返します
	if (rows.length == 0) {
		layer.msg("コード生成するテーブルを選択してください");
		return;
	}
	var tables = new Array();
	// 選択中の全行を走査し、各行に対応するIDを取得します
	$.each(rows, function(i, row) {
		tables[i] = row['tableName'];
	});
	location.href = prefix + "/batchDownload?tables=" + JSON.stringify(tables).replace('[','%5B').replace(']','%5D');
}

function batchCode() {
	var rows = $('#exampleTable').bootstrapTable('getSelections'); // 選択中の全行を返します。選択レコードがない場合は空配列を返します
	if (rows.length == 0) {
		layer.msg("コード生成するテーブルを選択してください");
		return;
	}
	var tables = new Array();
	// 選択中の全行を走査し、各行に対応するIDを取得します
	$.each(rows, function(i, row) {
		tables[i] = row['tableName'];
	});
	layer.confirm('ローカルプロジェクトのルートパスに選択レコードのコードを一括生成してもよろしいですか？', {
		btn : [ '確定', 'キャンセル' ]
	}, function() {
		$.ajax({
			url : prefix + "/batchCode",
			type : "post",
			data : {
				'tables' : JSON.stringify(tables)
			},
			success : function(r) {
				if (r.code == 0) {
					layer.msg(r.msg);
				} else {
					layer.msg(r.msg);
				}
			}
		});
	})

}

function edit(){
	console.log('設定ページを開く');
	layer.open({
		type : 2,
		title : '生成設定',
		maxmin : true,
		shadeClose : false, 
		area : [ '800px', '520px' ],
		content : prefix + '/edit'
	});
}

function columnEdit(tableName){
	layer.open({
		type : 2,
		title : 'カラム設定',
		maxmin : true,
		shadeClose : false,
		area : [ '800px', '520px' ],
		content : prefix + '/genColumns?tableName='+tableName
	});
}