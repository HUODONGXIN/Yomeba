var UserPay = {
    czData: [[30, "3000yomiコイン"], [50, "5000yomiコイン"], [100, "10000yomiコイン"], [200, "20000yomiコイン"], [500, "50000yomiコイン"], [365, "サイト全体年間読書パック"] ],
    czPayPalData: [[20, "10000yomiコイン"], [50, "25000yomiコイン"], [100, "50000yomiコイン"], [80, "サイト全体年間読書パック"]],
    sendPay: function () {
        $("#payform").submit();
    }
}

$(function () {
    $("#ulPayType li").click(function () {

        if($(this).attr("valp")==2){
            layer.alert("WeChat決済は現在準備中です。ご期待ください");
        }

        return ;



        $($(this).parent()).children().each(function () {
            $(this).removeClass("on");
        });
        $(this).addClass("on");

        var type = $(this).attr("valp");
        if (type == "3") {
            $("#ulPayPal").show();
            $("#ulPayPalXJ").show();
            $("#ulZFWX").hide();
            $("#ulZFWXXJ").hide();
        }
        else {
            $("#ulPayPal").hide();
            $("#ulPayPalXJ").hide();
            $("#ulZFWX").show();
            $("#ulZFWXXJ").show();
        }

    })

    $("#ulZFWX li").click(function () {
        $("#ulZFWX li").removeClass("on");
        $(this).addClass("on");
        if ($(this).attr("vals") > 0) {
            $("#pValue").val($(this).attr("vals"));
            $("#showTotal").html('￥' + $(this).attr("vals") + '元');
            for (var i = 0; i < UserPay.czData.length; i++) {
                if (UserPay.czData[i][0] == $(this).attr("vals")) {
                    $("#showRemark").html(UserPay.czData[i][1]);
                    break;
                }
            }
        }
    });
    $("#ulPayPal li").click(function () {
        $("#ulPayPal li").removeClass("on");
        $(this).addClass("on");
        if ($(this).attr("vals") > 0) {
            $("#pValue").val($(this).attr("vals"));
            $("#showPayPalTotal").html($(this).attr("vals") + 'ドル');
            for (var i = 0; i < UserPay.czData.length; i++) {
                if (UserPay.czPayPalData[i][0] == $(this).attr("vals")) {
                    $("#showPayPalRemark").html(UserPay.czPayPalData[i][1]);
                    break;
                }
            }
        }
    });
});