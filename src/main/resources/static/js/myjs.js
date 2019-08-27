



//切换增加与修改的界面
	function addOrInsert() {
		var myid = GetQueryString("sid");
		
		var uid = $("#sid");
		var myForm = $("#addOrInsertForm");
		
		var uidSpan = $("#sidSpan");
		var sbSpan = $("#submitSpan");
		
		if(myid==null){
			sbSpan.html("增加");
			myForm.attr("action","addStudentByClass");
			uid.attr("readonly", false);
			uid.attr("title","");
			uid.attr("onchange","");
			uid.attr("onblur","checkId(this.id)");
		}else{
			sbSpan.html("修改");
			myForm.attr("action","updataStudent");
			uid.attr("readonly", true);
			uid.attr("title","双击进行修改");
			uid.attr("onchange","checkId(this.id)");
			uid.attr("onblur","");
		}
	}

	
	
	//-----------------------dao--------------------------------------------------------------------------
	
	function checkALL() {
		
		if(checkClass("sclass")&checkId("sid")&chexkAge("sage")&checkName("sname")&checkClass("saddress")){
//			$("#mybs").attr("readonly",false);
			return true;
		}
//			$("#mybs").attr("readonly",true);
		return false;
	}
	
	function GetQueryString(name){
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
   	}
	//先暂时用get传值，之后可以改成json
	document.getElementById("myclass").value = GetQueryString("sclass");
	document.getElementById("myID").value = GetQueryString("sid");
	//document.getElementById("myage").value = GetQueryString("sage");
	//document.getElementById("myaddress").value = GetQueryString("saddress");
	function reviseReadonly(name){
		
		var temp = confirm("是否要修改此数据，可能会覆盖已有数据");
		if(temp){
			document.getElementById(name.id).readOnly = false;
			var uName = name.id;
			var uSpan = document.getElementById(uName);
			name.ondblclick=null;
		}
	}
	//验证班级，不允许包含特殊字符
	function checkClass(rag) {
		var temp = /^[\u4E00-\u9FA5A-Za-z0-9]+$/;	//验证班级只能为数字中文英文的组合
		var sText = "数字、中文、英文或三者组合";
		return check(rag,temp,sText);
	}
	//验证学号，暂定1~10位数字组合
	function checkId(rag) {
		var temp = /^\d{1,10}$/;	//验证ID是否为1~10数字组合
		var sText = "1~10位数字";
		return check(rag,temp,sText);
	}
	//校验年龄
	function chexkAge(rag) {
		var temp = /^[1-9]\d{0,2}$/;	//非零开头1~3正整数
		var sText = "1~3位非零开头正整数";
		return check(rag,temp,sText);
	}
	//名字校验函数
	function checkName(rag){
		var temp = /^[\u4e00-\u9fa5]{2,4}$/;
		var sText = "2~4个汉字";
		return check(rag,temp,sText);
	}
	//校验函数，输入需要校验的id，校验正则，和输出的内容
	function check(id,temp,text){
		var cn = document.getElementById(id).value;
		var tem = temp;
		var span = document.getElementById(id+"Span");
		if(cn=="" || cn==null){
			span.innerHTML="*请输入"+text;
			span.className="check-color-red check";
		}else if(tem.test(cn)){
			span.innerHTML="*输入内容符合要求";
			span.className="check-color-green check";
			return true;
		}else{
			span.innerHTML="*输入内容不符合要求，请输入"+text;
			span.className="check-color-red check";
		}
		return false;
	}