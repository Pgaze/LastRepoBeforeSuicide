
$('.criteresjsp_service').click(function(){
	var parent = $(this).parents();
	var id = $(this).attr('for');
	var input = parent.find('input#'+id);
	input.slideToggle('slow');
	if($(this).hasClass('clique')){
		$(this).removeClass('clique');
	}else{
		$(this).addClass('clique');
	}
	
})


