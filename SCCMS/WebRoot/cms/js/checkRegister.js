//��֤����
function checkEmail(input,span,url) {
	//input,span��Ϊjquery����
	var flag = false;
	if (!input.val() || !$.trim(input.val())) {
		span.html("Email����Ϊ��");
		return false;
	} else {
		var reg = /[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+/;
		if (!reg.test(input.val())) {
			span.html("��������ȷ��Email��ַ");
			return false;
		} else {
			$.ajax({
				async : false,
				url : url,
				type : "post",
				data : "email=" + input.val(),
				success : function(data) {
					if (!data) {
						span.html("��Email�Ѿ���ע��");
						flag = false;
					} else {
						span.html("<img src='/SCCMS/cms/images/label3.gif'>");
						flag = true;
					}
				}
			});
			return flag;
		}

	}
}
//��֤�û���
function checkUsername(input,span,url) {
	var flag=false;
	var name = input.val();
	var $nameInfo = span;
	// �����ǳƵ��ַ�����
	var length = name.length;
	if (length < 4 || length > 20) {
		$nameInfo.html("�û������Ϸ�");
		return false;
	} else {
		var reg = /^[0-9a-zA-Z_]+$/;
		if (reg.test(name)) {
			$.ajax({
				async : false,
				url : url,
				type : "post",
				data : "username=" + input.val(),
				success : function(data) {
					if (!data) {
						span.html("���û����Ѿ���ע��");
						flag = false;
					} else {
						span.html("<img src='/SCCMS/cms/images/label3.gif'>");
						flag = true;
					}
				}
			});
			return flag;
		} else {
			$nameInfo.html("�û������Ϸ�");
			return false;
		}
	}
}
//��֤����
function checkPassword(input,$pwdInfo) {
	var pwd = input.val();
	//�������
	//var reg = /[a-zA-z0-9]+/;
	if (pwd.length < 6 || pwd.length > 20
			//|| !reg.test(pwd)
			) {
		$pwdInfo.html("���벻�Ϸ�");
		return false;
	} else {
		$pwdInfo.html("<img src='/SCCMS/cms/images/label3.gif'>");
		return true;
	}
}
//�ظ�����
function checkRepeatPwd(input1,input2,$pwd1Info) {
	var pwd = input1.val();
	var pwd1 = input2.val();
	if (pwd1 && (!pwd1.indexOf(pwd) && pwd.length == pwd1.length)) {
		$pwd1Info.html("<img src='/SCCMS/cms/images/label3.gif'>");
		return true;
	} else {

		$pwd1Info.html("���β�һ��,����������");
		return false;
	}

}
//��֤��֤��
function checkImage(input,$numInfo,url) {
	var code = input.val();
	if (!code || !$.trim(code)) {
		$numInfo.html("��֤�벻��Ϊ��");
		return false;
	} else {
		var flag = false;
		$.ajax({
			async : false,
			url : url,	//"SCCMS/user/getCode?"
			type : "post",
			data : "code=" + code,
			success : function(data) {
				if (!data) {
					$numInfo.html("��֤����������");
					flag = false;
				} else {
					$numInfo.html("<img src='/SCCMS/cms/images/label3.gif'>");
					flag = true;
				}
			}
		});
		return flag;
	}
}
function checkPhone(input,span)
{
	var phone = input.val();
	var reg1 =/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;//����(0��ͷ)
	var reg2 =/^1[358]\d{9}$/;//�ֻ�(13,15,18)
	if(!reg1.test(phone)&&!reg2.test(phone))
	{
		span.html("���벻�Ϸ�������������");
		return false;
	}else
	{
		span.html("<img src='/SCCMS/cms/images/label3.gif'>");
		return true;
	}
}