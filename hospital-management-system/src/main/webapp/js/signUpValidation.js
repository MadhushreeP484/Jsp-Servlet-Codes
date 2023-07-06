function staffSignUpValidation(){
	var name=document.getElementById('name');
	var email=document.getElementById('email');
	var phone=document.getElementById('phone');
	var dob=document.getElementById('dob');
	var male=document.getElementById('male');
	var female=document.getElementById('female');
	var password=document.getElementById('password');
	var cPassword=document.getElementById('cPassword');
	
	if(name==null || name==''){
		alert('Invalid name');
	}
	if(email==null){
		alert('Invalid Email')
	}
	
	if(password!=cPassword){
		alert('Password missmatch');
	}
	
}

