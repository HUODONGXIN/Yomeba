var prefix = "/sys/menu"
$(function() {
	validateRule();
	// アイコン一覧を開く
    $("#ico-btn").click(function(){
        layer.open({
            type: 2,
			title:'アイコン一覧',
            content: '/fonts/FontIcoList.html',
            area: ['480px', '90%'],
            success: function(layero, index){
                //var body = layer.getChildFrame('.ico-list', index);
                //console.log(layero, index);
            }
        });
    });
});
$.validator.setDefaults({
	submitHandler : function() {
		submit01();
	}
});
function submit01() {
	$.ajax({
		cache : true,
		type : "POST",
		url : prefix + "/save",
		data : $('#signupForm').serialize(),
		async : false,
		error : function(request) {
			laryer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("保存しました");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // ウィンドウインデックスを取得
				parent.layer.close(index);

			} else {
				layer.alert(data.msg)
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
			type : {
				required : true
			}
		},
		messages : {
			name : {
				required : icon + "メニュー名を入力してください"
			},
			type : {
				required : icon + "メニュータイプを選択してください"
			}
		}
	})
}