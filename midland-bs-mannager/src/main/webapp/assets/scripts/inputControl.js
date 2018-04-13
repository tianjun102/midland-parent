/*
*
* 例子:
* description: //只能为数字控制 可以带小数点
* //6参数 绑定对象,整数部分长度,小数部分长度,最低显示的小数位(传0则表示没有小数部分则只显示整数部分),自定义事件{onblur:function(){},onkeypress:.....},是否允许输入负数">
* <input type="text" value="" onfocus="InitInput.setNumber(this,9,4,2,attributes,false)
* <input type="text" value="" onfocus="InitInput.setNumber(this,attributes)//2参数 绑定对象，自定义事件">
* <input type="text" value="" onfocus="InitInput.setNumber(this,attributes,false)//3参数 绑定对象，自定义事件，是否允许输入负数">     		
*     
* description: //只能为整数
*<input type="text" value="" onfocus="InitInput.setInt(this,9,attributes)"> //绑定对象,整数长度,自定义事件{onblur:function(){},onkeypress:.....}
*
*description: //手机验证
*<input type="text" value="" onfocus="InitInput.setMobile(this)"> //绑定对象
*
**/
InitInput = {
		//数字控件, 整数部分最大长度,小数部分最大长度,小数部分显示出的长度,子对象，是否允许输入负数
	setNumber : function(obj,maxIntLength,maxDecimalLength,displayDecimal,attributes,allowNegative)
	{
	   
	   var defaultDecimal = 4;//默认小数位长度
	   var defaultIntLength = 9;//默认整数部分长度
       var defaultDisplay=2;//默认最低显示的小数位
       var defaultNegative=false;//默认不允许输入负数	
	   var len = arguments.length; //参数个数,js不支持重载 通过判断参数个数来设定参数的值
	   //obj.style.textAlign="right";
	   if (len==2){//只传绑定对象和对象的事件
	   		attributes=arguments[1];
			maxIntLength=null;
			maxDecimalLength=null;
			displayDecimal=null;
	   }else if(len==3){//只传绑定对象，自定义事件和是否允许输入负数3个参数
	   		allowNegative=arguments[2];
			attributes=arguments[1];
			maxIntLength=null;
			maxDecimalLength=null;
			displayDecimal=null;

	   }
	   if(maxIntLength==null){
	   		maxIntLength=defaultIntLength;
	   }
	   if(maxDecimalLength==null){
	   		maxDecimalLength=defaultDecimal;
	   }
	   if(displayDecimal==null){
	   		displayDecimal=defaultDisplay;
	   }
	   
	   if(allowNegative==null){
	   		allowNegative=defaultNegative;
	   }
	   obj.maxlength=maxIntLength+maxDecimalLength+1;//规定最大输入的字符串长度 规则为整数部分长度+小数部分长度+"."+可能的"-"号
	   if (allowNegative) obj.maxlength=obj.maxlength+1;
	   var initValue = function()//初始化对象的值
	   {
	    	if(obj.value == null) obj.value="";
	    	if(obj.o_value == null)  obj.o_value = "";
	   		if(obj.t_value == null)  obj.o_value = "";
	   };
		var getFloat=function(dt){//去掉输入值前面多余的0
			var vl;
			if(!dt) return  "";
			if(isNaN(dt)) vl=parseFloat((dt+'').replace(/\,/g,''));
			else vl = parseFloat(dt);
			if (isNaN(vl)) return "";
			return vl.toString();
		};
		var checkFormat=function(obj,maxIntLength,maxDecimalLength,displayDecimal){//检查输入数字并替换成规定的格式
			//var obj= element.document.activeElement;
			var value=obj.value;
			value=getFloat(value);
			if(value.indexOf(".")>=0){//如果是输入值中带有小数点则检查小数部分长度是否达到了最低显示的长度，如果没有则补0
				var integral="";
				var decimal="";
				if (value.indexOf(".")==0&&value.length>1){
					integral="0";
					decimal=value.substring(1,value.length);
				}else if (value.indexOf(".")>0){
					
					integral=value.substring(0,value.indexOf("."));
					decimal=value.substring(value.indexOf(".")+1,value.length);
				}
				if (decimal.length<displayDecimal){
					var count=decimal.length;//小数点后的位数
					for (var i=0;i<displayDecimal-count;i++){
						decimal=decimal+"0";
					}
				}else if (decimal.length==displayDecimal){
					
				}else if(decimal.length<maxDecimalLength){
					var count=decimal.length;//小数点后的位数
					for (var i=0;i<maxDecimalLength-count;i++){
						decimal=decimal+"0";
					}
				}
				value=integral+"."+decimal;
			}else if (value!=""&&displayDecimal>0){//输入的是整数则补上小数部分，全部为0
				if(value!="0"){ //整数部分非0才补全后面的0 10.31fpt 页面控制经费格式用于PDF输出
					value=value+".";
					for (var i=0;i<displayDecimal;i++){
					value=value+"0";
					}
				}
			}
			
			obj.value=value;
		};

		
	    var forbid=function(objTR,maxIntLength,maxDecimalLength,allowNegative)//输入时控制，只允许输入数字，小数点，并根据传过来的参数决定是否允许输入负号
		{	
   			var txtval=objTR.value;
   			var key = getEvent().keyCode;
   			//document.selection.createRange().text--被选中的文本
   			var docSel=null;
   			if(document.createRange)//ff和标准2级dom
   			       docSel=document.createRange();
   			else if(document.selection&&document.selection.createRange)//ie
   			       docSel=document.selection.createRange();
   			
   			//if (txtval.length-docSel.text.length>=objTR.maxlength) getEvent().keyCode=0;
   			if((key < 48||key > 57)&&key != 46&&key!=45)
   			{  
   				 getEvent().keyCode = 0;
   			}
   			else
   			{
    			if(key == 46)
    			{
     				if(txtval!=""){
	 					if (txtval.indexOf(".") != -1)
							getEvent().keyCode = 0;
	 				}
      
    			}
				else if (key==45){
					
					if (!allowNegative)
							getEvent().keyCode=0;
	 				if(txtval!=""&& txtval.indexOf("-")!=-1)
	 						getEvent().keyCode=0;
				}		
   			}

	};
		$(obj).blur(function()//失去焦点时检查并格式化输入的数字
		{
			initValue();
		    forbid(obj,maxIntLength,maxDecimalLength,allowNegative);
			checkFormat(obj,maxIntLength,maxDecimalLength,displayDecimal);
			
			attributes && attributes.onblur ? attributes.onblur() : true;//如果该事件有自定义方法 则调用
			
		});
		
		$(obj).keypress(function() {//点击键盘按键时控制输入的字符为规定字符
		    initValue();
			
			if(!isNaN(obj.value)&&!obj.old_value) obj.old_value=obj.value;
			else if (isNaN(obj.value)&&!obj.old_value) obj.old_value="";
			forbid(obj,maxIntLength,maxDecimalLength,allowNegative);	
			attributes && attributes.onkeypress ? attributes.onkeypress() : true;//如果该事件有自定义方法 则调用
			//obj && obj.onchange ? obj.onchange() : true;//如果该事件有onchange方法 则调用
		});
		
		$(obj).keyup(function() {//按键弹起时检查输入的字符串长度是否符合要求
			var temLegth = maxIntLength;
			var decimal="";
			
			if(obj.value.indexOf("-")>=0){
				temLegth = temLegth+1;
			}
			
			//var integralforbid;	//取出输入数字的整数部分和小数部分
			if (obj.value.indexOf(".")>=0){
				decimal=obj.value.substring(obj.value.indexOf(".")+1,obj.length);
				integral=obj.value.substring(0,obj.value.indexOf("."));
			}
			else {
				integral=obj.value;
			}
			
			var regex;
			if(allowNegative)
				regex=/^\-?\d*?\.?\d*?$/;
			else
				regex=/^\d*?\.?\d*?$/;
			if(!obj.value.match(regex)) obj.value = isNaN(obj.old_value) ? '' : obj.old_value;//如果字符串非数字 则还原成旧值
			if (integral.length>temLegth){//如果字符串整数部分超过长度，则截取出符合长度的整数部分,并重新组合
				integral=integral.substring(0,temLegth);
				if (decimal=="")
					obj.value=integral;
				else
					obj.value=integral+"."+decimal;
			}
			if (decimal.length>maxDecimalLength){//如果字符串小数部分超过长度，则截取出符合长度的小数部分,并重新组合
				decimal=decimal.substring(0,maxDecimalLength);
				obj.value=integral+"."+decimal;
			}
			
			if(allowNegative)
				regex=/^(?:\-?\d+(?:\.\d+)?)?$/;
			else
				regex=/^(?:\d+(?:\.\d+)?)?$/;
			if(obj.value.match(regex)) obj.old_value = obj.value;//如果字符串为符合要求的数字，则变更旧值为目前的值
			attributes && attributes.onkeyup ? attributes.onkeyup() : true;//如果该事件有自定义方法 则调用
			obj && obj.onchange ? obj.onchange() : true;//如果该事件有onchange方法 则调用
		});
		
	},
	setTime : function(name,id,msg){
		if (msg == null){
            msg="时间格式不正确"
		}
		var time =/^(20|21|22|23|[0-1]\d):[0-5]\d:[0-5]\d$/;
        var regExp = new RegExp(time);
        var value=$('#'+id).val();
        if (value==null||value==""){
        	return true;
		}
        if(!regExp.test(value)){
            layer.tips(msg, "input[name='"+name+"']", {tips: 1});
            return false;
        }
        return true;
	},
	
	/*
    * description: //规定只允许输入整数
    * //onfocus = function()
    *    {
    *       InitInput.setInt(绑定对象,整数长度,自定义事件{onblur:function(){},onkeypress:.....})
    *    }
    **/
	setInt : function(obj,len,attributes) 
	{
	   var defaultIntLength = 9;//默认整数部分长度
	   if (len==null) len=defaultIntLength;
	   var initValue = function()
	   {
	    	if(obj.value == null) obj.value="";
	    	if(obj.o_value == null)  obj.o_value = "";
	   		if(obj.t_value == null)  obj.o_value = "";
	   };
	    
		var forbid = function()
		{

			if((getEvent().keyCode >= 48 && getEvent().keyCode <= 57)){
	  			if(getEvent().keyCode == 46){
			   		for(var i = 0; i < obj.value.length; i++) {
			    		ch = obj.value.charAt(i);
			}	}	}
			else
				getEvent().returnValue=false;
		};
		
		initValue();
		$(obj).blur(function(){
			initValue();
		    forbid();
			if( obj.value.length && obj.value.length > 0 && obj.value.lastIndexOf('.') == obj.value.length - 1)
				obj.value = obj.value.substring(0,obj.value.length-1);
			if(!obj.value.match(/^(?:[\-]?\d+(?:\.\d+)?|\.\d*?)?$/) || obj.value.length > len ){ obj.value = obj.o_value; }	
			else{if(obj.value.match(/^\.\d+$/)) obj.value=0+this.value;
				if(obj.value.match(/^\.$/)) obj.value=0;obj.o_value = obj.value;
			}
			
			attributes && attributes.onblur ? attributes.onblur() : true;
		});
		
		$(obj).keypress(function(){
		    initValue();
			forbid();
			if(!obj.value.match(/^[\-]?\d*?\.?\d*?$/) || obj.value.length > len) obj.value = isNaN(obj.t_value) ? '' : obj.t_value;
			else  obj.t_value = obj.value;
			if(obj.value.match(/^(?:[\-]?\d+(?:\.\d+)?)?$/)) obj.o_value = obj.value;
			
			attributes && attributes.onkeypress ? attributes.onkeypress() : true;
		});
		
		$(obj).keyup(function() {
		    initValue();
		    forbid();
			if(!obj.value.match(/^[\-]?\d*?\.?\d*?$/) || obj.value.length > len) obj.value = isNaN(obj.t_value) ? '' : obj.t_value;
			else  obj.t_value = obj.value;
			if(obj.value.match(/^(?:[\-]?\d+(?:\.\d+)?)?$/)) obj.o_value = obj.value;
			
			attributes && attributes.onkeyup ? attributes.onkeyup() : true;
		});
	},
	
	/**
	*create by zxg //规定手机输入格式
	*
	*
	*
	*
	*/
	setMobile : function (obj,attributes){
		var forbid = function()
		{
			//var checkCode = 0;
			if((getEvent().keyCode >= 48 && getEvent().keyCode <= 57)){
	  			if(getEvent().keyCode == 46){
			   		for(var i = 0; i < obj.value.length; i++) {
			    		ch = obj.value.charAt(i);
			}	}	}
			else
				getEvent().returnValue=false;
		};
		//var text=obj.value;
		obj.maxlength=11;
		obj.onkeypress=function(){
			forbid();
			if (obj.value.length-document.selection.createRange().text.length>=obj.maxlength)
				getEvent().keyCode=0;
			attributes && attributes.onkeypress ? attributes.onkeypress() : true;
		};
		obj.onblur=function (){
			var myreg = /^((1)+\d{10})$/;//手机验证正则表达式包括130-139,150-159,暂时不考虑香港手机情况 (20090316修改为验证开头为1的11位数字)
			if (!myreg.test(obj.value)){
					obj.value="";
			}
				attributes && attributes.onblur ? attributes.onblur() : true;
		};
		
	},
	/**
	 * create by zxg //只允许输入数字
	 */
	setInputNumOnly:function(obj){
		obj.onkeypress = function()
		{
			//var checkCode = 0;
			if((getEvent().keyCode >= 48 && getEvent().keyCode <= 57)){
	  			if(getEvent().keyCode == 46){
			   		for(var i = 0; i < obj.value.length; i++) {
			    		ch = obj.value.charAt(i);
			}	}	}
			else
				getEvent().returnValue=false;
		};
		
		obj.onkeyup = function() {//按键弹起时检查输入的字符串长度是否符合要求
			var regex=/^\d*?$/;
			if(!obj.value.match(regex)) obj.value = isNaN(obj.old_value) ? '' : obj.old_value;//如果字符串非数字 则还原成旧值
			
			regex=/^(?:\d+(?:\d+)?)?$/;
			if(obj.value.match(regex)) obj.old_value = obj.value;//如果字符串为符合要求的数字，则变更旧值为目前的值
		};
	},
	
	/**
	 * 设置文本框可以输入的最大长度
	 * @param obj
	 * @param len
	 */
	setMaxLength : function(obj,len) 
	{
		var defaultIntLength = 100;//默认长度
		if (len==null) len=defaultIntLength;
		if(obj.value == null) obj.value="";
	    
		var forbid = function() {
			if( obj.value.length && obj.value.length > len)
				obj.value = obj.value.substring(0,len);
		};
		
		obj.onblur = function() {
		    forbid();
		};
		
		obj.onkeyup = function() {
		    forbid();
		};
	},
	
	/**
	 * 设置文本框只能输入字母和数字
	 * @param obj
	 */
	setLetterAndNumber : function(obj) 
	{
		if(obj.value == null) obj.value="";
		var oldValue = obj.value;
		
		var forbid = function() {
			if(!obj.value.match(/^[\da-zA-Z]*$/))
				obj.value=oldValue;
		};
		
		obj.onblur = function() {
			forbid();
		};
		
		obj.onkeypress = function() {
			forbid();
		};
		obj.onkeyup = function() {
			forbid();
		};
	},
	
	/**
	 * 设置文本框除了中文汉字和中文字符 都可以输入
	 * @param obj
	 */
	setPatentNumber : function(obj) 
	{
		if(obj.value == null) obj.value="";
		var oldValue = obj.value;
		
		var forbid = function() {
			if(!obj.value.match(/^[^\u4e00-\u9fa5\uFE30-\uFFA0。]*$/))
				obj.value=oldValue;
		};
		
		obj.onblur = function() {
			forbid();
		};
		
		obj.onkeypress = function() {
			forbid();
		};
		obj.onkeyup = function() {
			forbid();
		};
	},
	
	//电话号码,传真号码验证
	//type : 默认为"电话号码"
	setTel : function (obj,type,attributes){
		obj.setAttribute("maxlength",25);
		
		var forbid = commonForbidAction;
		
		obj.onkeypress=function(){
			forbid();
			
			var event = getEvent();
			var selection = getSelectionText();
			if (obj.value.length-selection.length>=obj.maxlength){
				event.keyCode=0;
			}
			attributes && attributes.onkeypress ? attributes.onkeypress() : true;
		};
		
		obj.onblur=function (){
			var patrn="^\\d{3,4}-\\d{7,8}(-\\d{2,5})?$";
			var patrn2="^1\\d{10}$";
			var vreg = new RegExp(patrn);
			var vreg2 = new RegExp(patrn2);
			if(!vreg.test(obj.value) && !vreg2.test(obj.value) && obj.value != ""){
				if(!type){type = "电话号码";}
				var msg = type + "格式不正确，请参考格式如：020-87684338-001 \n如无座机号请输入手机号,请参考格式如：13012345678";
				alert(msg);
				obj.value = "";
			}
			attributes && attributes.onblur ? attributes.onblur() : true;
		};
		
	}
		
};

function getEvent() //同时兼容ie和ff的写法
  {
  if (document.all) return window.event;
  func = getEvent.caller;
  while (func != null) {
  var arg0 = func.arguments[0];
  if (arg0) {
  if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
  return arg0;
  }
  }
  func = func.caller;
  }
  return null;
  }


function commonForbidAction()
{
	var event=getEvent();
	
	if(navigator.userAgent.toLowerCase().indexOf("firefox") != -1) //firefox
	{
		if(event.keyCode == 8 || event.keyCode == 46 || (event.keyCode >= 37 && event.keyCode <= 40) //Backspace,Delete， 左移,上移,右移,下移
				|| (event.keyCode==0 && (event.charCode==99 || event.charCode==118)) //复制，粘贴
			) { return; }
		
		if(event.preventDefault && event.charCode && !((event.charCode >= 48 && event.charCode <= 57) || event.charCode==45))
		{
			event.preventDefault();
			return;
		}
	}
	else
	{
		if(!((event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode==45))
		{
			event.returnValue=false;
			return;
		}
	}
	
}

function getSelectionText(){
	var selection = "";
	if(window.getSelection)
	{
		//mozilla，chrome，safari
		if(window.getSelection().rangeCount>0){
			selection = window.getSelection().getRangeAt(0).toString();
		}else{
			selection = "";
		}
	} 
	else if(document.selection) 
	{
		//IE, 获取被选中的文本
		selection = document.selection.createRange().text;
	}
	return selection;
}
