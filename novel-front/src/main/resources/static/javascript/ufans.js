var uFans = {
    startSupportRead: function () {
        var uname = jQuery.cookie("waplogname");
        if (uname != undefined && uname != "") {
            if (spmymoney == 0) {
            }
            else {
                uFans.startSupport();
            }
        }
        else {
            layer.open({
                content: 'ログインしてください',
                style: BookDetail.msgStyle,
                time: 2
            });
        }
    },
    startSupport: function () {
        var rStr = '<a class="closePopup" href="javascript:void(0);" onclick="javascript:uFans.closeBox();"></a>';
        rStr += '<div class="popupTit">';
        rStr += '	<h3>作品を応援する</h3>';
        rStr += '</div>';
        rStr += '<div class="propsList cf">';
        rStr += '	<ul>';
        rStr += '		<li vals="100">';
        rStr += '			<a class="propWrap" href="javascript:void(0);">';
        rStr += '				<i class="icon_check"></i>';
        rStr += '				<span class="propsBox">100yomiコイン</span>';
        rStr += '			</a>';
        rStr += '		</li>';
        rStr += '		<li class="on"  vals="500">';
        rStr += '			<a class="propWrap" href="javascript:void(0);">';
        rStr += '				<i class="icon_check"></i>';
        rStr += '				<span class="propsBox">500yomiコイン</span>';
        rStr += '			</a>';
        rStr += '		</li>';
        rStr += '		<li vals="2000">';
        rStr += '			<a class="propWrap" href="javascript:void(0);">';
        rStr += '				<i class="icon_check"></i>';
        rStr += '				<span class="propsBox">2000yomiコイン</span>';
        rStr += '			</a>';
        rStr += '		</li>';
        rStr += '		<li vals="5000">';
        rStr += '			<a class="propWrap" href="javascript:void(0);">';
        rStr += '				<i class="icon_check"></i>';
        rStr += '				<span class="propsBox">5000yomiコイン</span>';
        rStr += '			</a>';
        rStr += '		</li>';
        rStr += '		<li vals="10000">';
        rStr += '			<a class="propWrap" href="javascript:void(0);">';
        rStr += '				<i class="icon_check"></i>';
        rStr += '				<span class="propsBox">10000yomiコイン</span>';
        rStr += '			</a>';
        rStr += '		</li>';
        rStr += '		<li vals="100000">';
        rStr += '			<a class="propWrap" href="javascript:void(0);">';
        rStr += '				<i class="icon_check"></i>';
        rStr += '				<span class="propsBox">100000yomiコイン</span>';
        rStr += '			</a>';
        rStr += '		</li>';
        rStr += '	</ul>';
        rStr += '</div>';
        rStr += '<p class="have_num">残高<span class="red">' + spmymoney + '</span>yomiコイン&nbsp;&nbsp;今回の応援<span class="red" id="pcTotal">500</span>yomiコイン</p>';
        rStr += '<p><textarea class="popup_text" id="sendSupportNote"   placeholder="応援ありがとうございます！作家への応援メッセージをどうぞ！"></textarea></p>';
        rStr += '<p class="tc"><a class="btn_red btn_send_pc" href="javascript:void(0);" onclick="javascript:uFans.SendSupport();">応援する</a></p>';
        $("#showPC").html(rStr);
        $("#showPC").show();
        $(".maskBox").show();
        $(".pcBox .propsList li").click(function () {
            $(".pcBox .propsList li").removeClass("on");
            $(this).addClass("on");
            $("#pcTotal").html($(this).attr("vals"));
        })
    },
    closeBox: function () {
        $(".pcBox,.flowerBox,.newsTipBox,.maskBox").hide();
    },
    SendSupport: function () {
        var uname = jQuery.cookie("waplogname");
        if (uname != undefined && uname != "") {
            var moneyTotal = spmymoney;
            var moneySupport = parseInt($("#pcTotal").html());
            var sendNote = $("#sendSupportNote").val();
            var clearSendNote = sendNote.replace(/[\ |\~|\`|\!|\@|\#|\$|\%|\^|\&|\*|\(|\)|\-|\_|\+|\=|\||\\|\[|\]|\{|\}|\;|\:|\"|\'|\,|\<|\.|\>|\/|\?]/g, "");
            if (sendNote == "") {
                layer.open({
                    content: '応援ありがとうございます！作家への応援メッセージをどうぞ！',
                    style: BookDetail.msgStyle,
                    time: 2
                });
                return;
            }
            if (clearSendNote.length<5)
            {
                layer.open({
                    content: 'コメントは5文字以上必要です！',
                    style: BookDetail.msgStyle,
                    time: 2
                });
                return;
            }
            if (moneyTotal >= moneySupport) {
                var BId = currentBId;
            }
            else {
                layer.open({
                    content: 'yomiコイン残高が不足しています',
                    style: BookDetail.msgStyle,
                    time: 2
                });
            }
        }
        else {
            layer.open({
                content: 'ログインしてください',
                style: BookDetail.msgStyle,
                time: 2
            });
        }
    },
    GetSupport: function (BId) {
    },
    GetFlower: function (BId) {
    },
    showNote: function (noteClass) {
        uFans.closeBox();
        $(".maskBox").show();
        var rStr = '<a class="closePopup" href="javascript:void(0);" onclick="javascript:uFans.closeBox();"></a>';
        rStr += '<div class="popupTit">';
        rStr += '	<h3>お知らせ</h3>';
        rStr += '</div>';
        if (noteClass == 'pc') {
            rStr += '<div class="tipWrap suc_txt_pc">作品への応援が完了しました！</div>';
        }
        else {
            rStr += '<div class="tipWrap suc_txt_flw">作品にいいねしました！</div>';
        }
        rStr += '<div class="tc">';
        rStr += '	<a href="javascript:void(0);" class="btn_red btn_sure"  onclick="javascript:uFans.closeBox();">OK</a>';
        rStr += '</div>';
        $("#showNote").html(rStr);
        $("#showNote").show();
    },
    formatDateTime: function (now) {
        if (now != null && now != "") {
            var dateN = new Date(+/\d+/.exec(now)[0]);
            var year = dateN.getFullYear();
            var month = dateN.getMonth() + 1;
            var date = dateN.getDate();
            var hour = dateN.getHours();
            var minute = dateN.getMinutes();
            var second = dateN.getSeconds();
            minute = parseInt(minute) < 10 ? "0" + minute : minute;

            if (hour == 0 && minute == 0 && second == 0) {
                return year + "-" + month + "-" + date;
            }
            else {
                return month + "-" + date + "   " + hour + ":" + minute;
            }
        }
        else {
            return "";
        }
    }
}