if ($.fn.pagination){
    $.fn.pagination.defaults.beforePageText = '第';
    $.fn.pagination.defaults.afterPageText = '全{pages}ページ';
    $.fn.pagination.defaults.displayMsg = '{from}〜{to}を表示、全{total}件';
}
if ($.fn.datagrid){
    $.fn.datagrid.defaults.loadMsg = '読み込み中…';
}
if ($.fn.treegrid && $.fn.datagrid){
    $.fn.treegrid.defaults.loadMsg = $.fn.datagrid.defaults.loadMsg;
}
if ($.messager){
    $.messager.defaults.ok = 'OK';
    $.messager.defaults.cancel = 'キャンセル';
}
if ($.fn.validatebox){
    $.fn.validatebox.defaults.missingMessage = '入力必須項目です';
    $.fn.validatebox.defaults.rules.email.message = '有効なメールアドレスを入力してください';
    $.fn.validatebox.defaults.rules.url.message = '有効なURLを入力してください';
    $.fn.validatebox.defaults.rules.length.message = '入力内容は{0}〜{1}文字以内で入力してください';
    $.fn.validatebox.defaults.rules.remote.message = 'このフィールドを修正してください';
}
if ($.fn.numberbox){
    $.fn.numberbox.defaults.missingMessage = '入力必須項目です';
}
if ($.fn.combobox){
    $.fn.combobox.defaults.missingMessage = '入力必須項目です';
}
if ($.fn.combotree){
    $.fn.combotree.defaults.missingMessage = '入力必須項目です';
}
if ($.fn.combogrid){
    $.fn.combogrid.defaults.missingMessage = '入力必須項目です';
}
if ($.fn.calendar){
    $.fn.calendar.defaults.weeks = ['日','月','火','水','木','金','土'];
    $.fn.calendar.defaults.months = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
}
if ($.fn.datebox){
    $.fn.datebox.defaults.currentText = '今日';
    $.fn.datebox.defaults.closeText = '閉じる';
    $.fn.datebox.defaults.okText = 'OK';
    $.fn.datebox.defaults.missingMessage = '入力必須項目です';
    $.fn.datebox.defaults.formatter = function(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
    };
    $.fn.datebox.defaults.parser = function(s){
        if (!s) return new Date();
        var ss = s.split('-');
        var y = parseInt(ss[0],10);
        var m = parseInt(ss[1],10);
        var d = parseInt(ss[2],10);
        if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
            return new Date(y,m-1,d);
        } else {
            return new Date();
        }
    };
}
if ($.fn.datetimebox && $.fn.datebox){
    $.extend($.fn.datetimebox.defaults,{
        currentText: $.fn.datebox.defaults.currentText,
        closeText: $.fn.datebox.defaults.closeText,
        okText: $.fn.datebox.defaults.okText,
        missingMessage: $.fn.datebox.defaults.missingMessage
    });
}
