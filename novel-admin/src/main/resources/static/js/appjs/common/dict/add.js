$().ready(function() {
	validateRule();
});

$.validator.setDefaults({
	submitHandler : function() {
		save();
	}
});
function save() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/common/dict/save",
		data : $('#signupForm').serialize(), // あなたのformid
		async : false,
		error : function(request) {
			parent.layer.alert("ネットワークがタイムアウトしました");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作が成功しました");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name);
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
			}
		},
		messages : {
			name : {
				required : icon + "名前を入力してください"
			}
		}
	})
}