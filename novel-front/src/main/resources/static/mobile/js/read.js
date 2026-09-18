var checkbg = "#A7A7A7";
var nr_body = document.getElementById("read");//ページのbody
var huyandiv = document.getElementById("huyandiv");//アイケアdiv
var lightdiv = document.getElementById("lightdiv");//ライトdiv
var fontfont = document.getElementById("fontfont");//フォントdiv
var fontbig = document.getElementById("fontbig");//大きい文字div
var fontmiddle = document.getElementById("fontmiddle");//中文字div
var fontsmall = document.getElementById("fontsmall");//小さい文字div
var nr1 =  document.getElementById("chaptercontent");//コンテンツdiv
//コンテンツページのユーザー設定
function nr_setbg(intype){
    var huyandiv = document.getElementById("huyandiv");
    var light = document.getElementById("lightdiv");
    if(intype == "huyan"){
        if(huyandiv.className == "button huyanon"){
            document.cookie="light=huyan;path=/";
            set("light","huyan");
        }
        else{
            document.cookie="light=no;path=/";
            set("light","no");
        }
    }
    if(intype == "light"){
        if(light.innerHTML == "消灯"){
            document.cookie="light=yes;path=/";
            set("light","yes");
        }
        else{
            document.cookie="light=no;path=/";
            set("light","no");
        }
    }
    if(intype == "big"){
        document.cookie="font=big;path=/";
        set("font","big");
    }
    if(intype == "middle"){
        document.cookie="font=middle;path=/";
        set("font","middle");
    }
    if(intype == "small"){
        document.cookie="font=small;path=/";
        set("font","small");
    }
}

//コンテンツページの設定読み込み
function getset(){
    var strCookie=document.cookie;
    var arrCookie=strCookie.split("; ");
    var light;
    var font;

    for(var i=0;i<arrCookie.length;i++){
        var arr=arrCookie[i].split("=");
        if("light"==arr[0]){
            light=arr[1];
            break;
        }
    }

    //light
    if(light == "yes"){
        set("light","yes");
    }
    else if(light == "no"){
        set("light","no");
    }
    else if(light == "huyan"){
        set("light","huyan");
    }
}


//コンテンツページの設定読み込み
function getset1(){
    var strCookie=document.cookie;
    var arrCookie=strCookie.split("; ");
    var light;
    var font;

    for(var j=0;j<arrCookie.length;j++){
        var arr=arrCookie[j].split("=");
        if("font"==arr[0]){
            font=arr[1];
            break;
        }
    }

    //font
    if(font == "big"){
        set("font","big");
    }
    else if(font == "middle"){
        set("font","middle");
    }
    else if(font == "small"){
        set("font","small");
    }
    else{
        set("font","middle");
    }
}

//コンテンツページの設定適用
function set(intype,p){

    //var nr_title =  document.getElementById("top1");//記事タイトル
    //var nr_title =  document.getElementById("nr_title");//記事タイトル
    //var shuqian_2 = document.getElementById("shuqian_2");//しおりリンク

    //var pt_prev =  document.getElementById("pt_prev1");
    //var pt_mulu =  document.getElementById("pt_mulu1");
    //var pt_next =  document.getElementById("pt_next1");
    //var pb_prev =  document.getElementById("pb_prev1");
    //var pb_mulu =  document.getElementById("pb_mulu1");
    //var pb_next =  document.getElementById("pb_next1");


    //ライト
    if(intype == "light"){
        if(p == "yes"){
            //消灯
            lightdiv.innerHTML = "点灯";
            lightdiv.className="button lighton";
            nr_body.style.backgroundColor = "#000";
            //nr_title.style.color = "#ccc";
            nr1.style.color = "#999";

            huyandiv.innerHTML = "アイケア";
            huyandiv.className="button huyanon";
            //pt_prev.style.cssText = "background-color:#222;color:#0065B5;";
            //pt_mulu.style.cssText = "background-color:#222;color:#0065B5;";
            //pt_next.style.cssText = "background-color:#222;color:#0065B5;";
            //pb_prev.style.cssText = "background-color:#222;color:#0065B5;";
            //pb_mulu.style.cssText = "background-color:#222;color:#0065B5;";
            //pb_next.style.cssText = "background-color:#222;color:#0065B5;";
            //shuqian_2.style.color = "#999";
        }
        else if(p == "no"){
            //点灯
            lightdiv.innerHTML = "消灯";
            lightdiv.className="button lightoff";
            nr_body.style.backgroundColor = "#fff";
            nr1.style.color = "#000";
            //nr_title.style.color = "#000";
            //pt_prev.style.cssText = "";
            //pt_mulu.style.cssText = "";
            //pt_next.style.cssText = "";
            //pb_prev.style.cssText = "";
            //pb_mulu.style.cssText = "";
            //pb_next.style.cssText = "";
            //shuqian_2.style.color = "#000";

            huyandiv.innerHTML = "アイケア";
            huyandiv.className="button huyanon";
        }
        else if(p == "huyan"){
            //アイケア
            lightdiv.innerHTML = "消灯";
            lightdiv.className="button lightoff";
            huyandiv.className="button huyanoff";
            nr_body.style.backgroundColor = "#005716";
            nr1.style.color = "#000";
            //pt_prev.style.cssText = "background-color:#0E7A18;color:#000;";
            //pt_mulu.style.cssText = "background-color:#0E7A18;color:#000;";
            //pt_next.style.cssText = "background-color:#0E7A18;color:#000;";
            //pb_prev.style.cssText = "background-color:#0E7A18;color:#000;";
            //pb_mulu.style.cssText = "background-color:#0E7A18;color:#000;";
            //pb_next.style.cssText = "background-color:#0E7A18;color:#000;";
            shuqian_2.style.color = "#000";
        }
    }
    //フォント
    if(intype == "font"){
        fontsmall.className="sizebg";
        if(p == "big"){
            fontbig.className="button sizebgon";
            nr1.style.fontSize="25px";
            fontmiddle.className="sizebg";
            fontsmall.className="sizebg";
        }
        if(p == "middle"){
            fontmiddle.className="button sizebgon";
            nr1.style.fontSize = "20px";
            fontbig.className="sizebg";
            fontsmall.className="sizebg";
        }
        if(p == "small"){
            fontsmall.className="button sizebgon";
            nr1.style.fontSize = "14px";
            fontbig.className="sizebg";
            fontmiddle.className="sizebg";
        }
    }
}
