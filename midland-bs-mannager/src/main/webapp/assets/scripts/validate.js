//判断0-9 a-z 并且包含下划线的字母数子
function　checkLetterNum(id,name,msg){
    var regLetterNum = /^[a-zA-Z0-9_]{6,20}$/;
    var value = $("#id").val();
    if (value == null || value.trim() == "") {
        layer.tips(msg, "input[name='"+name+"']", {tips: 1});
        return false;
    }
    if (!regLetterNum.test(value)) {
        layer.tips("仅支持英文、数字和下划线,长度为6-20个字符！", "input[name='username']", {tips: 1});
        return false;
    }
    return true;
}

function　notEmpty(id,name,msg){
    var value = $("#"+id).val();
    if (value == null || value.trim() == "") {
        if(msg == null|| msg==""){
            msg="不能为空！";
        }
        layer.tips(msg, "input[name='"+name+"']", {tips: 1});
        return false;
    }
    return true;
}

function checkPhone(id,name,msg) {

    var reg = /^1[3,4,5,7,8]\d{9}$/;
    var phone = $("input[name='"+name+"']").val();
    if (phone == null || phone.trim() == '') {
        layer.tips("手机号不能为空！", "input[name='"+name+"']", {tips: 3});
        return false;
    }
    if (!reg.test(phone)) {
        layer.tips("手机号格式有误,请核对!", "input[name='"+name+"']", {tips: 3});
        $("input[name='phone']").focus();
        return false;
    }
    return true;
}

//检查邮箱格式
function checkEmail(id,name,msg) {
    var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    var email = $("input[name='"+name+"']").val();
    if (email==null || email.trim() == '') {
        layer.tips("邮箱不能为空！", "input[name='"+name+"']",{tips:3});
        return false;
    }
    if (!reg.test(email)) {
        layer.tips("邮箱格式有误,请核对!", "input[name='"+name+"']", {tips: 3});
        $("input[name='"+name+"']").focus();
        return false;
    }
    return true;
}

function checkSelect(ids,msgs){
    alert(1);
    var  idArr= new Array();
    var  msgArr= new Array();
    idArr = ids.split("|");
    msgArr = msgs.split("|");
    for (var i=0;i<idArr.length ;i++ )
    {
        if (($("#"+idArr[i]).val()!=null&&$("#"+idArr[i]).val()!="")||($("#"+idArr[i]+" option:selected").val()!=null&&$("#"+idArr[i]+" option:selected").val()!="")){
            continue;
        }else {
            layer.tips(msgArr[i], "select[name='"+idArr[i]+"']",{tips:3});
            return false;
        }
    }
    return true;
}

function checkUrl(id,name,msg){
    var urlString = $("#"+id).val();
    if(urlString==null || urlString.trim() == ''){
        layer.tips("网址不能为空！", "input[name='"+name+"']",{tips:3});
        return false;
    }
    if(urlString!=""){
        var reg=/(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/g;
        if(!reg.test(urlString)){
            layer.tips(msg, "input[name='"+name+"']",{tips:3});
            return false;
        }
    }
    return true;
}

