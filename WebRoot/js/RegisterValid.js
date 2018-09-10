function checkLogname(name){
	var logname=$(name).val();
	var sp=$("#sp2");
	if(logname==""){
		sp.empty();
		sp.html("名称不能为空")
		return false;
	}else{
	$.ajax({
		type:'POST',
		url:'user?method=checkLogname',
		data:"logname="+logname,
		success:function(data){
			sp.empty();
			if(data==""){
				sp.append("<img src=\"img/chenggong2.gif\" style='height:20px;width:25px'>");
			}else{
				sp.html(data)
				return false;
			}
		}
	})
	}
}
function checkLevel(pwd){
	var password=$(pwd).val();
	var pwdsp=$("#pwd");
	var process=null;
	var pwddiv=$("#password");
	pwdsp.empty();
	if(password!=""){
		pwddiv.html("<div class='form-group'><label  class='col-sm-4 control-label'>密码强度:</label><div class='col-sm-3'>" +
		"<div class='progress' id='process'>");
		process=$("#process");
		if(password.length<6){
			process.html("<div class='progress-bar progress-bar-danger' role='progressbar'" +
					" aria-valuenow='60' aria-valuemin='0' aria-valuemax='100'" +
					" style='width: 33%;'><span class='sr-only'>弱</span> </div></div> </div></div>");
		}else if(password.length<12){
			process.html("<div class='progress-bar progress-bar-info' role='progressbar'" +
					" aria-valuenow='60' aria-valuemin='0' aria-valuemax='100'" +
					" style='width: 66%;'><span class='sr-only'>中</span> </div></div></div></div>");
		}else{
			process.html("<div class='progress-bar progress-bar-success' role='progressbar'" +
					" aria-valuenow='60' aria-valuemin='0' aria-valuemax='100'" +
					" style='width: 100%;'><span class='sr-only'>强</span> </div></div></div></div>");
		}
	}else{
		pwdsp.html("密码不能为空");
	}
	
}

