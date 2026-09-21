// 以下は公式サンプルです
$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		console.log('更新を送信');
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/common/generator/update",
		data : $('#signupForm').serialize(),// あなたのformid
		async : false,
		error : function(request) {
			parent.layer.alert("ネットワーク接続がタイムアウトしました");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg(data.msg);

			} else {
				parent.layer.msg(data.msg);
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			author : {
				required : true
			},
			email : {
				required : true,
			},
			package : {
				required : true,
			},
			
		},
		messages : {

			author : {
				required : icon + "作家を入力してください"
			},
			email : {
				required : icon + "メールアドレスを入力してください",
			},
			package : {
				required : icon + "パッケージ名を入力してください",
			},
		}
	})
}
