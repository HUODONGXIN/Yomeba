$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function getCheckedRoles() {
	var adIds = "";
	$("input:checkbox[name=role]:checked").each(function(i) {
		if (0 == i) {
			adIds = $(this).val();
		} else {
			adIds += ("," + $(this).val());
		}
	});
	return adIds;
}
function save() {
	$("#roleIds").val(getCheckedRoles());
	$.ajax({
		cache : true,
		type : "POST",
		url : "/sys/user/save",
		data : $('#signupForm').serialize(),// あなたのformid
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
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			name : {
				required : true
			},
			username : {
				required : true,
				minlength : 2,
				remote : {
					url : "/sys/user/exit", // バックエンドの処理プログラム
					type : "post", // データ送信方式
					dataType : "json", // データ受信形式
					data : { // 送信するデータ
						username : function() {
							return $("#username").val();
						}
					}
				}
			},
			password : {
				required : true,
				minlength : 6
			},
			confirm_password : {
				required : true,
				minlength : 6,
				equalTo : "#password"
			},
			email : {
				required : true,
				email : true
			},
			topic : {
				required : "#newsletter:checked",
				minlength : 2
			},
			agree : "required"
		},
		messages : {

			name : {
				required : icon + "氏名を入力してください"
			},
			username : {
				required : icon + "アカウントを入力してください",
				minlength : icon + "アカウントは2文字以上で入力してください",
				remote : icon + "アカウントは既に存在します"
			},
			password : {
				required : icon + "パスワードを入力してください",
				minlength : icon + "パスワードは6文字以上で入力してください"
			},
			confirm_password : {
				required : icon + "パスワードを再入力してください",
				minlength : icon + "パスワードは6文字以上で入力してください",
				equalTo : icon + "入力されたパスワードが一致しません"
			},
			email : icon + "メールアドレスを入力してください",
		}
	})
}

var openDept = function(){
	layer.open({
		type:2,
		title:"部門を選択",
		area : [ '300px', '450px' ],
		content:"/system/sysDept/treeView"
	})
}
function loadDept( deptId,deptName){
	$("#deptId").val(deptId);
	$("#deptName").val(deptName);
}