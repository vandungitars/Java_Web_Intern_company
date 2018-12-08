$(document).ready(function(){
	$("#form-logup").hide();
	$("#login").click(function(){
		$("#form-logup").hide();
		$("#form-login").show();
	});
	$("#logup").click(function(){
		$("#form-login").hide();
		$("#form-logup").show();
	});
	//////////////////////////////////////////////////
	/*$("#btn-cancel-logup").click(function(){
		nowlink = window.location.href;
		link = nowlink.replace("/login","/test");
		window.location = link;
	});*/
	//////////////////////////////////////////////////
	$("#btn-register").click(function(){
		var formdata = $("#form-logup").serializeArray();
		json = {};
		$.each(formdata,function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/api/register",
			type: "POST",
			data:{
				dataJson: JSON.stringify(json),
			},
			success: function(value){
				/*if(value == 'true'){
					nowLink = window.location.href;
					link = nowLink.replace("admin","user");
					window.location = link;
				}*/
			}
		})
	});
	////////////////////////////////////////////////////
	var files= [];
	$("#image").change(function(event){
		files = event.target.files;
		forms = new FormData();
		forms.append("file",files[0]);
		$.ajax({
			url: "/api/uploadfile",
			type: "POST",
			data:forms,
			contentType:false,
			processData:false,
			enctype:"multipart/form-data",
			success: function(value){}
		})
	});
	
	$(".changeText").each(function(){
	    var $this = $(this);
	    var t = $this.text();
	    $this.html(t.replace('&lt','<').replace('&gt', '>'));
	});
	
	/*Product*/
	$(".btn-addProduct").click(function(){
		var optionText = $("#dropdownListPortfolio :selected").text();
		var formcontent = $("#form-product").serializeArray();
		json = {};
		$.each(formcontent, function(i,field){
			json[field.name] = field.value;
		});
		$.ajax({
			url: "/api/addProduct",
			type: "POST",
			data: {
				dataJson: JSON.stringify(json),
				optionText: optionText,
			},
			success: function(value){}
		})
	});

	$(".btn-updateProduct").click(function(){
		var idProduct = $(this).closest("tr").find(".idProduct").text();
		$(".btn-confirmUpdateProduct").click(function(){
			var optionText = $("#dropdownListPortfolio :selected").text();
			var formUpdateProduct = $("#form-updateProduct").serializeArray();
			json = {};
			$.each(formUpdateProduct,function(i,field){
				json[field.name] = field.value;
			});
			$.ajax({
				url: "/api/updateProduct",
				type: "POST",
				data:{
					dataJson: JSON.stringify(json),
					optionText: optionText,
					idProduct: idProduct,
				},
				success: function(value){}
			})
		});
	});
	
	$(".btn-deleteProduct").click(function(){
		var idProduct = $(this).closest("tr").find(".idProduct").text();
		$(this).closest("tr").remove();
		$.ajax({
			url: "/api/deleteProduct",
			type: "POST",
			data: {
				idProduct: idProduct,
			},
			success: function(value){}
		})
	});
	
	$(".disableUser").change(function() {
        var idUser = $(this).closest("tr").find(".idUser").text();
        var enabled = $(this).closest("tr").find(".enabled").text();
        $.ajax({
        	url: "/api/disableUser",
        	type: "POST",
        	data: {
        		idUser: idUser,
        		enabled: enabled,
        	},
        	sucess: function(value){}
        })
    });
})
