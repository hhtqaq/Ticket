function checkAll() {
		var password=$("#inputPassword2").val();
		var phone = $("#phone2").val();
		var msesage=$("#message").val();
		var passwordmsg=$("#pwd2");
		var phonemsg = $("#phonemsg2");
		var phonereg = /1\d{10}/ig;
		var checkmsg=$("#checkmessage");
		var checkpassword=$("#checkpassword").val();
		//初始化
		var logname=$("#firstname2").val();
		var lognamereg=/\w{3,16}/ig;
		var lognamesp=$("#sp2");
		phonemsg.empty();
		lognamesp.empty();
		if(!lognamereg.test(logname)){
			lognamesp.html("用户名非法");
			return false;
		}
		if(checkpassword!=password){
			checkmsg.html("密码不一致");
			return false;
		}
		if(password==""){
		passwordmsg.html("密码不能为空");
		return false;
		}
		if (!phonereg.test(phone)) {
			phonemsg.html("电话号码非法");
			return false;
		}
	
	}
