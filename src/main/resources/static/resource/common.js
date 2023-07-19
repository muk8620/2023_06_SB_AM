$('select[data-value]').each(function(index, item){
	
	const items = $(item);
	
	const defaultValue = items.attr('data-value').trim();
	
	if (defaultValue.length > 0) {
		items.val(defaultValue);
	}
})

$('.modal-exam').click(function(){
//	$('.layer-bg').show();
//	$('.layer').show();

	$('.layer-bg, .layer').css('display', 'block');
})

$('#closeBtn').click(function(){
//	$('.layer-bg').hide();
//	$('.layer').hide();
	
	$('.layer-bg, .layer').css('display', 'none');
})

$('.close-x-btn').click(function(){
//	$('.layer-bg').hide();
//	$('.layer').hide();
	
	$('.layer-bg, .layer').css('display', 'none');
})

$('.layer-bg').click(function(){
//	$('.layer-bg').hide();
//	$('.layer').hide();
	
	$('.layer-bg, .layer').css('display', 'none');
})

$('.popUp-exam').click(function(){
	let popOption = "width=650px, height=550px, top=100px, left=100px, scrollbars=yes";
	let openUrl = '/usr/home/popUp';
	window.open(openUrl, 'pop', popOption);
})